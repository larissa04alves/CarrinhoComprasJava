package com.larissa.projeto.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:postgresql://localhost:5432/carrinho_db";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "postgres";

    /**
     * Estabelece uma conexão com o banco de dados PostgreSQL.
     *
     * @return Objeto Connection para interagir com o banco de dados.
     * @throws SQLException Caso ocorra um erro ao tentar conectar.
     */
    public static Connection conectar() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver do PostgreSQL não encontrado. Verifique se a dependência está configurada.", e);
        } catch (SQLException e) {
            throw new SQLException("Erro ao conectar ao banco de dados. Verifique as credenciais e URL.", e);
        }
    }
}
