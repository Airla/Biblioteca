package br.edu.ifpb.dac.biblioteca.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBAccessException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import br.edu.ifpb.dac.biblioteca.modelo.Leitor;
import br.edu.ifpb.dac.biblioteca.servico.ServicoLeitores;

@Named
@ViewScoped
public class LeitorBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Leitor leitor = new Leitor();
	private List<Leitor> leitores;
	
	@EJB
	private ServicoLeitores dao;
	@Inject
	private FacesContext facesContext;
	
	public LeitorBean() {
		
	}
	
	@PostConstruct
	public void init() {
		leitores = this.dao.todosLeitores();
	}
	
	public String cadastra() {
		System.out.println("Cadastra - Leitor: "+leitor.getNome());
		
		try {
			this.dao.salva(leitor);
			
			} catch (EJBAccessException e) {
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Usuário sem permissão para salvar.",null));
				FacesContext.getCurrentInstance()
			    .getExternalContext()
			    .getFlash().setKeepMessages(true);
				
				return null;
			}
		
		leitores.add(leitor);
		this.leitor = new Leitor();
		
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Leitor cadastrado.",null));
		
		FacesContext.getCurrentInstance()
	    .getExternalContext()
	    .getFlash().setKeepMessages(true);

		return "/admin/leitores.xhtml?faces-redirect=true";
	}

	public String altera() {
		System.out.println("Altera - Leitor: "+leitor.getNome());
	
		try {
			this.dao.altera(leitor);			
		} catch (EJBAccessException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Usuário sem permissão para alterar.",null));
			return null;
		}
		
		this.leitor = new Leitor();
		
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Leitor alterado.",null));
		
		FacesContext.getCurrentInstance()
	    .getExternalContext()
	    .getFlash().setKeepMessages(true);
		
		return "leitores?faces-redirect=true";
	}
	
	public String remove(Leitor l) {
		System.out.println("Remove- Leitor: "+l.getNome());
		try {
			this.dao.remove(l);
			leitores.remove(l);
		} catch (EJBAccessException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Usuário sem permissão para remover.",null));
			return null;
		}
		
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Leitor removido.",null));
		
		FacesContext.getCurrentInstance()
	    .getExternalContext()
	    .getFlash().setKeepMessages(true);
		
		return "/admin/leitores.xhtml?faces-redirect=true";
	}	
	
	public Leitor getLeitor() {
		return leitor;
	}

	public List<Leitor> getLeitores() {
		return leitores;
	}
	
	public ServicoLeitores getDao() {
		return dao;
	}

	public void setDao(ServicoLeitores dao) {
		this.dao = dao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setLeitor(Leitor leitor) {
		this.leitor = leitor;
	}

	public void setLeitores(List<Leitor> leitores) {
		this.leitores = leitores;
	}

	public FacesContext getFacesContext() {
		return facesContext;
	}

	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}
}
