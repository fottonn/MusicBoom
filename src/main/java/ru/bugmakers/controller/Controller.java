package ru.bugmakers.controller;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ivan
 */
@RestController
@RequestMapping("/")
public class Controller {

    @RequestMapping(method = RequestMethod.GET, value = "test")
    @ResponseBody
    public String testRest(){
        return null;
    }
}
