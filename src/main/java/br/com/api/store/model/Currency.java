package br.com.api.store.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "currency")
@Getter
@Setter
public class Currency extends GenericEntity {
	private Double amount;

	@OneToMany(mappedBy = "currency", fetch = FetchType.LAZY)
	private List<HistoryCurrency> history;
}
