package com.skilldistillery.mvcdesolatemidterm.data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class EventDAOImpl implements EventDAO {
	
	@PersistenceContext
	private EntityManager em;

}
