package co.edu.icesi.demo.modelo.control;

import java.util.List;

import javax.ejb.Local;

import co.edu.icesi.demo.modelo.TiposUsuarios;

@Local
public interface ITiposUsuariosLogic {

	public void save(TiposUsuarios entity);

	public void update(TiposUsuarios entity);

	public void delete(Long codigo);

	public TiposUsuarios findById(Long id);

	public List<TiposUsuarios> findAll();

}
