package principal.daos;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


import principal.modelo.Livro;


public class LivroDAO implements DAO<Livro> {
	
	private EntityManager em;
	private EntityManagerFactory emf;

	
	public LivroDAO() {
		emf = Persistence.createEntityManagerFactory("biblioteca_mysql");
		em = emf.createEntityManager();
	}
	
	@Override
	public Livro buscarPorId(Integer id) {
		Livro livro = em.find(Livro.class, id);
		return livro;
	}

	@Override
	public List<Livro> listar() {
		List<Livro> lista = em.createQuery("from Livro", Livro.class).getResultList();
		return lista;
	}

	@Override
	public Integer salvar(Livro livro) {
		em.getTransaction().begin();
		em.persist(livro);
		em.getTransaction().commit();
		return livro.getId();

	}

	@Override
	public Integer atualizar(Livro livro) {
		em.getTransaction().begin();
		em.merge(livro);
		em.getTransaction().commit();
		return livro.getId();
	}

	@Override
	public void apagar(Integer id) {
		Livro l = em.find(Livro.class, id);
		em.getTransaction().begin();
		em.remove(l);
		em.getTransaction().commit();
		
	}

	@Override
	public void close() {
		em.close();
		emf.close();
		
	}



}
