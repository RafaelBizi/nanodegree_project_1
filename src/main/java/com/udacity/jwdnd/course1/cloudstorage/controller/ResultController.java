package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author RafaelBizi
 * @created 10/06/2021 - 09:03
 * @project nanodegree_project_1
 */
@Controller

public class ResultController {

    @GetMapping("/result")
    public String resultView() {
        return "result";
    }

}
