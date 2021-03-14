package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LivroTest {

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
		 * Seleciona a opção livros no menu
		 */
		WebElement submenuFuncoes = driver.findElement(By.id("j_idt9:submenuFuncoes"));
		submenuFuncoes.click();
		Thread.sleep(3000);
		
		WebElement menuItemLivros = driver.findElement(By.id("j_idt9:menuItemLivros"));
		menuItemLivros.click();
		Thread.sleep(10000);
		
		/**
		 * Tenta cadastrar o livro sem as informacoes
		 */
		WebElement botaoSalvar1 = driver.findElement(By.id("form:j_idt47"));
		botaoSalvar1.click();
		Thread.sleep(5000);
		
		/**
		 * Testa cadastrar livro
		 */
		WebElement inputTextTituloLivro = driver.findElement(By.id("form:titulo"));
		inputTextTituloLivro.sendKeys("Sistemas Corporativos");
		Thread.sleep(5000);
		
		WebElement inputFieldDescricaoLivro = driver.findElement(By.id("form:descricao"));
		inputFieldDescricaoLivro.sendKeys("Este livro aborda temas de sistemas corporativos.");
		Thread.sleep(5000);
		
		WebElement inputTextIsbnLivro = driver.findElement(By.id("form:isbn"));
		inputTextIsbnLivro.sendKeys("11111");
		Thread.sleep(5000);
		
		WebElement inputTextDataLivro = driver.findElement(By.id("form:data"));
		inputTextDataLivro.sendKeys("06102017");
		Thread.sleep(5000);
		
		WebElement inputTextQuantidadeLivro = driver.findElement(By.id("form:quantidade"));
		inputTextQuantidadeLivro.clear();
		Thread.sleep(5000);
		
		inputTextQuantidadeLivro.sendKeys("12");
		Thread.sleep(5000);
		
		WebElement botaoAdicionarAutor = driver.findElement(By.id("form:j_idt40"));
		botaoAdicionarAutor.click();
		Thread.sleep(5000);
		
		WebElement botaoSalvar2 = driver.findElement(By.id("form:j_idt47"));
		botaoSalvar2.click();
		Thread.sleep(5000);
				
		/**
		 * Testa editar livro
		 */
		
		/**
		 * Testa editar e cancela
		 */
		WebElement botaoEditar = driver.findElement(By.id("j_idt49:tabelaLivros:0:j_idt59"));
		botaoEditar.click();
		Thread.sleep(3000);
		
		WebElement botaoCancelar = driver.findElement(By.id("form:j_idt50"));
		botaoCancelar.click();
		Thread.sleep(3000);
		
		/**
		 * Testa editar e salva
		 */
		
		WebElement botaoEditar2 = driver.findElement(By.id("j_idt49:tabelaLivros:0:j_idt59"));
		botaoEditar2.click();
		Thread.sleep(3000);
		
		WebElement inputTextTituloLivroEditado = driver.findElement(By.id("form:titulo"));
		inputTextTituloLivroEditado.clear();
		Thread.sleep(5000);
		inputTextTituloLivroEditado.sendKeys("Sistemas Corporativos Editado");
		Thread.sleep(5000);
		
		WebElement inputFieldDescricaoLivroEditado = driver.findElement(By.id("form:descricao"));
		inputFieldDescricaoLivroEditado.clear();
		Thread.sleep(5000);
		inputFieldDescricaoLivroEditado.sendKeys("Este livro aborda temas de sistemas corporativos. Editado");
		Thread.sleep(5000);
		
		WebElement inputTextIsbnLivroEditado = driver.findElement(By.id("form:isbn"));
		inputTextIsbnLivroEditado.clear();
		Thread.sleep(5000);
		inputTextIsbnLivroEditado.sendKeys("22222");
		Thread.sleep(5000);
		
		WebElement inputTextDataLivroEditado = driver.findElement(By.id("form:data"));
		inputTextDataLivroEditado.clear();
		Thread.sleep(5000);
		inputTextDataLivroEditado.sendKeys("06102016");		
		Thread.sleep(5000);
		
		WebElement inputTextQuantidadeLivroEditado = driver.findElement(By.id("form:quantidade"));
		inputTextQuantidadeLivroEditado.clear();
		Thread.sleep(5000);		
		inputTextQuantidadeLivroEditado.sendKeys("10");
		Thread.sleep(5000);
		
		WebElement botaoAlterar = driver.findElement(By.id("form:j_idt49"));
		botaoAlterar.click();
		Thread.sleep(5000);		
		
		/**
		 * Testa remover livro
		 */
		
		/**
		 * clica em remover porem cancela
		 */
		WebElement botaoRemover = driver.findElement(By.id("j_idt49:tabelaLivros:0:j_idt62"));
		botaoRemover.click();
		Thread.sleep(5000);
		
		WebElement botaoNao = driver.findElement(By.id("j_idt49:j_idt65"));
		botaoNao.click();
		Thread.sleep(5000);
		
		/**
		 * clica em remover e confirma
		 */
		WebElement botaoRemover2 = driver.findElement(By.id("j_idt49:tabelaLivros:0:j_idt62"));
		botaoRemover2.click();
		Thread.sleep(5000);
		
		WebElement botaoSim = driver.findElement(By.id("j_idt49:j_idt64"));
		botaoSim.click();
		Thread.sleep(5000);
	
	}


}
