package zj.common;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.gson.Gson;

/**
 * 切面类，对拦截的方法进行判断
 * @author wujun
 */
@Aspect
@Component
public class OperatorAspect implements Ordered{

	LVLogger logger = LVLogger.getLogger(OperatorAspect.class);
	
	
	
	@After(value="@annotation(opertorlog)")
	public void logAfter(Opertorlog opertorlog) {
		try {
			// 获取被拦截方法参数
			Map<String, String[]> parameter = getParameters();
			Gson gson = new Gson();
			String parameters = gson.toJson(parameter);
			
			if(!("null").equals(parameter.get("j_password"))|| !("null").equals(parameter.get("passWord"))){//删除用户登录密码
				@SuppressWarnings("unchecked")
				Map<String, String> map = gson.fromJson(parameters, Map.class);
				map.remove("j_password");
				map.remove("passWord");
				parameters = gson.toJson(map);
			}
			logger.error("after"+opertorlog.functionPath());
			
			//日志对象
//			Log log = new Log();
//			if (opertorlog != null) {
//				log.setFunctionpath(opertorlog.functionPath());//功能路径
//				log.setOperatetype(opertorlog.operateType());//操作类型
//				log.setLogcontent(opertorlog.name() + ":" + parameters);//日志内容
//			}
//			log.setIpaddress(getIpAddr());//IP地址
//			log.setOperateurl(getPathUrl());//请求url
//			log.setUsername(getUserName());//用户名称
//			log.setBroswer(BrowseTool.checkBrowse(getBroswer()));//浏览器类型
//			
//			//保存日志信息
//			logDaoImpl.add(log);
		} catch (Exception ex) {
			logger.error("记录日志保存数据异常", ex);
		}

	}
	
	@Before(value="@annotation(opertorBeforelog)")
	public void logBefore(OpertorBeforelog opertorBeforelog) {
		try {
			// 获取被拦截方法参数
			Map<String, String[]> parameter = getParameters();
			Gson gson = new Gson();
			String parameters = gson.toJson(parameter);
			logger.error("before"+opertorBeforelog.functionPath());
//			//日志对象
//			Log log = new Log();
//			if (opertorBeforelog != null) {
//				log.setFunctionpath(opertorBeforelog.functionPath());//功能路径
//				log.setOperatetype(opertorBeforelog.operateType());//操作类型
//				log.setLogcontent(opertorBeforelog.name() + ":" + parameters);//日志内容
//			}
//			log.setIpaddress(getIpAddr());//IP地址
//			log.setOperateurl(getPathUrl());//请求url
//			log.setUsername(getUserName());//用户名称
//			log.setBroswer(BrowseTool.checkBrowse(getBroswer()));//浏览器类型
//			
//			//保存日志信息
//			logDaoImpl.add(log);
		} catch (Exception ex) {
			logger.error("记录日志保存数据异常", ex);
		}
		
	}
	@SuppressWarnings("unchecked")
	public Map<String, String[]> getParameters() {
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getParameterMap();
	}

	

	/**
//	 * 获取当前登录用户
//	 * @return
//	 */
//	public String getUserName() {
//		return ((User) ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession().getAttribute("user")).getUsername();
//	}

	/**
	 * 获取IP地址
	 * @return
	 */
	public String getIpAddr() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		
		String ip = request.getHeader("x-forwarded-for"); 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("Proxy-Client-IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("WL-Proxy-Client-IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("HTTP_CLIENT_IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getRemoteAddr(); 
	    } 
	    return ip; 
		
//		String ipAddress = null;
//		ipAddress = request.getHeader("x-forwarded-for");
//		if (ipAddress == null || ipAddress.length() == 0
//				|| "unknown".equalsIgnoreCase(ipAddress)) {
//			ipAddress = request.getHeader("Proxy-Client-IP");
//		}
//		if (ipAddress == null || ipAddress.length() == 0
//				|| "unknown".equalsIgnoreCase(ipAddress)) {
//			ipAddress = request.getHeader("WL-Proxy-Client-IP");
//		}
//		if (ipAddress == null || ipAddress.length() == 0
//				|| "unknown".equalsIgnoreCase(ipAddress)) {
////			ipAddress = request.getRemoteAddr();
////			if (ipAddress.equals("127.0.0.1")) {
//				// 根据网卡取本机配置的IP
//				InetAddress inet = null;
//				try {
//					inet = InetAddress.getLocalHost();
//				} catch (UnknownHostException e) {
//					e.printStackTrace();
//				}
//				ipAddress = inet.getHostAddress();
////			}
//
//		}
//
//		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
//		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
//			if (ipAddress.indexOf(",") > 0) {
//				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
//			}
//		}
//		return ipAddress;
	}
	
	/**
	 * 获取操作URL
	 * @return
	 */
	public String getPathUrl() {
		return (((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest()).getServletPath();
	}
	
	/**
	 * 获取用户浏览器类型
	 * @return
	 */
	public String getBroswer() {
		return (((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest()).getHeader("User-Agent");
	}

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

	


}