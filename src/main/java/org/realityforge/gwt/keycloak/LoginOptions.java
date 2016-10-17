package org.realityforge.gwt.keycloak;

import javax.annotation.Nullable;

/**
 * Options class to pass to login action.
 */
public final class LoginOptions
{
  @Nullable
  private String _redirectUri;
  @Nullable
  private String _prompt;
  @Nullable
  private String _loginHint;
  @Nullable
  private String _action;
  @Nullable
  private String _locale;

  /**
   * Return the uri to redirect to after login.
   */
  @Nullable
  public String getRedirectUri()
  {
    return _redirectUri;
  }

  public LoginOptions setRedirectUri( @Nullable final String redirectUri )
  {
    _redirectUri = redirectUri;
    return this;
  }

  /**
   * Return the prompt.
   * By default the login screen is displayed if the user is not logged-in to
   * Keycloak. To only authenticate to the application if the user is already
   * logged-in and not display the login page if the user is not logged-in, set
   * this option to none.
   */
  @Nullable
  public String getPrompt()
  {
    return _prompt;
  }

  public LoginOptions setPrompt( @Nullable final String prompt )
  {
    _prompt = prompt;
    return this;
  }

  /**
   * Return the login hint.
   * Used to pre-fill the username/email field on the login form.
   */
  @Nullable
  public String getLoginHint()
  {
    return _loginHint;
  }

  public LoginOptions setLoginHint( @Nullable final String loginHint )
  {
    _loginHint = loginHint;
    return this;
  }

  /**
   * Return action value.
   * If value is 'register' then user is redirected to registration page, otherwise to login page.
   */
  @Nullable
  public String getAction()
  {
    return _action;
  }

  public LoginOptions setAction( @Nullable final String action )
  {
    _action = action;
    return this;
  }

  /**
   * Return the desired locale for the UI.
   */
  @Nullable
  public String getLocale()
  {
    return _locale;
  }

  public LoginOptions setLocale( @Nullable final String locale )
  {
    _locale = locale;
    return this;
  }
}
