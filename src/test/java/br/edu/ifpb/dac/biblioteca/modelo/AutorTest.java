package br.edu.ifpb.dac.biblioteca.modelo;


import javax.faces.validator.ValidatorException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AutorTest {

	private Autor autor;
	
	@BeforeEach
	void setUp() throws Exception {
		autor = new Autor();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testSetNome() {
		/**
		 * Retorna false pois não está de acordo com os parametros mínimos de aceitação para o 
		 * título de um livro.
		 * O título não deve ser nulo ou conter apenas valores em brancos
		 * O título não pode conter menos de 3 caractéres ou mais de 150
		 */
		
		RuntimeException exception1 = Assertions.assertThrows(RuntimeException.class, () -> autor.setNome(null));
        Assertions.assertEquals("O nome está nulo", exception1.getMessage());
        
        RuntimeException exception2 = Assertions.assertThrows(RuntimeException.class, () -> autor.setNome(""));
        Assertions.assertEquals("O nome está vazio", exception2.getMessage());
        
        RuntimeException exception3 = Assertions.assertThrows(RuntimeException.class, () -> autor.setNome("  "));
        Assertions.assertEquals("O nome não possui algum caractere válido", exception3.getMessage());
        
        RuntimeException exception4 = Assertions.assertThrows(RuntimeException.class, () -> autor.setNome("te"));
        Assertions.assertEquals("O nome deve conter de 3 à 150 caractéres", exception4.getMessage());
        
        RuntimeException exception5 = Assertions.assertThrows(RuntimeException.class, () -> autor.setNome("Teste de unidade é "
        		+ "toda a aplicação de teste nas assinaturas de entrada e saída de um sistema. Consiste em validar dados "
        		+ "válidos e inválidos via I/O..."));
        Assertions.assertEquals("O nome deve conter de 3 à 150 caractéres", exception5.getMessage());
        		
		/**
		 * Retorna true pois está de acordo com os parâmetro mínimos de aceitação para o 
		 * título de um livro
		 */
        this.autor.setNome("Sist");        
        this.autor.setNome("Teste de unidade é toda a aplicação de teste nas assinaturas de entrada "
				+ "e saída de um sistema. Consiste em validar dados válidos e inválidos via I/O (");
        
        Assertions.assertTrue(true);        		
	}
	
	@Test
	void testSetEmail() {
		/**
		 * Retorna false pois não está de acordo com os parametros de aceitação para o 
		 * e-mail.
		 * O e-mail não deve ser nulo ou conter apenas valores em brancos
		 * O e-mail não pode conter menos de 4 caractéres ou mais de 150
		 * O e-mail deve conter o caracter @
		 */
		
		RuntimeException exception1 = Assertions.assertThrows(RuntimeException.class, () -> autor.setEmail(null));
        Assertions.assertEquals("O email está nulo", exception1.getMessage());
        
        RuntimeException exception2 = Assertions.assertThrows(RuntimeException.class, () -> autor.setEmail(""));
        Assertions.assertEquals("O email está vazio", exception2.getMessage());
        
        RuntimeException exception3 = Assertions.assertThrows(RuntimeException.class, () -> autor.setEmail("  "));
        Assertions.assertEquals("O email não possui algum caractere válido", exception3.getMessage());
        
        RuntimeException exception4 = Assertions.assertThrows(RuntimeException.class, () -> autor.setEmail("ema"));
        Assertions.assertEquals("O email deve conter de 4 à 150 caractéres", exception4.getMessage());
        
        RuntimeException exception5 = Assertions.assertThrows(RuntimeException.class, () -> autor.setEmail("testedeunidadeéto"
        		+ "daaaplicaçãodetestenasassinaturasdeentradaesaídadeumsistemaconsisteemvalidardadosválidoseinválidosviaentra"
        		+ "dafsaídsendoaplicadopordese@"));
        Assertions.assertEquals("O email deve conter de 4 à 150 caractéres", exception5.getMessage());
        
        ValidatorException exception6 = Assertions.assertThrows(ValidatorException.class, () -> autor.setEmail("email"));
        Assertions.assertEquals("Formato inválido de e-mail (Deve conter @)", exception6.getMessage());
		
        RuntimeException exception7 = Assertions.assertThrows(RuntimeException.class, () -> autor.setEmail("email@ gmail"));
        Assertions.assertEquals("Formato inválido de e-mail (Deve conter @)", exception7.getMessage());
        
        /**
		 * Retorna true pois está de acordo com os parâmetro mínimos de aceitação para o 
		 * email
		 */
        this.autor.setNome("aut@");        
        this.autor.setNome("testedeunidadeétodaaaplicaçãodetestenasassinaturasdeentradaesa"
        		+ "ídadeumsistemaconsisteemvalidardadosválidoseinválidosviaentradafsaíd"
        		+ "sendoaplicadopordes@");
        
        Assertions.assertTrue(true);        		
	}

}
