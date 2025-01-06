package com.larissa.projeto;

import com.larissa.projeto.repository.Conexao;

import java.sql.Connection;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try (Connection conn = Conexao.conectar()) {
            if (conn != null) {
                System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco: " + e.getMessage());
        }
    }
}