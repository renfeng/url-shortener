package com.google.developers.url.http;

import com.google.inject.servlet.ServletModule;

public class DefaultServletModule extends ServletModule {

	@Override
	protected void configureServlets() {

		/*
		 * /api/401/;jsessionid=37fycpy88nx7
		 */
		serve("/api/401/*").with(UnauthorizedServlet.class);

		/*
		 * it doesn't work to serve the root, i.e. just a slash, which goes to a welcome file, see web.xml
		 */
		serve("/api/url/shorten").with(UrlShortenerServlet.class);
//		serve("/*").with(UrlShortenerServlet.class);
		serveRegex("/(?!index.html|favicon.ico|sw.js).*").with(UrlShortenerServlet.class);
	}
}
