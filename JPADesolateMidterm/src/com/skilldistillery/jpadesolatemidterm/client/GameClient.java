package com.skilldistillery.jpadesolatemidterm.client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.skilldistillery.jpadesolatemidterm.entities.Game;

public class GameClient {

	public static void main(String[] args) {

		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("DesolateMidterm");
		EntityManager em = emf.createEntityManager();

		Game game = em.find(Game.class, 1);

		System.out.println(game);

		em.close();
		emf.close();
		
	}

}
