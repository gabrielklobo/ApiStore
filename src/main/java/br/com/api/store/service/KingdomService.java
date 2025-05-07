package br.com.api.store.service;

import org.springframework.stereotype.Service;

import br.com.api.store.model. Kingdom;
import br.com.api.store.repository. KingdomRepository;

@Service
public class KingdomService extends GenericServiceImpl< Kingdom, Long>{

	public KingdomService( KingdomRepository repository) {
		super(repository);
	}
}
