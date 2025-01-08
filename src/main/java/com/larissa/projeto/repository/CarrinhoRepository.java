package com.larissa.projeto.repository;

import com.larissa.projeto.model.Carrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarrinhoRepository {

    // Método para adicionar um produto ao carrinho
    public void adicionarAoCarrinho(Carrinho carrinho) throws SQLException {
        String sql = "INSERT INTO carrinho (nome, categoria, valor, quantidade, valor_total) VALUES (?, ?, ?, ?, ?)";
        try (Connection conectar = Conexao.conectar(); PreparedStatement stmt = conectar.prepareStatement(sql)) {
            stmt.setString(1, carrinho.getNome());
            stmt.setString(2, carrinho.getCategoria());
            stmt.setDouble(3, carrinho.getValor());
            stmt.setInt(4, carrinho.getQuantidade());
            stmt.setDouble(5, carrinho.getValorTotal());
            stmt.executeUpdate();
        }
    }

    // Método para listar todos os produtos do carrinho
    public List<Carrinho> listarCarrinho() throws SQLException {
        String sql = "SELECT * FROM carrinho";
        List<Carrinho> carrinhos = new ArrayList<>();
        try (Connection conectar = Conexao.conectar(); PreparedStatement stmt = conectar.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Carrinho carrinho = new Carrinho(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("categoria"),
                        rs.getDouble("valor"),
                        rs.getInt("quantidade"),
                        rs.getDouble("valor_total")
                );
                carrinhos.add(carrinho);
            }
        }
        return carrinhos;
    }

    // Método para remover um produto do carrinho
    public void atualizarQuantidade(int id, int novaQuantidade, double novoValorTotal) throws SQLException {
        String sql = "UPDATE carrinho SET quantidade = ?, valor_total = ? WHERE id = ?";
        try (Connection conectar = Conexao.conectar(); PreparedStatement stmt = conectar.prepareStatement(sql)) {
            stmt.setInt(1, novaQuantidade);
            stmt.setDouble(2, novoValorTotal);
            stmt.setInt(3, id);
            stmt.executeUpdate();
        }
    }

    // Método para remover um produto do carrinho
    public void removerDoCarrinho(int id) throws SQLException {
        String sql = "DELETE FROM carrinho WHERE id = ?";
        try (Connection conectar = Conexao.conectar(); PreparedStatement stmt = conectar.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
