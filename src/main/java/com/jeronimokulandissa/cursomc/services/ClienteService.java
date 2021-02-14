package com.jeronimokulandissa.cursomc.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jeronimokulandissa.cursomc.domain.Cidade;
import com.jeronimokulandissa.cursomc.domain.Cliente;
import com.jeronimokulandissa.cursomc.domain.Endereco;
import com.jeronimokulandissa.cursomc.domain.enums.Perfil;
import com.jeronimokulandissa.cursomc.domain.enums.TipoCliente;
import com.jeronimokulandissa.cursomc.dto.ClienteDTO;
import com.jeronimokulandissa.cursomc.dto.ClienteNewDTO;
import com.jeronimokulandissa.cursomc.repositories.ClienteRepository;
import com.jeronimokulandissa.cursomc.repositories.EnderecoRepository;
import com.jeronimokulandissa.cursomc.security.UserSS;
import com.jeronimokulandissa.cursomc.services.exceptions.AuthorizationException;
import com.jeronimokulandissa.cursomc.services.exceptions.DataIntegrityException;
import com.jeronimokulandissa.cursomc.services.exceptions.ObjectNotFoundException;


@Service
public class ClienteService 
{
	
	/*Quando se declara uma dependência dentro de uma class e se coloca "Autowired"
	 * essa dependência será automáticamente instanciada pelo Spring usando (ID ou IC)
	 * */
	@Autowired 
	private ClienteRepository clienteRepository;
	
	@Autowired
	private BCryptPasswordEncoder passEndoder;
	
	@Autowired 
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private S3Service s3Service;
	
	@Autowired
	private ImageService imageService;
	
	@Value("${img.prefix.client.profile}")
	private String prefix;
	
	@Value("${img.profile.size}")
	private Integer size;
	
	
	
	
	public Cliente find(Integer id) 
	{
		UserSS user = UserService.authenticated(); // Pega  as informações do usuário autenticado (logado)
		if(user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId()))
		{
			throw new AuthorizationException("Acesso negado");
		}
		
		Optional<Cliente> obj = clienteRepository.findById(id);
		// Se o objecto retornar nullo vai mostrar uma exception devidamente tratada
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	@Transactional
	public Cliente insert(Cliente obj) 
	{
		obj.setId(null); // Garante que o obj a ser inserido tem o Id null. Se o obj tem Id não nullo, então o método "repo.save(obj)" vai considerar que é uma atualização e não inserção
		obj = clienteRepository.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	
	public Cliente update (Cliente obj) 
	{
		// Temos que instanciar um cliente a partir do banco de dados
		Cliente newObj = find(obj.getId()); 
		// find(obj.getId());  Verifica se o Id realmente existe
		updateData(newObj, obj);
		return clienteRepository.save(newObj);
	}
	
	public void delete(Integer id) 
	{
		find(id); // Verifica se o Id realmente existe. Caso não exista, retorna a excessão
		
		try 
		{
			clienteRepository.deleteById(id);
		}
		catch(DataIntegrityViolationException e) 
		{
			throw new DataIntegrityException("Não é possível excluir porque existem pedidos relacionadas");
		}
		
	}
	
	public List<Cliente> findAll()
	{
		return clienteRepository.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy,  String direction)
	{
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy );
		
		return clienteRepository.findAll(pageRequest);		
	}
	
	public Cliente fromDTO(ClienteDTO objDto) 
	{
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null, null);
	}
	public Cliente fromDTO(ClienteNewDTO objNewDto) 
	{
		Cliente cli = new Cliente(null, objNewDto.getNome(), objNewDto.getEmail(), objNewDto.getCpfOuCnpj(), TipoCliente.toEnum(objNewDto.getTipo()), passEndoder.encode(objNewDto.getSenha()));
		Cidade cid = new Cidade(objNewDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objNewDto.getLogradoura(), objNewDto.getNumero(), objNewDto.getComplemento(), objNewDto.getBairro(), objNewDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objNewDto.getTelefone());
		if(objNewDto.getTelefoneOpcao1()!=null) 
		{
			cli.getTelefones().add(objNewDto.getTelefoneOpcao1());
		}
		if(objNewDto.getTelefoneOpcao2()!=null) 
		{
			cli.getTelefones().add(objNewDto.getTelefoneOpcao2());
		}		
		return cli;
	}
	
	// Método auxiliar
	private void updateData(Cliente newObj, Cliente obj) 
	{
		/*
		 *  O "obj" recebe os novos valores dos campos que serão atualizados.  Esses novos valores serão passados para os campos do "newObj".
		 *  
		 *  No "newObj" existem todos os campos. Tanto os que serão atualizado como os que não serão atualiados
		 * */
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
	public URI uploadProfilePicture(MultipartFile multiparteFile) 
	{
		UserSS user = UserService.authenticated(); // Pega  as informações do usuário autenticado (logado)
		if(user==null) 
		{
			throw new AuthorizationException("Acesso negado");
		}
		BufferedImage jpgImage = imageService.getJpgImageFromFile(multiparteFile);
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);
		String fileName = prefix+user.getId()+".jpg";
		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");		
	}
}
