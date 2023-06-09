package com.reccos.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.reccos.admin.exceptions.ObjectnotFoundException;
import com.reccos.admin.model.Player;
import com.reccos.admin.repository.PlayerRepository;

@Service
public class PlayerService {
	
	@Autowired
	private PlayerRepository repository;

	public Player listById(Long id) {
		Optional<Player> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Erro! Objeto não encontrado! PLAYER ID " + id));
	}
	
	public Page<Player> listAllPaginate(int page, int size) {
		Pageable paging = PageRequest.of(page, size);
		return repository.findAll(paging);
	}

	public List<Player> searchByChar(String letra) {
		return repository.findByNameStartingWith(letra);
	}

	public List<Player> findByName(String name) {
		return repository.findByNameContaining(name);
	}

	public List<Player> listByStatus(boolean status) {
		List<Player> obj = repository.findByStatus(status);
		return obj;
	}

	public Player create(Player obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public Player update(Long id, Player obj) {
		obj.setId(id);
		return repository.save(obj);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

}
