package pl.mibar.shipWars.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

@Controller
public class ChatController {

    @Autowired
    private ChatMessagesService messagesService;

    @RequestMapping("chat.html")
    public ModelAndView getChatPage(HttpSession session) {
        System.out.println(session.getAttribute("test"));
        session.setAttribute("test", "aaaa");
        ModelAndView mav = new ModelAndView("chat");
        mav.addObject("messages", messagesService.getMessages());
        mav.addObject("test1", "hello");
        return mav;
    }

    @RequestMapping("chatMessage.html")
    public String addChatMessage(String message) {
        messagesService.addMessage(message);
        return "redirect:chat.html";
    }

    @RequestMapping(value = "chatMessages.html", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public List<String> getChatMessages() {
        return messagesService.getMessages();
    }

}
