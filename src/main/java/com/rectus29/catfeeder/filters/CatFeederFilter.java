package com.rectus29.catfeeder.filters;

import javax.servlet.*;
import java.io.IOException;

/*-----------------------------------------------------*/
/*      _____           _               ___   ___      */
/*     |  __ \         | |             |__ \ / _ \     */
/*     | |__) |___  ___| |_ _   _ ___     ) | (_) |    */
/*     |  _  // _ \/ __| __| | | / __|   / / \__, |    */
/*     | | \ \  __/ (__| |_| |_| \__ \  / /_   / /     */
/*     |_|  \_\___|\___|\__|\__,_|___/ |____| /_/      */
/*                                                     */
/*                Date: 04/10/2018 10:30                */
/*                 All right reserved                  */
/*-----------------------------------------------------*/

public class CatFeederFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
		chain.doFilter(req, resp);
	}

	public void init(FilterConfig config) throws ServletException {

	}

}
