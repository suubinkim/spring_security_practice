package com.practice.controller;

import com.practice.dto.JoinDto;
import com.practice.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 * com.practice.controller
 *   |_ JoinController
 * </pre>
 * 
 * @Author  : subin1
 * @Date    : 12/11/24 10:53 AM
 * @description : JoinController
 */
@RestController
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @PostMapping("/join")
    public String join(@RequestBody JoinDto joinDto) {
        joinService.joinProcess(joinDto);

        return "ok";
    }

}
