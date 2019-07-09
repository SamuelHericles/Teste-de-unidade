package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao {

	//Variaveis da classe
	private String descricao;
	private List<Lance> lances;

	//Construtor para usu�rio inserir seu lance para qual evento
	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<Lance>();
	}

	//Test Drive Development - TDD
	
	/* 
	 * -Se sempre escrevermos o teste antes, garantimos que todo nosso c�digo j� "nasce" testado;
	 * 
	 * -Temos seguran�a para refatorar nosso c�digo, afinal sempre refatoraremos com uma bateria
	 *  de testes que garante que n�o quebraremos o comportamento j� existente;
	 * 
	 * -Como o teste � a primeira classe que usa o seu c�digo, voc� naturalmente tende a escrever 
	 *  c�digo mais f�cil de ser usado e, por consequ�ncia, mais f�cil de ser mantido.
	 */

	
	/*
	 * - baby steps: passos pequenos e consissos, para evoluir com base em testes.
	 * - Com o TDD nos deixa pensar cada vez mais simples as implementa��es.
	 * - Tome passo pequenos sempre quando sua confian�a estiver baixa.
	 * - A unica maneira de trabalhar com qualidade e segunra�a em c�digo assim � tendo uma bateria 
	 *   de testes que garanta qualquer mundan�a feita; 
	 */
	
	//Para usar o equals melhor, devemos implementar o hashCode na classe que instaciamos
	public void propoe(Lance lance) {
		if (lances.isEmpty() || podeDarLance(lance.getUsuario())) {
			lances.add(lance);
		}
	}

	//Verificar o lance dado	
	private boolean podeDarLance(Usuario usuario) {
		return !extracted().getUsuario().equals(usuario)&& qtdDeLanceDo(usuario) < 5;
	}

	//Saber a quantidade de lances ofertados
	private int qtdDeLanceDo(Usuario usuario) {
		int total = 0;
		for (Lance l : lances) {
			if(l.getUsuario().equals(usuario))total++;
		}
		return total;
	}

	private Lance extracted() {
		return lances.get(lances.size() - 1);
	}

	//Obter a descri��o do lance
	public String getDescricao() {
		return descricao;
	}

	//Obter a lista de lances
	public List<Lance> getLances() {
		return Collections.unmodifiableList(lances);
	}
	
	//Saber se o ano eh bissexto ou n�o
	public boolean isBissexto(int ano) {
		if((ano % 4 ==0) && (ano % 100 != 0) ) return true;
		else if ((ano % 400 == 0 )) return true;
		else return false;
	}

}
