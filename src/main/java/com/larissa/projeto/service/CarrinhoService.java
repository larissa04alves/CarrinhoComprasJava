package com.larissa.projeto.service;

import com.larissa.projeto.model.Carrinho;
import com.larissa.projeto.model.Produto;
import com.larissa.projeto.repository.CarrinhoRepository;

import java.sql.SQLException;

public class CarrinhoService {
    private final CarrinhoRepository carrinhoRepository = new CarrinhoRepository();
    private final EstoqueService estoqueService = new EstoqueService();

    public void adicionarProdutoCarrinho(int produtoId, int quantidade) throws SQLException{
        if (!estoqueService.verificarEstoque(produtoId, quantidade)){
            throw new IllegalArgumentException("Quantidade indisponível no estoque");
        }


    }
}
