package com.centroinformacion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.centroinformacion.entity.Cliente;

public interface ClienteService {

	public abstract List<Cliente> listaTodos();

	// FILTRAR
	public abstract List<Cliente> listaPorNombreClienteLike(String filtro);

	// INSERTA Y ACTUALIZA
	public abstract Cliente insertaActualizaCliente(Cliente obj);

	// ELIMINAR
	public abstract void eliminaClientePorId(int idCliente);

	// LISTA POR UN ID
	public abstract Optional<Cliente> listaClientePorId(int idCliente);

	
	
	// VALIDACIONES REGISTRAR

	public abstract List<Cliente> listaClientePorTelefonoDiferenteSiMismo(String telefono, int idCliente);

	public abstract List<Cliente> listaClientePorCorreoDiferenteSiMismo(String correo, int idCliente);

	public abstract List<Cliente> listaClientePorDireccionDiferenteSiMismo(String direccion, int idCliente);

	
	
	// VALIDACIONES ACTUALIZAR

	public abstract List<Cliente> listaClientePorTelefonoDiferenteSiMismoActualiza(String telefono, int idCliente);

	public abstract List<Cliente> listaClientePorCorreoDiferenteSiMismoActualiza(String correo, int idCliente);

	public abstract List<Cliente> listaClientePorDireccionDiferenteSiMismoActualiza(String direccion, int idCliente);
	

	// TRANSACCION
	public abstract List<Cliente> listaCliente (String filtro, Pageable pageable);
	public abstract List<Cliente> listaCliente ();
	
}
