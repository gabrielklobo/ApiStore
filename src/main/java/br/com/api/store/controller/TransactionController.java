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

import br.com.api.store.dto.TransactionDTO;
import br.com.api.store.model.Transaction;
import br.com.api.store.service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

	private TransactionService service;

	public TransactionController(TransactionService service) {
		this.service = service;
	}
	@GetMapping
	public ResponseEntity<List<Transaction>> getAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Transaction> get(@PathVariable Long id) {
		return service.findById(id).map(x -> ResponseEntity.ok().body(x))
				.orElseGet(() -> ResponseEntity.badRequest().build());
	}

	@PostMapping
	public ResponseEntity<Transaction> save(@RequestBody TransactionDTO transactionDTO) {
		try {
			Transaction transactionResponse = service.validateExistsAndSave(transactionDTO);

			return ResponseEntity.status(201).body(transactionResponse);
		} catch (NoSuchElementException nsee) {
			return ResponseEntity.badRequest().build();
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping
	public ResponseEntity<Transaction> update(@RequestBody Transaction transaction) {
		return service.update(transaction).map(x -> ResponseEntity.status(201).body(transaction))
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
