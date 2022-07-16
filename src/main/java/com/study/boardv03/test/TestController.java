package com.study.boardv03.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @Autowired
    TestMapper testMapper;

    @GetMapping("/test")
    public String test(Model model){
        int one = testMapper.getOne();
        model.addAttribute("one", one);
        return "test";
    }
}
