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
@Table(name = "kingdom")
@Getter
@Setter
public class Kingdom extends GenericEntity {

	@Column(nullable = false)
	private String region;

	@OneToMany(mappedBy = "kingdom", fetch = FetchType.LAZY)
	private List<Transaction> transactions;
}
