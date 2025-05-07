package br.com.api.store.service;

import org.springframework.stereotype.Service;

import br.com.api.store.model.HistoryCurrency;
import br.com.api.store.repository.HistoryCurrencyRepository;

@Service
public class HistoryCurrencyService extends GenericServiceImpl<HistoryCurrency, Long>{

	public HistoryCurrencyService(HistoryCurrencyRepository repository) {
		super(repository);
	}
}
