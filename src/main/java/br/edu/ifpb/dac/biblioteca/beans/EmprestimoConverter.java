package br.edu.ifpb.dac.biblioteca.beans;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.edu.ifpb.dac.biblioteca.modelo.Emprestimo;
import br.edu.ifpb.dac.biblioteca.servico.ServicoEmprestimos;

@FacesConverter(managed = true, forClass = Emprestimo.class)
public class EmprestimoConverter implements Converter<Emprestimo>{

	@Inject
	private ServicoEmprestimos servico;

	@Override
	public Emprestimo getAsObject(FacesContext context, UIComponent component, String idEmprestimo) {
		if (idEmprestimo == null || idEmprestimo.isEmpty()) {
			return null;
		}
		
		try {
			Integer id = Integer.parseInt(idEmprestimo);
			System.out.println("emprestimoConverter - id: "+idEmprestimo);
			return servico.buscaPeloId(id);
		} catch (NumberFormatException e) {
			throw new ConverterException(new FacesMessage("Emprestimo com ID inválido."),e);
		}

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Emprestimo emprestimo) {
		if (emprestimo == null) {
			return "";
		}
		if (emprestimo.getId() != null) {
			return emprestimo.getId().toString();
		}else {
			throw new ConverterException(new FacesMessage("Emprestimo com ID inválido."),null);
		}

	}
}
