package com.larissa.projeto;

import com.larissa.projeto.model.Produto;
import com.larissa.projeto.repository.EstoqueRepository;


import java.sql.Connection;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        EstoqueRepository estoqueRepo = new EstoqueRepository();

        try {
            // Adicionar produto
            Produto produto = new Produto(0, "Notebook", 3000.00, 10, "Eletrônicos");
            estoqueRepo.adicionarProduto(produto);

            // Listar produtos
            estoqueRepo.listarProdutos().forEach(p -> {
                System.out.println("Produto: " + p.getNome() + " | Quantidade: " + p.getQuantidade());
            });

        } catch (SQLException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}