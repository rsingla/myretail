package com.target.retail.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductHomeController {

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public ModelAndView document(Locale locale, Model model, HttpServletRequest request) {

    String baseUrl =
        String.format("%s://%s:%d/", request.getScheme(), request.getServerName(), request.getServerPort());

    return new ModelAndView("redirect:" + baseUrl + "myretail/swagger-ui.html");
  }
}
