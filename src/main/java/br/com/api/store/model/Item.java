package br.com.api.store.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "item")
@Getter
@Setter
public class Item extends GenericEntity {
	
	@OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
	private List<Transaction> transactions;
}
