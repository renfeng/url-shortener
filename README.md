Setup a project on https://console.developers.google.com

Create a set of client credentials (client id and secret) for web application

Get a refresh  token from Google API Playground with the client id and secret just created.

Get the source code

```
git clone https://github.com/renfeng/url-shortener
```

Set appengine.app.id in url-shortener/pom.xml

Copy
url-shortener/shared/src/main/resource/message.properties.example
as
url-shortener/shared/src/main/resource/message.properties

Set client id, secret, and refresh token.

Install Polymer and its components

```
bower install --save Polymer/polymer#^1.0.0
bower install --save PolymerElements/paper-input
bower install --save PolymerElements/paper-button
bower install --save PolymerElements/iron-ajax
bower install --save PolymerElements/iron-image
```

Compile, and run locally (GAE devserver)

```
cd url-shortener
mvn
cd gae-default
mvn
```

To upload to GAE

```
mvn appengine:update
```

A live site, http://goto.dushu.hu/
