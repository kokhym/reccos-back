package com.reccos.admin.controller;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.reccos.admin.model.Group;
import com.reccos.admin.service.GroupService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/groups")
public class GroupController {

	@Autowired
	private GroupService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Group> listById(@PathVariable Long id) {
		Group group = service.listById(id);
		return ResponseEntity.ok().body(group);
	}
	
	@GetMapping
	public ResponseEntity<List<Group>> listarTodos(){
		List<Group> list = service.listAll();
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<Group> criarGroup(@RequestBody Group group) {
		Group obj = service.create(group);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PostMapping("/{ligaId}/leagues")
	public ResponseEntity<Group> addTag(@PathVariable(value = "ligaId") Long ligaId,
			@RequestBody Group tagRequest) {
		Group grp = service.groupByLeague(tagRequest, ligaId);

		return new ResponseEntity<>(grp, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Group> atualizarGroup(@PathVariable Long id, @RequestBody Group group){
		System.out.println("UPDATE GROUP CONTROLLER");
		Group obj = service.update(id, group);
		
		if(obj != null) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.badRequest().build();
	}
	
	
}
