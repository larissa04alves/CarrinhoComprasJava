package com.larissa.projeto.controller;

import com.larissa.projeto.model.Carrinho;
import com.larissa.projeto.model.Produto;
import com.larissa.projeto.repository.CarrinhoRepository;

import java.sql.SQLException;
import java.util.List;

public class CarrinhoController {
    private final CarrinhoRepository carrinhoRepository = new CarrinhoRepository();
    private final EstoqueController estoqueService = new EstoqueController();


    public void adicionarProdutoCarrinho(int produtoId, int quantidade) throws SQLException {
        if (!estoqueService.verificarEstoque(produtoId, quantidade)) {
            throw new IllegalArgumentException("Estoque insuficiente para o produto ID: " + produtoId);
        }

        List<Produto> produtos = estoqueService.listarEstoque();
        Produto produtoEscolhido = produtos.stream()
                .filter(p -> p.getId() == produtoId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        double valorTotal = produtoEscolhido.getValor() * quantidade;
        Carrinho carrinho = new Carrinho(0, produtoEscolhido.getNome(), produtoEscolhido.getCategoria(),
                produtoEscolhido.getValor(), quantidade, valorTotal);

        carrinhoRepository.adicionarAoCarrinho(carrinho);
        estoqueService.atualizarEstoque(produtoId, produtoEscolhido.getQuantidade() - quantidade);
    }

    public void removerProdutoCarrinho(int carrinhoId) throws SQLException {
        List<Carrinho> carrinhos = carrinhoRepository.listarCarrinho();
        Carrinho carrinhoEscolhido = carrinhos.stream()
                .filter(c -> c.getId() == carrinhoId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Item não encontrado no carrinho"));

        List<Produto> produtos = estoqueService.listarEstoque();
        Produto produtoAtualizado = produtos.stream()
                .filter(p -> p.getNome().equals(carrinhoEscolhido.getNome()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado no estoque"));

        estoqueService.atualizarEstoque(produtoAtualizado.getId(),
                produtoAtualizado.getQuantidade() + carrinhoEscolhido.getQuantidade());

        // Remover do carrinho
        carrinhoRepository.removerDoCarrinho(carrinhoId);
    }

    public List<Carrinho> listarCarrinho() throws SQLException {
        return carrinhoRepository.listarCarrinho();
    }

    public double calcularValorTotal() throws SQLException {
        List<Carrinho> carrinhos = listarCarrinho();
        return carrinhos.stream()
                .mapToDouble(Carrinho::getValorTotal)
                .sum();
    }
}

