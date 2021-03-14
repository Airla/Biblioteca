package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MenuTest {

	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\airla\\Desktop\\bibliotecaJSFSeguro_Inicial\\bibliotecaJSF\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void menuTest() throws InterruptedException {

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
		WebElement submenuFuncoes1 = driver.findElement(By.id("j_idt9:submenuFuncoes"));
		submenuFuncoes1.click();
		Thread.sleep(3000);

		WebElement menuItemLeitores = driver.findElement(By.id("j_idt9:menuItemLeitores"));
		menuItemLeitores.click();
		Thread.sleep(10000);

		/**
		 * Seleciona a opção livros no menu
		 */
		WebElement submenuFuncoes2 = driver.findElement(By.id("j_idt9:submenuFuncoes"));
		submenuFuncoes2.click();
		Thread.sleep(5000);

		WebElement menuItemLivros = driver.findElement(By.id("j_idt9:menuItemLivros"));
		menuItemLivros.click();
		Thread.sleep(5000);
		
		/**
		 * Seleciona a opção autores no menu
		 */
		WebElement submenuFuncoes3 = driver.findElement(By.id("j_idt9:submenuFuncoes"));
		submenuFuncoes3.click();
		Thread.sleep(5000);

		WebElement menuItemAutores = driver.findElement(By.id("j_idt9:menuItemAutores"));
		menuItemAutores.click();
		Thread.sleep(5000);
		
		/**
		 * Seleciona a opção emprestimos no menu
		 */
		WebElement submenuFuncoes4 = driver.findElement(By.id("j_idt9:submenuFuncoes"));
		submenuFuncoes4.click();
		Thread.sleep(5000);

		WebElement menuItemEmprestimos = driver.findElement(By.id("j_idt9:menuItemEmprestimos"));
		menuItemEmprestimos.click();
		Thread.sleep(5000);
		
		/**
		 * Seleciona a opção home no menu
		 */

		WebElement menuItemHome = driver.findElement(By.id("j_idt9:menuItemHome"));
		menuItemHome.click();
		Thread.sleep(5000);

		/**
		 * Verifica se o nome do usuario aparece no menu
		 */
		WebElement nomeUsuarioMenu = driver.findElement(By.id("j_idt9:nomeUsuario"));
		assertTrue("Title Divergente", nomeUsuarioMenu.getText().contentEquals("Bem vindo(a) Airla Cordeiro"));
		Thread.sleep(5000);
		
		/**
		 * Testa sair da conta
		 */ 
		WebElement botaoLogout = driver.findElement(By.id("j_idt9:j_idt14:botaoLogout"));
		botaoLogout.click();
		Thread.sleep(5000);		
	}

}
