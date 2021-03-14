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

import br.edu.ifpb.dac.biblioteca.modelo.Autor;
import br.edu.ifpb.dac.biblioteca.modelo.Livro;

@DeclareRoles({"ADMIN", "BIBLIOTECARIO"})
@Stateless
public class ServicoAutores {
	
	@PersistenceContext
	private EntityManager manager;
	
	@PostConstruct
	void aposCriacao() {
	    System.out.println("[INFO] AutorDao foi criado.");
	}
	
	@RolesAllowed({"ADMIN", "BIBLIOTECARIO"})
	public void salva(Autor autor) {
	    System.out.println("[INFO] Salvando o Autor " + autor.getNome());
	    manager.persist(autor);
	    System.out.println("[INFO] Salvou o Autor " + autor.getNome());
	}
	
	@PermitAll
	public List<Autor> todosAutores() {
	    System.out.println("[INFO] Consultando todos os autores ");
		return manager.createQuery("select a from Autor a",Autor.class).getResultList();
	}

	@PermitAll
	public Autor buscaPelaId(Integer autorId) {
	    System.out.println("[INFO] Consultando Autor pelo id ");

		Autor autor = manager.find(Autor.class,autorId);
		
		return autor;
	}
	
	@RolesAllowed({"ADMIN", "BIBLIOTECARIO"})
	public void altera(Autor autor) {
		System.out.println("[INFO] Alterando o Autor " + autor.getNome());
	    manager.merge(autor);
	    System.out.println("[INFO] Autor " + autor.getNome()+" alterado com sucesso.");	
	}
	
	@RolesAllowed({"ADMIN", "BIBLIOTECARIO"})
	public void remove(Autor autor) {
		try {
			System.out.println("[INFO] Removendo o autor: " + autor.getNome());
			autor = manager.find(Autor.class, autor.getId());
			manager.remove(autor);
		}catch(PersistenceException e) {
			e.printStackTrace();
			throw new PersistenceException("Ocorreu um erro ao tentar remover o autor");
		}
	}
}
