package br.com.caelum.leilao.builder;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class CriadoDeLeilao {

	//ESTA CLASSE SE CHAMA DE TEST DATA BUILDER - TDB
	//TEM A ESPECIALIDADE DE ACLOPAR OS M�TODOS PARA CRIA��O DOS CEN�RIOS
	private Leilao leilao;

	public CriadoDeLeilao para(String descricao) {
		this.leilao = new Leilao(descricao);
		return this;//devolve o propria classe criador de leilao, assim podemos aninhar m�todos para ficar mais simples nosso c�digo.
	}
	
	public CriadoDeLeilao lance(Usuario usuario,Double valor) {
		leilao.propoe(new Lance(usuario,valor));
		return this;
	}
	
	
	//devolve o leilao do atributo
	public Leilao constroi() {
		return leilao;
	}
}
