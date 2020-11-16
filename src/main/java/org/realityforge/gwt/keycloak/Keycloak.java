package org.realityforge.gwt.keycloak;

import com.google.gwt.core.client.JavaScriptObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * The class used when interacting with the keycloak javascript library.
 *
 * Derived from information describing the <a href="https://keycloak.gitbooks.io/securing-client-applications-guide/content/v/2.0/topics/oidc/javascript-adapter.html">Javascript Adapter</a>.
 */
@SuppressWarnings( { "WeakerAccess", "unused" } )
public class Keycloak
{
  private static final Logger LOG = Logger.getLogger( Keycloak.class.getName() );
  /**
   * The default minimum validity time in seconds.
   * A reasonable time under which we expect a remote call to reach the server with some degree of safety.
   */
  private static final int MIN_TOKEN_VALIDITY_SECONDS = 15;
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
    _impl = KeycloakImpl.create( this, configURL );
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
    return getImpl().isAuthenticated();
  }

  /**
   * The base64 encoded token that can be sent in the Authorization header in requests to services.
   */
  public String getToken()
  {
    return getImpl().getToken();
  }

  /**
   * The parsed token as a JavaScript object.
   */
  @SuppressWarnings( "unchecked" )
  public <T> T getParsedToken()
  {
    return (T) getImpl().getParsedToken();
  }

  /**
   * The user id.
   */
  public String getSubject()
  {
    return getImpl().getSubject();
  }

  /**
   * The base64 encoded ID token.
   */
  public String getIDToken()
  {
    return getImpl().getIDToken();
  }

  /**
   * The parsed id token as a JavaScript object.
   */
  @SuppressWarnings( "unchecked" )
  public <T> T getParsedIDToken()
  {
    return (T) getImpl().getParsedIDToken();
  }

  /**
   * The base64 encoded refresh token.
   */
  public String getRefreshToken()
  {
    return getImpl().getRefreshToken();
  }

  /**
   * The parsed refresh token as a JavaScript object.
   */
  @SuppressWarnings( "unchecked" )
  public <T> T getParsedRefreshToken()
  {
    return (T) getImpl().getParsedRefreshToken();
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
    getImpl().init( onLoad,
                    options.getToken(),
                    options.getRefreshToken(),
                    options.getIdToken(),
                    options.getTimeSkewInSeconds(),
                    options.isCheckLoginIframe(),
                    options.getCheckLoginIframeIntervalInSeconds(),
                    options.getResponseMode().name().toLowerCase(),
                    options.getFlow().name().toLowerCase() );
  }

  /**
   * Redirects to login form on (options is an optional object with redirectUri and/or prompt fields).
   */
  public void login()
  {
    login( getLoginOptions() );
  }

  private void login( @Nullable final LoginOptions options )
  {
    LOG.info( "Preparing to Login Keycloak instance for client " + _key );

    getImpl().login( options != null ? options.getRedirectUri() : null,
                     options != null ? options.getPrompt() : null,
                     options != null ? options.getLoginHint() : null,
                     options != null ? options.getAction() : null,
                     options != null ? options.getLocale() : null );
  }

  /**
   * Redirects to logout.
   */
  public void logout()
  {
    logout( getLogoutOptions() );
  }

  private void logout( @Nullable final LogoutOptions options )
  {
    LOG.info( "Preparing to Logout Keycloak instance for client " + _key );
    beforeAuthLogout();
    getImpl().logout( options != null ? options.getRedirectUri() : null );
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
    getImpl().updateToken( minValiditySeconds, successCallback, failureCallback );
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

  /**
   * The underlying Keycloak implementation.
   */
  private final static class KeycloakImpl
    extends JavaScriptObject
  {
    static native KeycloakImpl create( @Nonnull final Keycloak keycloak, @Nonnull final String configURL )
      /*-{

        var impl = $wnd.Keycloak(configURL);

        impl.wrapper = keycloak;
        impl.onReady = $entry(function(authenticated) {
          impl.wrapper.@org.realityforge.gwt.keycloak.Keycloak::onReady(*)(authenticated);
        });
        impl.onAuthSuccess = $entry(function() {
          impl.wrapper.@org.realityforge.gwt.keycloak.Keycloak::onAuthSuccess()();
        });
        impl.onAuthError = $entry(function() {
          impl.wrapper.@org.realityforge.gwt.keycloak.Keycloak::onAuthError()();
        });
        impl.onAuthRefreshSuccess = $entry(function() {
          impl.wrapper.@org.realityforge.gwt.keycloak.Keycloak::onAuthRefreshSuccess()();
        });
        impl.onAuthRefreshError = $entry(function() {
          impl.wrapper.@org.realityforge.gwt.keycloak.Keycloak::onAuthRefreshError()();
        });
        impl.onAuthLogout = $entry(function() {
          impl.wrapper.@org.realityforge.gwt.keycloak.Keycloak::onAuthLogout()();
        });
        impl.onTokenExpired = $entry(function() {
          impl.wrapper.@org.realityforge.gwt.keycloak.Keycloak::onTokenExpired()();
        });

        return impl;
      }-*/;

    @SuppressWarnings( "ProtectedMemberInFinalClass" )
    protected KeycloakImpl()
    {
    }

    native boolean isAuthenticated() /*-{
      return this.authenticated;
    }-*/;

    @Nullable
    native String getSubject() /*-{
      return this.subject;
    }-*/;

    @Nullable
    native String getToken() /*-{
      return this.token;
    }-*/;

    native <T extends JavaScriptObject> T getParsedToken() /*-{
      return this.tokenParsed;
    }-*/;

    @Nullable
    native String getIDToken() /*-{
      return this.idToken;
    }-*/;

    native <T extends JavaScriptObject> T getParsedIDToken() /*-{
      return this.idTokenParsed;
    }-*/;

    @Nullable
    native String getRefreshToken() /*-{
      return this.refreshToken;
    }-*/;

    native <T extends JavaScriptObject> T getParsedRefreshToken() /*-{
      return this.tokenParsed;
    }-*/;

    native boolean isTokenExpired( final int minValiditySeconds ) /*-{
      return this.isTokenExpired(minValiditySeconds);
    }-*/;

    native void init( @Nullable final String onLoad,
                      @Nullable final String token,
                      @Nullable final String refreshToken,
                      @Nullable final String idToken,
                      @Nullable final Integer timeSkew,
                      final boolean checkLoginIframe,
                      final int checkLoginIframeInterval,
                      @Nonnull final String responseMode,
                      @Nonnull final String flow ) /*-{
      var options = {};
      if (null != onLoad) {
        options.onLoad = onLoad;
      }
      if (null != token) {
        options.token = token;
      }
      if (null != refreshToken) {
        options.refreshToken = refreshToken;
      }
      if (null != idToken) {
        options.idToken = idToken;
      }
      if (null != timeSkew) {
        options.timeSkew = timeSkew;
      }
      if (null != checkLoginIframe) {
        options.checkLoginIframe = checkLoginIframe;
      }
      options.checkLoginIframeInterval = checkLoginIframeInterval;
      if (null != checkLoginIframe) {
        options.checkLoginIframe = checkLoginIframe;
      }
      options.responseMode = responseMode;
      options.flow = flow;
      this.init(options);
    }-*/;

    native void login( @Nullable final String redirectUri,
                       @Nullable final String prompt,
                       @Nullable final String loginHint,
                       @Nullable final String action,
                       @Nullable final String locale ) /*-{
      var options = {};
      if (null != redirectUri) {
        options.redirectUri = redirectUri;
      }
      if (null != prompt) {
        options.prompt = prompt;
      }
      if (null != loginHint) {
        options.loginHint = loginHint;
      }
      if (null != action) {
        options.action = action;
      }
      if (null != locale) {
        options.locale = locale;
      }
      this.login(options);
    }-*/;

    native void logout( @Nullable final String redirectUri ) /*-{
      var options = {};
      if (null != redirectUri) {
        options.redirectUri = redirectUri;
      }
      this.logout(options);
    }-*/;

    native void updateToken( final int minValiditySeconds,
                             @Nullable final Runnable successCallback,
                             @Nullable final Runnable failureCallback ) /*-{
      var keycloak = this;
      var onSuccess =
        $entry(function() {
          keycloak.wrapper.@org.realityforge.gwt.keycloak.Keycloak::onTokenUpdateSuccess(*)(successCallback);
        });
      var onFailure =
        $entry(function() {
          keycloak.wrapper.@org.realityforge.gwt.keycloak.Keycloak::onTokenUpdateFailure(*)(failureCallback);
        });
      this.updateToken(minValiditySeconds).success(onSuccess).error(onFailure);
    }-*/;

    /**
     * Clear authentication state, including tokens.
     * This can be useful if application has detected the session was expired, for example if updating token fails.
     * Invoking this results in onAuthLogout callback listener being invoked.
     */
    native boolean clearToken() /*-{
      return this.clearToken();
    }-*/;
  }
}
