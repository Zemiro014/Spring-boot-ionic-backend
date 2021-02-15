package com.jeronimokulandissa.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jeronimokulandissa.cursomc.domain.Cliente;
import com.jeronimokulandissa.cursomc.dto.ClienteDTO;
import com.jeronimokulandissa.cursomc.dto.ClienteNewDTO;
import com.jeronimokulandissa.cursomc.services.ClienteService;


/* Esta class é um controlador Rest que irá responder por "/categorias" EndPoint */
@RestController
@RequestMapping(value="/clientes") 
public class ClienteResource 
{
	@Autowired
	private ClienteService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET) 
	public ResponseEntity<Cliente> find(@PathVariable Integer id)  // O "/clientes/{id}" é o EndPoint para consumir o que o método "find" do "ClienteResource" oferece 
	{
		Cliente obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/email", method=RequestMethod.GET) 
	public ResponseEntity<Cliente> find(@RequestParam(value="value") String email)  // O "/clientes/{id}" é o EndPoint para consumir o que o método "find" do "ClienteResource" oferece 
	{
		Cliente obj = service.findByEmail(email);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDto)
	{
		Cliente obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id)
	{
		Cliente obj = service.fromDTO(objDto);
		obj.setId(id); // Para o desencargo de consciência. Para garantir que o Id a ser atualizado é o Id que foi passado
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')") // Apenas um Adminastrador pode acessar esse EndPoint
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) 
	{
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')") // Apenas um Adminastrador pode acessar esse EndPoint
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll() 
	{
		List<Cliente> list = service.findAll(); // Buscaum List de Clientes no banco de dados e depois esse List é convertido para List ClienteDTO pelo código abaixo
		List<ClienteDTO> listDTO = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList()); // Convertendo um List  para outro List
		return ResponseEntity.ok().body(listDTO);
	}
	
	
	/*
	 * Realizando Busca de dados por paginação
	 * */
	@PreAuthorize("hasAnyRole('ADMIN')") // Apenas um Adminastrador pode acessar esse EndPoint
	@RequestMapping(value="/page",method = RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findPage(	
														@RequestParam(value="page", defaultValue="0") Integer page, 
														@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
														@RequestParam(value="orderBy", defaultValue="nome") String orderBy,  
														@RequestParam(value="direction", defaultValue="ASC") String direction) 
	{
		Page<Cliente> list = service.findPage(page, linesPerPage, orderBy,  direction); // Busca um Page de Clientes no banco de dados e depois esse Page é convertido para Page ClienteDTO pelo código abaixo
		Page<ClienteDTO> listDTO = list.map(obj -> new ClienteDTO(obj)); // Convertendo um Page  para outro Page
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(value="/picture", method = RequestMethod.POST)
	public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name="file") MultipartFile file)
	{
		URI uri = service.uploadProfilePicture(file);		
		return ResponseEntity.created(uri).build();
	}
}
