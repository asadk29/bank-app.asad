package com.asadk.cards.service;

import com.asadk.cards.dto.CardsDto;

public interface ICardsService {
	
	public void createCard(String mobileNum);
	
	public CardsDto fetchCard(String mobileNum);
	
	public boolean updateCard(CardsDto cardsDto);
	
	public boolean deleteCard(String mobileNum);

}
