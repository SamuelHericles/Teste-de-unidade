package br.com.caelum.leilao.teste;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.caelum.leilao.builder.CriadoDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;
// inicar o m�todo antes dos testes, assim n�o precisamos chamar o m�todo todas
// vez que precisarmos.
// � criado a cada m�todo anotado como "@Test"
// teste � da org.util
/*
 * Ao testar uma Lista, fazer N+1 verifica��es(quantidade de asserts), o
 * primeiro para garantir o tamanho da lista, depois N asserts para garantir o
 * conte�do interno completo dessa lista.
 */
//Hamcrest = � um framework que nos auxilia em m�todo com m�todos mais intuitivos.
public class AvaliadorTest {

	private Avaliador leiloeiro;
	private Usuario joao;
	private Usuario jose;
	private Usuario maria;

	// Com o Before inicia a cada m�todo que comece com @Test
	@Before
	public void criaAvaliador() {
		this.leiloeiro = new Avaliador();
		this.joao = new Usuario("Jo�o");
		this.jose = new Usuario("Jos�");
		this.maria = new Usuario("Maria");
		System.out.println("Inicializar");
	}

	// Esta anota��o finaliza com a cada m�todo com que tem anota��o @Test
	// � util para liberar rescursos que estes m�todos @Test estav�o consumindo
	@After
	public void setUp() {
		System.out.println("Finalizar");
	}

	/*
	 * BeforeClass e AfterClass � util para quando querendo usar um recurso apena uma vez e dopois libera-lo apenas uma vez. 
	 * */
	//Anot��o que faz o m�todo ser executado antes de todos os m�todos dessa classe,executado apenas uma vez.
	@BeforeClass
	public static void testandoBeforeClass() {
		System.out.println("Before class");
	}
	
	//Anota�a� que faz o m�todo ser executado no final da execu��o de todos os m�todos dessa classe,executado apenas uma vez.
	@AfterClass
	public static void testandoAfterClass() {
		System.out.println("After Class");
	}
	
	//Com o expected n�o precisamos mais poluir com try e cath nosso m�todo.
	@Test(expected=RuntimeException.class)
	public void naoDeveAvaliarLeilaoSemNenhumLanceDado() {
		
	}
	
	
	
	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		// parte 1: cenario

		Leilao leilao = new Leilao("Plyastation 3 Novo");

		// parte 2:a��o
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(jose, 400.0));
		leilao.propoe(new Lance(maria, 250.0));

		leiloeiro.avalia(leilao);

		double maiorEsperado = 400;
		double menorEsperado = 250;

		// parte 3:valida��o
		// o primeiro paramentro � o esperado e o segundo � o calculado.
		// 0,00001 � o tamanho de erro aceitavel para diferenciar o esperado do
		// calculado
		assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);

		/*
		 * conven��o de pastas a vantagem � a rastreabilidade das classes voc� sabe qual
		 * pasta est� as classes de testes.
		 */
		// DESENVOLVIMENTO COM TESTE � MAIS PRODUTIVOS POIS S�O 100 LINHA DE C�DIGO QUE
		// SER�O REALMENTE UTEIS, POR EXEMPLO.
		// Equivaliencia de testes: s� precisa fazer um certa bateria de testes
		// especifica que serve para outras com apenas valores
		// diferentes, mas o resultado que esperamos seria o mesmo para todas.
	}

	@Test
	public void deveEntenderLancesEmOrdemCrescenteComOutrosValores() {
		Leilao leilao = new Leilao("Playstation 3 Novo");

		leilao.propoe(new Lance(maria, 1000.0));
		leilao.propoe(new Lance(joao, 2000.0));
		leilao.propoe(new Lance(jose, 3000.0));

		leiloeiro.avalia(leilao);

		assertEquals(3000, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(1000, leiloeiro.getMenorLance(), 0.00001);

	}

	@Test
	public void deveEntenderLeilaoComApenasUmLance() {
		Leilao leilao = new Leilao("Playstation 3 Novo");

		leilao.propoe(new Lance(joao, 1000));

		leiloeiro.avalia(leilao);

		assertEquals(1000, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(1000, leiloeiro.getMenorLance(), 0.00001);

	}

	@Test
	public void deveEncontrarOsTresMaioresLances() {
		Leilao leilao = new CriadoDeLeilao().para("Playstation 3 novo").lance(joao, 100.00).lance(maria, 250.00)
				.lance(joao, 300.00).lance(maria, 400.00).constroi();

		leiloeiro.avalia(leilao);

		List<Lance> maiores = leiloeiro.getTresMaiores();
		assertEquals(3, maiores.size());
		assertEquals(400, maiores.get(0).getValor(), 0.00001);
		assertEquals(300, maiores.get(1).getValor(), 0.00001);
		assertEquals(200, maiores.get(2).getValor(), 0.00001);

	}

	@Test
	public void umUnicoLance() {
		Leilao leilao = new Leilao("Carro");

		leilao.propoe(new Lance(joao, 200.0));

		leiloeiro.avalia(leilao);

		assertEquals(200, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(200, leiloeiro.getMenorLance(), 0.00001);

	}

	@Test
	public void lancesAletorios() {
		Leilao leilao = new CriadoDeLeilao().para("Paystation 3 Novo").lance(joao, 200.00).lance(maria, 450.00)
				.lance(joao, 120.00).lance(maria, 200.00).lance(joao, 300.00).lance(maria, 2000.00).lance(joao, 700.00)
				.constroi();

		leiloeiro.avalia(leilao);

		assertEquals(700.0, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(120.0, leiloeiro.getMenorLance(), 0.00001);
	}
}

