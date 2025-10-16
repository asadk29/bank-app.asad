package com.asadk.cards.service;

import com.asadk.cards.dto.CardsDto;

public interface ICardsService {
	
	public void createCard(String mobileNum);
	
	public CardsDto fetchCard(String mobileNum);
	
	public void updateCard(CardsDto cardsDto);
	
	public void deleteCard(String mobileNum);

}
