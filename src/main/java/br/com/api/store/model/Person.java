package br.com.api.store.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "person")
@Getter
@Setter
public class Person extends GenericEntity {

	@Column(nullable = false)
	private String race;
	
	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
	private List<Transaction> transactionsCustomer;
	
	@OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
	private List<Transaction> transactionsSeller;
}
