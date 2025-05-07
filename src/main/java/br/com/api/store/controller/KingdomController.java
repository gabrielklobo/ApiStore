package br.com.api.store.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.store.model.Kingdom;
import br.com.api.store.service.KingdomService;

@RestController
@RequestMapping("/kingdom")
public class KingdomController {

	private KingdomService service;

	public KingdomController(KingdomService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<Kingdom>> getAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Kingdom> get(@PathVariable Long id) {
		return service.findById(id).map(x -> ResponseEntity.ok().body(x))
				.orElseGet(() -> ResponseEntity.badRequest().build());
	}

	@PostMapping
	public ResponseEntity<Kingdom> save(@RequestBody Kingdom kingdom) {
		try {
			Kingdom kingdomResponse = service.save(kingdom);

			return ResponseEntity.status(201).body(kingdomResponse);
		} catch (NoSuchElementException nsee) {
			return ResponseEntity.badRequest().build();
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping
	public ResponseEntity<Kingdom> update(@RequestBody Kingdom kingdom) {
		return service.update(kingdom).map(x -> ResponseEntity.status(201).body(x))
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		if (service.deleteByIdWithReturn(id)) {
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

}
