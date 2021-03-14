package br.edu.ifpb.dac.biblioteca.modelo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Usuario {

	@Id
	@Column(length = 150)
	private String email;
	private String nome;
	@Column(length = 255)
	private String senha;
	@Column(length = 700)
	private String saltoHash;
	
	private String matricula;
	
	private String cpf;
	
	@ElementCollection(targetClass = String.class,fetch = FetchType.EAGER)
	private List<String> papeis;
	
	public Usuario() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}


	public List<String> getPapeis() {
		return papeis;
	}

	public void setPapeis(List<String> papeis) {
		this.papeis = papeis;
	}

	public Set<String> getPapeisString(){
		return new HashSet<String>(papeis);
	}

	public String getSaltoHash() {
		return saltoHash;
	}

	public void setSaltoHash(String saltoHash) {
		this.saltoHash = saltoHash;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
