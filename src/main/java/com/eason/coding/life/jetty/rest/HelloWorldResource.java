package com.eason.coding.life.jetty.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class HelloWorldResource {
	@RequestMapping(value = "hello", method = RequestMethod.GET)
	@ResponseBody
	public String sayHello() {
		return "hello";
	}
}
