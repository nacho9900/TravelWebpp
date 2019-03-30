package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private UserService us;

    @RequestMapping("/")
    public ModelAndView index() { return new ModelAndView("index"); }

    @RequestMapping("/home")
    public ModelAndView home() { return new ModelAndView("home"); }

    @RequestMapping("/signup")
    public ModelAndView signup() { return new ModelAndView("signup"); }

    @RequestMapping("/signin")
    public ModelAndView signin() { return new ModelAndView("signin"); }

    @RequestMapping(value = "/signin", method = {RequestMethod.POST})
    public ModelAndView validateSignIn(@RequestParam String username,
                                       @RequestParam String password) {
        ModelAndView mav = new ModelAndView("home");

            //User user = us.findByUsername(username).orElseThrow(UserNotFoundException::new);
            Optional<User> user = us.findByUsername(username);
            if(user.isPresent())
                if(user.get().getPassword().equals(password)) {
                    return mav;
            }
            //wrong password

        //catch(UserNotFoundException e) {
            //wrong username and maybe password too
        //}
        mav.setViewName("signin");
        return mav;
    }

    @RequestMapping(value = "/signup", method = {RequestMethod.POST})
    public ModelAndView validateSignUp(@RequestParam String first_name,
                                       @RequestParam String last_name,
                                       @RequestParam String email,
                                       @RequestParam String password,
                                       @RequestParam String psw_repeat ) {


        ModelAndView mav = new ModelAndView("index");
        return mav;
    }

}
