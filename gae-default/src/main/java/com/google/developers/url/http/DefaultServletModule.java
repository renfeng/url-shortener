package com.google.developers.url.http;

import com.google.inject.servlet.ServletModule;

public class DefaultServletModule extends ServletModule {

	@Override
	protected void configureServlets() {

		/*
		 * /api/401/;jsessionid=37fycpy88nx7
		 */
		serve("/api/401/*").with(UnauthorizedServlet.class);

		serve("/api/url/shorten").with(UrlShortenerServlet.class);

		serveRegex("/(?!index.html|favicon.ico).+").with(UrlShortenerServlet.class);
	}
}
