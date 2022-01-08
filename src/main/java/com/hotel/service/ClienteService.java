package com.hotel.service;

import java.util.List;

import com.hotel.dao.ClienteDao;
import com.hotel.entity.Cliente;

/**
 *
 *
 * @author Matthew Mazzotta
 */
public class ClienteService {

	private ClienteDao clienteDao = new ClienteDao();
	
	public void saveCliente(Cliente cliente) {
		clienteDao.saveCliente(cliente);
	}
	
	public void deleteCliente(Cliente cliente) {
		clienteDao.deleteCliente(cliente);
	}
	
	public Cliente getById(Long id) {
		return clienteDao.getById(id);
	}
	
	public List<Cliente> getAll() {
		return clienteDao.getAll();
	}
	
	public List<Cliente> getClientiByCognome(String cognome) {
		return clienteDao.getClientiByCognome(cognome);
	}
	
	public Cliente getClienteByNumeroDocumento(String numeroDocumento) {
		return clienteDao.getClienteByNumeroDocumento(numeroDocumento);
	}
}