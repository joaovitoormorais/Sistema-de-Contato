package br.com.contato.projeto.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import dao.ContatoDAO;
import models.Contato;

public class Aplicacao {

	public static void main(String[] args) {
		
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String username = "postgres";
		String password = "220504";
		
		 try (Connection conexao = DriverManager.getConnection(url, username, password)) {
	            System.out.println("Conectado com sucesso no banco de dados contato!");

	            ContatoDAO conDAO = new ContatoDAO();
	            
	            Scanner scanMenuContato = new Scanner(System.in);
	            int menuContato;
	            
	            do {
	            	System.out.println("\nEscolha as opções do menu:");
	            	System.out.println("1- Cadastrar contato");
	            	System.out.println("2- Listar contato");
	            	System.out.println("3- Excluir contato");
	            	System.out.println("4- Sair");
	            	
	            	menuContato = scanMenuContato.nextInt();
	            	
	            	scanMenuContato.nextLine();
	            	
	            	System.out.println("\nA opção escolhida foi:" + menuContato);
	            	
	            	switch(menuContato) {
	            		
	            	case 1:
	            		 System.out.println("-------------------------");
	            		 System.out.println("Digite seu nome:");
	            		 String nome = scanMenuContato.nextLine();
	            		 
	            		 System.out.println("Digite seu cpf:");
	            		 String cpf = scanMenuContato.nextLine();
	            		 
	            		 System.out.println("Digite seu telefone:");
	            		 String telefone = scanMenuContato.nextLine();
	            		 
	            		 System.out.println("Digite seu email:");
	            		 String email = scanMenuContato.nextLine();
	            		 
	            		 Contato con = new Contato();
	            		 
	            		 con.setNome(nome);;
	            		 con.setCpf(cpf);
	            		 con.setTelefone(telefone);
	            		 con.setEmail(email);
	            		 
	            		 conDAO.inserirContatoDAO(conexao, con);
	            		 
	            		 System.out.println("Contato inserido com sucesso!");
	            		 break;
	            		 
	            	case 2:
	            		 System.out.println("Digite seu cpf:");
	            		String cpfPesquisa = scanMenuContato.nextLine();
	            		
	            
	            	
	            	List<Contato> contato = conDAO.pesquisarPorCpfDAO(conexao, cpfPesquisa);
	            		 
	            	if(contato.isEmpty()) {
	            		System.out.println("Nenhum contato encontrado com o Cpf fornecido!");
	            	}else {
	            		System.out.println("Contatos encontrados:");
	            		for(Contato c : contato) {
	            			System.out.println("Código: " + c.getCodigo() +
	            	                   " | Nome: " + c.getNome() +
	            	                   " | CPF: " + c.getCpf() +
	            	                   " | Telefone: " + c.getTelefone() +
	            	                   " | Email: " + c.getEmail());
	            		}
	            	}
	            	
	            	break;
	            		 
	            	case 3:
	            	System.out.println("Digite o código que você quer excluir:");
	            	int codigo = scanMenuContato.nextInt();
	            	
	            	conDAO.excluirContatoDAO(conexao, codigo);
	            	
	            	System.out.println("Contato excluido com sucesso!");
	            		break;
	            	
	            	case 4:
	            		System.out.println("Encerrando o programa...");
	            		 break;
	            		 
	            		 default:
	            			 System.out.println("Opção inválida, tente novamente.");
	            	}
	            
	            }while(menuContato != 4);
	            
	            	
	            }catch (SQLException e) {
					System.err.println("Erro ao conectar com o banco de daods!" + e.getMessage());
				}
	            
		 }
		 
	}