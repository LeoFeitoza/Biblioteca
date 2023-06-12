package principal.daos;

import java.util.List;

public interface DAO<T> {
	
	T buscarPorId(Integer id);	
	List<T> listar();
	Integer salvar(T entidade);
	Integer atualizar(T entidade);
	void apagar(Integer id);	
	void close();

}
