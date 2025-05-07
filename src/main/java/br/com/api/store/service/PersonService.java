package br.com.api.store.service;

import org.springframework.stereotype.Service;

import br.com.api.store.model.Person;
import br.com.api.store.repository.PersonRepository;

@Service
public class PersonService extends GenericServiceImpl<Person, Long>{

	public PersonService(PersonRepository repository) {
		super(repository);
	}
}
