package com.larissa.projeto.service;

import com.larissa.projeto.model.Produto;
import com.larissa.projeto.repository.EstoqueRepository;

import java.sql.SQLException;
import java.util.List;

public class EstoqueService {
    private final EstoqueRepository estoqueRepository = new EstoqueRepository();

    public boolean verificarEstoque(int produtoId, int quantidadeDesjada) throws SQLException {
        List<Produto> produtos = estoqueRepository.listarProdutos();
        for (Produto produto : produtos) {
            if (produto.getId() == produtoId) {
                return produto.getQuantidade() >= quantidadeDesjada;
            }
        }
        return false; //Caso o produto não seja encontrado
    }

    public void atualizarEstoque(int produtoId, int novaQuantidade) throws SQLException {
        estoqueRepository.atualizarQuantidade(produtoId, novaQuantidade);
    }

    public List<Produto> listarProdutos() throws SQLException {
        return estoqueRepository.listarProdutos();
    }
}
