package com.controller;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class SeleniumExample
{
	public static void main(String... args) {
        // Create a new instance of the html unit driver
        // Notice that the remainder of the code relies on the interface, 
        // not the implementation.
        WebDriver driver = new HtmlUnitDriver();

        // And now use this to visit Google
        driver.get("https://piviijava.herokuapp.com/");

        // Find the text input element by its name
        WebElement elementNome = driver.findElement(By.name("nome"));
        WebElement elementSalario = driver.findElement(By.name("salario"));
        WebElement elementEspecializacao = driver.findElement(By.name("especializacao"));
        WebElement elementForm = driver.findElement(By.name("especializacao"));
        
        // Enter something to search for
        elementNome.sendKeys("SeleniumTest");
        elementSalario.sendKeys("10000");
        elementEspecializacao.sendKeys("Ortopedista");
        
        // Now submit the form. WebDriver will find the form for us from the element
        elementNome.submit();

        // Check the title of the page
        //System.out.println("Mensagem: " + driver.findElement(By.name("mensagem")).getAttribute("value"));

        driver.quit();
    }
}