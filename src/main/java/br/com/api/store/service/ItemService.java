package br.com.api.store.service;

import org.springframework.stereotype.Service;

import br.com.api.store.model.Item;
import br.com.api.store.repository.ItemRepository;

@Service
public class ItemService extends GenericServiceImpl<Item, Long>{

	public ItemService(ItemRepository itemRepository) {
		super(itemRepository);
	}
}
