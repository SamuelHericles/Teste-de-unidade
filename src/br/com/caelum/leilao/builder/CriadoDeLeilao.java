package br.com.caelum.leilao.builder;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class CriadoDeLeilao {

	/*Esta classe se Test Data Builder - TDB
	 - Tem a especialidade de acplocar os m�todos para cria��o de cen�rios
	*/
	private Leilao leilao;

	//Cria um leilao a partir de uma string
	public CriadoDeLeilao para(String descricao) {
		this.leilao = new Leilao(descricao);
		return this;//devolve o propria classe criador de leilao, assim podemos aninhar m�todos para ficar mais simples nosso c�digo.
	}
	
	//Recebe a sequencia de lances ofertados
	public CriadoDeLeilao lance(Usuario usuario,Double valor) {
		leilao.propoe(new Lance(usuario,valor));
		return this;
	}
	
	//Devolve o atributo leil�o
	public Leilao constroi() {
		return leilao;
	}
}
