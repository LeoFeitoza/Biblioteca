package principal.modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Livro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nome;
	private int quantidade;
	private int qteretirada;
	private List<String> fila = new ArrayList<>();
	
	public Livro(String nome, int qte, int qteretirada) {
		this.nome = nome;
		this.quantidade = qte;
		this.qteretirada = qteretirada;
	}
	
	public Livro() {
		
	}

	public void printLivro() {
		System.out.println("ID: " + this.id);
		System.out.println("Titulo: " + this.nome);
		System.out.println("Quantidade: " + this.quantidade);
		System.out.println("Disponiveis: " + (this.quantidade -this.qteretirada));
		System.out.println();
	}
	
	public int getQteretirada() {
		return qteretirada;
	}

	public void setQteretirada(int qteretirada) {
		this.qteretirada = qteretirada;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<String> getFila() {
		return fila;
	}

	public void setFila(List<String> fila) {
		this.fila = fila;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int qte) {
		this.quantidade = qte;
	}
	
	public boolean checkDispobibilidade() {
		if(this.quantidade == this.qteretirada) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public void colocarNaFila(String user) {
		fila.add(user);
	}
	
	public void tirarDaFila(Cliente user) {
		for(int i = 0; i<fila.size(); i++) {
			if(fila.get(i) == user.getNome()) {
				fila.remove(i);
				return;
			}
		}
	}
	
	public void retirar() {
		this.qteretirada++;
	}
	
	public void devolver() {
		this.qteretirada--;
	}
	
	public void printFila() {
		System.out.println("Fila: ");
		for(String nome : this.fila) {
			System.out.println("Nome: " + nome);
		}
	}

	
}
