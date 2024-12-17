package com.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <pre>
 * com.practice.controller
 *   |_ MainController
 * </pre>
 *
 * @Author : subin1
 * @Date : 12/11/24 8:42 AM
 * @description : MainController
 */

@Controller
public class MainController {

    @GetMapping("/")
    public String mainP() {
        return "main";
    }
}
