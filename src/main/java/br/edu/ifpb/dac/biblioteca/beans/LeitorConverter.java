package br.edu.ifpb.dac.biblioteca.beans;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.edu.ifpb.dac.biblioteca.modelo.Leitor;
import br.edu.ifpb.dac.biblioteca.servico.ServicoLeitores;

@FacesConverter(managed = true, forClass = Leitor.class)
public class LeitorConverter implements Converter<Leitor>{

	@Inject
	private ServicoLeitores servico;

	@Override
	public Leitor getAsObject(FacesContext context, UIComponent component, String idLeitor) {
		if (idLeitor == null || idLeitor.isEmpty()) {
			return null;
		}
		
		try {
			Integer id = Integer.parseInt(idLeitor);
			System.out.println("LeitorConverter - id: "+idLeitor);
			return servico.buscaPeloId(id);
		} catch (NumberFormatException e) {
			throw new ConverterException(new FacesMessage("Leitor com ID inválido."),e);
		}

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Leitor leitor) {
		if (leitor == null) {
			return "";
		}
		if (leitor.getId() != null) {
			return leitor.getId().toString();
		}else {
			throw new ConverterException(new FacesMessage("Leitor com ID inválido."),null);
		}

	}	
}
