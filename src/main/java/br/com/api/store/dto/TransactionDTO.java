package br.com.api.store.dto;

import java.util.Date;

import br.com.api.store.enums.TransactionTypeEnum;

public record TransactionDTO(Long transctionId, Long itemId, Long customerId, Long sellerId, Long kingdomId, TransactionTypeEnum type,
		Long itemCurrencySourceId, Long itemCurrencyTargetId, Date date) {
}
