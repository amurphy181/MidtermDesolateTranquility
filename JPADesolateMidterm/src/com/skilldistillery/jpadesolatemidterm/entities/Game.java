package com.skilldistillery.jpadesolatemidterm.entities;

import javax.persistence.*;
import java.util.*;

@Entity
public class Game {
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private int id;
	private String title;
	@Column(name="max_players")
	private int maxPlayers;	
	@ManyToOne
	@JoinColumn(name="platform")
	private Platform platform;
	
	@ManyToMany(mappedBy="games")
	private List<User> users;
	
	@OneToMany(mappedBy="event")
	private List<Game> games;
	
	
	
	//add and remove users
	
	
	public void addUser(User user) {
		if(users == null) users = new ArrayList<>();
		
		if(!users.contains(user)) {
			users.add(user);
			user.addGame(this);
		}
	}
	
	public void removeUser(User user) {
		if(users != null && users.contains(user)) {
			users.remove(user);
			user.removeGame(this);
		}
	}
	
	
	
	//getters and setters
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getMaxPlayers() {
		return maxPlayers;
	}
	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}
	public Platform getPlatform() {
		return platform;
	}
	public void setPlatform(Platform platform) {
		this.platform = platform;
	}
	public int getId() {
		return id;
	}
	
	//hash
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + maxPlayers;
		result = prime * result + ((platform == null) ? 0 : platform.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	
	//equals
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		if (id != other.id)
			return false;
		if (maxPlayers != other.maxPlayers)
			return false;
		if (platform == null) {
			if (other.platform != null)
				return false;
		} else if (!platform.equals(other.platform))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	//toString
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Game [id=");
		builder.append(id);
		builder.append(", title=");
		builder.append(title);
		builder.append(", maxPlayers=");
		builder.append(maxPlayers);
		builder.append(", platform=");
		builder.append(platform);
		builder.append("]");
		return builder.toString();
	}
	
}
