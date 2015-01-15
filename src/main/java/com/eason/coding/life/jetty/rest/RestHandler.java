package com.eason.coding.life.jetty.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@RequestMapping("/blog")
public class RestHandler {
	@RequestMapping(value = "/blog/{id}", method = RequestMethod.GET)
	public void handleGet(@PathVariable Long id, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		System.out.println("handleGet");
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);

		response.getWriter().println("<h1>Hello World</h1>");
		response.getWriter().println("<li>Request blog id: " + id + "</li>");
		response.getWriter().println(
				"<li>Server port: " + request.getServerPort() + "</li>");
	}
}