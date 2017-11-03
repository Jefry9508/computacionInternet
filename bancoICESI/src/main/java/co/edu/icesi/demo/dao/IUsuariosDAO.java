package co.edu.icesi.demo.dao;

import java.util.List;

import javax.ejb.Local;

import co.edu.icesi.demo.modelo.Usuarios;

@Local
public interface IUsuariosDAO {

	public void save(Usuarios entity);

	public void update(Usuarios entity);

	public void delete(Usuarios entity);

	public Usuarios findById(Long id);

	public List<Usuarios> findAll();

}
