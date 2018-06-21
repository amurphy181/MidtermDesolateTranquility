package com.skilldistillery.mvcdesolatemidterm.data;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.skilldistillery.mvcdesolatemidterm.data.GameDAO;

@Transactional
@Component
public class GameDAOImpl implements GameDAO {

}
