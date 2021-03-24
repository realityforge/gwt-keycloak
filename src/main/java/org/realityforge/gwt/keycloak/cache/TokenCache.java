package org.realityforge.gwt.keycloak.cache;

import akasha.Global;
import akasha.Storage;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import jsinterop.base.Js;
import org.realityforge.gwt.keycloak.InitOptions;
import org.realityforge.gwt.keycloak.Keycloak;

/**
 * Utility class responsible for storing and retrieving auth and refresh tokens for keycloak clients
 * inside the browsers local or session storage. The tokens are stored and reset from local storage
 * based by adding an instance of {@link TokenCachingListener} as a listener on the {@link Keycloak}
 * instance. The initial values of the tokens are supplied by configuring the {@link Keycloak} instance
 * by invoking {@link #configure(Keycloak)} and supplying the keycloak instance.
 */
public final class TokenCache
{
  @Nonnull
  private static final String AUTH_TOKEN_SUFFIX = ".auth_token";
  @Nonnull
  private static final String REFRESH_TOKEN_SUFFIX = ".refresh_token";

  private TokenCache()
  {
  }

  /**
   * Configure the Keycloak instance with auth and refresh tokens if any are present in storage.
   *
   * @param keycloak the keycloak instance.
   */
  public static void configure( @Nonnull final Keycloak keycloak )
  {
    final InitOptions initOptions = keycloak.getInitOptions();
    initOptions.setToken( getSavedToken( getAuthToken( keycloak ) ) );
    initOptions.setRefreshToken( getSavedToken( getRefreshTokenKey( keycloak ) ) );
  }

  static void saveTokens( @Nonnull final Keycloak keycloak )
  {
    setAuthToken( keycloak, keycloak.getToken() );
    setRefreshToken( keycloak, keycloak.getRefreshToken() );
    keycloak.getInitOptions().setToken( keycloak.getToken() );
    keycloak.getInitOptions().setRefreshToken( keycloak.getRefreshToken() );
  }

  static void resetSavedTokens( @Nonnull final Keycloak keycloak )
  {
    setAuthToken( keycloak, null );
    setRefreshToken( keycloak, null );
    keycloak.getInitOptions().setToken( null );
    keycloak.getInitOptions().setRefreshToken( null );
  }

  private static void setAuthToken( @Nonnull final Keycloak keycloak, @Nullable final String value )
  {
    setSavedToken( getAuthToken( keycloak ), value );
  }

  private static void setRefreshToken( @Nonnull final Keycloak keycloak, @Nullable final String value )
  {
    setSavedToken( getRefreshTokenKey( keycloak ), value );
  }

  @Nonnull
  private static String getAuthToken( @Nonnull final Keycloak keycloak )
  {
    return keycloak.getKey() + AUTH_TOKEN_SUFFIX;
  }

  @Nonnull
  private static String getRefreshTokenKey( @Nonnull final Keycloak keycloak )
  {
    return keycloak.getKey() + REFRESH_TOKEN_SUFFIX;
  }

  private static void setSavedToken( @Nonnull final String key, @Nullable final String value )
  {
    if ( !Js.global().has( "localStorage" ) )
    {
      return;
    }
    final Storage storage = Global.localStorage();
    if ( null == value )
    {
      storage.removeItem( key );
    }
    else
    {
      storage.setItem( key, value );
    }
  }

  @Nullable
  private static String getSavedToken( @Nonnull final String key )
  {
    return !Js.global().has( "localStorage" ) ? null : Global.localStorage().getItem( key );
  }
}
