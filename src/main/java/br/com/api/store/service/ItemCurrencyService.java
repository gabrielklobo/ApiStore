package br.com.api.store.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.api.store.dto.ItemCurrencyDTO;
import br.com.api.store.model.Category;
import br.com.api.store.model.Currency;
import br.com.api.store.model.Item;
import br.com.api.store.model.ItemCurrency;
import br.com.api.store.model.Kingdom;
import br.com.api.store.repository.ItemCurrencyRepository;
import jakarta.transaction.Transactional;

@Service
public class ItemCurrencyService extends GenericServiceImpl<ItemCurrency, Long> {

	private final ItemService itemService;
	private final KingdomService kingdomService;
	private final CategoryService categoryService;
	private final CurrencyService currencyService;

	public ItemCurrencyService(ItemCurrencyRepository repository, ItemService itemService,
			KingdomService kingdomService, CategoryService categoryService, CurrencyService currencyService) {
		super(repository);

		this.itemService = itemService;
		this.kingdomService = kingdomService;
		this.categoryService = categoryService;
		this.currencyService = currencyService;
	}

	@Transactional
    public ItemCurrency save(ItemCurrencyDTO itemCurrencyDTO) {
        Item item = itemService.findById(itemCurrencyDTO.itemId())
            .orElseThrow(() -> new NoSuchElementException("Item not found"));

        Kingdom kingdom = kingdomService.findById(itemCurrencyDTO.kingdomId())
            .orElseThrow(() -> new NoSuchElementException("Kingdom not found"));

        Category category = categoryService.findById(itemCurrencyDTO.categoryId())
            .orElseThrow(() -> new NoSuchElementException("Category not found"));

        Currency currency = currencyService.findById(itemCurrencyDTO.currencyId())
            .orElseThrow(() -> new NoSuchElementException("Currency not found"));

        ItemCurrency itemCurrency = new ItemCurrency();
        itemCurrency.setItem(item);
        itemCurrency.setKingdom(kingdom);
        itemCurrency.setCategory(category);
        itemCurrency.setCurrency(currency);
        itemCurrency.setAmount(itemCurrencyDTO.amount());

        return repository.save(itemCurrency);
    }
    
	@Transactional
    public Optional<ItemCurrency> update(ItemCurrencyDTO itemCurrencyDTO) {
    	return repository.findById(itemCurrencyDTO.itemCurrencyId()).map(o -> save(itemCurrencyDTO));
    }
}
