package com.rectus29.catfeeder.filters;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rectus29.catfeeder.CatFeederApplication;
import com.rectus29.catfeeder.CatFeederConfiguration;

import javax.servlet.*;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*-----------------------------------------------------*/
/*      _____           _               ___   ___      */
/*     |  __ \         | |             |__ \ / _ \     */
/*     | |__) |___  ___| |_ _   _ ___     ) | (_) |    */
/*     |  _  // _ \/ __| __| | | / __|   / / \__, |    */
/*     | | \ \  __/ (__| |_| |_| \__ \  / /_   / /     */
/*     |_|  \_\___|\___|\__|\__,_|___/ |____| /_/      */
/*                                                     */
/*                Date: 04/10/2018 10:30               */
/*                 All right reserved                  */
/*-----------------------------------------------------*/

public class CatFeederFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
		CatFeederApplication cfa = CatFeederApplication.getInstance();
		HttpServletRequest httpReq = ((HttpServletRequest) req);
		HttpServletResponse httpResp = ((HttpServletResponse) resp);
		if("GET".equals(httpReq.getMethod())){
			//if get set json into response
			httpReq.setAttribute("jsonData", cfa.printState().toString());
		}else if("POST".equals(httpReq.getMethod())){
			try {
				//if post save the data and set json into response
				JsonElement jsonElement = new JsonParser().parse(httpReq.getParameter("jsonData"));
				CatFeederApplication.getInstance().applyNewConfiguration(jsonElement);
			}catch(Exception ex){

			}
			httpReq.setAttribute("jsonData", cfa.printState().toString());
		}
		//let go the request
		chain.doFilter(req, resp);
	}

	public void init(FilterConfig config) throws ServletException {

	}

}
