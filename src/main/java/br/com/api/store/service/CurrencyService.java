package br.com.api.store.service;

import org.springframework.stereotype.Service;

import br.com.api.store.model.Currency;
import br.com.api.store.repository.CurrencyRepository;

@Service
public class CurrencyService extends GenericServiceImpl<Currency, Long>{

	public CurrencyService(CurrencyRepository repository) {
		super(repository);
	}
}
