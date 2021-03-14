package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CadastroUsuarioTest {

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
	public void testaCadastro() throws InterruptedException {
		/**
		 * Acessa o site na página de registra usuário
		 */
		driver.get("http://localhost:8080/bibliotecaJSF/registra_usuario.xhtml");
		Thread.sleep(3000);
		
		/**
		 * Testa se o título condiz com o esperado
		 */
		assertTrue("Title Divergente", driver.getTitle().contentEquals("Registro de usuario"));
		Thread.sleep(3000);
		
		/**
		 * Tenta cadastrar usuario sem as informacoes
		 */
		WebElement botaoRegistrar1 = driver.findElement(By.id("form:botaoRegistrar"));
		botaoRegistrar1.click();
		Thread.sleep(5000);
		
		/**
		 * Preenche informaçoes para cadastrar usuario
		 */
		WebElement inputTextNome = driver.findElement(By.id("form:nome"));
		inputTextNome.sendKeys("Airla Cordeiro");
		Thread.sleep(5000);
		
		WebElement inputTextCPF = driver.findElement(By.id("form:cpf"));
		inputTextCPF.sendKeys("12345678901");
		Thread.sleep(5000);
		
		WebElement inputTextMatricula = driver.findElement(By.id("form:matricula"));
		inputTextMatricula.sendKeys("111111111111");
		Thread.sleep(3000);
		
		WebElement inputTextEmail = driver.findElement(By.id("form:email"));
		inputTextEmail.sendKeys("airlav@gmail.com");
		Thread.sleep(3000);
		
		WebElement inputTextSenha = driver.findElement(By.id("form:senha"));
		inputTextSenha.sendKeys("Airla12!");
		Thread.sleep(3000);
		
		WebElement botaoAdicionrPapel = driver.findElement(By.id("form:botaoAdicionaPapel"));
		botaoAdicionrPapel.click();
		Thread.sleep(3000);
		
		WebElement inputTextSenha1 = driver.findElement(By.id("form:senha"));
		inputTextSenha1.sendKeys("Airla123!");
		Thread.sleep(3000);
		
		/**
		 * Verifica se foi adicionado um usuario do tipo escolhido 
		 * neste caso ADMIN
		 */
		WebElement fieldset = driver.findElement(By.id("form:papeisUsuarioHTML"));
		assertTrue("Title Divergente", fieldset.getText().contentEquals("ADMIN"));
		Thread.sleep(3000);
		
		/**
		 * Verifica se não foi adicionado o outro tipo de usuário 
		 * neste caso o BIBLIOTECARIO
		 */
		WebElement fieldset2 = driver.findElement(By.id("form:papeisUsuarioHTML"));
		assertFalse("Title Divergente", fieldset2.getText().contentEquals("BIBLIOTECARIO"));
		Thread.sleep(3000);
		
		/**
		 * Cadastra usuario
		 */
		WebElement botaoRegistrar2 = driver.findElement(By.id("form:botaoRegistrar"));
		botaoRegistrar2.click();
		Thread.sleep(5000);
		
		/**
		 * Verifica se foi adicionado uma mensagem de usuario cadastrado
		 */
		WebElement msg = driver.findElement(By.id("form:msg"));
		assertTrue("Title Divergente", msg.getText().contentEquals("Usuário cadastrado"));
		Thread.sleep(3000);
	}

	@Test
	public void testaVoltarParaLogin() throws InterruptedException {
		/**
		 * Acessa o site na página de registrar usuário
		 */
		driver.get("http://localhost:8080/bibliotecaJSF/registra_usuario.xhtml");
		Thread.sleep(3000);
		
		/**
		 * Testa o botão de voltar para login sem ter cadastrado um usuário
		 */
		WebElement botaoVoltar = driver.findElement(By.id("form:botaoVoltar"));
		botaoVoltar.click();
		Thread.sleep(3000);
	}
}
