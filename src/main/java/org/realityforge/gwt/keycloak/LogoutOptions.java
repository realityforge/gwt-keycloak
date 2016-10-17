package org.realityforge.gwt.keycloak;

import javax.annotation.Nullable;

/**
 * Options class to pass to logout action.
 */
public final class LogoutOptions
{
  @Nullable
  private String _redirectUri;

  /**
   * Return the uri to redirect to after login.
   */
  @Nullable
  public String getRedirectUri()
  {
    return _redirectUri;
  }

  public LogoutOptions setRedirectUri( @Nullable final String redirectUri )
  {
    _redirectUri = redirectUri;
    return this;
  }
}
