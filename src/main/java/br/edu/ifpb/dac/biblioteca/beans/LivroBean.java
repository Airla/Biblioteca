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
import br.edu.ifpb.dac.biblioteca.modelo.GeneroLivro;
import br.edu.ifpb.dac.biblioteca.modelo.Livro;
import br.edu.ifpb.dac.biblioteca.modelo.StatusLivro;
import br.edu.ifpb.dac.biblioteca.servico.ServicoAutores;
import br.edu.ifpb.dac.biblioteca.servico.ServicoLivros;

@Named
@ViewScoped
public class LivroBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Livro livro = new Livro();
	private List<Livro> livros;
	private Integer autorId;
	private List<Autor> autores;
	private List<Autor> autoresLivro;
	private Livro livroSelecionado;
	
	@EJB
	private ServicoLivros servicoLivro;
	@EJB
	private ServicoAutores servicoAutor;
	@Inject
	private FacesContext facesContext;
	
	public LivroBean() {
		
	}
	
	@PostConstruct
	public void init() {
		livros = this.servicoLivro.todosLivros();
		autores = this.servicoAutor.todosAutores();
		autoresLivro = new ArrayList<Autor>();
		System.out.println("[INFO] LivroBean criado ");	    
	}

	public StatusLivro[] getStatus() {
		return StatusLivro.values();		
	}
	
	public GeneroLivro[] getGeneros() {
		return GeneroLivro.values();		
	}
	
	public void carregaAutoresLivro() {
		if (livro.getAutores()!=null && !livro.getAutores().isEmpty()) {
			autoresLivro = livro.getAutores();
			System.out.println("CarregaAutoresLivro: "+autoresLivro.size() +" - autores carregados.");
		}else autoresLivro = new ArrayList<Autor>();
	}
	
	public Livro getLivro() {
		return livro;
	}
	
		
	public String cadastra() {
		
		System.out.println("Cadastra - Livro: "+livro.getTitulo()+" - DATA: "+livro.getDataLancamento());
		livro.setAutores(autoresLivro);
		
		try {
		this.servicoLivro.salva(livro);
		
		} catch (EJBAccessException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Usuário sem permissão para alterar.",null));
			FacesContext.getCurrentInstance()
		    .getExternalContext()
		    .getFlash().setKeepMessages(true);
			
			return null;
		}
		
		livros = servicoLivro.todosLivros();
		this.livro = new Livro();
		this.autoresLivro = new ArrayList<Autor>();
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Livro cadastrado.",null));
		
		FacesContext.getCurrentInstance()
	    .getExternalContext()
	    .getFlash().setKeepMessages(true);

		return "/admin/livros.xhtml?faces-redirect=true";
	}
	
	public String altera() {
		System.out.println("Altera - Livro: "+livro.getTitulo());
		livro.setAutores(autoresLivro);
		try {
			this.servicoLivro.altera(livro);			
		} catch (EJBAccessException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Usuário sem permissão para alterar.",null));
			return null;
		}

		this.livro = new Livro();
		this.autoresLivro = new ArrayList<Autor>();
		
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Livro alterado.",null));
		
		FacesContext.getCurrentInstance()
	    .getExternalContext()
	    .getFlash().setKeepMessages(true);		
		
		return "livros?faces-redirect=true";
	}
	
	public String remove(Livro l) {
		System.out.println("Remove- Livro: "+l.getTitulo());
		try {
			this.servicoLivro.remove(l);
			livros.remove(l);
		} catch (EJBAccessException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Usuário sem permissão para remover.",null));
			return null;
		}
		
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Livro removido.",null));
		
		FacesContext.getCurrentInstance()
	    .getExternalContext()
	    .getFlash().setKeepMessages(true);
		
		return "/admin/livros.xhtml?faces-redirect=true";
	}
	
	public void adicionaAutorLivro() {
		Autor autor = servicoAutor.buscaPelaId(autorId);
		System.out.println("AdicionaAutorLivro - Livro: "+livro.getTitulo()+" - Autor: "+autor.getNome());
		autoresLivro.add(autor);
	}
	
	public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
 
        Livro livroFiltrado = (Livro) value;
        return livroFiltrado.getTitulo().toLowerCase().contains(filterText);
    }
	
	public List<Livro> getLivros() {
		return livros;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	public List<Autor> getAutoresLivro() {
		return autoresLivro;
	}

	public void setAutoresLivro(List<Autor> autoresLivro) {
		this.autoresLivro = autoresLivro;
	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public ServicoLivros getServicoLivro() {
		return servicoLivro;
	}

	public void setServicoLivro(ServicoLivros servicoLivro) {
		this.servicoLivro = servicoLivro;
	}

	public ServicoAutores getServicoAutor() {
		return servicoAutor;
	}

	public void setServicoAutor(ServicoAutores servicoAutor) {
		this.servicoAutor = servicoAutor;
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

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

	public Livro getLivroSelecionado() {
		return livroSelecionado;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public void setLivroSelecionado(Livro livroSelecionado) {
		this.livroSelecionado = livroSelecionado;
	}
}
