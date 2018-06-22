package com.skilldistillery.jpadesolatemidterm.entities;

import javax.persistence.*;
import java.util.*;


@Entity
public class User {
	
	
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private int id;
	@Column(name="name")
	private String userName;
	private String password;
	
	@ManyToMany
	@JoinTable(name = "user_game", 
	joinColumns = @JoinColumn(name = "user_id"),
	inverseJoinColumns = @JoinColumn(name = "game_id"))
	private List<Game> games;
	
	@ManyToMany
	@JoinTable(name = "user_event", 
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "event_id"))
	private List<Event> events;
	
	@OneToMany(mappedBy = "user")
	private List<Event> createdEvents;
	
	
	
	
	
	
	//add and remove methods
	
	public void addCreatedEvent(Event event) {
		if (createdEvents == null)
			createdEvents = new ArrayList<>();

		if (!createdEvents.contains(event)) {
			createdEvents.add(event);
			if (event.getGame() != null) {
				event.getGame().getEvents().remove(event);
			}
			event.setUsers(this);
		}
	}
	
	public void removeCreatedEvent(Event event) {
		event.setUsers(null);
		if(createdEvents != null) {
			createdEvents.remove(event);
		}
	}
	
	
	public void addEvent(Event event) {
		if(events == null) events = new ArrayList<>();
		
		if(!events.contains(event)) {
			events.add(event);
			event.addUser(this);
		}
	}
	
	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public List<Event> getCreatedEvents() {
		return createdEvents;
	}

	public void setCreatedEvents(List<Event> userEvents) {
		this.createdEvents = userEvents;
	}

	public void removeEvent(Event event) {
		if(events != null && events.contains(event)) {
			events.remove(event);
			event.removeUser(this);
		}
	}
	
	public void addGame(Game game) {
		if(games == null) games = new ArrayList<>();
		
		if(!games.contains(game)) {
			games.add(game);
			game.addUser(this);
		}
	}
	
	public void removeGame(Game game) {
		if(games != null && games.contains(game)) {
			games.remove(game);
			game.removeUser(this);
		}
	}
	
	
	
	
	
	

	
	// User entity getters and setters
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", password=");
		builder.append(password);
		builder.append(", games=");
		builder.append(games);
		builder.append("]");
		return builder.toString();
	}

	

}
