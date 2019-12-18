package org.gumplab.mvc.controller;

import org.gumplab.mvc.annotation.Controller;
import org.gumplab.mvc.annotation.RequestMapping;

@Controller
@RequestMapping("/study")
public class TestController {

    @RequestMapping("/mvcTest")
    public String mvcTest() {
        return "hand write mvc！";
    }


}
