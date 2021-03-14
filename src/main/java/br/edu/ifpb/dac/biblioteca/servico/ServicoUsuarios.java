package br.edu.ifpb.dac.biblioteca.servico;


import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.mindrot.jbcrypt.BCrypt;

import br.edu.ifpb.dac.biblioteca.modelo.Autor;
import br.edu.ifpb.dac.biblioteca.modelo.Usuario;

@Stateless
public class ServicoUsuarios {
	
	@PersistenceContext
	private EntityManager manager;
	
	@PostConstruct
	void aposCriacao() {
	    System.out.println("[INFO] UsuarioDao foi criado.");
	}
	
	public void salva(Usuario usuario) {
		
		usuario.setSenha(transformaSenhaEmHash(usuario.getSenha()));
		
		manager.persist(usuario);
		System.out.println("[INFO] Salvou o Usuario " + usuario.getNome());
	}
	
	private String transformaSenhaEmHash(String senhaBruta) {
		System.out.println("Gerando Hash usando Bcrypt");
		String senhaHashed = null;
		String salto = null;
		
		salto = BCrypt.gensalt();//Gerar um salto aleatório
	    senhaHashed = BCrypt.hashpw(senhaBruta, salto);//Gerar o hash da senha usando o salto aleatório
	    
	    System.out.println("Bcrypt - senhaHash: "+senhaHashed);
		
		return senhaHashed;
	}
	
	public Usuario buscaPeloEmailESenha(String email, String senha) {
	    System.out.println("[INFO] Consultando o usuario pelo e-mail: "+email);
	    
	    Usuario usuarioRecuperado = null; 
	    try {
	    	
	    
	    usuarioRecuperado = manager.createQuery("select u from Usuario u where u.email = :email",Usuario.class)
		.setParameter("email", email)
		.getSingleResult();
	    
	    }catch (NoResultException nre){
	    	return null;
	    }
	    
	    if (usuarioRecuperado!=null) {
			if(verificaSenhaHash(senha, usuarioRecuperado.getSenha())) {
				return usuarioRecuperado;
			}
			
		}
	    
		return null; 
	}

	private boolean verificaSenhaHash(String senha, String senhaRecuperada) {

		if(null == senhaRecuperada || !senhaRecuperada.startsWith("$2a$")) {
			throw new RuntimeException("A senha com hash é inválida");
		}
		return BCrypt.checkpw(senha, senhaRecuperada);
	}

    
	public Usuario buscaPelaId(String email) {
	    System.out.println("[INFO] Consultando Usuario pelo email: "+email);

		Usuario usuario = manager.find(Usuario.class,email);
		
		return usuario;
	}

	public List<Usuario> todosUsuarios() {
	    System.out.println("[INFO] Consultando todos os autores ");
		return manager.createQuery("select u from Usuario u",Usuario.class).getResultList();
	}
	
}
