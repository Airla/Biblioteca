package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class EmprestimoTest {

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
		 * Seleciona a opção emprestimos no menu
		 */
		WebElement submenuFuncoes = driver.findElement(By.id("j_idt9:submenuFuncoes"));
		submenuFuncoes.click();
		Thread.sleep(3000);
		
		WebElement menuItemLeitores = driver.findElement(By.id("j_idt9:menuItemEmprestimos"));
		menuItemLeitores.click();
		Thread.sleep(10000);
		
		/**
		 * Tenta cadastrar o emprestimo sem as informacoes
		 */
		WebElement botaoSalvar1 = driver.findElement(By.id("form:botaoSalvar"));
		botaoSalvar1.click();
		Thread.sleep(5000);
		
		/**
		 * Testa cadastrar emprestimo
		 */
		WebElement calendarDataEmprestimo = driver.findElement(By.id("form:calendarData"));
		calendarDataEmprestimo.sendKeys("15122020");
		Thread.sleep(5000);
		
		WebElement checkbox = driver.findElement(By.id("form:checkboxDT:selecionaTodoLivros"));
		checkbox.click();
		Thread.sleep(5000);
		
		WebElement botaoLivrosSelecionados = driver.findElement(By.id("form:j_idt39"));
		botaoLivrosSelecionados.click();
		Thread.sleep(5000);
		
		WebElement botaoFecharDialogo = driver.findElement(By.className("ui-icon-closethick"));
		botaoFecharDialogo.click();
		Thread.sleep(5000);
		
		WebElement botaoSalvar2 = driver.findElement(By.id("form:botaoSalvar"));
		botaoSalvar2.click();
		Thread.sleep(5000);
	
	}

}
