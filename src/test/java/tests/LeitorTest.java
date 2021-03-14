package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LeitorTest {

	private WebDriver driver;
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\airla\\Desktop\\bibliotecaJSFSeguro_Inicial\\bibliotecaJSF\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void leitorTest() throws InterruptedException {
		
		/**
		 * Realiza o login na aplicação
		 */
		driver.get("http://localhost:8080/bibliotecaJSF/login.xhtml");
		Thread.sleep(3000);
		
		WebElement inputTextEmail = driver.findElement(By.id("form:login"));
		inputTextEmail.sendKeys("airla@gmail.com");
		Thread.sleep(3000);
		
		WebElement inputTextSenha = driver.findElement(By.id("form:senha"));
		inputTextSenha.sendKeys("Airla123!");
		Thread.sleep(3000);
		
		WebElement botaoAdicionrPapel = driver.findElement(By.id("form:botaoLogin"));
		botaoAdicionrPapel.click();
		Thread.sleep(3000);
		
		/**
		 * Seleciona a opção leitores no menu
		 */
		WebElement submenuFuncoes = driver.findElement(By.id("j_idt9:submenuFuncoes"));
		submenuFuncoes.click();
		Thread.sleep(3000);
		
		WebElement menuItemLeitores = driver.findElement(By.id("j_idt9:menuItemLeitores"));
		menuItemLeitores.click();
		Thread.sleep(10000);
		
		/**
		 * Tenta cadastrar o leitor sem as informacoes
		 */
		WebElement botaoSalvar1 = driver.findElement(By.id("form:j_idt28"));
		botaoSalvar1.click();
		Thread.sleep(3000);
		
		/**
		 * Testa cadastrar leitor
		 */
		WebElement inputTextNomeLeitor = driver.findElement(By.id("form:nome"));
		inputTextNomeLeitor.sendKeys("Maria");
		Thread.sleep(3000);
		
		WebElement inputTextCpfLeitor = driver.findElement(By.id("form:cpf"));
		inputTextCpfLeitor.sendKeys("12345678901");
		Thread.sleep(3000);
		
		WebElement inputTextTelefoneLeitor = driver.findElement(By.id("form:telefone"));
		inputTextTelefoneLeitor.sendKeys("99999999999");
		Thread.sleep(3000);
		
		WebElement botaoSalvar2 = driver.findElement(By.id("form:j_idt28"));
		botaoSalvar2.click();
		Thread.sleep(3000);
		
		/**
		 * Testa editar autor
		 */
		
		//Testa editar e cancela
		WebElement botaoEditar = driver.findElement(By.id("j_idt29:tabelaLeitores:0:j_idt37"));
		botaoEditar.click();
		Thread.sleep(3000);
		
		WebElement botaoCancelar = driver.findElement(By.id("form:j_idt30"));
		botaoCancelar.click();
		Thread.sleep(3000);
		
		//Testa editar e salva
		WebElement botaoEditar2 = driver.findElement(By.id("j_idt29:tabelaLeitores:0:j_idt37"));
		botaoEditar2.click();
		Thread.sleep(3000);
		
		WebElement inputTextNomeAutorEditado = driver.findElement(By.id("form:nome"));
		inputTextNomeAutorEditado.clear();
		Thread.sleep(3000);
		inputTextNomeAutorEditado.sendKeys("Maria Silva");
		Thread.sleep(3000);
		
		WebElement inputTextCpfLeitorEditado = driver.findElement(By.id("form:cpf"));
		inputTextCpfLeitorEditado.clear();
		Thread.sleep(3000);
		inputTextCpfLeitorEditado.sendKeys("12312312312");
		Thread.sleep(3000);
		
		WebElement inputTextTelefoneLeitorEditado = driver.findElement(By.id("form:telefone"));
		inputTextTelefoneLeitorEditado.clear();
		Thread.sleep(3000);
		inputTextTelefoneLeitorEditado.sendKeys("88888888888");
		Thread.sleep(3000);
		
		WebElement botaoAuterar = driver.findElement(By.id("form:j_idt29"));
		botaoAuterar.click();
		Thread.sleep(5000);
		
		/**
		 * Testa remover leitor
		 */
		
		//clica em remover porem cancelar
		WebElement botaoRemover = driver.findElement(By.id("j_idt32:tabelaLeitores:0:j_idt43"));
		botaoRemover.click();
		Thread.sleep(3000);
		
		WebElement botaoNao = driver.findElement(By.id("j_idt32:j_idt46"));
		botaoNao.click();
		Thread.sleep(3000);
		
		//clica em remover e confirma
		WebElement botaoRemover2 = driver.findElement(By.id("j_idt32:tabelaLeitores:0:j_idt43"));
		botaoRemover2.click();
		Thread.sleep(3000);
		
		WebElement botaoSim = driver.findElement(By.id("j_idt32:j_idt45"));
		botaoSim.click();
		Thread.sleep(3000);
	
	}

}
