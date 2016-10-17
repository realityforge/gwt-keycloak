package org.realityforge.gwt.keycloak;

import javax.annotation.Nonnull;

/**
 * Callback invoked when keycloak initializes or fails to initialize.
 */
public interface KeycloakListener
{
  /**
   * Called when the adapter is initialized.
   */
  void onReady( @Nonnull Keycloak keycloak, boolean authenticated );

  /**
   * Called when a user is successfully authenticated.
   */
  void onAuthSuccess( @Nonnull Keycloak keycloak );

  /**
   * Called if there was an error during authentication.
   */
  void onAuthError( @Nonnull Keycloak keycloak );

  /**
   * Called when the token is refreshed.
   */
  void onAuthRefreshSuccess( @Nonnull Keycloak keycloak );

  /**
   * Called if there was an error while trying to refresh the token.
   */
  void onAuthRefreshError( @Nonnull Keycloak keycloak );

  /**
   * Called before logout action occurs.
   */
  void beforeAuthLogout( @Nonnull Keycloak keycloak );

  /**
   * Called if the user is logged out( will only be called if the session status iframe is enabled,
   * or in Cordova mode ).
   */
  void onAuthLogout( @Nonnull Keycloak keycloak );

  /**
   * Called when the access token is expired. When this happens you can for
   * refresh the token, or if refresh is not available(ie.with implicit flow ) you can redirect to login screen.
   */
  void onTokenExpired( @Nonnull Keycloak keycloak );
}
