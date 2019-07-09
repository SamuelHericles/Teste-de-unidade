package br.com.caelum.leilao.dominio;

public class Lance {

	/*
	     Quando usamos o m�todo 'equals' com algumas vari�veis,estes atributos devem possuir 
	 uma fun��o hashCode em suas classes.
	*/

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(valor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lance other = (Lance) obj;
		if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor))
			return false;
		return true;
	}

	//Variaveis da classe
	private Usuario usuario;
	private double valor;
	
	//Construtor da classe
	public Lance(Usuario usuario, double valor) {
		//Se for valor negativo, lan�a esta exe��o.
		if(valor<=0) throw new IllegalArgumentException();
		this.usuario = usuario;
		this.valor = valor;
	}

	//Obter o nome do usu�rio
	public Usuario getUsuario() {
		return usuario;
	}

	//Obter o valor do lance do usu�rio
	public double getValor() {
		return valor;
	}
	
	
	
}
