package com.naveed.oauth2.authorizationserver.model;

public class Message {
	private Long id;
	private String content;
	private String sender;
	private String timestamp;

	public Message() {
	}

	public Message(Long id, String content, String sender, String timestamp) {
		this.id = id;
		this.content = content;
		this.sender = sender;
		this.timestamp = timestamp;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}
