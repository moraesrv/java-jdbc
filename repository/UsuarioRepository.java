package repository;

import entity.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por estabelecer uma interface com o banco de dados.
 * Através dessa classe é possível realizar as seguintes operações:
 * - Inserir um usuário
 * - Atualizar um usuário
 * - Deletar um usuário
 * - Consultar um usuário
 * - Consultar todos os usuários
 */
public class UsuarioRepository {

	/**
	 * Contém os dados de conexão com o SGBD:
	 * - jdbc: biblioteca java para conexão com o SGBD
	 * - mysql: a conexão será realiza com um banco MySQL
	 * - localhost: indica que o banco está disponível na sua máquina local
	 * - 3306: porta na qual está rodando o serviço de banco do MySQL
	 * - unifacear: nome da base de dados/schema 
	 */
	private final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/unifacear";
	
	// usuário do banco de dados
	private final String USUARIO = "root";
	
	// senha do banco de dados
	private final String SENHA = "root";

	// Contém a conexão que foi estabelecida com o banco de dados
	private Connection connection;
	
	public UsuarioRepository() {
		try {
			// Tenta estabelecer conexão com o banco de dados
			this.connection = DriverManager.getConnection(
				CONNECTION_STRING,
				USUARIO,
				SENHA
			);
			
			// Verifica se a conexão foi estabelecida com o servidor
			if (!this.connection.isClosed()) {
				System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
			} else {
				System.out.println("Não foi possível estabelecer conexão com o banco de dados");
			}
		} catch (SQLException e) {
			System.out.println("Não foi possível estabelecer conexão com o banco de dados");
			e.printStackTrace();
		}
	}
	
	/**
	 * Método para inserir um usuário
	 * @param dados do usuário
	 */
	public void inserir(Usuario usuario) {
		String comandoSQL = "insert into TB_Usuario (id_usuario, nome, cpf) values (?, ?, ?);";
		try {
			// Cria um comando SQL
			PreparedStatement ps = connection.prepareStatement(comandoSQL);
			// Atribui valores ao comando SQL
			ps.setInt(1, usuario.getId());
			ps.setString(2, usuario.getNome());
			ps.setString(3, usuario.getCpf());
			// Executa o comando SQL
			ps.execute();
			
			System.out.println(String.format("Usuário inserido com sucesso: %s", usuario.imprimir()));
			
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao tentar inserir o usuário");
			e.printStackTrace();
		}
	}
	
	/**
	 * Método para atualizar os dados do usuário
	 * @param dados do usuário
	 */
	public void atualizar(Usuario usuario) {
		String comandoSQL = "update TB_Usuario set nome = ?, cpf = ? where id_usuario = ?;";
		try {
			// Cria um comando SQL
			PreparedStatement ps = connection.prepareStatement(comandoSQL);
			// Atribui valores ao comando SQL
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getCpf());
			ps.setInt(3, usuario.getId());
			// Executa o comando SQL
			ps.execute();
			
			System.out.println(String.format("Usuário atualizado com sucesso: %s", usuario.imprimir()));
			
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao tentar atualizar o usuário");
			e.printStackTrace();
		}
	}
	
	/**
	 * Método para deletar um usuário pelo id
	 * @param id identificação do usuário
	 */
	public void remover(Integer id) {
		String comandoSQL = "delete from tb_usuario where id_usuario = ?;";
		try {
			// Cria o comando SQL
			PreparedStatement ps = connection.prepareStatement(comandoSQL);
			// Atribui valores ao comando SQL
			ps.setInt(1, id);
			// Executa o comando SQL
			ps.execute();
			
			System.out.println(String.format("Usuário deletado com sucesso: %d", id));
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao tentar deletar o usuário");
			e.printStackTrace();
		}
	}
	
	/**
	 * Método para consultar um usuário pelo id
	 * @param id identificação do usuário
	 * @return dados do usuário
	 */
	public Usuario pesquisarPeloId(Integer id) {
		Usuario usuario = null;
		String comandoSQL = "select id_usuario, nome, cpf from tb_usuario where id_usuario = ?;";
		try {
			PreparedStatement ps = connection.prepareStatement(comandoSQL);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				// Obtém os dados do usuário consultado
				usuario = new Usuario();
				usuario.setId(rs.getInt(1));
				usuario.setNome(rs.getString(2));
				usuario.setCpf(rs.getString(3));
			}
			System.out.println(String.format("Usuários encontrado na base de dados. Id: %d", id));
		} catch (SQLException e) {
			System.out.println(String.format("Ocorreu um erro ao pesquisar o usuário. Id: %d", id));
			e.printStackTrace();
		}
		return usuario;
	}
	
	/**
	 * Método para consultar todos os usuários
	 * @return lista de usuários
	 */
	public List<Usuario> pesquisarTodos() {
		List<Usuario> usuarios = new ArrayList<>();

		String comandoSQL = "select id_usuario, nome, cpf from tb_usuario;";
		try {
			PreparedStatement ps = connection.prepareStatement(comandoSQL);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				// Obtém os dados do usuário
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt(1));
				usuario.setNome(rs.getString(2));
				usuario.setCpf(rs.getString(3));
				// Adiciona o usuário na lista
				usuarios.add(usuario);
			}
			System.out.println("Usuários pesquisados com sucesso!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao pesquisar todos os usuários");
			e.printStackTrace();
		}
		return usuarios;
	}

}