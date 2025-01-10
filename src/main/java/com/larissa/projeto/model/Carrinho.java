package com.larissa.projeto.model;

public class Carrinho {
    private int id;
    private String nome;
    private String categoria;
    private double valor;
    private int quantidade;
    private double valorTotal;

    public Carrinho(int id, String nome, String categoria, double valor, int quantidade, double valorTotal) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.valor = valor;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
