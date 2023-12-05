package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	private static Conexao instancia;
	private static String DRIVER = "org.sqlite.JDBC";
	private static String BD = "jdbc:sqlite:resources/bdclientes";
	private static Connection conexao;
	
	private Conexao() {
		
	}
	
	// Este é um método estático que retorna uma instância da classe Conexao.
	public static Conexao getInstancia() {
	    // Verifica se a instância já foi criada.
	    if(instancia == null) {
	        // Se não foi criada, cria uma nova instância da classe Conexao.
	        instancia  = new Conexao();
	    }
	    // Retorna a instância existente ou recém-criada.
	    return instancia;
	}
	
	/**
	 * Método responsável por abrir uma conexão com o banco de dados.
	 * Utiliza a classe DriverManager para obter uma conexão com as configurações especificadas.
	 * Configura a conexão para desativar o modo de commit automático.
	 *
	 * @return Uma instância de Connection representando a conexão com o banco de dados.
	 *         Null se ocorrer uma exceção durante a tentativa de conexão.
	 */
	public Connection abrirConexao() {
	    try {
	        // Carrega o driver JDBC necessário para a comunicação com o banco de dados.
	        Class.forName(DRIVER);

	        // Estabelece uma conexão com o banco de dados usando a URL especificada.
	        conexao = DriverManager.getConnection(BD);

	        // Configura a conexão para desativar o modo de commit automático.
	        conexao.setAutoCommit(false);
	    } catch (SQLException | ClassNotFoundException e) {
	        // Captura exceções relacionadas a falhas na conexão e exibe uma mensagem de erro.
	        System.out.println("Erro ao conectar com o Banco de Dados: " + e.getMessage());
	    }

	    // Retorna a instância de Connection estabelecida ou null se ocorreu uma exceção.
	    return conexao;
	}

	
	/**
	 * Método responsável por fechar a conexão com o banco de dados, se estiver aberta.
	 * Também atribui null à variável de conexão para indicar que não há mais uma conexão ativa.
	 * Este método é geralmente chamado ao finalizar uma transação ou ao encerrar a aplicação.
	 */
	public void fecharConexao() {
	    try {
	        // Verifica se a conexão está aberta antes de tentar fechá-la.
	        if (conexao != null && !conexao.isClosed()) {
	            // Fecha a conexão com o banco de dados.
	            conexao.close();
	        }
	    } catch (SQLException e) {
	        // Captura exceções relacionadas a erros no fechamento da conexão e exibe uma mensagem de erro.
	        System.out.println("Erro ao fechar a Conexao: " + e.getMessage());
	    } finally {
	        // Garante que mesmo em caso de exceção, a conexão é marcada como nula.
	        conexao = null;
	    }
	}
}
