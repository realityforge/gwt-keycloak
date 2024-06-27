package org.realityforge.gwt.keycloak;

import jsinterop.annotations.JsNonNull;
import jsinterop.annotations.JsNullable;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Options class to pass to the init action.
 */
@JsType( isNative = true, namespace = JsPackage.GLOBAL, name = "Object" )
public class KeycloakInitOptions
{
  public KeycloakInitOptions()
  {
  }

  @JsProperty( name = "onLoad" )
  @JsNullable
  public native String onLoad();

  @JsProperty
  public native void setOnLoad( @JsNullable String onLoad );

  @JsProperty( name = "token" )
  @JsNullable
  public native String token();

  @JsProperty
  public native void setToken( @JsNullable String token );

  @JsProperty( name = "refreshToken" )
  @JsNullable
  public native String refreshToken();

  @JsProperty
  public native void setRefreshToken( @JsNullable String refreshToken );

  @JsProperty( name = "IdToken" )
  @JsNullable
  public native String IdToken();

  @JsProperty
  public native void setIdToken( @JsNullable String IdToken );

  @JsProperty( name = "timeSkew" )
  @JsNullable
  public native int timeSkew();

  @JsProperty
  public native void setTimeSkew( int timeSkew );

  @JsProperty( name = "checkLoginIframe" )
  public native boolean checkLoginIframe();

  @JsProperty
  public native void setCheckLoginIframe( boolean checkLoginIframe );

  @JsProperty( name = "checkLoginIframeInterval" )
  public native int checkLoginIframeInterval();

  @JsProperty
  public native void setCheckLoginIframeInterval( int checkLoginIframeIntervalInSeconds );

  @JsProperty( name = "responseMode" )
  @JsNonNull
  public native String responseMode();

  @JsProperty
  public native void setResponseMode( @JsNonNull String responseMode );

  @JsProperty( name = "flow" )
  @JsNonNull
  public native String flow();

  @JsProperty
  public native void setFlow( @JsNonNull String responseMode );
}
