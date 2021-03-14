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

import br.edu.ifpb.dac.biblioteca.modelo.Leitor;
@DeclareRoles({"ADMIN", "BIBLIOTECARIO"})
//@NamedQuery(name = "Leitor.findByCPF", query = "SELECT l FROM Leitor l WHERE l.cpf=:cpf")
@Stateless
public class ServicoLeitores {
	
	@PersistenceContext
	private EntityManager manager;
	
	@PostConstruct
	void aposCriacao() {
	    System.out.println("[INFO] ServicoLeitor foi criado.");
	}
	
	@RolesAllowed({"ADMIN", "BIBLIOTECARIO"})
	public void salva(Leitor leitor) {
	    System.out.println("[INFO] Salvando o Leitor " + leitor.getNome());
	    manager.persist(leitor);
	    System.out.println("[INFO] Salvou o Leitor " + leitor.getNome());
	}
	
	@PermitAll
	public List<Leitor> todosLeitores() {
		return manager.createQuery("select l from Leitor l",Leitor.class).getResultList();
	}

	@PermitAll
	public Leitor buscaPeloId(Integer leitorId) {
		Leitor leitor = manager.find(Leitor.class,leitorId);
		
		return leitor;
	}
	
	@RolesAllowed({"ADMIN", "BIBLIOTECARIO"})
	public Leitor buscaPeloCPF(String leitorCPF) {
//		Leitor leitor = manager.createQuery("SELECT l FROM Leitor l WHERE l.cpf=leitorCPF", Leitor.class).getSingleResult();
//				manager.createNamedQuery("Leitor.findByCPF", Leitor.class)
//				.setParameter("cpf", leitorCPF).getSingleResult();
//	Leitor leitor = manager.createQuery("SELECT l FROM Leitor l WHERE l.cpf=:cpf", Leitor.class).setParameter("cpf", leitorCPF).getSingleResult();			
		
//		String jpql = "select l from Leitor l where l.cpf = :cpf";
		
//		Query query = (Query) manager.createQuery(
//				"select from Leitor where cpf >= :ano");
//				((TypedQuery<Leitor>) query).setParameter("cpf", leitorCPF);
//				List veiculos = query.getResultList();
				
		return manager.createNamedQuery(
                "Leitor.findBycpf", Leitor.class)
                .setParameter("cpf", leitorCPF).getSingleResult();
	}
	
	@RolesAllowed({"ADMIN", "BIBLIOTECARIO"})
	public void altera(Leitor leitor) {
		System.out.println("[INFO] Alterando o Leitor " + leitor.getNome());
	    manager.merge(leitor);
	    System.out.println("[INFO] Leitor " + leitor.getNome()+" alterado com sucesso.");	
	}
	
	@RolesAllowed({"ADMIN", "BIBLIOTECARIO"})
	public void remove(Leitor leitor) {
		try {
			System.out.println("[INFO] Removendo o leitor: " + leitor.getNome());
			leitor = manager.find(Leitor.class, leitor.getId());
			manager.remove(leitor);
		}catch(PersistenceException e) {
			e.printStackTrace();
			throw new PersistenceException("Ocorreu um erro ao tentar remover o leitor");
		}
	}
}
