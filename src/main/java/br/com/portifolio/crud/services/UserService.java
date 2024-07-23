package br.com.portifolio.crud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.portifolio.crud.entities.User;
import br.com.portifolio.crud.repositories.UserRepository;
import br.com.portifolio.crud.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;

	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(Long Id) {
		
		Optional<User> usuario = repository.findById(Id);
		return usuario.orElseThrow(() -> new ResourceNotFoundException(Id));
		
	}
	
	public User insert(User usuario) {
		return repository.save(usuario);
	}
	
	
	public void delete(Long id) {
		
		try {
		    repository.deleteById(id);
	} catch (ResourceNotFoundException e) {
		// TODO: handle exception
	} 
		
}
	
	
	
	public User update(Long id, User usuario) {
		try {
		User cadastro = repository.getReferenceById(id);
		updateDados(cadastro, usuario);
		return repository.save(cadastro);
		} catch (RuntimeException e) {
			throw new ResourceNotFoundException(id);
			
		}
		
	}

	private void updateDados(User cadastro, User usuario) {
		cadastro.setNome(usuario.getNome());
		cadastro.setEmail(usuario.getEmail());
		cadastro.setTelefone(usuario.getTelefone());
		
	}
	
	
	
	
	
}

