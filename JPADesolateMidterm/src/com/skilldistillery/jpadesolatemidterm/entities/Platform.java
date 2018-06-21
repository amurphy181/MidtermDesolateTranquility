package com.skilldistillery.jpadesolatemidterm.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Platform {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private int id;
	@Column(name="name")
	private String platformName;
	
	@OneToMany(mappedBy="platform")
	List<Game> games;
	
	
	
	
	//add remove method
	
	public void addGame(Game game) {
		if(games == null) games = new ArrayList<>();
		
		if(!games.contains(game)) {
			games.add(game);
			if(game.getPlatform() != null) {
				game.getPlatform().getGames().remove(game);
			}
			
			game.setPlatform(this);
		}
	}
	
	public void removeGame(Game game) {
		game.setPlatform(null);
		if(games != null) {
			games.remove(game);
		}
	}
	
	//getters setters

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public int getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((games == null) ? 0 : games.hashCode());
		result = prime * result + id;
		result = prime * result + ((platformName == null) ? 0 : platformName.hashCode());
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
		Platform other = (Platform) obj;
		if (games == null) {
			if (other.games != null)
				return false;
		} else if (!games.equals(other.games))
			return false;
		if (id != other.id)
			return false;
		if (platformName == null) {
			if (other.platformName != null)
				return false;
		} else if (!platformName.equals(other.platformName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Platform [id=");
		builder.append(id);
		builder.append(", platformName=");
		builder.append(platformName);
		builder.append("]");
		return builder.toString();
	}
	
	
	//getters and setters
	
	
}
