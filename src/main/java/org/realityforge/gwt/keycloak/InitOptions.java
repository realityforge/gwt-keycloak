package org.realityforge.gwt.keycloak;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Options class to pass to the init action.
 */
public final class InitOptions
{
  @Nullable
  private OnLoadAction _onLoadAction = OnLoadAction.LOGIN_REQUIRED;
  @Nullable
  private String _token;
  @Nullable
  private String _refreshToken;
  @Nullable
  private String _idToken;
  @Nullable
  private Integer _timeSkewInSeconds;
  private boolean _checkLoginIframe = true;
  private int _checkLoginIframeIntervalInSeconds = 5;
  @Nonnull
  private ResponseMode _responseMode = ResponseMode.FRAGMENT;
  @Nonnull
  private Flow _flow = Flow.STANDARD;

  @Nullable
  public OnLoadAction getOnLoadAction()
  {
    return _onLoadAction;
  }

  public InitOptions setOnLoadAction( @Nullable final OnLoadAction onLoadAction )
  {
    _onLoadAction = onLoadAction;
    return this;
  }

  @Nullable
  public String getToken()
  {
    return _token;
  }

  public InitOptions setToken( @Nullable final String token )
  {
    _token = token;
    return this;
  }

  @Nullable
  public String getRefreshToken()
  {
    return _refreshToken;
  }

  public InitOptions setRefreshToken( @Nullable final String refreshToken )
  {
    _refreshToken = refreshToken;
    return this;
  }

  @Nullable
  public String getIdToken()
  {
    return _idToken;
  }

  public InitOptions setIdToken( @Nullable final String idToken )
  {
    _idToken = idToken;
    return this;
  }

  @Nullable
  public Integer getTimeSkewInSeconds()
  {
    return _timeSkewInSeconds;
  }

  public InitOptions setTimeSkewInSeconds( @Nullable final Integer timeSkewInSeconds )
  {
    _timeSkewInSeconds = timeSkewInSeconds;
    return this;
  }

  public boolean isCheckLoginIframe()
  {
    return _checkLoginIframe;
  }

  public InitOptions setCheckLoginIframe( final boolean checkLoginIframe )
  {
    _checkLoginIframe = checkLoginIframe;
    return this;
  }

  public int getCheckLoginIframeIntervalInSeconds()
  {
    return _checkLoginIframeIntervalInSeconds;
  }

  public InitOptions setCheckLoginIframeIntervalInSeconds( final int checkLoginIframeIntervalInSeconds )
  {
    _checkLoginIframeIntervalInSeconds = checkLoginIframeIntervalInSeconds;
    return this;
  }

  @Nonnull
  public ResponseMode getResponseMode()
  {
    return _responseMode;
  }

  public InitOptions setResponseMode( @Nonnull final ResponseMode responseMode )
  {
    _responseMode = responseMode;
    return this;
  }

  @Nonnull
  public Flow getFlow()
  {
    return _flow;
  }

  public InitOptions setFlow( @Nonnull final Flow flow )
  {
    _flow = flow;
    return this;
  }
}
