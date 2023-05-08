package principal.view;

import principal.modelo.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
	
	private List<Cliente> listaClientes;
	private Catalogo catalogo;
	private Scanner leitorOperador;
	
	public Menu(Catalogo catalogo, List<Cliente> clientes) {
		this.catalogo = catalogo;
		this.listaClientes = clientes;
		this.leitorOperador = new Scanner(System.in);
	}
	
	public void mostrarMenu() {
		
		System.out.println("Bem-vindo à Biblioteca!");
		while(true) { 
			System.out.println("Qual menu deseja acessar:");
			System.out.println("1. Realizar emprestimo");
			System.out.println("2. Menu de Clientes");
			System.out.println("3. Menu de Livros");
			System.out.println("4. Devolução");
			System.out.println("5. Sair");
			int opcao = leitorOperador.nextInt();
			switch (opcao) {
              	case 1:
              		menuEmprestimos();
              		break;
              	case 2:
              		menuClientes();
              		break;
              	case 3:
              		menuLivros();
              		break;
              	case 4:
              		devolverLivro();
              		break;
              	case 5:
              		leitorOperador.close();
              		
              		return;
              default:
                  System.out.println("Opção inválida!");
          }
		}
    }
	
	private void menuClientes() {
		while(true) { 
			System.out.println("Menu de Clientes:");
			System.out.println("1. Listar Clientes");
			System.out.println("2. Adicionar Cliente");
			System.out.println("3. Sair");
			int opcao = leitorOperador.nextInt();
			switch (opcao) {
            	case 1:
            		for(Cliente c : listaClientes) {
            			System.out.println("Nome: " + c.getNome());
            			if(c.getRetirado()>=0) {
            				System.out.println("Livro Retirado: " + catalogo.getLivro(c.getRetirado()).getNome());
            			}
            			else {
            				System.out.println("Sem Livro Retirado");
            			}
            			if(c.getEspera()>=0) {
            				System.out.println("Livro em Espera: " + catalogo.getLivro(c.getEspera()).getNome());
            			}
            			else {
            				System.out.println("Sem livro em espera");
            			}
            			System.out.println();
            		}
            		break;
            	case 2:
            		adicionarCliente();
            		break;
            	case 3:
            		return;
            	default:
            		System.out.println("Opção inválida!");
			  }
		}
	}
	
	private void menuEmprestimos() {
		while(true) { 
			System.out.println("Menu de emprestimos:");
			System.out.println("1. Realizar Emprestimo ");
			System.out.println("2. Checar Disponibilidade de Livro");
			System.out.println("3. Checar Fila de Livro");
			System.out.println("4. Sair");
			int opcao = leitorOperador.nextInt();
			switch (opcao) {
              	case 1:
              		realizarEmprestimo();
              		break;
              	case 2:
              		catalogo.printCatalogo();
              		break;
              	case 3:
              		checarFila();
              		break;
              	case 4:
              		return;
              	default:
              		System.out.println("Opção inválida!");
          }
		}
	}
	
	private void menuLivros() {
		
        while (true) {
            System.out.println("Selecione uma opção:");
            System.out.println("1. Mostrar catálogo de livros");
            System.out.println("2. Adicionar um livro");
            System.out.println("3. Sair");

            int opcao = leitorOperador.nextInt();

            switch (opcao) {
                case 1:
                    catalogo.printCatalogo();
                    break;
                case 2:
                    adicionarLivro();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
	}
	
	private void adicionarLivro() {
		String nome;
		int quantidade;
		System.out.println("Insira o nome do livro: ");
		this.leitorOperador.nextLine();
		nome = this.leitorOperador.nextLine();
		System.out.println("Insira quantos livros estarão disponiveis: ");
		quantidade = this.leitorOperador.nextInt();

		catalogo.addLivro(new Livro(catalogo.ultimo() ,nome, quantidade, 0));
	}
	/*
	private void removerLivro() {
		int id;
		System.out.println("Qual o ID do livro que deseja remover:");
		id = this.leitorOperador.nextInt();
		catalogo.removerLivro(id);
	}*/
	
	private void adicionarCliente() {
		String nome;
		System.out.println("Insira o nome do cliente: ");
		this.leitorOperador.nextLine();
		nome = this.leitorOperador.nextLine();
		listaClientes.add(new Cliente(nome, -1, -1));
	}
	
	private void realizarEmprestimo() {
		String nome;
		int id;
		this.leitorOperador.nextLine();
		catalogo.printCatalogo();
		System.out.println("Nome do Cliente: ");
		nome = this.leitorOperador.nextLine();
		System.out.println("Id do livro: ");
		id = this.leitorOperador.nextInt();
		for(Cliente c : listaClientes) {
			if(c.getNome().equals(nome)) {
				c.retirarLivro(catalogo.getLivro(id));
			}
		}
	}
		
	private void checarFila() {
		int id;
		
		catalogo.printCatalogo();
		System.out.println("Qual livro deseja checar: ");
		id = this.leitorOperador.nextInt();
		catalogo.getLivro(id).printFila();
	}
	
	
	private void devolverLivro() {
		String nome;
		this.leitorOperador.nextLine();
		System.out.println("Qual cliente está realizando a devolução");
		nome = this.leitorOperador.nextLine();
		for(Cliente c : listaClientes) {
			if(c.getNome().equals(nome)) {
				if(c.getRetirado()>=0) {
					c.devolverLivro(catalogo.getLivro(c.getRetirado()));
				}
			}
		}
		
	}
	
	
}

