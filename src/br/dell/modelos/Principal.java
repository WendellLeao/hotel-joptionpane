package br.dell.modelos;

import javax.swing.JOptionPane;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.HashMap;
/**
 * Esta classe é responsável por instanciar os hospedes do hotel e atribuir os valores
 * da classe {@link br.dell.modelos.Hospede} por meio da interação com o usuário. Após isso é instanciado um 
 * quarto para o hospede. 
 * 
 * @author Wendell Leão de Oliveira
 * @version 4
 */
public class Principal {
	/**
	 * Este é o método principal
	 */
	public static void main(String[] args) {
		/**A interface Map parece ser a mais adequeada para o caso, 
		 * pois com ela é possível associar o número do quarto com 
		 * o nome do hóspede apenas passando como parâmetro dois 
		 * atributos no método put.*/
		HashMap<Integer, Hospede> mapa = new HashMap<Integer, Hospede>();

		Hospede hospede1 = new Hospede(528641349, 265498647, "Cláudio Soares", 58, "Rua das Pamonhas");
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
				JOptionPane.showMessageDialog(null, "Foi informado um valor de entrada inválido! Insira apenas números.", 
						null, JOptionPane.ERROR_MESSAGE);
			}
		}
		
		while(true) {			
			try {
				hospede.setRG(Long.parseLong(JOptionPane.showInputDialog("Digite seu RG:")));
				break;
			}catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null, "Foi informado um valor de entrada inválido! Insira apenas números.", 
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
				JOptionPane.showMessageDialog(null, "Foi informado um valor de entrada inválido!", 
						null, JOptionPane.ERROR_MESSAGE);
			}
		}
		
		hospede.setEndereco(JOptionPane.showInputDialog("Digite seu endereço:"));
		
		JOptionPane.showMessageDialog(null, "Você foi cadastrado com sucesso!");
		
		int numeroQuarto;
		while(true) {			
			try {
				numeroQuarto = Integer.parseInt(JOptionPane.showInputDialog("Agora digite o número do seu quarto:"));
				break;
			}catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null, "Foi informado um valor de entrada inválido!", 
						null, JOptionPane.ERROR_MESSAGE);
			}
		}
		
		Quarto quarto = new Quarto(numeroQuarto, hospede);
		mapa.put(numeroQuarto, hospede);
		
		imprimirInformacoesCadastro(hospede, quarto.getNumero());
		encontrarHospedePeloNumeroQuarto(mapa);
	}
	
	/**
	 * Este método procura no mapa qual hospede está associado com o número do
	 * quarto inserido pelo usuário.
	 * @param mapa é uma instância do hashmap. 
	 */
	public static void encontrarHospedePeloNumeroQuarto(HashMap<Integer, Hospede> mapa) {
		
		int numeroQuarto;
		
		while(true) {			
			try {
				numeroQuarto = Integer.parseInt(JOptionPane.showInputDialog("Digite o número de um quarto para saber quem está o hospedando:"));
				break;
			}catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null, "Foi informado um valor de entrada inválido!", 
						null, JOptionPane.ERROR_MESSAGE);
			}
		}
		
		if(mapa.get(numeroQuarto) == null) {
			JOptionPane.showMessageDialog(null, "Não existe hospede associado ao quarto " + numeroQuarto + ".");
		}else {
			imprimirInformacoesCadastro(mapa.get(numeroQuarto));
		}
	}
	
	/**
	 * Este método imprime e mostra ao usuário as informações inseridas no cadastro. 
	 * @param hospede instância de um hospede do hotel
	 * @param numeroQuarto número do quarto inserido pelo usuário no cadastro
	 */
	public static void imprimirInformacoesCadastro(Hospede hospede, int numeroQuarto) {
		JOptionPane.showMessageDialog(null, 
				"Nome: " + hospede.getNome() + 
				"\nCPF: " + hospede.getCPF() + 
				"\nRG: " + hospede.getRG() + 
				"\nIdade: " + hospede.getIdade() + 
				"\nEndereço: " + hospede.getEndereco() + 
				"\nNúmero do quarto: " + numeroQuarto);
	}
	
	/**
	 * Este método imprime e mostra ao usuário as informações inseridas no cadastro 
	 * menos o número do quarto. 
	 * @param hospede instância de um hospede do hotel
	 */
	public static void imprimirInformacoesCadastro(Hospede hospede) {
		JOptionPane.showMessageDialog(null, 
				"Nome: " + hospede.getNome() + 
				"\nCPF: " + hospede.getCPF() + 
				"\nRG: " + hospede.getRG() + 
				"\nIdade: " + hospede.getIdade() + 
				"\nEndereço: " + hospede.getEndereco());
	}
	
	/**
	 * Este método verifica se o nome inserido pelo usuário possui caracteres especiais.
	 * @param nome inserido pelo usuário para fazer a verificação.
	 * @return retorna uma booleana. Se houver caracteres especiais ele retorna falso e 
	 * se não houver retorna verdadeiro. 
	 */
	public static boolean validaNome(String nome) {
		Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
		Matcher matcher = pattern.matcher(nome);

		boolean contemCaracterEspecial = matcher.find();
		boolean contemNumero = nome.matches(".*\\d.*");

		if (contemCaracterEspecial|| contemNumero) {
			System.out.println(nome + " contém caracter especial");
			return false;
		}
		else {
			System.out.println(nome + " não contém caracter especial");
			return true;
		}
	}
}

