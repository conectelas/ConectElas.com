package br.org.conectelas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	@GetMapping("/{id}")
	public ResponseEntity<TemaModel> getById(@PathVariable long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<TemaModel> postTema(@RequestBody TemaModel tema) {
		return repository.findByNome(tema.getNome()).map(resp -> ResponseEntity.status(HttpStatus.CONFLICT).body(resp))
				.orElseGet(() -> ResponseEntity.ok(repository.save(tema)));
	}

	@PutMapping
	public ResponseEntity<TemaModel> putTema(@RequestBody TemaModel tema) {
		return ResponseEntity.ok(repository.save(tema));
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}

}
