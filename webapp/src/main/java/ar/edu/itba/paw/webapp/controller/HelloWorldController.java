package ar.edu.itba.paw.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloWorldController {

    @RequestMapping("/")
    public ModelAndView helloWorld() {
        final ModelAndView ret = new ModelAndView("index");

        ret.addObject("alo", "HelloWorld");
        return ret;
    }
}