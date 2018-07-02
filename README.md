# gwt-keycloak

[![Build Status](https://secure.travis-ci.org/realityforge/gwt-keycloak.png?branch=master)](http://travis-ci.org/realityforge/gwt-keycloak)
[<img src="https://img.shields.io/maven-central/v/org.realityforge.gwt.keycloak/gwt-keycloak.svg?label=latest%20release"/>](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22org.realityforge.gwt.keycloak%22%20a%3A%22gwt-keycloak%22)

A simple library to provide keycloak support to GWT. The library wraps and adapts the
[keycloak adapter](https://www.keycloak.org/docs/3.0/securing_apps/topics/oidc/javascript-adapter.html)
that is deployed on the keycloak server.

## Quick Start

The simplest way to use the library is to add the following dependency
into the build system. i.e.

```xml
<dependency>
   <groupId>org.realityforge.gwt.keycloak</groupId>
   <artifactId>gwt-keycloak</artifactId>
   <version>0.2</version>
   <scope>provided</scope>
</dependency>
```

Then you add the following snippet into the .gwt.xml file.

```xml
<module rename-to='myapp'>
  ...

  <!-- Enable the keycloak library -->
  <inherits name="org.realityforge.gwt.keycloak.Keycloak"/>
</module>
```

Then you can interact with the Keycloak object from within the browser.

First you need to setup a Keycloak instance. Assuming your keycloak server has an application client
named `"MyApp"` and the configuration json is at `http://127.0.0.1:8080/myapp/keycloak.json` then you
end up with code like:

```java
final Keycloak keycloak = new Keycloak( "MyApp", "http://127.0.0.1:8080/myapp/keycloak.json" );

keycloak.setListener( new KeycloakListenerAdapter()
{
  @Override
  public void onReady( @Nonnull final Keycloak keycloak, final boolean authenticated )
  {
    if( authenticated )
    {
      //Already authenticated, start app here
    }
    else
    {
      keycloak.login();
    }
  }
} );

keycloak.init();
```

When the token is needed to interact with a keycloak protected resource you can simply use the following
code to access the current token, updating it if it is stale and needs to be refreshed.

```java
final int minTokenValiditySeconds = 30;
keycloak.updateToken( minTokenValiditySeconds, () -> remoteCallUsingToken( keycloak.getToken() ) );
```

This should be sufficient to put together a simple Keycloak application.

## Appendix

* [Keycloak Javascript Adapter](https://keycloak.gitbooks.io/securing-client-applications-guide/content/v/2.0/topics/oidc/javascript-adapter.html)
