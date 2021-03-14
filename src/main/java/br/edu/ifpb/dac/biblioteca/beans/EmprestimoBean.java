package br.edu.ifpb.dac.biblioteca.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBAccessException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;


import br.edu.ifpb.dac.biblioteca.modelo.Emprestimo;
import br.edu.ifpb.dac.biblioteca.modelo.Leitor;
import br.edu.ifpb.dac.biblioteca.modelo.Livro;
import br.edu.ifpb.dac.biblioteca.servico.ServicoEmprestimos;
import br.edu.ifpb.dac.biblioteca.servico.ServicoLeitores;
import br.edu.ifpb.dac.biblioteca.servico.ServicoLivros;

@Named
@ViewScoped
public class EmprestimoBean implements Serializable{
//estava request
	private static final long serialVersionUID = 1L;
	private Emprestimo emprestimo = new Emprestimo();
	private List<Emprestimo> emprestimosAtivos;
	private String leitorCPF;
	private Integer livroId;
	private List<Leitor> leitores;
	private List<Livro> livrosEmprestimo;
	private List<Livro> livros;
	private Leitor leitor;
	private Integer leitorId;
	
	@EJB
	private ServicoEmprestimos servicoEmprestimo;
	@EJB
	private ServicoLeitores servicoLeitores;
	@EJB
	private ServicoLivros servicoLivros;
	
	@Inject
	private FacesContext facesContext;
	
	public EmprestimoBean() {
		
	}
	
	@PostConstruct
	public void init() {
		emprestimosAtivos = this.servicoEmprestimo.emprestimosAtivos();
		leitores = this.servicoLeitores.todosLeitores();
		livros = this.servicoLivros.todosLivros();
	}
	
	public String cadastra() {
		System.out.println("Cadastra - Emprestimo: - DATA DEVOLUÇÃO: "+emprestimo.getDevolucao());		
		emprestimo.setLivros(livrosEmprestimo);
		Leitor leitor = servicoLeitores.buscaPeloId(leitorId);
		emprestimo.setDevolvido(false);
		emprestimo.setLeitor(leitor);
		
		try {
			
			this.servicoEmprestimo.salva(emprestimo);
			
			} catch (EJBAccessException e) {
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Usuário sem permissão para salvar.",null));
				FacesContext.getCurrentInstance()
			    .getExternalContext()
			    .getFlash().setKeepMessages(true);
				
				return null;
			}
		
		
		emprestimosAtivos.add(emprestimo);
		this.emprestimo = new Emprestimo();
		this.livrosEmprestimo = new ArrayList<Livro>();
		
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Emprestimo salvo.",null));
		
		FacesContext.getCurrentInstance()
	    .getExternalContext()
	    .getFlash().setKeepMessages(true);

		return "/admin/emprestimos.xhtml?faces-redirect=true";
	}
	
	public String livrosDevolvidos(Emprestimo emp) {
		System.out.println("Devolve livros - Emprestimo: "+emprestimo.getId());
		this.emprestimo = emp;
		emprestimo.setDevolvido(true);
		try {
			this.servicoEmprestimo.livrosDevolvidos(emprestimo);
			emprestimosAtivos.remove(emprestimo);
		} catch (EJBAccessException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Usuário sem permissão para realizar "
					+ "devolução.",null));
			return null;
		}		
		
		this.emprestimo = new Emprestimo();
		
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Devolução concluída.",null));
		
		FacesContext.getCurrentInstance()
	    .getExternalContext()
	    .getFlash().setKeepMessages(true);	
		return "emprestimos?faces-redirect=true";
	}
	
	public List<Emprestimo> getEmprestimosAtivos() {
		return emprestimosAtivos;
	}

	public void setEmprestimosAtivos(List<Emprestimo> emprestimosAtivos) {
		this.emprestimosAtivos = emprestimosAtivos;
	}

	public Emprestimo getEmprestimo() {
		return emprestimo;
	}

	public void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
	}

	public String getLeitorCPF() {
		return leitorCPF;
	}

	public void setLeitorCPF(String leitorCPF) {
		this.leitorCPF = leitorCPF;
	}

	public Integer getLivroId() {
		return livroId;
	}

	public void setLivroId(Integer livroId) {
		this.livroId = livroId;
	}

	public List<Leitor> getLeitores() {
		return leitores;
	}

	public void setLeitores(List<Leitor> leitores) {
		this.leitores = leitores;
	}

	public List<Livro> getLivrosEmprestimo() {
		return livrosEmprestimo;
	}

	public void setLivrosEmprestimo(List<Livro> livrosEmprestimo) {
		this.livrosEmprestimo = livrosEmprestimo;
	}

	public Leitor getLeitor() {
		return leitor;
	}

	public void setLeitor(Leitor leitor) {
		this.leitor = leitor;
	}
	public ServicoEmprestimos getServicoEmprestimo() {
		return servicoEmprestimo;
	}

	public void setServicoEmprestimo(ServicoEmprestimos servicoEmprestimo) {
		this.servicoEmprestimo = servicoEmprestimo;
	}

	public ServicoLeitores getServicoLeitores() {
		return servicoLeitores;
	}

	public void setServicoLeitores(ServicoLeitores servicoLeitores) {
		this.servicoLeitores = servicoLeitores;
	}

	public ServicoLivros getServicoLivros() {
		return servicoLivros;
	}

	public void setServicoLivros(ServicoLivros servicoLivros) {
		this.servicoLivros = servicoLivros;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public FacesContext getFacesContext() {
		return facesContext;
	}

	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

	public Integer getLeitorId() {
		return leitorId;
	}

	public void setLeitorId(Integer leitorId) {
		this.leitorId = leitorId;
	}

}
