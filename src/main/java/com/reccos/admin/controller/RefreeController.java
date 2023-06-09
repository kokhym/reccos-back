package com.reccos.admin.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.reccos.admin.exceptions.ObjectnotFoundException;
import com.reccos.admin.model.Refree;
import com.reccos.admin.repository.FederationRepository;
import com.reccos.admin.repository.RefreeRepository;
import com.reccos.admin.service.RefreeService;

@RestController
@RequestMapping(value = "/api/refrees")
@CrossOrigin("*")
public class RefreeController {

	@Autowired
	private RefreeService service;

	@Autowired
	private RefreeRepository refreeRepository;

	@Autowired
	private FederationRepository federationRepository;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Refree> listById(@PathVariable Long id) {
		Refree arbitro = service.listById(id);
		return ResponseEntity.ok().body(arbitro);
	}

	@GetMapping(value = "/status/{status}")
	public ResponseEntity<List<Refree>> listByStatus(@PathVariable Boolean status) {
		List<Refree> list = service.listByStatus(status);
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/find")
	public ResponseEntity<Page<Refree>> pageAllLits(
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "12") int size) {
		Page<Refree> list = service.listAllPaginate(page, size);
		return ResponseEntity.ok().body(list);
	}

	@GetMapping
	public ResponseEntity<List<Refree>> listarTodos() {
		List<Refree> list = service.listAll();
		return ResponseEntity.ok().body(list);
	}

	@PostMapping
	public ResponseEntity<Refree> criarRefree(@RequestBody Refree arbitro) {
		Refree obj = service.create(arbitro);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PostMapping("/{federationId}/federation")
	public ResponseEntity<Refree> saveLeague(@RequestBody Refree refree,
			@PathVariable("federationId") Long federationId) {
		Refree tag = federationRepository.findById(federationId).map(tutorial -> {
			tutorial.addRefree(refree);
			return refreeRepository.save(refree);
		}).orElseThrow(() -> new ObjectnotFoundException("Not found REFREE BY FEDERATION with id = " + federationId));

		return new ResponseEntity<>(tag, HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Refree> atualizarRefree(@PathVariable Long id, @RequestBody Refree arbitro) {
		Refree obj = service.update(id, arbitro);

		if (obj != null) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.badRequest().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Refree> deletarRefree(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
