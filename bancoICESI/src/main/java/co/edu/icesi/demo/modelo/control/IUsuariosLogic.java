package co.edu.icesi.demo.modelo.control;

import java.util.List;

import javax.ejb.Local;

import co.edu.icesi.demo.modelo.Usuarios;

@Local
public interface IUsuariosLogic {

	public void save(Usuarios entity);

	public void update(Usuarios entity);

	public void delete(Long id);

	public Usuarios findById(Long id);

	public List<Usuarios> findAll();
}
