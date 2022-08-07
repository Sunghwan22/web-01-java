package com.makao.Bank.controllers;

import com.makao.Bank.views.HomePageGenerator;
import com.makao.Bank.views.PageGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
  @GetMapping("/")
  public String home() {
    PageGenerator pageGenerator = new HomePageGenerator();
    return pageGenerator.html();
  }
}
