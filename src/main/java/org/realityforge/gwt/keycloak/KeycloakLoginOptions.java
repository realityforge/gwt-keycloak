package org.realityforge.gwt.keycloak;

import jsinterop.annotations.JsNullable;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Options class to pass to the login action.
 */
@SuppressWarnings( "unused" )
@JsType( isNative = true, namespace = JsPackage.GLOBAL, name = "Object" )
public class KeycloakLoginOptions
{
  public KeycloakLoginOptions()
  {
  }

  /**
   * Return the uri to redirect to after login.
   */
  @JsProperty( name = "redirectUri" )
  @JsNullable
  public native String redirectUri();

  @JsProperty
  public native void setRedirectUri( @JsNullable String redirectUri );

  /**
   * Return the prompt.
   * By default the login screen is displayed if the user is not logged-in to
   * Keycloak. To only authenticate to the application if the user is already
   * logged-in and not display the login page if the user is not logged-in, set
   * this option to none.
   */
  @JsProperty( name = "prompt" )
  @JsNullable
  public native String prompt();

  @JsProperty
  public native void setPrompt( @JsNullable String prompt );

  /**
   * Return the login hint.
   * Used to pre-fill the username/email field on the login form.
   */
  @JsProperty( name = "loginHint" )
  @JsNullable
  public native String loginHint();

  @JsProperty
  public native void setLoginHint( @JsNullable String loginHint );

  /**
   * Return action value.
   * If value is 'register' then user is redirected to registration page, otherwise to login page.
   */
  @JsProperty( name = "action" )
  @JsNullable
  public native String action();

  @JsProperty
  public native void setAction( @JsNullable String action );

  /**
   * Return the desired locale for the UI.
   */
  @JsProperty( name = "locale" )
  @JsNullable
  public native String locale();

  @JsProperty
  public native void setLocale( @JsNullable String locale );
}
