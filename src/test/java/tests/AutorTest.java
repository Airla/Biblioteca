package tests;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AutorTest {

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
	public void autorTest() throws InterruptedException {
		
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
		 * Seleciona a opção autores no menu
		 */
		WebElement submenuFuncoes = driver.findElement(By.id("j_idt9:submenuFuncoes"));
		submenuFuncoes.click();
		Thread.sleep(3000);
		
		WebElement menuItemAutores = driver.findElement(By.id("j_idt9:menuItemAutores"));
		menuItemAutores.click();
		Thread.sleep(10000);
		
		/**
		 * Tentar cadastrar autor sem as informacoes
		 */
		WebElement botaoSalvar1 = driver.findElement(By.id("form:botaoSalvar"));
		botaoSalvar1.click();
		Thread.sleep(3000);
		
		/**
		 * Preenche informaçoes para cadastrar autor
		 */
		WebElement inputTextNomeAutor = driver.findElement(By.id("form:nome"));
		inputTextNomeAutor.sendKeys("Martin");
		Thread.sleep(3000);
		
		WebElement inputTextEmailAutor = driver.findElement(By.id("form:email"));
		inputTextEmailAutor.sendKeys("martin@gmail.com");
		Thread.sleep(3000);
		
		WebElement botaoSalvar2 = driver.findElement(By.id("form:botaoSalvar"));
		botaoSalvar2.click();
		Thread.sleep(3000);
		
		/**
		 * Testa editar autor
		 */
		
		/**
		 * Testa editar e cancela
		 */
		WebElement botaoEditar = driver.findElement(By.id("j_idt26:autoresTable:0:botaoEditar"));
		botaoEditar.click();
		Thread.sleep(3000);
		
		WebElement botaoCancelar = driver.findElement(By.id("form:j_idt27"));
		botaoCancelar.click();
		Thread.sleep(3000);
		
		/**
		 * Testa editar e salva
		 */		
		
		WebElement botaoEditar2 = driver.findElement(By.id("j_idt26:autoresTable:0:botaoEditar"));
		botaoEditar2.click();
		Thread.sleep(3000);
		
		WebElement inputTextNomeAutorEditado = driver.findElement(By.id("form:nome"));
		inputTextNomeAutorEditado.clear();
		Thread.sleep(3000);
		inputTextNomeAutorEditado.sendKeys("Martin Fowler");
		Thread.sleep(3000);
		
		WebElement inputTextEmailAutorEditado = driver.findElement(By.id("form:email"));
		inputTextEmailAutorEditado.clear();
		Thread.sleep(3000);
		inputTextEmailAutorEditado.sendKeys("martinfowler@gmail.com");
		Thread.sleep(3000);
		
		WebElement botaoAuterar = driver.findElement(By.id("form:botaoAlterar"));
		botaoAuterar.click();
		Thread.sleep(5000);
		
		/**
		 * Testa remover autor
		 */
		
		/**
		 * clica em remover porem cancela
		 */
		WebElement botaoRemover = driver.findElement(By.id("j_idt26:autoresTable:0:botaoRemover"));
		botaoRemover.click();
		Thread.sleep(3000);
		
		WebElement botaoNao = driver.findElement(By.id("j_idt26:j_idt36"));
		botaoNao.click();
		Thread.sleep(3000);
		
		/**
		 * clica em remover e confirma
		 */
		WebElement botaoRemover2 = driver.findElement(By.id("j_idt26:autoresTable:0:botaoRemover"));
		botaoRemover2.click();
		Thread.sleep(3000);
		
		WebElement botaoSim = driver.findElement(By.id("j_idt26:j_idt35"));
		botaoSim.click();
		Thread.sleep(3000);
		
		/**
		 * Testa voltar para livros
		 */
		WebElement botaoVoltarParaLivros = driver.findElement(By.id("form:botaoCancelar"));
		botaoVoltarParaLivros.click();
		Thread.sleep(3000);
	
	}

}
