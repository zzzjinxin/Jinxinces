package com.aaa.jinxin.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName 金鑫
 * @Description: TODO
 * @Author yinwe
 * @Date 2020/1/27
 * @Version V1.0
 **/
@Controller
public class IndexController {
    @GetMapping("/")
    public String Hello(){

            return "index";
    }
}
