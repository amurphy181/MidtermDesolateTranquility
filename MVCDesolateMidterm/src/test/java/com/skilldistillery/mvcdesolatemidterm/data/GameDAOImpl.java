package com.skilldistillery.mvcdesolatemidterm.data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.skilldistillery.mvcdesolatemidterm.data.GameDAO;

@Transactional
@Component
public class GameDAOImpl implements GameDAO {
	
	@PersistenceContext
	private EntityManager em;

}
