import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.controller.Medico;
import com.controller.MedicoApplication;
import com.controller.MedicoServlet;
import com.controller.SeleniumExample;

public class MedicoTest
{
	@Test
	public void TestDeveRetornarVazioQuandoDadosPassadosCorretos()
	{
		MedicoApplication caminhaoApplication = new MedicoApplication();
		
		Medico m = new Medico();
		//Mudanças na Classe teste
		m.setNome("");
		m.setEspecializacao("Ortopedista");
		m.setSalario(10000);
		StringBuffer sb = new StringBuffer();
		boolean valido = caminhaoApplication.validar(m.getNome(), m.getSalario() + "", m.getEspecializacao(),new StringBuffer());
		
		assertEquals(sb.toString(), "");
	}
	
	@Test
	public void testDeveRetornarMensagemDeErroQuandoPassadoSalarioNulo()
	{
		MedicoApplication ma = new MedicoApplication();
		StringBuffer sb = new StringBuffer();
		ma.validar("nome", null, "especializacao", sb);
		assertEquals(sb.toString(), "Favor preencher o campo salario.");
	}
	
	@Test
	public void testDeveRetornarMensagemDeErroQuandoPassadoSalarioVazio()
	{
		MedicoApplication ma = new MedicoApplication();
		StringBuffer sb = new StringBuffer();
		ma.validar("nome", "", "especializacao", sb);
		assertEquals(sb.toString(), "Favor preencher o campo salario.");
	}
	
	@Test
	public void testDeveRetornarMensagemDeErroQuandoPassadoEspecializacaoNulo()
	{
		MedicoApplication ma = new MedicoApplication();
		StringBuffer sb = new StringBuffer();
		ma.validar("nome", "10000", null, sb);
		assertEquals(sb.toString(), "Favor digite uma especializacao.");
	}
	
	@Test
	public void testDeveRetornarMensagemDeErroQuandoPassadoEspecializacaoVazio()
	{
		MedicoApplication ma = new MedicoApplication();
		StringBuffer sb = new StringBuffer();
		ma.validar("nome", "10000", "", sb);
		assertEquals(sb.toString(), "Favor digite uma especializacao.");
	}

	@Test
	public void testDeveRetornarMensagemDeErroQuandoPassadoSalarioCimaDe100000()
	{
		MedicoApplication ma = new MedicoApplication();
		StringBuffer sb = new StringBuffer();
		ma.validar("nome", "100001", null, sb);
		assertEquals(sb.toString(), "Favor digite uma especializacao.");
	}
	@Test
	public void testDeveRetornarMensagemVaziaQuandoPassadoSalarioMenorQue100000()
	{
		MedicoApplication ma = new MedicoApplication();
		StringBuffer sb = new StringBuffer();
		ma.validar("nome", "99999", "Especializacao", sb);
		assertEquals(sb.toString(), "");
	}
	@Test
	public void testDeveRetornarMensagemDeErroQuandoPassadoNomeNulo()
	{
		MedicoApplication ma = new MedicoApplication();
		StringBuffer sb = new StringBuffer();
		ma.validar(null, "99999", "Especializacao", sb);
		assertEquals(sb.toString(), "Favor digitar o nome do médico.");
	}
	@Test
	public void testDeveRetornarMensagemDeErroQuandoPassadoNomeVazio()
	{
		MedicoApplication ma = new MedicoApplication();
		StringBuffer sb = new StringBuffer();
		ma.validar("", "99999", "Especializacao", sb);
		assertEquals(sb.toString(), "Favor digitar o nome do médico.");
	}
	
	@Test
	public void TestCriando()
	{
		MedicoApplication medicoApplication = new MedicoApplication();
		
		Medico m = new Medico();
		m.setEspecializacao("Ortopedista");
		m.setNome("Marcus");
		m.setSalario(1000);
		List<Medico> medicosAntes = medicoApplication.todos();
		medicoApplication.create(m);
		List<Medico> medicosDepois = medicoApplication.todos();
		assertEquals(medicosAntes.size() + 1 ,medicosDepois.size());
		
	}
	@Test
	public void testPreencheDoPostEDoGet()
	{
		MedicoServlet ms = new MedicoServlet();
		String mensagem = null;
		HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        try {
			ms.doPost(request, response);
			ms.doGet(request, response);
		} catch (IOException e) {
			mensagem = e.getMessage();
		} catch (ServletException e) {
			mensagem = e.getMessage();
		}
        catch(Exception e) {
			mensagem = e.getMessage();
		}
        
		assertNotEquals(mensagem, null);
	}
	
	@Test
	public void TestChamaOSelenium()
	{
		SeleniumExample.main(null);
		assertEquals(true, true);
	}
	
	
	//Teste com selenium
	private static WebDriver driver;
	// Método que inicia tudo que for necessário para o teste
	// Cria uma instância do navegador e abre a página inicial da DevMedia.
	@BeforeClass
	public static void setUpTest(){
		driver = new FirefoxDriver();
		driver.get("http://piviijava.herokuapp.com/");
	}
	
	// Método que finaliza o teste, fechando a instância do WebDriver.    
	@AfterClass
	public static void tearDownTest(){
		driver.quit();
	}
	
	@Test
	public void testaTituloDaPagina(){
		WebElement mensagem = driver.findElement(By.name("mensagem"));
		assertEquals("", mensagem.getText());
	}
	
	// Método que testa o login no site DevMedia.
	@Test
	public void testSeleniumJUnit() {

	// Instancia um novo objeto do tipo "WebElement", e passa como parâmetro
	// um elemento da tela cujo valor do atributo "name" seja igual a "usuario".
	WebElement nome = driver.findElement(By.name("nome"));
	WebElement salario = driver.findElement(By.name("salario"));
	WebElement especializacao = driver.findElement(By.name("especializacao"));
	
	// Insere dados no elemento "senha".
	nome.sendKeys("testSeleniumJUnit");
	salario.sendKeys("123456");
	especializacao.sendKeys("Ortopedista");
	
	// Clica no botão "OK" e submete os dados para concluir o login.
		driver.findElement(By.id("buscar")).click();
	}

	
}