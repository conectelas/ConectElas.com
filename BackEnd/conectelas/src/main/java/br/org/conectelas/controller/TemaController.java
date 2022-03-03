package br.org.conectelas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.conectelas.model.TemaModel;
import br.org.conectelas.repository.TemaRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/tema")
@RestController
public class TemaController {
	
	@Autowired
	private TemaRepository repository;
	
	@GetMapping
	public List<TemaModel> getAll() {
		return repository.findAll();
	}
	
	
	@PostMapping
	public ResponseEntity <TemaModel> postTema(@RequestBody TemaModel tema) {
		return ResponseEntity.ok(repository.save(tema));
	}
	
}
