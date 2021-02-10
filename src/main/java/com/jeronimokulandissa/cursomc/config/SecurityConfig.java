package com.jeronimokulandissa.cursomc.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.jeronimokulandissa.cursomc.security.JWTAuthenticationFilter;
import com.jeronimokulandissa.cursomc.security.JWTAuthorizationFilter;
import com.jeronimokulandissa.cursomc.security.JWTUtil;


// Essa class permite controlar os meus Endpoints
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // Está sendo usado para permitir autorizar apenas alguns perfis especificos
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	private Environment env;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	
	/*Estamos injectando uma interface "UserDetailsService". O Spring-Security é inteligente o suficiente. Ele vai procurar neste sistema 
	 * a class que implementa o "UserDetailsService" que é "UserDetailsServiceImplement" e injecta-lo automaticamente.
	 * 
	 * */ 
	@Autowired
	private UserDetailsService userDetailsService; 
	
	/*Visto que apos adicioar as dependência do Spring-Security, todos os EndPoints ficarão bloqueados.
	 * Por isso motivo iremos configurar as permissões
	 * */ 
	private static final String[] PUBLIC_MATCHERS = {
			"/h2-console/**" // Toda URL com que contem em seu corpo: "/h2-console/maisAlgumaCoisa" está liberado
	};
	
	private static final String[] PUBLIC_MATCHERS_GET = {
			// Todas URLs que contem em seu corpo as informações abaixo, estão permitidos para realizar a pesquisa de dados ao sistema 
			"/productos/**", 
			"/categorias/**"
	};
	
	private static final String[] PUBLIC_MATCHERS_POST = {
			"/clientes/**",
			"/auth/forgot/**"
	};
	
	// Configuração de permissões de acesso dos Endpoints
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		// Para Acessar o h2-console
		if(Arrays.asList(env.getActiveProfiles()).contains("test")) // Pegando os profiles activos. Se nestes profiles estiver no "test", isso quer dizer que vou usar o h2-console 
		{
			http.headers().frameOptions().disable();
		}
		
		http.cors().and().csrf().disable();
		http.authorizeRequests()
			.antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
			.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
			.antMatchers(PUBLIC_MATCHERS).permitAll()
			.anyRequest().authenticated();
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //Usado para assegurar que o nosso BackEnd não crie sessão de usuário
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() 
	{
	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
	    return source;
	}
	
	 // Meu sistema terá disponível em forma de "Bean" um componente "BCryptPasswordEncoder" que poderei injectar em qualquer class do meu sistema
	 @Bean
	 public BCryptPasswordEncoder bCryptPasswordEncoder() 
	 {
		  return new BCryptPasswordEncoder();
	 }
	 
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception 
	{
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}	  

}
