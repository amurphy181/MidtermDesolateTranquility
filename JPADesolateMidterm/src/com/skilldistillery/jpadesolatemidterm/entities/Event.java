package com.skilldistillery.jpadesolatemidterm.entities;

import java.util.*;
import javax.persistence.*;

@Entity
public class Event {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	@Column(name = "start_date")
	private Date startDate;
	private int status;
	private String location;
	private int visibility;
	
	@ManyToOne
	@JoinColumn(name="creator_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "game_id")
	private Game game;

	@ManyToMany(mappedBy = "events")
	private List<User> users;

	public void addUser(User user) {
		if (users == null)
			users = new ArrayList<>();

		if (!users.contains(user)) {
			users.add(user);
			user.addEvent(this);
		}
	}

	public void removeUser(User user) {
		if (users == null && users.contains(user)) {
			users.remove(user);
			user.removeEvent(this);
		}
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getVisibility() {
		return visibility;
	}

	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public int getId() {
		return id;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((game == null) ? 0 : game.hashCode());
		result = prime * result + id;
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + status;
		result = prime * result + ((users == null) ? 0 : users.hashCode());
		result = prime * result + visibility;
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
		Event other = (Event) obj;
		if (game == null) {
			if (other.game != null)
				return false;
		} else if (!game.equals(other.game))
			return false;
		if (id != other.id)
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (status != other.status)
			return false;
		if (users == null) {
			if (other.users != null)
				return false;
		} else if (!users.equals(other.users))
			return false;
		if (visibility != other.visibility)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Event [id=");
		builder.append(id);
		builder.append(", startDate=");
		builder.append(startDate);
		builder.append(", status=");
		builder.append(status);
		builder.append(", location=");
		builder.append(location);
		builder.append(", visibility=");
		builder.append(visibility);
		builder.append(", users=");
		builder.append(users);
		builder.append("]");
		return builder.toString();
	}

}
