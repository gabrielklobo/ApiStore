package br.com.api.store.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "history_currency")
@Getter
@Setter
public class HistoryCurrency {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional = false)
	private Currency currency;
	
	@Column(name = "value_new")
	private Double amountNew;
	
	@Column(name = "value_old")
	private Double amountOld;
	
	private Date date;
}
