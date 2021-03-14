package br.edu.ifpb.dac.biblioteca.modelo;

public enum GeneroLivro {

	TERROR("Terror"),
	ROMANCE("Romance"),
	FICCAO("Ficção"),
	AUTOAJUDA("Autoajuda"),
	HISTORIA("História"),
	INFANTIL("Infantil");
	
	private String descricao;
	
	private GeneroLivro(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
