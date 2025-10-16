package com.asadk.cards.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asadk.cards.constants.CardsConstants;
import com.asadk.cards.dto.CardsDto;
import com.asadk.cards.entity.Cards;
import com.asadk.cards.exception.CardAlreadyExistException;
import com.asadk.cards.exception.ResourceNotFoundException;
import com.asadk.cards.mapper.CardsMapper;
import com.asadk.cards.repository.CardsRepository;

@Service
public class CardsServiceImp implements ICardsService {

	CardsRepository cardsRepo;
	
	@Autowired
	public CardsServiceImp(CardsRepository cardsRepo) {
		this.cardsRepo = cardsRepo;
	}
	
	
	@Override
	public void createCard(String mobileNum) {
		
		Optional<Cards> cardsOptional = cardsRepo.findByMobileNumber(mobileNum);
		
		if(cardsOptional.isPresent()) {
			throw new CardAlreadyExistException("Card is already registered with the given mobile number");
		}
				              
	   Cards card = createNewCard(mobileNum);		
	   
	   cardsRepo.save(card);
		
	}
	
	private Cards createNewCard(String mobileNum) {
		
		Cards card = new Cards();
		
		Long randomCardNo = 100000000000L + new Random().nextInt(900000000);
		
		card.setMobileNumber(mobileNum);
		card.setCardNumber(randomCardNo.toString());
		card.setCardType(CardsConstants.CREDIT_CARD);
		card.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
		card.setAmountUsed(0);
		card.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
		card.setCreatedAt(LocalDateTime.now());
		card.setCreatedBy("Asad");
		
		return card;
		
	}


	@Override
	public CardsDto fetchCard(String mobileNum) {
		
		Cards card = cardsRepo.findByMobileNumber(mobileNum)
				              .orElseThrow(() -> new ResourceNotFoundException("Card","Mobile Number",mobileNum));
		
		CardsDto cardsDto = new CardsDto();
		CardsMapper.mapToCardsDto(card, cardsDto);
		
		return cardsDto;
		
	}
   
	@Override
	public boolean updateCard(CardsDto cardsDto) {
	 	
	 	Cards card = cardsRepo.findByCardNumber(cardsDto.getCardNumber())
	 			              .orElseThrow(() -> new ResourceNotFoundException("Card","Card Number",cardsDto.getCardNumber()));
		
	 	CardsMapper.mapToCards(cardsDto, card);
	 	cardsRepo.save(card);
	 	
	 	return true;
	}


	@Override
	public boolean deleteCard(String mobileNum) {
		
		Cards cards = cardsRepo.findByMobileNumber(mobileNum)
				               .orElseThrow( () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNum));
		
        cardsRepo.deleteById(cards.getCardId());
        return true;
		
	}

}
