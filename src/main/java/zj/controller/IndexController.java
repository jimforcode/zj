package zj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping("/main")
	public String index() {
		System.out.println("------------------------------------------------");
		return "/WEB-INF/view/index";
	}
}
