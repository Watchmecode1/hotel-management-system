package it.faggiorosso.service;

import java.util.List;

import it.faggiorosso.dao.ClienteDao;
import it.faggiorosso.entity.Cliente;

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