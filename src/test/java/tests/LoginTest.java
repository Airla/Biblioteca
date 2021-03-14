package tests;


import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

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
	public void loginTest() throws InterruptedException {
		driver.get("http://localhost:8080/bibliotecaJSF/login.xhtml");
		Thread.sleep(5000);
		
		assertTrue("Title Divergente", driver.getTitle().contentEquals("Login"));
		Thread.sleep(5000);
		
		WebElement inputTextEmail = driver.findElement(By.id("form:login"));
		inputTextEmail.sendKeys("airla@gmail.com");
		Thread.sleep(5000);
		
		WebElement inputTextSenha = driver.findElement(By.id("form:senha"));
		inputTextSenha.sendKeys("Airla123!");
		Thread.sleep(5000);
		
		WebElement botaoAdicionrPapel = driver.findElement(By.id("form:botaoLogin"));
		botaoAdicionrPapel.click();
		Thread.sleep(5000);
		
		WebElement msg = driver.findElement(By.id("form:msg"));
		assertTrue("Title Divergente", msg.getText().contentEquals("Login com sucesso"));
		Thread.sleep(5000);
	}

}
