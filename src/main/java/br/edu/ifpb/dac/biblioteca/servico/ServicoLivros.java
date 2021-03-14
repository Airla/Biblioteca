package br.edu.ifpb.dac.biblioteca.servico;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import br.edu.ifpb.dac.biblioteca.modelo.Livro;

/**
 * Session Bean implementation class ServicoLivros
 */
@DeclareRoles({"ADMIN", "BIBLIOTECARIO"})
@Stateless
public class ServicoLivros {

	@PersistenceContext
	private EntityManager manager;
	
	@PostConstruct
	void aposCriacao() {
	    System.out.println("[INFO] ServicoLivros foi criado.");
	}
	
	@RolesAllowed({"ADMIN", "BIBLIOTECARIO"})
	public void salva(Livro livro) {
	    System.out.println("[INFO] Salvando o Livro " + livro.getTitulo());
	    manager.persist(livro);
	    System.out.println("[INFO] Salvou o Livro " + livro.getTitulo());
	}

	@PermitAll
	public List<Livro> todosLivros() {
		return manager.createQuery("select l from Livro l",Livro.class).getResultList();
	}

	@PermitAll
	public Livro buscaPelaId(Integer livroId) {
		Livro livro = manager.find(Livro.class,livroId);
		
		return livro;
	}

	@RolesAllowed({"ADMIN", "BIBLIOTECARIO"})
	public void altera(Livro livro) {
		System.out.println("[INFO] Alterando o Livro " + livro.getTitulo());
	    manager.merge(livro);
	    System.out.println("[INFO] Livro " + livro.getTitulo()+" alterado com sucesso.");	
	}

	@RolesAllowed({"ADMIN", "BIBLIOTECARIO"})
	public void remove(Livro livro) {
		try {
			System.out.println("[INFO] Removendo o livro: " + livro.getTitulo());
			livro = manager.find(Livro.class, livro.getId());
			manager.remove(livro);
		}catch(PersistenceException e) {
			e.printStackTrace();
			throw new PersistenceException("Ocorreu um erro ao tentar remover o livro");
		}
	}

}
