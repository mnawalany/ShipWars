package pl.mibar.shipWars;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(value = "/calculate.html", method = RequestMethod.GET)
    public String getCalculatePage() {
        return "calculateResult";
    }

    @RequestMapping(value = "/calculate.html", method = RequestMethod.POST)
    public ModelAndView performCalculation(@RequestParam int a, @RequestParam int b) {
        ModelAndView mav = new ModelAndView("calculateResult");
        mav.addObject("a", a);
        mav.addObject("b", b);
        mav.addObject("result", a+b);
        return mav;
    }


}
