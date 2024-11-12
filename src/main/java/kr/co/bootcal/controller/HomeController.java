package kr.co.bootcal.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(){

        return "index";
    }
    @GetMapping(value = "/testLogin")
    public String testMain(){
        return "testLogin";
    }
    @GetMapping(value = "/testMain")
    public String test(HttpServletRequest req){
        HttpSession session = req.getSession();
        if(session.getAttribute("nickname") == null){
            return "/testLogin";
        }
        return "testMain";
    }

    @GetMapping(value = "/testChat")
    public String testChat(){
        return "testChat";
    }

    @GetMapping(value = "/testBoard")
    public String testBoard(){
        System.out.println("??");
        return "/testBoard";
    }
}
