package br.com.api.store.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.api.store.enums.TransactionTypeEnum;
import br.com.api.store.model.Transaction;

public interface TransactionRepository extends IGenericRepository<Transaction, Long> {
	
	@Query("SELECT t FROM Transaction t WHERE t.item.id = :itemId " + "AND t.customer.id = :customerId "
			+ "AND t.seller.id = :sellerId " + "AND t.kingdom.id = :kingdomId " + "AND t.type = :type "
			+ "AND t.itemCurrencySource.id = :itemCurrencySourceId "
			+ "AND t.itemCurrencyTarget.id = :itemCurrencyTargetId ")
	Optional<Transaction> findExistTransaction(@Param("itemId") Long itemId, @Param("customerId") Long customerId,
			@Param("sellerId") Long sellerId, @Param("kingdomId") Long kingdomId,
			@Param("type") TransactionTypeEnum type, @Param("itemCurrencySourceId") Long itemCurrencySourceId,
			@Param("itemCurrencyTargetId") Long itemCurrencyTargetId);
}
