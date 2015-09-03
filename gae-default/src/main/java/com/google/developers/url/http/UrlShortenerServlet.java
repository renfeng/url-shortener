package com.google.developers.url.http;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.urlshortener.Urlshortener;
import com.google.api.services.urlshortener.model.Url;
import com.google.developers.api.UrlShortenerManager;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by frren on 2015-09-01.
 */
@Singleton
public class UrlShortenerServlet extends HttpServlet {

	private final UrlShortenerManager urlShortenerManager;

	@Inject
	public UrlShortenerServlet(UrlShortenerManager urlShortenerManager) {
		this.urlShortenerManager = urlShortenerManager;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		/*
		 * requestURI will never be just a slash (length == 1), see the comments in DefaultServletModule
		 */
		String shortUrl = "http://goo.gl" + req.getRequestURI();
		try {
			Url longUrl = urlShortenerManager.getClient()
					.url().get(shortUrl).execute();
			resp.sendRedirect(longUrl.getLongUrl());
		} catch (GoogleJsonResponseException ex) {
			resp.setStatus(ex.getStatusCode());
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/plain");

		Url longUrl = new Url().setLongUrl(IOUtils.toString(req.getInputStream()));
		Url url = urlShortenerManager.getClient().url().insert(longUrl).execute();
		resp.getWriter().print(url.getId()
				.replaceFirst("http://goo.gl", "http://dushu.hu")
				.replaceFirst("https://goo.gl", "http://dushu.hu"));
	}
}
