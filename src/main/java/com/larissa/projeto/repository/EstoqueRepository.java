package com.larissa.projeto.repository;

import com.larissa.projeto.model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstoqueRepository {

    //Adiciona um produto ao estoque
    public void adicionarProduto(Produto produto) throws SQLException {
        String sql = "INSERT INTO estoque (nome, valor, quantidade, categoria) VALUES (?, ?, ?, ?)";
        try (Connection conectar = Conexao.conectar(); PreparedStatement stmt = conectar.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getValor());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setString(4, produto.getCategoria());
            stmt.executeUpdate();
        }
    }

    //Lista todos os produtos do estoque
    public List<Produto> listarProdutos() throws SQLException {
        String sql = "SELECT * FROM estoque";
        List<Produto> produtos = new ArrayList<>();
        try (Connection conectar = Conexao.conectar(); PreparedStatement stmt = conectar.prepareStatement(sql); ResultSet rs = stmt.executeQuery();){
                while (rs.next()) {
                    Produto produto = new Produto(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getDouble("valor"),
                            rs.getInt("quantidade"),
                            rs.getString("categoria")
                    );
                    produtos.add(produto);
                }
        }
        return produtos;
    }

    //Atualiza a quantidade de um produto no estoque
    public void atualizarQuantidade(int id, int novaquantidade) throws SQLException {
        String sql = "UPDATE estoque SET quantidade = ? WHERE id = ?";
        try (Connection conectar = Conexao.conectar(); PreparedStatement stmt = conectar.prepareStatement(sql)) {
            stmt.setInt(1, novaquantidade);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }

    //Remove um produto do estoque
    public void removerProduto(int id) throws SQLException {
        String sql = "DELETE FROM estoque WHERE id = ?";
        try (Connection conectar = Conexao.conectar(); PreparedStatement stmt = conectar.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
