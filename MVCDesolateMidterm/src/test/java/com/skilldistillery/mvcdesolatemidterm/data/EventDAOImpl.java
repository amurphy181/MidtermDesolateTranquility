package com.skilldistillery.mvcdesolatemidterm.data;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.skilldistillery.mvcdesolatemidterm.data.EventDAO;

@Transactional
@Component
public class EventDAOImpl implements EventDAO {

}
