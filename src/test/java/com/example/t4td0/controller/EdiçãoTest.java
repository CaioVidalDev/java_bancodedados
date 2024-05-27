package com.example.t4td0.controller;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class EdiçãoTest {

    private static WebDriver driver; // variavel driver do tipo WebDriver

    @BeforeAll // Executar o método abaixo antes de todos os testes
    public static void setUp() { // Configuração
        WebDriverManager.firefoxdriver().setup(); // Configura o driver do Firefox
        FirefoxOptions options = new FirefoxOptions(); // Opções do Firefox
        driver = new FirefoxDriver(options); // Inicializa o driver com o Firefox 
    }

    @SuppressWarnings("deprecation")
    @Test // Metodo Teste
    public void testEdicaoTarefa() { // Teste para cadastro de tarefa
        driver.get("http://localhost:8080/tarefas"); // Abrir a pagina de lista de tarefas

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // Tempo de espera de 10 segundos para inicializar

       WebElement cadastrar = driver.findElement(By.className("btn-voltar")); // Encontrar o botao de cadastrar tarefa
       cadastrar.click(); // Clicar no botao

       WebElement caixaDeBusca = driver.findElement(By.id("titulo-cadastro")); // Encontra a caixa de texto 
       caixaDeBusca.click(); // Clica na caixa de texto
       caixaDeBusca.sendKeys("Teste De Cadastrar"); // Digita "Teste De Cadastrar"

       WebElement salvarCadastro = driver.findElement(By.id("salvarCadastro")); // Encontra o botão de salvar cadastro
       salvarCadastro.click(); // Clica no botão

       WebElement voltar = driver.findElement(By.className("btn-voltar")); // Encontra o botão de voltar
       voltar.click(); // Clica no botão de voltar

        /* Editar Tarefa */
       WebElement editar = driver.findElement(By.className("btn-editar")); // Encontra o botão de editar
       editar.click(); // Clica no botao

       WebElement caixaDeBuscaEditar = driver.findElement(By.id("novo-titulo")); // Encontra a caixa de texto
       caixaDeBuscaEditar.click(); // Clica no botao
       caixaDeBuscaEditar.sendKeys(" Editado"); // Digita " Editado"

       WebElement salvarCadastroEditar = driver.findElement(By.id("salvarEdit")); // Encontra o botão de salvar edição
       salvarCadastroEditar.click(); // Clica no botao
 
       WebElement voltarEditar = driver.findElement(By.className("btn-voltar")); // Encontra o botão de voltar
       voltarEditar.click(); // Clica no botao

       /* Tarefa Editada com Sucesso */
       WebElement cadastroSucesso = driver.findElement(By.className("tarefa-nome")); // Encontra o campo da tarefa cadastrada
       String TarefaCadastroSucess = cadastroSucesso.getText(); // Recebe o texto da tarefa
       
       if(TarefaCadastroSucess.contains("Teste De Cadastrar Editado")) { // Se no texto contem "Teste De Cadastrar Editado"
        System.out.println("Teste de editar tarefa realizado com sucesso"); // Aparece essa mensagem
        }else{
         System.out.println("Erro na Pesquisa"); // Aparece essa mensagem
        }

         /* Excluir Tarefa */
         WebElement excluir = driver.findElement(By.className("btn-excluir")); // Encontra o botão para ir a tela de excluir
         excluir.click(); // Clica no botao
  
         WebElement excluirSim = driver.findElement(By.className("btn-sim")); // Encontra o botão de sim para excluir
         excluirSim.click(); // Clica no botao
         
       driver.close(); // Fechar navegador

    }

}
