package com.practice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 * com.practice.controller
 *   |_ MainController
 * </pre>
 * 
 * @Author  : subin1
 * @Date    : 12/11/24 8:42 AM
 * @description : MainController
 */

@RestController
public class MainController {

    @GetMapping("/")
    public String mainP() {
        return "main Controller";
    }
}
