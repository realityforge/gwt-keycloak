package org.realityforge.gwt.keycloak;

import akasha.promise.Promise;
import com.google.gwt.core.client.JavaScriptObject;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsNonNull;
import jsinterop.annotations.JsNullable;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType( isNative = true, namespace = JsPackage.GLOBAL, name = "Keycloak" )
public class KeycloakImpl
{
  @JsConstructor
  public KeycloakImpl( @SuppressWarnings( "unused" ) @JsNonNull String configUrl )
  {
  }

  @JsProperty( name = "authenticated" )
  public native boolean authenticated();

  @JsProperty( name = "token" )
  @JsNullable
  public native String token();

  @JsProperty( name = "tokenParsed" )
  @JsNullable
  public native JavaScriptObject tokenParsed();

  @JsProperty( name = "subject" )
  @JsNullable
  public native String subject();

  @JsProperty( name = "idToken" )
  @JsNullable
  public native String idToken();

  @JsProperty( name = "idTokenParsed" )
  @JsNullable
  public native String idTokenParsed();

  @JsProperty( name = "refreshToken" )
  @JsNullable
  public native String refreshToken();

  @JsProperty( name = "refreshTokenParsed" )
  @JsNullable
  public native JavaScriptObject refreshTokenParsed();

  @JsMethod( name = "init" )
  public native void init( @JsNonNull KeycloakInitOptions options );

  @JsMethod( name = "login" )
  public native void login();

  @JsMethod( name = "login" )
  public native void login( @JsNonNull KeycloakLoginOptions options );

  @JsMethod( name = "isTokenExpired" )
  public native boolean isTokenExpired( int minValiditySeconds );

  @JsMethod( name = "logout" )
  public native void logout();

  @JsMethod( name = "logout" )
  public native void logout( @JsNonNull KeycloakLogoutOptions options );

  @JsMethod( name = "updateToken" )
  public native UpdateResponse updateToken( int minValiditySeconds );

  /**
   * Clear authentication state, including tokens.
   * This can be useful if application has detected the session was expired, for example if updating token fails.
   * Invoking this results in onAuthLogout callback listener being invoked.
   */
  @JsMethod( name = "clearToken" )
  public native boolean clearToken();

  @JsProperty( name = "onReady" )
  @JsNullable
  public KeycloakAuthenticatedCallback onReady;
  @JsProperty( name = "onAuthSuccess" )
  @JsNullable
  public KeycloakCallback onAuthSuccess;
  @JsProperty( name = "onAuthError" )
  @JsNullable
  public KeycloakCallback onAuthError;
  @JsProperty( name = "onAuthRefreshSuccess" )
  @JsNullable
  public KeycloakCallback onAuthRefreshSuccess;
  @JsProperty( name = "onAuthRefreshError" )
  @JsNullable
  public KeycloakCallback onAuthRefreshError;
  @JsProperty( name = "onAuthLogout" )
  @JsNullable
  public KeycloakCallback onAuthLogout;
  @JsProperty( name = "onTokenExpired" )
  @JsNullable
  public KeycloakCallback onTokenExpired;
}
