package principal.modelo;

import java.util.ArrayList;
import java.util.List;

public class Catalogo {
	private List<Livro> lista = new ArrayList<>();
	
	public void addLivro(Livro livro) {
		lista.add(livro);
	}
	
	public Livro getLivro(int id) {
		return lista.get(id);
	}
	
	public void printCatalogo() {
		for(int i=0; i<lista.size(); i++) {
			lista.get(i).printLivro();
		}
	}
	
	/*public void removerLivro(int id) {
		lista.remove(id);
	}*/
	
	public int ultimo() {
		return lista.size();
	}

}
