package com.skilldistillery.jpadesolatemidterm.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class Message {
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private int id;
	private String content;
	@Column(name="time_sent")
	private Date timeSent;
	
	@ManyToOne
	@JoinColumn(name = "event_id")
	private Event event;
	@OneToOne
	@JoinColumn(name="sender_id")
	private User user;

	
	
	
	//getters setters
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTimeSent() {
		return timeSent;
	}

	public void setTimeSent(Date timeSent) {
		this.timeSent = timeSent;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public int getId() {
		return id;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((event == null) ? 0 : event.hashCode());
		result = prime * result + id;
		result = prime * result + ((timeSent == null) ? 0 : timeSent.hashCode());
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
		Message other = (Message) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (event == null) {
			if (other.event != null)
				return false;
		} else if (!event.equals(other.event))
			return false;
		if (id != other.id)
			return false;
		if (timeSent == null) {
			if (other.timeSent != null)
				return false;
		} else if (!timeSent.equals(other.timeSent))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Message [id=");
		builder.append(id);
		builder.append(", content=");
		builder.append(content);
		builder.append(", timeSent=");
		builder.append(timeSent);
		builder.append(", event=");
		builder.append(event);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	
}
