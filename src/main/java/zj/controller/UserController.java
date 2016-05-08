package zj.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import zj.common.LVLogger;
import zj.service.UserServiceI;

@Controller
@RequestMapping("/user")
public class UserController {
    LVLogger logger = LVLogger.getLogger(this.getClass());
	
	@Autowired
	private UserServiceI userServiceI; 
	
	@RequestMapping(value="/{id}/get")
	@ResponseBody
	public Map<String ,Object> getUser(@PathVariable String id,HttpServletRequest request){
		Map<String,Object> map= new HashMap<String,Object>();
	
			
 				logger.error("用户ID为用户ID为用户ID为用户ID为用户ID为用户ID为用户ID为用户ID为用户ID为用户ID为用户ID为用户ID为用户ID为用户ID为用户ID为用户ID为用户ID为用户ID为用户ID为用户ID为用户ID为用户ID为用户ID为用户ID为");
 		
		logger.debug("用户ID为"+id+"的用户信息为"+userServiceI.getUserById(id));
		logger.debug("用户ID为"+id+"的用户信息为"+userServiceI.getUserById(id));
		map.put("result", userServiceI.getUserById(id));
		System.out.println(request.getRequestURI());
		
		
		return map;
	}
	@RequestMapping(value="/get")
	public String index(){
		return "index";
	}
	}
