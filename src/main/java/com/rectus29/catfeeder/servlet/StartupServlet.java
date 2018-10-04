package com.rectus29.catfeeder.servlet;

import com.rectus29.catfeeder.CatFeederApplication;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/*-----------------------------------------------------*/
/*      _____           _               ___   ___      */
/*     |  __ \         | |             |__ \ / _ \     */
/*     | |__) |___  ___| |_ _   _ ___     ) | (_) |    */
/*     |  _  // _ \/ __| __| | | / __|   / / \__, |    */
/*     | | \ \  __/ (__| |_| |_| \__ \  / /_   / /     */
/*     |_|  \_\___|\___|\__|\__,_|___/ |____| /_/      */
/*                                                     */
/*                Date: 03/10/2018 10:29               */
/*                 All right reserved                  */
/*-----------------------------------------------------*/
public class StartupServlet extends HttpServlet {

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		CatFeederApplication.getInstance().initApplication();
	}
}
