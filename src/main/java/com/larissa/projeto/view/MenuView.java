package com.larissa.projeto.view;

import com.larissa.projeto.controller.CarrinhoController;
import com.larissa.projeto.controller.EstoqueController;
import com.larissa.projeto.model.Produto;

import java.util.Scanner;

public class MenuView {

    private final EstoqueController estoqueService = new EstoqueController();
    private final CarrinhoController carrinhoService = new CarrinhoController();

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
            System.out.println("6. Adicionar produto ao estoque");
            System.out.println("7. Remover produto do estoque");
            System.out.println("\n 0. Sair \n");
            System.out.print("Escolha uma opção: ");

            String entrada = scanner.nextLine();

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
                    case 6 -> adicionarProdutoAoEstoque(scanner);
                    case 7 -> removerProdutoDoEstoque(scanner);
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
            System.out.print("* Adicionar produtos ao seu carrinho *\n");
            listarProdutosNoEstoque();
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
            System.out.print("* Remover produtos do seu carrinho *\n");
            listarProdutosNoCarrinho();
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
            System.out.println("\n=== Produtos no Carrinho ===");
            System.out.printf("%-5s %-20s %-15s %-15s%n", "ID", "Nome", "Quantidade", "Valor Total");
            System.out.println("------------------------------------------------------------");
            carrinhoService.listarCarrinho().forEach(carrinho -> {
                System.out.printf("%-5d %-20s %-15d %-15.2f%n",
                        carrinho.getId(),
                        carrinho.getNome(),
                        carrinho.getQuantidade(),
                        carrinho.getValorTotal());
            });
        } catch (Exception e) {
            System.err.println("Erro ao listar produtos no carrinho: " + e.getMessage());
        }
    }

    private void adicionarProdutoAoEstoque(Scanner scanner) {
        try {
            System.out.print("* Adicionar produto ao estoque *\n");
            System.out.print("Digite o nome do produto: ");
            String nome = scanner.nextLine();

            System.out.print("Digite a categoria do produto: ");
            String categoria = scanner.nextLine();

            System.out.print("Digite o valor do produto: ");
            double valor = Double.parseDouble(scanner.nextLine());

            System.out.print("Digite a quantidade do produto: ");
            int quantidade = Integer.parseInt(scanner.nextLine());

            Produto produto = new Produto(0, nome, valor, quantidade, categoria);
            estoqueService.adicionarProduto(produto);

            System.out.println("Produto adicionado ao estoque com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println("Erro: Entrada inválida. Certifique-se de digitar valores numéricos para valor e quantidade.");
        } catch (Exception e) {
            System.err.println("Erro ao adicionar produto ao estoque: " + e.getMessage());
        }
    }

    private void listarProdutosNoEstoque() {
        try {
            System.out.println("\n=== Produtos no Estoque ===");
            System.out.printf("%-5s %-20s %-15s %-10s %-10s%n", "ID", "Nome", "Categoria", "Valor", "Quantidade");
            System.out.println("---------------------------------------------------------------");
            estoqueService.listarEstoque().forEach(produto -> {
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

    private void removerProdutoDoEstoque(Scanner scanner) {
        try {
            System.out.print("* Remover produto do estoque *\n");
            listarProdutosNoEstoque();

            System.out.print("Digite o ID do produto a ser removido: ");
            int produtoId = Integer.parseInt(scanner.nextLine());

            estoqueService.removerProduto(produtoId);

            System.out.println("Produto removido do estoque com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println("Erro: Entrada inválida. Certifique-se de digitar um ID válido.");
        } catch (Exception e) {
            System.err.println("Erro ao remover produto do estoque: " + e.getMessage());
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
