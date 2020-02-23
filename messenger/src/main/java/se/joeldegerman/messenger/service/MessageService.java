package se.joeldegerman.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import se.joeldegerman.messenger.database.DatabaseClass;
import se.joeldegerman.messenger.model.Message;

public class MessageService {

	private Map<Long, Message> messages = DatabaseClass.getMessages();

	public MessageService() {
		messages.put(1L, new Message(1, "Hello world", "Joel"));
		messages.put(2L, new Message(2, "Hello jersey", "Joel"));
		messages.put(3L, new Message(3, "Hello jpa", "Joel"));
		messages.put(4L, new Message(4, "Hello data", "Joel"));
	}

	public List<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values());
	}

	public List<Message> getAllMessagesForYear(int year) {
		List<Message> list = new ArrayList<Message>();
		Calendar cal = Calendar.getInstance();
		for (Message message : getAllMessages()) {
			cal.setTime(message.getCreated());
			if (cal.get(Calendar.YEAR) == year) {
				list.add(message);
			}
		}
		return list;
	}

	public List<Message> getallMessagesPaginated(int start, int size) {
		//This because list is zero based and you want it to be one based, so start = 1 would mean index two without the subtraction of 1
		start -= 1;
		List<Message> list = getAllMessages();
		if (start + size > list.size())
			return new ArrayList<Message>();
		return list.subList(start , start + size);
	}

	public Message getMessage(long id) {
		return messages.get(id);
	}

	public Message addMessage(Message message) {
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}

	public Message updateMessage(Message message) {
		if (message.getId() <= 0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}

	public Message removeMessage(long id) {
		return messages.remove(id);
	}
}
