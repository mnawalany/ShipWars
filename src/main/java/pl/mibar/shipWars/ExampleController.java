package pl.mibar.shipWars;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExampleController {

    @RequestMapping("exampleController.html")
    public ModelAndView example() {
        ModelAndView mav = new ModelAndView("hello");
        mav.addObject("message", "this is some dynamic message");
        return mav;
    }

    @RequestMapping("/main.html")
    public ModelAndView main() {
        ModelAndView mav = new ModelAndView("hello");
        mav.addObject("message", "this is some dynamic message in main");
        return mav;
    }

}
