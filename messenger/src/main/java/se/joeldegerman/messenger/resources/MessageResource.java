package se.joeldegerman.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import se.joeldegerman.messenger.model.Message;
import se.joeldegerman.messenger.service.MessageService;

@Path("messages")
public class MessageResource {
	
	MessageService messageService = new MessageService();

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Message> getMessages() {
		return messageService.getAllMessages();
	}
	
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Message getMessageById(@PathParam("id") long id) {
		return messageService.getMessage(id);
	}
	
	@POST
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Message addMessage(Message message) {
		return messageService.addMessage(message);
	}
	
	@PUT
	@Path("{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Message updateMessage(Message message, @PathParam("id") long id) {
		message.setId(id);
		return messageService.updateMessage(message);
	}
	
	@DELETE
	@Path("{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Message deleteMessage(@PathParam("id") long id) {
		return messageService.removeMessage(id);
	}
	
}
