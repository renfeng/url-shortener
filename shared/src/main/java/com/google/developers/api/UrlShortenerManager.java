package com.google.developers.api;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.services.urlshortener.Urlshortener;
import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * Created by frren on 2015-09-01.
 */
public class UrlShortenerManager extends ClientManager<Urlshortener> {

	private final HttpTransport transport;
	private final JsonFactory jsonFactory;

	@Inject
	public UrlShortenerManager(
			@Named("refreshToken") String refreshToken,
			@Named("clientId") String clientId,
			@Named("clientSecret") String clientSecret,
			HttpTransport transport, JsonFactory jsonFactory) {

		super(refreshToken, clientId, clientSecret, transport, jsonFactory);

		this.transport = transport;
		this.jsonFactory = jsonFactory;
	}

	@Override
	protected void updateCredential(GoogleCredential credential) {

		Urlshortener urlshortener = new Urlshortener.Builder(transport, jsonFactory, credential)
				.setApplicationName("GDG Event Management").build();

		setClient(urlshortener);
	}
}
