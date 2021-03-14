package br.edu.ifpb.dac.biblioteca.beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBAccessException;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.dac.biblioteca.modelo.Autor;
import br.edu.ifpb.dac.biblioteca.modelo.Usuario;
import br.edu.ifpb.dac.biblioteca.servico.ServicoAutores;
import br.edu.ifpb.dac.biblioteca.servico.ServicoUsuarios;

import java.io.Serializable;

@Named
@ViewScoped
public class UsuarioBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario usuario = new Usuario();
	private List<Usuario> usuarios;
	private String papel;
	private List<String> papeisUsuario;
	
	@EJB
	private ServicoUsuarios dao;
	@Inject
	private FacesContext facesContext;
	
	public UsuarioBean() {
		
	}
	
	@PostConstruct
	public void init() {
		usuarios = this.dao.todosUsuarios();
		papeisUsuario = new ArrayList<String>();
	}
	
	public String cadastra() {
		System.out.println("Cadastra - Usuario: "+usuario.getNome());
		usuario.setPapeis(papeisUsuario);
		
		try {
			this.dao.salva(usuario);
			
			} catch (EJBAccessException e) {
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Usuário sem permissão para cadastrar.",null));
				FacesContext.getCurrentInstance()
			    .getExternalContext()
			    .getFlash().setKeepMessages(true);
				
				return null;
			}
		
		usuarios.add(usuario);
		this.usuario = new Usuario();		
		papeisUsuario = new ArrayList<String>();
		
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Usuário cadastrado",null));
		
		FacesContext.getCurrentInstance()
	    .getExternalContext()
	    .getFlash().setKeepMessages(true);
		
		return "/login.xhtml?faces-redirect=true";
	}
	
	public void adicionaPapel() {
		papeisUsuario.add(papel);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String getPapel() {
		return papel;
	}

	public void setPapel(String papel) {
		this.papel = papel;
	}

	public List<String> getPapeisUsuario() {
		return papeisUsuario;
	}

	public void setPapeisUsuario(List<String> papeisUsuario) {
		this.papeisUsuario = papeisUsuario;
	}
	
}
