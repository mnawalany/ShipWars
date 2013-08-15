package pl.mibar.shipWars.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.List;

@Controller
public class ChatController {

    @Autowired
    private ChatMessagesService messagesService;

    @RequestMapping("chat.html")
    public ModelAndView getChatPage() {
        ModelAndView mav = new ModelAndView("chat");
        mav.addObject("messages", messagesService.getMessages());
        return mav;
    }

    @RequestMapping("chatMessage.html")
    public ModelAndView addChatMessage(String message) {
        messagesService.addMessage(message);
        return getChatPage();
    }

    @RequestMapping(value = "chatMessages.html", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public List<String> getChatMessages() {
        return messagesService.getMessages();
    }

}
