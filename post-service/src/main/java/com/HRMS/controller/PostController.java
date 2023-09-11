package com.HRMS.controller;

import com.HRMS.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.HRMS.constants.RestApiList.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(POST)
public class PostController {
    private final PostService service;
}
