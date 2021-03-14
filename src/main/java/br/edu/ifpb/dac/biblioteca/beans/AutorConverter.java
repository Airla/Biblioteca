package br.edu.ifpb.dac.biblioteca.beans;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.edu.ifpb.dac.biblioteca.modelo.Autor;
import br.edu.ifpb.dac.biblioteca.servico.ServicoAutores;

//Essa é a forma indicada. A injeção de dependência do EJB não acontece
//https://stackoverflow.com/questions/8459903/creating-master-detail-pages-for-entities-how-to-link-them-and-which-bean-scope
//http://fritzthecat-blog.blogspot.com/2019/09/jsf-master-detail-example-with-jpa.html
//@Named
//@RequestScoped

//Essas alterações funcionaram:
//https://stackoverflow.com/questions/45682309/changing-faces-config-xml-from-2-2-to-2-3-causes-javax-el-propertynotfoundexcept
//https://stackoverflow.com/questions/52511992/injection-in-a-converter-does-not-work-in-jsf-2-3

@FacesConverter(managed = true, forClass = Autor.class)
public class AutorConverter implements Converter<Autor>{
	
	@Inject
	private ServicoAutores servico;

	@Override
	public Autor getAsObject(FacesContext context, UIComponent component, String idAutor) {
		if (idAutor == null || idAutor.isEmpty()) {
			return null;
		}
		
		try {
			Integer id = Integer.parseInt(idAutor);
			System.out.println("AutorConverter - id: "+idAutor);
			return servico.buscaPelaId(id);
		} catch (NumberFormatException e) {
			throw new ConverterException(new FacesMessage("Autor com ID inválido."),e);
		}

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Autor autor) {
		if (autor == null) {
			return "";
		}
		if (autor.getId() != null) {
			return autor.getId().toString();
		}else {
			throw new ConverterException(new FacesMessage("Autor com ID inválido."),null);
		}

	}

}
