package com.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping( "/")
public class MainController {
    public ModelAndView main() {
        return new ModelAndView("redirect:/device");
    }

}
