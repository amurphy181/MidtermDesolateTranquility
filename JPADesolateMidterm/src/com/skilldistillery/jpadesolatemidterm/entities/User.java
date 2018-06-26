package com.skilldistillery.jpadesolatemidterm.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;



@Entity
public class User {
	
	
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private int id;
	@Column(name="name")
	private String userName;
	private String password;
	private boolean admin;
	private boolean status;
	@Column(name="profile_summary")
	private String summary;
	@Column(name="picture_url")
	private String pictureURL;
	@OneToMany(mappedBy= "user")
	private List<Friend> requests;
	
	

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany
	@JoinTable(name = "user_game", 
	joinColumns = @JoinColumn(name = "user_id"),
	inverseJoinColumns = @JoinColumn(name = "game_id"))
	private List<Game> games;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany
	@JoinTable(name = "user_event", 
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "event_id"))
	private List<Event> events;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "creator")
	private List<Event> createdEvents;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany
	@JoinTable(name="friends",
				joinColumns = @JoinColumn(name = "user_id"),
				inverseJoinColumns = @JoinColumn(name = "friend_id"))
	private List<User> friendList;
	
	
	
	
	
	
	
	
	
	//add and remove methods
	
	public void addFriend(User friend) {
		if (friendList == null)
			friendList = new ArrayList<>();
		
		if (!friendList.contains(friend)) {
			friendList.add(friend);
			
		}
	}
	
	public void removeFriend(User friend) {
		if(friendList != null) {
			friendList.remove(friend);
		}
	}
	public void addCreatedEvent(Event event) {
		if (createdEvents == null)
			createdEvents = new ArrayList<>();

		if (!createdEvents.contains(event)) {
			createdEvents.add(event);
			if (event.getGame() != null) {
				event.getGame().getEvents().remove(event);
			}
			event.setCreator(this);;
		}
	}
	
	public void removeCreatedEvent(Event event) {
		event.setUsers(null);
		event.setCreator(null);
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
	
	public void removeEvent(Event event) {
		if(events != null && events.contains(event)) {
			events.remove(event);
			event.removeUser(this);
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
	
	
	public boolean getAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public List<User> getFriendList() {
		return friendList;
	}

	public void setFriendList(List<User> friendList) {
		this.friendList = friendList;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public String getPictureURL() {
		return pictureURL;
	}

	public void setPictureURL(String pictureURL) {
		this.pictureURL = pictureURL;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (admin ? 1231 : 1237);
		result = prime * result + ((createdEvents == null) ? 0 : createdEvents.hashCode());
		result = prime * result + ((events == null) ? 0 : events.hashCode());
		result = prime * result + ((friendList == null) ? 0 : friendList.hashCode());
		result = prime * result + ((games == null) ? 0 : games.hashCode());
		result = prime * result + id;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((pictureURL == null) ? 0 : pictureURL.hashCode());
		result = prime * result + (status ? 1231 : 1237);
		result = prime * result + ((summary == null) ? 0 : summary.hashCode());
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
		if (admin != other.admin)
			return false;
		if (createdEvents == null) {
			if (other.createdEvents != null)
				return false;
		} else if (!createdEvents.equals(other.createdEvents))
			return false;
		if (events == null) {
			if (other.events != null)
				return false;
		} else if (!events.equals(other.events))
			return false;
		if (friendList == null) {
			if (other.friendList != null)
				return false;
		} else if (!friendList.equals(other.friendList))
			return false;
		if (games == null) {
			if (other.games != null)
				return false;
		} else if (!games.equals(other.games))
			return false;
		if (id != other.id)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (pictureURL == null) {
			if (other.pictureURL != null)
				return false;
		} else if (!pictureURL.equals(other.pictureURL))
			return false;
		if (status != other.status)
			return false;
		if (summary == null) {
			if (other.summary != null)
				return false;
		} else if (!summary.equals(other.summary))
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
		builder.append(", admin=");
		builder.append(admin);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}

	

}
