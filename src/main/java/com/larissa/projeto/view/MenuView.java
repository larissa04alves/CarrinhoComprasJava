package com.larissa.projeto.view;

import com.larissa.projeto.service.CarrinhoService;
import com.larissa.projeto.service.EstoqueService;

import java.util.Scanner;

public class MenuView {

    private final EstoqueService estoqueService = new EstoqueService();
    private final CarrinhoService carrinhoService = new CarrinhoService();

    public void exibirMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n=== Carrinho de Compras ===");
            System.out.println("1. Adicionar produto ao carrinho");
            System.out.println("2. Listar produtos no carrinho");
            System.out.println("3. Remover produto do carrinho");
            System.out.println("4. Calcular valor total do carrinho");
            System.out.println("\n=== Gerenciamento do estoque ===");
            System.out.println("5. Listar produtos no estoque");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            String entrada = scanner.nextLine();

            // Valida a entrada para garantir que é um número
            if (!entrada.matches("\\d+")) {
                System.out.println("Opção inválida! Digite um número válido.");
                continue;
            }

            int opcao = Integer.parseInt(entrada);

            try {
                switch (opcao) {
                    case 1 -> adicionarProdutoAoCarrinho(scanner);
                    case 2 -> listarProdutosNoCarrinho();
                    case 3 -> removerProdutoDoCarrinho(scanner);
                    case 4 -> calcularValorTotal();
                    case 5 -> listarProdutosNoEstoque();
                    case 0 -> {
                        continuar = false;
                        System.out.println("Encerrando o programa...");
                    }
                    default -> System.out.println("Opção inválida! Escolha entre 0 e 5.");
                }
            } catch (Exception e) {
                System.err.println("Erro: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private void adicionarProdutoAoCarrinho(Scanner scanner) {
        try {
            System.out.print("Digite o ID do produto: ");
            int produtoId = Integer.parseInt(scanner.nextLine());

            System.out.print("Digite a quantidade: ");
            int quantidade = Integer.parseInt(scanner.nextLine());

            carrinhoService.adicionarProdutoCarrinho(produtoId, quantidade);
            System.out.println("Produto adicionado ao carrinho com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println("Erro: Entrada inválida. Certifique-se de digitar números inteiros.");
        } catch (Exception e) {
            System.err.println("Erro ao adicionar produto ao carrinho: " + e.getMessage());
        }
    }

    private void removerProdutoDoCarrinho(Scanner scanner) {
        try {
            System.out.print("Digite o ID do item no carrinho: ");
            int carrinhoId = Integer.parseInt(scanner.nextLine());

            carrinhoService.removerProdutoCarrinho(carrinhoId);
            System.out.println("Produto removido do carrinho com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println("Erro: Entrada inválida. Certifique-se de digitar números inteiros.");
        } catch (Exception e) {
            System.err.println("Erro ao remover produto do carrinho: " + e.getMessage());
        }
    }

    private void listarProdutosNoCarrinho() {
        try {
            carrinhoService.listarCarrinho().forEach(carrinho -> {
                System.out.println("ID: " + carrinho.getId() +
                        ", Nome: " + carrinho.getNome() +
                        ", Quantidade: " + carrinho.getQuantidade() +
                        ", Valor Total: " + carrinho.getValorTotal());
            });
        } catch (Exception e) {
            System.err.println("Erro ao listar produtos no carrinho: " + e.getMessage());
        }
    }

    private void listarProdutosNoEstoque() {
        try {
            System.out.println("\n=== Produtos no Estoque ===");
            System.out.printf("%-5s %-20s %-15s %-10s %-10s%n", "ID", "Nome", "Categoria", "Valor", "Quantidade");
            System.out.println("---------------------------------------------------------------");
            estoqueService.listarProdutos().forEach(produto -> {
                System.out.printf("%-5d %-20s %-15s %-10.2f %-10d%n",
                        produto.getId(),
                        produto.getNome(),
                        produto.getCategoria(),
                        produto.getValor(),
                        produto.getQuantidade());
            });
        } catch (Exception e) {
            System.err.println("Erro ao listar produtos no estoque: " + e.getMessage());
        }
    }

    private void calcularValorTotal() {
        try {
            double total = carrinhoService.calcularValorTotal();
            System.out.printf("Valor total do carrinho: R$ %.2f%n", total);
        } catch (Exception e) {
            System.err.println("Erro ao calcular o valor total do carrinho: " + e.getMessage());
        }
    }
}
//teste