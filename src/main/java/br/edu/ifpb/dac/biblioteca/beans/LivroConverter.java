package br.edu.ifpb.dac.biblioteca.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.dac.biblioteca.modelo.Autor;
import br.edu.ifpb.dac.biblioteca.modelo.Livro;
import br.edu.ifpb.dac.biblioteca.servico.ServicoLivros;

//Essa é a forma indicada. A injeção de dependência do EJB não acontece
//https://stackoverflow.com/questions/8459903/creating-master-detail-pages-for-entities-how-to-link-them-and-which-bean-scope
//http://fritzthecat-blog.blogspot.com/2019/09/jsf-master-detail-example-with-jpa.html
//@Named
//@RequestScoped

//Essas alterações funcionaram:
//https://stackoverflow.com/questions/45682309/changing-faces-config-xml-from-2-2-to-2-3-causes-javax-el-propertynotfoundexcept
//https://stackoverflow.com/questions/52511992/injection-in-a-converter-does-not-work-in-jsf-2-3

@FacesConverter(managed = true, forClass = Livro.class)
public class LivroConverter implements Converter<Livro>{
	
	@Inject
	private ServicoLivros servico;

	@Override
	public Livro getAsObject(FacesContext context, UIComponent component, String idLivro) {
		if (idLivro == null || idLivro.isEmpty()) {
			return null;
		}
		
		try {
			Integer id = Integer.parseInt(idLivro);
			System.out.println("LivroConverter - id: "+idLivro);
			Livro l = servico.buscaPelaId(id);
			return l;
		} catch (NumberFormatException e) {
			throw new ConverterException(new FacesMessage("Livro com ID inválido."),e);
		}

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Livro livro) {
		if (livro == null) {
			return "";
		}
		if (livro.getId() != null) {
			return livro.getId().toString();
		}else {
			throw new ConverterException(new FacesMessage("Livro com ID inválido."),null);
		}

	}

}
