package com.naveed.oauth2.authorizationserver.controller;

import com.naveed.oauth2.authorizationserver.model.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

	private final List<Message> messages = new ArrayList<>();
	private final AtomicLong counter = new AtomicLong(1);
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public MessageController() {
		messages.add(new Message(counter.getAndIncrement(), "Welcome to the Message API!", "System",
			LocalDateTime.now().format(formatter)));
		messages.add(new Message(counter.getAndIncrement(), "This is a sample message.", "User1",
			LocalDateTime.now().format(formatter)));
	}

	@GetMapping
	public ResponseEntity<List<Message>> getAllMessages() {
		return ResponseEntity.ok(messages);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Message> getMessageById(@PathVariable Long id) {
		return messages.stream()
				.filter(msg -> msg.getId().equals(id))
				.findFirst()
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Message> createMessage(@RequestBody Message message,
			@AuthenticationPrincipal Jwt jwt) {
		String sender = jwt != null ? jwt.getClaimAsString("sub") : message.getSender();
		if (sender == null || sender.isEmpty()) {
			sender = "Anonymous";
		}

		Message newMessage = new Message(
			counter.getAndIncrement(),
			message.getContent(),
			sender,
			LocalDateTime.now().format(formatter)
		);
		messages.add(newMessage);
		return ResponseEntity.ok(newMessage);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Message> updateMessage(@PathVariable Long id,
			@RequestBody Message updatedMessage) {
		return messages.stream()
				.filter(msg -> msg.getId().equals(id))
				.findFirst()
				.map(message -> {
					message.setContent(updatedMessage.getContent());
					message.setSender(updatedMessage.getSender());
					message.setTimestamp(LocalDateTime.now().format(formatter));
					return ResponseEntity.ok(message);
				})
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
		boolean removed = messages.removeIf(msg -> msg.getId().equals(id));
		return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}

	@GetMapping("/count")
	public ResponseEntity<Long> getMessageCount() {
		return ResponseEntity.ok((long) messages.size());
	}
}
