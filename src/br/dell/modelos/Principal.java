package br.dell.modelos;

import javax.swing.JOptionPane;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.HashMap;
/**
 * Esta classe � respons�vel por instanciar os hospedes do hotel e atribuir os valores
 * da classe {@link br.dell.modelos.Hospede} por meio da intera��o com o usu�rio. Ap�s isso � instanciado um 
 * quarto para o hospede. 
 * 
 * @author Wendell Le�o de Oliveira
 * @version 4
 */
public class Principal {
	/**
	 * Este � o m�todo principal
	 */
	public static void main(String[] args) {
		/**A interface Map parece ser a mais adequeada para o caso, 
		 * pois com ela � poss�vel associar o n�mero do quarto com 
		 * o nome do h�spede apenas passando como par�metro dois 
		 * atributos no m�todo put.*/
		HashMap<Integer, Hospede> mapa = new HashMap<Integer, Hospede>();

		Hospede hospede1 = new Hospede(528641349, 265498647, "Cl�udio Soares", 58, "Rua das Pamonhas");
		mapa.put(115, hospede1);
		Hospede hospede2 = new Hospede(256987145, 125487635, "Silvana Martillo", 37, "Estrada Mosaicos Dourados");
		mapa.put(208, hospede2);
		Hospede hospede3 = new Hospede(256987145, 125487635, "Adriano Ricardo de Souza", 42, "Avenida Raios Cortantes");
		mapa.put(159, hospede3);
		Hospede hospede = new Hospede();
	
		while(true) {			
			try {
				hospede.setCPF(Long.parseLong(JOptionPane.showInputDialog("Digite seu CPF:")));
				break;
			}catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null, "Foi informado um valor de entrada inv�lido! Insira apenas n�meros.", 
						null, JOptionPane.ERROR_MESSAGE);
			}
		}
		
		while(true) {			
			try {
				hospede.setRG(Long.parseLong(JOptionPane.showInputDialog("Digite seu RG:")));
				break;
			}catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null, "Foi informado um valor de entrada inv�lido! Insira apenas n�meros.", 
						null, JOptionPane.ERROR_MESSAGE);
			}
		}
		
		hospede.setNome(JOptionPane.showInputDialog("Digite seu nome:"));
		validaNome(hospede.getNome());

		while(true) {			
			try {
				hospede.setIdade(Integer.parseInt(JOptionPane.showInputDialog("Digite sua idade:")));
				break;
			}catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null, "Foi informado um valor de entrada inv�lido!", 
						null, JOptionPane.ERROR_MESSAGE);
			}
		}
		
		hospede.setEndereco(JOptionPane.showInputDialog("Digite seu endere�o:"));
		
		JOptionPane.showMessageDialog(null, "Voc� foi cadastrado com sucesso!");
		
		int numeroQuarto;
		while(true) {			
			try {
				numeroQuarto = Integer.parseInt(JOptionPane.showInputDialog("Agora digite o n�mero do seu quarto:"));
				break;
			}catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null, "Foi informado um valor de entrada inv�lido!", 
						null, JOptionPane.ERROR_MESSAGE);
			}
		}
		
		Quarto quarto = new Quarto(numeroQuarto, hospede);
		mapa.put(numeroQuarto, hospede);
		
		imprimirInformacoesCadastro(hospede, quarto.getNumero());
		encontrarHospedePeloNumeroQuarto(mapa);
	}
	
	/**
	 * Este m�todo procura no mapa qual hospede est� associado com o n�mero do
	 * quarto inserido pelo usu�rio.
	 * @param mapa � uma inst�ncia do hashmap. 
	 */
	public static void encontrarHospedePeloNumeroQuarto(HashMap<Integer, Hospede> mapa) {
		
		int numeroQuarto;
		
		while(true) {			
			try {
				numeroQuarto = Integer.parseInt(JOptionPane.showInputDialog("Digite o n�mero de um quarto para saber quem est� o hospedando:"));
				break;
			}catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null, "Foi informado um valor de entrada inv�lido!", 
						null, JOptionPane.ERROR_MESSAGE);
			}
		}
		
		if(mapa.get(numeroQuarto) == null) {
			JOptionPane.showMessageDialog(null, "N�o existe hospede associado ao quarto " + numeroQuarto + ".");
		}else {
			imprimirInformacoesCadastro(mapa.get(numeroQuarto));
		}
	}
	
	/**
	 * Este m�todo imprime e mostra ao usu�rio as informa��es inseridas no cadastro. 
	 * @param hospede inst�ncia de um hospede do hotel
	 * @param numeroQuarto n�mero do quarto inserido pelo usu�rio no cadastro
	 */
	public static void imprimirInformacoesCadastro(Hospede hospede, int numeroQuarto) {
		JOptionPane.showMessageDialog(null, 
				"Nome: " + hospede.getNome() + 
				"\nCPF: " + hospede.getCPF() + 
				"\nRG: " + hospede.getRG() + 
				"\nIdade: " + hospede.getIdade() + 
				"\nEndere�o: " + hospede.getEndereco() + 
				"\nN�mero do quarto: " + numeroQuarto);
	}
	
	/**
	 * Este m�todo imprime e mostra ao usu�rio as informa��es inseridas no cadastro 
	 * menos o n�mero do quarto. 
	 * @param hospede inst�ncia de um hospede do hotel
	 */
	public static void imprimirInformacoesCadastro(Hospede hospede) {
		JOptionPane.showMessageDialog(null, 
				"Nome: " + hospede.getNome() + 
				"\nCPF: " + hospede.getCPF() + 
				"\nRG: " + hospede.getRG() + 
				"\nIdade: " + hospede.getIdade() + 
				"\nEndere�o: " + hospede.getEndereco());
	}
	
	/**
	 * Este m�todo verifica se o nome inserido pelo usu�rio possui caracteres especiais.
	 * @param nome inserido pelo usu�rio para fazer a verifica��o.
	 * @return retorna uma booleana. Se houver caracteres especiais ele retorna falso e 
	 * se n�o houver retorna verdadeiro. 
	 */
	public static boolean validaNome(String nome) {
		Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
		Matcher matcher = pattern.matcher(nome);

		boolean contemCaracterEspecial = matcher.find();
		boolean contemNumero = nome.matches(".*\\d.*");

		if (contemCaracterEspecial|| contemNumero) {
			System.out.println(nome + " cont�m caracter especial");
			return false;
		}
		else {
			System.out.println(nome + " n�o cont�m caracter especial");
			return true;
		}
	}
}

