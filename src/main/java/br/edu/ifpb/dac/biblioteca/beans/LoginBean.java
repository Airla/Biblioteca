package br.edu.ifpb.dac.biblioteca.beans;

import java.util.Optional;
import java.util.Set;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.dac.biblioteca.modelo.Usuario;
import br.edu.ifpb.dac.biblioteca.servico.ServicoUsuarios;
import br.edu.ifpb.dac.biblioteca.servico.identitystore.UsuarioPrincipal;

@Named
@RequestScoped
public class LoginBean {

	private Usuario usuario;
		
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private ExternalContext externalContext;
	
	@Inject
	private SecurityContext securityContext;
	
	public LoginBean() {
		usuario = new Usuario();
	}
	
	public void login() {
		
		System.out.println("Login: "+usuario.getEmail()+" e senha: "+usuario.getSenha());
		AuthenticationStatus status = executaAutenticacao();
		System.out.println("Status: "+status);
		switch (status) {
		case SEND_CONTINUE:
			System.out.println("Principal: "+securityContext.getCallerPrincipal());
			if (securityContext.getCallerPrincipal()!=null) {
				facesContext.responseComplete();
			}else {
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro no login",null));
				FacesContext.getCurrentInstance()
			    .getExternalContext()
			    .getFlash().setKeepMessages(true);
			}
			break;
		case SEND_FAILURE:
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro no login",null));
			FacesContext.getCurrentInstance()
		    .getExternalContext()
		    .getFlash().setKeepMessages(true);
			break;
		case SUCCESS:
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Login com sucesso",null));
			break;
		case NOT_DONE:
				//NADA			
			break;
		}
		
	}
	
	public String logout() {
		HttpServletRequest request = (HttpServletRequest)externalContext.getRequest();
		try {
			request.logout();
		} catch (ServletException e) {
			e.printStackTrace();
		}
		request.getSession().invalidate();
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Logout realizado com sucesso",null));
		
		return "login.xhtml?faces-redirect=true";
	}
	
	private AuthenticationStatus executaAutenticacao() {
		return securityContext.authenticate((HttpServletRequest)externalContext.getRequest(), 
				(HttpServletResponse) externalContext.getResponse(), AuthenticationParameters
				.withParams().credential(new UsernamePasswordCredential(usuario.getEmail(), usuario.getSenha())));
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public Usuario getUsuarioLogado() {
		Usuario nomeUsuarioLogado = null;
		Optional<UsuarioPrincipal> usuarioLogado = securityContext.getPrincipalsByType(UsuarioPrincipal.class).stream().findFirst();
		if (usuarioLogado.isPresent()) {
			nomeUsuarioLogado = usuarioLogado.get().getUsuario();
		}
		
		return nomeUsuarioLogado;
	}
}
