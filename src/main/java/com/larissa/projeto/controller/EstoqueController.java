package com.larissa.projeto.controller;

import com.larissa.projeto.model.Produto;
import com.larissa.projeto.repository.EstoqueRepository;

import java.sql.SQLException;
import java.util.List;

public class EstoqueController {
    private final EstoqueRepository estoqueRepository = new EstoqueRepository();

    public void adicionarProduto(Produto produto) throws SQLException {
        estoqueRepository.adicionarProduto(produto);
    }

    public void removerProduto(int produtoId) throws SQLException {
        estoqueRepository.removerProduto(produtoId);
    }

    public boolean verificarEstoque(int produtoId, int quantidadeDesejada) throws SQLException {
        List<Produto> produtos = estoqueRepository.listarProdutos();
        for (Produto produto : produtos) {
            if (produto.getId() == produtoId) {
                return produto.getQuantidade() >= quantidadeDesejada;
            }
        }
        return false; // Produto não encontrado ou quantidade insuficiente
    }

    public void atualizarEstoque(int produtoId, int novaQuantidade) throws SQLException {
        estoqueRepository.atualizarQuantidade(produtoId, novaQuantidade);
    }

    public List<Produto> listarEstoque() throws SQLException {
        return estoqueRepository.listarProdutos();
    }
}