package br.edu.ifpb.dac.biblioteca.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBAccessException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.dac.biblioteca.modelo.Autor;
import br.edu.ifpb.dac.biblioteca.servico.ServicoAutores;

@Named
@ViewScoped
public class AutorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Autor autor = new Autor();
	private List<Autor> autoresFiltrados;
	private List<Autor> autores;
	@EJB
	private ServicoAutores dao;
	@Inject
	private FacesContext facesContext;

	public Autor getAutor() {
		return autor;
	}

	public AutorBean() {

	}

	@PostConstruct
	public void init() {		
		autores = this.dao.todosAutores();
		autoresFiltrados = new ArrayList<Autor>();
	}

	public String cadastra() {
		
		System.out.println("Cadastra - autor: "+autor.getNome()+" - E-mail: "+autor.getEmail());
		
		try {
		this.dao.salva(autor);
		
		} catch (EJBAccessException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Usuário sem permissão para alterar.",null));
			FacesContext.getCurrentInstance()
		    .getExternalContext()
		    .getFlash().setKeepMessages(true);
			
			return null;
		}
		
		autores.add(autor);
		this.autor = new Autor();
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Autor cadastrado",null));
		
		FacesContext.getCurrentInstance()
	    .getExternalContext()
	    .getFlash().setKeepMessages(true);

		return "/admin/autores.xhtml?faces-redirect=true";
	}

	public String altera() {
		System.out.println("Altera - Autor: " + autor.getNome());	
		
		try {
			this.dao.altera(autor);			
		} catch (EJBAccessException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Usuário sem permissão para alterar.",null));
			return null;
		}
		
		this.autor = new Autor();

		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Autor alterado.",null));
		
		FacesContext.getCurrentInstance()
	    .getExternalContext()
	    .getFlash().setKeepMessages(true);
		
		return "autores?faces-redirect=true";
	}
	
	public String remove(Autor a) {
		System.out.println("Remove- Autor: "+a.getNome());
		try {
			this.dao.remove(a);
			autores.remove(a);
		} catch (EJBAccessException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Usuário sem permissão para remover.",null));
			return null;
		}
		
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Autor removido.",null));
		
		FacesContext.getCurrentInstance()
	    .getExternalContext()
	    .getFlash().setKeepMessages(true);
		
		return "/admin/autores.xhtml?faces-redirect=true";
	}
	
	public boolean filterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
 
        Autor autor = (Autor) value;
        return autor.getNome().toLowerCase().contains(filterText);
    }
	
	public List<Autor> getAutores() {
		return autores;
	}

	public ServicoAutores getDao() {
		return dao;
	}

	public void setDao(ServicoAutores dao) {
		this.dao = dao;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	public FacesContext getFacesContext() {
		return facesContext;
	}

	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Autor> getAutoresFiltrados() {
		return autoresFiltrados;
	}

	public void setAutoresFiltrados(List<Autor> autoresFiltrados) {
		this.autoresFiltrados = autoresFiltrados;
	}
}
