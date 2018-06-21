package com.skilldistillery.jpadesolatemidterm.client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.skilldistillery.jpadesolatemidterm.entities.Platform;

public class PlatformClient {
	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("DesolateMidterm");
		EntityManager em = emf.createEntityManager();

		Platform platform = em.find(Platform.class, 1);

		System.out.println(platform);

		em.close();
		emf.close();
		
		
	}
}
