package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Contato;

public class ContatoDAO {
	
	public static void inserirContatoDAO(Connection conexao, Contato con) throws SQLException {
		System.out.println("Inserindo dados do contato!");
		
		String comandoInsert = "INSERT INTO contato (nome, cpf, telefone, email)  values (?, ?, ?, ?)";
		
		PreparedStatement pstm = conexao.prepareStatement(comandoInsert);
		
		pstm.setString(1, con.getNome());
		pstm.setString(2, con.getCpf());
		pstm.setString(3, con.getTelefone());
		pstm.setString(4, con.getEmail());
		
		pstm.execute();
		
		System.out.println("Contato cadastrado com sucesso!");
		
	}
	
	public List<Contato> pesquisarPorCpfDAO(Connection conexao, String cpf) throws SQLException {
		
		String comandodePesquisa = "SELECT * FROM contato WHERE cpf = ?";
		
		List<Contato> listaDeContato = new ArrayList<>();
		
		try(PreparedStatement pstm = conexao.prepareStatement(comandodePesquisa)) {
		
		pstm.setString(1, cpf);
		
		ResultSet resultadoPesquisa = pstm.executeQuery();
		
		while(resultadoPesquisa.next()) {
			
		Contato contato = new Contato();
			
			contato.setCodigo(resultadoPesquisa.getInt("codigo"));
			contato.setNome(resultadoPesquisa.getString("nome"));
			contato.setCpf(resultadoPesquisa.getString("cpf"));
			contato.setTelefone(resultadoPesquisa.getString("telefone"));
			contato.setEmail(resultadoPesquisa.getString("email"));
			listaDeContato.add(contato);
		}
	}
		
		return listaDeContato;
	
	}
		
	public static void excluirContatoDAO(Connection conexao, int codigo) throws SQLException {
		String comandoExcluir = "DELETE FROM contato where codigo = ?";
		
		try(PreparedStatement pstm = conexao.prepareStatement(comandoExcluir)) {
			pstm.setInt(1, codigo);
			
			int linhasAfetadas = pstm.executeUpdate();
			
			if(linhasAfetadas > 0) {
				System.out.println("Contato excluido com sucesso!");
			}else {
				System.out.println("Nenhum contato foi encontrado com o código fornecido.");
				
			
				
			}
		}
		
	}
}
