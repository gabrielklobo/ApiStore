package br.com.api.store.model;

import java.util.Date;

import br.com.api.store.enums.TransactionTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "transaction")
@Getter
@Setter
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false)
	private Item item;
	
	@ManyToOne(optional = false)
	private Person customer;

	@ManyToOne(optional = false)
	private Person seller;

	@ManyToOne(optional = false)
	private Kingdom kingdom;

	@Enumerated(EnumType.STRING)
	private TransactionTypeEnum type;

	@ManyToOne(optional = false)
	private ItemCurrency itemCurrencySource;

	@ManyToOne(optional = false)
	private ItemCurrency itemCurrencyTarget;

	@Column(name = "value_final", nullable = false)
	private double amountFinal;

	private Date date;
}
