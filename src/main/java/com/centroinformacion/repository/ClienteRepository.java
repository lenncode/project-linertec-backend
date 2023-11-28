package com.centroinformacion.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.centroinformacion.entity.Cliente;
import com.centroinformacion.entity.Proveedor;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	// VALIDACIONES PARA REGISTRAR

	@Query("select e from Cliente e where e.telefono = ?1 and e.idCliente <> ?2 ")
	public List<Cliente> listaPorTelefonoDiferenteSiMismo(String telefono, int idCliente);

	@Query("select e from Cliente e where e.correo = ?1 and e.idCliente <> ?2 ")
	public List<Cliente> listaPorCorreoDiferenteSiMismo(String correo, int idCliente);

	@Query("select e from Cliente e where e.direccion = ?1 and e.idCliente <> ?2 ")
	public List<Cliente> listaPorDireccionDiferenteSiMismo(String direccion, int idCliente);

	// VALIDACIONES PARA ACTUALIZAR

	@Query("select e from Cliente e where e.telefono = ?1 and e.idCliente <> ?2 ")
	public List<Cliente> listaPorTelefonoDiferenteSiMismoActualiza(String telefono, int idCliente);

	@Query("select e from Cliente e where e.correo = ?1 and e.idCliente <> ?2 ")
	public List<Cliente> listaPorCorreoDiferenteSiMismoActualiza(String correo, int idCliente);

	@Query("select e from Cliente e where e.direccion = ?1 and e.idCliente <> ?2 ")
	public List<Cliente> listaPorDireccionDiferenteSiMismoActualiza(String direccion, int idCliente);

	@Query("SELECT e FROM Cliente e WHERE e.nombreCliente LIKE ?1")
	public List<Proveedor> listaPorNombreLike(String filtro);

	
	// FILTRAR
	@Query("select p from Cliente p where p.nombreCliente like ?1 ")
	public List<Cliente> listaClienteNombreLike(String filtro);
	
	
	@Query("Select x from Cliente x where nombreCliente like :var_filtro")
	public abstract List<Cliente> listaCliente(@Param("var_filtro") String filtro, Pageable pageable);

	@Query("Select x from Cliente x order by x.nombreCliente asc")
	public abstract List<Cliente> listaCliente();
	
}
