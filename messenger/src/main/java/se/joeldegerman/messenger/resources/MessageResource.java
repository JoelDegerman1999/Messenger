package se.joeldegerman.messenger.resources;

import java.util.List;

import javax.ws.rs.BeanParam;
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
import se.joeldegerman.messenger.resources.beans.MessageFilterBean;
import se.joeldegerman.messenger.service.MessageService;

@Path("messages")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class MessageResource {

	MessageService messageService = new MessageService();

	@GET
	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean) {
		if (filterBean.getYear() > 0) {
			return messageService.getAllMessagesForYear(filterBean.getYear());
		}
		if (filterBean.getStart() > 0 && filterBean.getSize() > 0) {
			return messageService.getallMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
		return messageService.getAllMessages();
	}

	@GET
	@Path("{id}")
	public Message getMessageById(@PathParam("id") long id) {
		return messageService.getMessage(id);
	}

	@POST
	public Message addMessage(Message message) {
		return messageService.addMessage(message);
	}

	@PUT
	@Path("{id}")
	public Message updateMessage(Message message, @PathParam("id") long id) {
		message.setId(id);
		return messageService.updateMessage(message);
	}

	@DELETE
	@Path("{id}")
	public Message deleteMessage(@PathParam("id") long id) {
		return messageService.removeMessage(id);
	}
	
	@Path("{messageId}" + "/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}

}
