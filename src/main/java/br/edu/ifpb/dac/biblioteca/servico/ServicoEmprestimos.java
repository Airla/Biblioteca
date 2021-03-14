package br.edu.ifpb.dac.biblioteca.servico;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.edu.ifpb.dac.biblioteca.modelo.Emprestimo;
import br.edu.ifpb.dac.biblioteca.modelo.Livro;

@DeclareRoles({"ADMIN", "BIBLIOTECARIO"})
@Stateless
public class ServicoEmprestimos {

	@PersistenceContext
	private EntityManager manager;
	
	@PostConstruct
	void aposCriacao() {
	    System.out.println("[INFO] ServicoEmprestimos foi criado.");
	}
	
	@RolesAllowed({"ADMIN", "BIBLIOTECARIO"})
	public void salva(Emprestimo emprestimo) {
	    System.out.println("[INFO] Salvando o Emprestimo " + emprestimo.getId());
	    for (Livro li : emprestimo.getLivros()) {
			if(emprestimo.getLivros().size()==0) {
				System.out.println("vazio");
			}else {
				System.out.println("livros emprestimo" +li.getTitulo());
			}
		
		}
	    manager.persist(emprestimo);
	    System.out.println("[INFO] Salvou o Emprestimo " + emprestimo.getId());
	}
	
	@PermitAll
	public List<Emprestimo> todosEmprestimos() {
		return manager.createQuery("select e from Emprestimo e",Emprestimo.class).getResultList();
	}
	
	@RolesAllowed({"ADMIN", "BIBLIOTECARIO"})
	public List<Emprestimo> emprestimosAtivos() {
		return manager.createQuery("select e from Emprestimo e where e.devolvido=0",Emprestimo.class).getResultList();
	}
	

	@PermitAll
	public Emprestimo buscaPeloId(Integer emprestimoId) {
		Emprestimo emprestimo = manager.find(Emprestimo.class,emprestimoId);
		
		return emprestimo;
	}

	@RolesAllowed({"ADMIN", "BIBLIOTECARIO"})
	public void livrosDevolvidos(Emprestimo emprestimo) {
		System.out.println("[INFO] Alterando o Emprestimo " + emprestimo.getId());
	    manager.merge(emprestimo);
	    System.out.println("[INFO] Emprestimo " + emprestimo.getId()+" alterado com sucesso.");	
	}
}
