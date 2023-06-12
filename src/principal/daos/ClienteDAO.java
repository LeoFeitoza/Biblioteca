package principal.daos;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


import principal.modelo.Cliente;


public class ClienteDAO implements DAO<Cliente> {
	
	private EntityManager em;
	private EntityManagerFactory emf;

	
	public ClienteDAO() {
		emf = Persistence.createEntityManagerFactory("biblioteca_mysql");
		em = emf.createEntityManager();
	}
	
	@Override
	public Cliente buscarPorId(Integer id) {
		Cliente cliente = em.find(Cliente.class, id);
		return cliente;
	}

	@Override
	public List<Cliente> listar() {
		List<Cliente> lista = em.createQuery("from Cliente", Cliente.class).getResultList();
		return lista;
	}

	@Override
	public Integer salvar(Cliente cliente) {
		em.getTransaction().begin();
		em.persist(cliente);
		em.getTransaction().commit();
		return cliente.getId();

	}

	@Override
	public Integer atualizar(Cliente cliente) {
		em.getTransaction().begin();
		em.merge(cliente);
		em.getTransaction().commit();
		return cliente.getId();
	}

	@Override
	public void apagar(Integer id) {
		Cliente c = em.find(Cliente.class, id);
		em.getTransaction().begin();
		em.remove(c);
		em.getTransaction().commit();
		
	}

	@Override
	public void close() {
		em.close();
		emf.close();
		
	}



}
