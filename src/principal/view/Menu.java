package principal.view;

import principal.modelo.*;
import java.util.List;
//import java.util.ArrayList;
import java.util.Scanner;
import principal.daos.*;

public class Menu {
	
	//private List<Cliente> listaClientes;
	//private Catalogo catalogo;
	private Scanner leitorOperador;
	
	/*public Menu(Catalogo catalogo, List<Cliente> clientes) {
		this.catalogo = catalogo;
		this.listaClientes = clientes;
		this.leitorOperador = new Scanner(System.in);
	}*/
	
	public Menu() {
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
			System.out.println("3. Atualizar Cliente");
			System.out.println("4. Apagar Cliente");
			System.out.println("5. Sair");
			int opcao = leitorOperador.nextInt();
			switch (opcao) {
            	case 1:
            		listarCliente();
            		break;
            	case 2:
            		adicionarCliente();
            		break;
            	case 3:
            		atualizarCliente();
            		break;
            	case 4:
            		apagarCliente();
            		break;
            	case 5:
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
              		//catalogo.printCatalogo();
              		listarLivros();
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
            System.out.println("3. Atualizar um livro");
            System.out.println("4. Apagar um livro");
            System.out.println("5. Sair");

            int opcao = leitorOperador.nextInt();

            switch (opcao) {
                case 1:
                    listarLivros();
                    break;
                case 2:
                    adicionarLivro();
                    break;
                case 3:
                    atualizarLivro();
                    break;
                case 4:
                    apagarLivro();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
	}
	
	private void adicionarLivro() {
		LivroDAO livrodao = new LivroDAO();
		
		String nome;
		int quantidade;
		System.out.println("Insira o nome do livro: ");
		this.leitorOperador.nextLine();
		nome = this.leitorOperador.nextLine();
		System.out.println("Insira quantos livros estarão disponiveis: ");
		quantidade = this.leitorOperador.nextInt();
		Livro livro = new Livro(nome, quantidade,0);
		livrodao.salvar(livro);
		
		livrodao.close();
		

		//catalogo.addLivro(new Livro(catalogo.ultimo() ,nome, quantidade, 0));
	}
	
	private void atualizarLivro() {
		System.out.println("Insira o ID do livro que deseja atualizar: ");
		int id = this.leitorOperador.nextInt();
		this.leitorOperador.nextLine();
		int qte;
		
		LivroDAO livroDAO = new LivroDAO();
		
		Livro livro = livroDAO.buscarPorId(id);
		
		if (livro != null) {
			System.out.println("Insira o novo nome do livro: ");
			String novoNome = this.leitorOperador.nextLine();
			System.out.println("Insira a quantidade desejada:");
			System.out.println("Quantidade atual: " + livro.getQuantidade());
			qte = this.leitorOperador.nextInt();
			this.leitorOperador.nextLine();
			livro.setQuantidade(qte);
			livro.setNome(novoNome);
			livroDAO.atualizar(livro);
			System.out.println("Livro atualizado com sucesso!");
		} else {
			System.out.println("Livro não encontrado.");
		}
		
		livroDAO.close();
	}
	
	private void apagarLivro() {
		System.out.println("Insira o ID do livro que deseja apagar: ");
		int id = this.leitorOperador.nextInt();
		this.leitorOperador.nextLine();
		
		LivroDAO livroDAO = new LivroDAO();
		
		Livro livro = livroDAO.buscarPorId(id);
		
		if (livro != null) {
			livroDAO.apagar(id);
			System.out.println("Livro excluido com sucesso!");
		} else {
			System.out.println("Livro não encontrado.");
		}
		
		livroDAO.close();
	}
	
	private void adicionarCliente() {
		ClienteDAO a = new ClienteDAO();
		String nome;
		System.out.println("Insira o nome do cliente: ");
		this.leitorOperador.nextLine();
		nome = this.leitorOperador.nextLine();
		Cliente c = new Cliente(nome, -1, -1);
		a.salvar(c);
		a.close();
	}
	
	private void atualizarCliente() {
		System.out.println("Insira o ID do cliente que deseja atualizar: ");
		int id = this.leitorOperador.nextInt();
		this.leitorOperador.nextLine();
		
		ClienteDAO clienteDAO = new ClienteDAO();
		
		Cliente cliente = clienteDAO.buscarPorId(id);
		
		if (cliente != null) {
			System.out.println("Insira o novo nome do cliente: ");
			String novoNome = this.leitorOperador.nextLine();
			cliente.setNome(novoNome);
			clienteDAO.atualizar(cliente);
			System.out.println("Cliente atualizado com sucesso!");
		} else {
			System.out.println("Cliente não encontrado.");
		}
		
		clienteDAO.close();
		
	}
	
	private void apagarCliente() {
		System.out.println("Insira o ID do cliente que deseja apagar: ");
		int id = this.leitorOperador.nextInt();
		this.leitorOperador.nextLine();
		
		ClienteDAO clienteDAO = new ClienteDAO();
		
		Cliente cliente = clienteDAO.buscarPorId(id);
		
		if (cliente != null) {
			clienteDAO.apagar(id);
			System.out.println("Cliente excluido com sucesso!");
		} else {
			System.out.println("Cliente não encontrado.");
		}
		
		clienteDAO.close();
		
	}
	
	private void realizarEmprestimo() {
		ClienteDAO a = new ClienteDAO();
		Cliente c = new Cliente();
		LivroDAO livrodao = new LivroDAO();
		Livro livro = new Livro();
		int idcliente;
		int idlivro;
		this.leitorOperador.nextLine();
		listarLivros();
		//catalogo.printCatalogo();
		System.out.println("ID do Cliente: ");
		idcliente = this.leitorOperador.nextInt();
		this.leitorOperador.nextLine();
		System.out.println("Id do livro: ");
		idlivro = this.leitorOperador.nextInt();
		this.leitorOperador.nextLine();
		try {
			c = a.buscarPorId(idcliente);
			livro = livrodao.buscarPorId(idlivro);
			c.retirarLivro(livro);
			a.atualizar(c);
			livrodao.atualizar(livro);
		} catch (Exception e) {
			System.out.println("ID invalido");
			e.printStackTrace();
		}
		a.close();
		livrodao.close();
	}
		
	private void checarFila() {
		int id;
		
		LivroDAO livrodao = new LivroDAO();
		
		listarLivros();
		System.out.println("Qual livro deseja checar: ");
		id = this.leitorOperador.nextInt();
		//catalogo.getLivro(id).printFila();
		livrodao.buscarPorId(id).printFila();
		livrodao.close();
	}
	
	
	private void devolverLivro() {
		ClienteDAO clientedao = new ClienteDAO();
		Cliente c = new Cliente();
		LivroDAO livrodao = new LivroDAO();
		Livro livro = new Livro();
		int id;
		
		//String nome;
		this.leitorOperador.nextLine();
		System.out.println("Qual ID do cliente realizando a devolução");
		id = this.leitorOperador.nextInt();
		this.leitorOperador.nextLine();
		c = clientedao.buscarPorId(id);
		livro = livrodao.buscarPorId(c.getRetirado());
		c.devolverLivro(livro);
		clientedao.atualizar(c);
		livrodao.atualizar(livro);
		clientedao.close();
		livrodao.close();
		
		/*for(Cliente c : clientedao.listar()) {
			if(c.getNome().equals(nome)) {
				if(c.getRetirado()>=0) {
					c.devolverLivro(catalogo.getLivro(c.getRetirado()));
				}
			}
		}*/
		
	}
	
	private void listarCliente() {
		ClienteDAO a = new ClienteDAO();
		List<Cliente> b = a.listar();
		LivroDAO livrodao = new LivroDAO();
		for(Cliente c : b) {
			System.out.println("Nome: " + c.getNome());
			System.out.println("ID: " + c.getId());
			if(c.getRetirado()>=0) {
				System.out.println("Livro Retirado: " + livrodao.buscarPorId(c.getRetirado()).getNome());
			}
			else {
				System.out.println("Sem Livro Retirado");
			}
			if(c.getEspera()>=0) {
				System.out.println("Livro em Espera: " + livrodao.buscarPorId(c.getEspera()).getNome());
			}
			else {
				System.out.println("Sem livro em espera");
			}
			System.out.println();
		}
		a.close();
		livrodao.close();
	}
	
	private void listarLivros() {
		LivroDAO a = new LivroDAO();
		List<Livro> b = a.listar();
		for(Livro c : b) {
			c.printLivro();
		}
		a.close();
	}
	
	
	
}
