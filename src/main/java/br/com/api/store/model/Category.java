package br.com.api.store.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "category")
public class Category extends GenericEntity {

	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	private List<Transaction> transactions;
}
