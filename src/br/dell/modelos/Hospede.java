package br.dell.modelos;

/**
 * Esta classe � respons�vel por armazenas os atributos dos hospedes do hotel.
 * 
 * @author Wendell Le�o de Oliveira
 * @version 1
 */
public class Hospede {

	private long CPF;
	private long RG;
	private String nome;
	private int idade;
	private String endereco;

	public Hospede() {
		
	}
	
	public Hospede(long CPF, long RG, String nome, int idade, String endereco) {
		this.CPF = CPF;
		this.RG = RG;
		this.nome = nome;
		this.idade = idade;
		this.endereco = endereco;
	}

	public long getCPF() {
		return this.CPF;
	}
	
	/**
	 * Este m�todo atribui � vari�vel CPF o valor inserido pelo 
	 * par�metro de entrada. Passa a responsabilidade para quem
	 * o chamar.
	 * @param CPF n�mero de CPF do hospede.
	 * @throws NumberFormatException caso o valor inserido como par�metro 
	 * n�o for um n�mero.
	 */
	public void setCPF(long CPF) throws NumberFormatException {
		this.CPF = CPF;
	}
	
	public long getRG() {
		return this.RG;
	}
	
	public void setRG(long RG) {
		this.RG = RG;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
}
