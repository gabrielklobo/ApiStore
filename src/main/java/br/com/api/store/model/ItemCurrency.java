package br.com.api.store.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "item_currency")
@Getter
@Setter
public class ItemCurrency {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional = false)
	private Item item;

	@ManyToOne(optional = false)
	private Kingdom kingdom;
	
	@ManyToOne(optional = false)
	private Category category;

	@ManyToOne(optional = false)
	private Currency currency;
	
	@OneToMany(mappedBy = "itemCurrencySource", fetch = FetchType.LAZY)
	private List<Transaction> transactionsSource;
	
	@OneToMany(mappedBy = "itemCurrencyTarget", fetch = FetchType.LAZY)
	private List<Transaction> transactionsTarget;

	private Double amount;
}
