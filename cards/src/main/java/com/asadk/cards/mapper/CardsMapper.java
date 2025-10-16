package com.asadk.cards.mapper;

import com.asadk.cards.dto.CardsDto;
import com.asadk.cards.entity.Cards;

public class CardsMapper {
	
	public static Cards mapToCards(CardsDto cardsDto, Cards card) {
		
		card.setMobileNumber(cardsDto.getMobileNumber());
		card.setCardNumber(cardsDto.getCardNumber());
		card.setCardType(cardsDto.getCardType());
		card.setTotalLimit(cardsDto.getTotalLimit());
		card.setAmountUsed(cardsDto.getAmountUsed());
		card.setAvailableAmount(cardsDto.getAvailableAmount());
		
		return card;
		
	}
	
	public static CardsDto mapToCardsDto(Cards card, CardsDto cardsDto) {
		
		cardsDto.setMobileNumber(card.getMobileNumber());
		cardsDto.setCardNumber(card.getCardNumber());
		cardsDto.setCardType(card.getCardType());
		cardsDto.setTotalLimit(card.getTotalLimit());
		cardsDto.setAmountUsed(card.getAmountUsed());
		cardsDto.setAvailableAmount(card.getAvailableAmount());
		
		return cardsDto;
		
	}

}
