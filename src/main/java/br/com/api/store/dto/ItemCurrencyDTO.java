package br.com.api.store.dto;

public record ItemCurrencyDTO(Long itemCurrencyId, Long itemId, Long kingdomId, Long categoryId, Long currencyId, Double amount) {
}
