package com.asadk.cards.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asadk.cards.entity.Cards;

public interface CardsRepository extends JpaRepository<Cards, Long> {
	
	public Optional<Cards> findByMobileNumber(String mobileNum);
	
	public Optional<Cards> findByCardNumber(String cardNumber);

}
