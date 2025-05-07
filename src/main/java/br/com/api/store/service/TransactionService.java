package br.com.api.store.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.api.store.dto.TransactionDTO;
import br.com.api.store.model.Item;
import br.com.api.store.model.ItemCurrency;
import br.com.api.store.model.Kingdom;
import br.com.api.store.model.Person;
import br.com.api.store.model.Transaction;
import br.com.api.store.repository.TransactionRepository;
import jakarta.transaction.Transactional;

@Service
public class TransactionService extends GenericServiceImpl<Transaction, Long> {

	private final ItemService itemService;
	private final PersonService personService;
	private final KingdomService kingdomService;
	private final ItemCurrencyService itemCurrencyService;

	public TransactionService(TransactionRepository repository, ItemService itemService, PersonService personService,
			KingdomService kingdomService, ItemCurrencyService itemCurrencyService) {
		super(repository);
		this.itemService = itemService;
		this.personService = personService;
		this.kingdomService = kingdomService;
		this.itemCurrencyService = itemCurrencyService;
	}

	@Transactional
	public Transaction validateExistsAndSave(TransactionDTO transactionDTO) {
		synchronized (this) {
			Optional<Transaction> transactionExists = ((TransactionRepository) repository).findExistTransaction(
					transactionDTO.itemId(), transactionDTO.customerId(), transactionDTO.sellerId(),
					transactionDTO.kingdomId(), transactionDTO.type(), transactionDTO.itemCurrencySourceId(),
					transactionDTO.itemCurrencyTargetId());

			if (transactionExists.isPresent()) {
				throw new IllegalStateException("Transaction already exists with the same details.");
			}
			
			return save(transactionDTO);
		}
	}
	
	@Transactional
    public Optional<Transaction> update(TransactionDTO transactionDTO) {
    	return repository.findById(transactionDTO.transctionId()).map(o -> save(transactionDTO));
    }
	
	public Transaction save(TransactionDTO transactionDTO) {
		Item item = itemService.findById(transactionDTO.itemId())
				.orElseThrow(() -> new NoSuchElementException("Item not found"));

		Person customer = personService.findById(transactionDTO.customerId())
				.orElseThrow(() -> new NoSuchElementException("Customer not found"));

		Person seller = personService.findById(transactionDTO.sellerId())
				.orElseThrow(() -> new NoSuchElementException("Seller not found"));

		Kingdom kingdom = kingdomService.findById(transactionDTO.kingdomId())
				.orElseThrow(() -> new NoSuchElementException("Kingdom not found"));

		ItemCurrency source = itemCurrencyService.findById(transactionDTO.itemCurrencySourceId())
				.orElseThrow(() -> new NoSuchElementException("Source currency not found"));

		ItemCurrency target = itemCurrencyService.findById(transactionDTO.itemCurrencyTargetId())
				.orElseThrow(() -> new NoSuchElementException("Target currency not found"));

		Transaction transaction = new Transaction();
		transaction.setItem(item);
		transaction.setCustomer(customer);
		transaction.setSeller(seller);
		transaction.setKingdom(kingdom);
		transaction.setType(transactionDTO.type());
		transaction.setItemCurrencySource(source);
		transaction.setItemCurrencyTarget(target);
		transaction.setAmountFinal(source.getAmount() * target.getAmount());
		transaction.setDate(transactionDTO.date());

		return repository.save(transaction);
	}
}
