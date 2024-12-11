package com.practice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 * com.practice.controller
 *   |_ AdminController
 * </pre>
 * 
 * @Author  : subin1
 * @Date    : 12/11/24 8:43 AM
 * @description : AdminController
 */
@RestController
public class AdminController {

    @GetMapping("/admin")
    public String adminP() {
        return "admin Controller";
    }
}
