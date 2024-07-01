package org.realityforge.gwt.keycloak;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * The class used when interacting with the keycloak javascript library.
 * <p>
 * Derived from information describing the <a href="https://keycloak.gitbooks.io/securing-client-applications-guide/content/v/2.0/topics/oidc/javascript-adapter.html">Javascript Adapter</a>.
 */
@SuppressWarnings( { "WeakerAccess", "unused" } )
public class Keycloak
{
  @Nonnull
  private static final Logger LOG = Logger.getLogger( Keycloak.class.getName() );
  /**
   * The default minimum validity time in seconds.
   * A reasonable time under which we expect a remote call to reach the server with some degree of safety.
   */
  private static final int MIN_TOKEN_VALIDITY_SECONDS = 60;
  @Nonnull
  private final InitOptions _initOptions = new InitOptions();
  @Nonnull
  private final LoginOptions _loginOptions = new LoginOptions();
  @Nonnull
  private final LogoutOptions _logoutOptions = new LogoutOptions();
  @Nonnull
  private final KeycloakListenerBroker _broker = new KeycloakListenerBroker();
  @Nonnull
  private final KeycloakImpl _impl;
  @Nonnull
  private final String _key;
  @Nonnull
  private final String _configURL;
  /**
   * Actions pending a valid token.
   */
  @Nonnull
  private final List<Runnable> _actions = new ArrayList<>();

  public Keycloak( @Nonnull final String key, @Nonnull final String configURL )
  {
    _key = key;
    _configURL = configURL;
    _impl = new KeycloakImpl( configURL );
    _impl.onReady = this::onReady;
    _impl.onAuthSuccess = this::onAuthSuccess;
    _impl.onAuthError = this::onAuthError;
    _impl.onAuthRefreshSuccess = this::onAuthRefreshSuccess;
    _impl.onAuthRefreshError = this::onAuthRefreshError;
    _impl.onAuthLogout = this::onAuthLogout;
    _impl.onTokenExpired = this::onTokenExpired;
    LOG.info( "Keycloak instance created for client " + _key );
  }

  /**
   * An arbitrary key to label keycloak instance.
   * It is usually aligned with the name of the client on server.
   */
  @Nonnull
  public String getKey()
  {
    return _key;
  }

  @Nonnull
  public String getConfigURL()
  {
    return _configURL;
  }

  /**
   * Is true if the user is authenticated, false otherwise.
   */
  public boolean isAuthenticated()
  {
    return getImpl().authenticated();
  }

  /**
   * The base64 encoded token that can be sent in the Authorization header in requests to services.
   */
  public String getToken()
  {
    return getImpl().token();
  }

  /**
   * The parsed token as a JavaScript object.
   */
  @SuppressWarnings( "unchecked" )
  public <T> T getParsedToken()
  {
    return (T) getImpl().tokenParsed();
  }

  /**
   * The user id.
   */
  public String getSubject()
  {
    return getImpl().subject();
  }

  /**
   * The base64 encoded ID token.
   */
  public String getIDToken()
  {
    return getImpl().idToken();
  }

  /**
   * The parsed id token as a JavaScript object.
   */
  @SuppressWarnings( "unchecked" )
  public <T> T getParsedIDToken()
  {
    return (T) getImpl().idTokenParsed();
  }

  /**
   * The base64 encoded refresh token.
   */
  public String getRefreshToken()
  {
    return getImpl().refreshToken();
  }

  /**
   * The parsed refresh token as a JavaScript object.
   */
  @SuppressWarnings( "unchecked" )
  public <T> T getParsedRefreshToken()
  {
    return (T) getImpl().refreshTokenParsed();
  }

  /**
   * Return the listener associated with the Keycloak instance.
   *
   * @return the listener associated with the Keycloak instance.
   */
  @Nonnull
  private KeycloakListener getListener()
  {
    return _broker.getListener();
  }

  /**
   * Add a listener to receive messages from the Keycloak instance.
   *
   * @param listener the listener to receive messages from the Keycloak instance.
   */
  public void addKeycloakListener( @Nonnull final KeycloakListener listener )
  {
    _broker.addKeycloakListener( listener );
  }

  public void removeKeycloakListener( @Nonnull final KeycloakListener listener )
  {
    _broker.removeKeycloakListener( listener );
  }

  @Nonnull
  public InitOptions getInitOptions()
  {
    return _initOptions;
  }

  @Nonnull
  public LoginOptions getLoginOptions()
  {
    return _loginOptions;
  }

  @Nonnull
  public LogoutOptions getLogoutOptions()
  {
    return _logoutOptions;
  }

  public void init()
  {
    init( getInitOptions() );
  }

  /**
   * Called to initialize the adapter.
   */
  private void init( @Nonnull final InitOptions options )
  {
    LOG.info( "Initializing Keycloak instance for client " + _key );
    final OnLoadAction onLoadAction = options.getOnLoadAction();
    final String onLoad =
      OnLoadAction.LOGIN_REQUIRED == onLoadAction ? "login-required" :
      OnLoadAction.CHECK_SSO == onLoadAction ? "check-sso" :
      null;
    final KeycloakInitOptions keycloakInitOptions = new KeycloakInitOptions();
    keycloakInitOptions.setOnLoad( onLoad );
    keycloakInitOptions.setToken( options.getToken() );
    keycloakInitOptions.setRefreshToken( options.getRefreshToken() );
    keycloakInitOptions.setIdToken( options.getIdToken() );
    final Integer timeSkewInSeconds = options.getTimeSkewInSeconds();
    if ( timeSkewInSeconds != null )
    {
      keycloakInitOptions.setTimeSkew( timeSkewInSeconds );
    }
    keycloakInitOptions.setCheckLoginIframe( options.isCheckLoginIframe() );
    keycloakInitOptions.setCheckLoginIframeInterval( options.getCheckLoginIframeIntervalInSeconds() );
    keycloakInitOptions.setResponseMode( options.getResponseMode().name().toLowerCase() );
    keycloakInitOptions.setFlow( options.getFlow().name().toLowerCase() );
    getImpl().init( keycloakInitOptions );
  }

  /**
   * Redirects to the login form on (options is an optional object with redirectUri and/or prompt fields).
   */
  public void login()
  {
    login( getLoginOptions() );
  }

  private void login( @Nullable final LoginOptions options )
  {
    LOG.info( "Preparing to Login Keycloak instance for client " + _key );
    if ( null != options )
    {
      final KeycloakLoginOptions keycloakLoginOptions = new KeycloakLoginOptions();
      keycloakLoginOptions.setAction( options.getAction() );
      keycloakLoginOptions.setRedirectUri( options.getRedirectUri() );
      keycloakLoginOptions.setPrompt( options.getPrompt() );
      keycloakLoginOptions.setLocale( options.getLocale() );
      keycloakLoginOptions.setLoginHint( options.getLoginHint() );
      getImpl().login( keycloakLoginOptions );
    }
    else
    {
      getImpl().login();
    }
  }

  /**
   * Redirects to the logout url.
   */
  public void logout()
  {
    logout( getLogoutOptions() );
  }

  private void logout( @Nullable final LogoutOptions options )
  {
    LOG.info( "Preparing to Logout Keycloak instance for client " + _key );
    beforeAuthLogout();
    if ( null != options )
    {
      final KeycloakLogoutOptions keycloakLogoutOptions = new KeycloakLogoutOptions();
      keycloakLogoutOptions.setRedirectUri( options.getRedirectUri() );
      getImpl().logout( keycloakLogoutOptions );
    }
    else
    {
      getImpl().logout();
    }
  }

  /**
   * If the token expires within minValidity seconds the token is refreshed.
   * If the session status iframe is enabled, the session status is also checked.
   * On failure the tokens are cleared.
   */
  public void updateToken( final int minValiditySeconds,
                           @Nullable final Runnable successCallback,
                           @Nullable final Runnable failureCallback )
  {
    final KeycloakCallback success = () -> {
      if ( null != successCallback )
      {
        successCallback.run();
      }
    };
    final KeycloakCallback error = () -> {
      if ( null != failureCallback )
      {
        failureCallback.run();
      }
    };
    getImpl()
      .updateToken( minValiditySeconds )
            .success(success)
            .error(error);
  }

  /**
   * Invokes {@link #updateToken(int, Runnable, Runnable)} with null failureCallback.
   */
  public void updateToken( final int minValiditySeconds,
                           @Nullable final Runnable successCallback )
  {
    updateToken( minValiditySeconds, successCallback, null );
  }

  /**
   * Invokes {@link #updateToken(int, Runnable)} with {@link #MIN_TOKEN_VALIDITY_SECONDS} for minValiditySeconds.
   */
  public void updateToken( @Nullable final Runnable successCallback )
  {
    updateToken( MIN_TOKEN_VALIDITY_SECONDS, successCallback );
  }

  /**
   * Ensure that the token does not expire in next minValiditySeconds or update token and execute action.
   * Actions are queued until valid tokens are received and then executed.
   *
   * @param minValiditySeconds the minimum token validity.
   * @param action             the action to perform once a valid token is present.
   */
  public void updateTokenAndExecute( final int minValiditySeconds, @Nonnull final Runnable action )
  {
    _actions.add( action );
    updateToken( minValiditySeconds, () -> {
      _actions.forEach( Runnable::run );
      _actions.clear();
    } );
  }

  /**
   * Invokes {@link #updateTokenAndExecute(int, Runnable)} with {@link #MIN_TOKEN_VALIDITY_SECONDS} for minValiditySeconds.
   *
   * @param action the action to perform once a valid token is present.
   */
  public void updateTokenAndExecute( @Nonnull final Runnable action )
  {
    _actions.add( action );
    updateToken( MIN_TOKEN_VALIDITY_SECONDS, () -> {
      _actions.forEach( Runnable::run );
      _actions.clear();
    } );
  }

  /**
   * Returns true if the token has less than minValiditySeconds seconds left before it expires.
   */
  public boolean isTokenExpired( final int minValiditySeconds )
  {
    return getImpl().isTokenExpired( minValiditySeconds );
  }

  /**
   * Returns true if the token is expired.
   */
  public boolean isTokenExpired()
  {
    return isTokenExpired( 0 );
  }

  protected final void onReady( final boolean authenticated )
  {
    getListener().onReady( this, authenticated );
  }

  protected final void onAuthSuccess()
  {
    getListener().onAuthSuccess( this );
  }

  protected final void onAuthError()
  {
    getListener().onAuthError( this );
  }

  protected final void onAuthRefreshSuccess()
  {
    getListener().onAuthRefreshSuccess( this );
  }

  protected final void onAuthRefreshError()
  {
    getImpl().clearToken();
    getListener().onAuthRefreshError( this );
  }

  protected final void beforeAuthLogout()
  {
    getListener().beforeAuthLogout( this );
  }

  protected final void onAuthLogout()
  {
    getListener().onAuthLogout( this );
  }

  protected final void onTokenExpired()
  {
    getListener().onTokenExpired( this );
  }

  /**
   * Called when token refresh succeeds.
   */
  protected final void onTokenUpdateSuccess( @Nullable final Runnable listener )
  {
    if ( null != listener )
    {
      listener.run();
    }
  }

  /**
   * Retrieve the underlying implementation.
   */
  @Nonnull
  protected KeycloakImpl getImpl()
  {
    return _impl;
  }

  /**
   * Called when token refresh fails.
   */
  protected final void onTokenUpdateFailure( @Nullable final Runnable listener )
  {
    getImpl().clearToken();
    if ( null != listener )
    {
      listener.run();
    }
  }
}
