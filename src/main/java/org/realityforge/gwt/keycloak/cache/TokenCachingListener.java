package org.realityforge.gwt.keycloak.cache;

import javax.annotation.Nonnull;
import org.realityforge.gwt.keycloak.Keycloak;
import org.realityforge.gwt.keycloak.KeycloakListener;
import org.realityforge.gwt.keycloak.KeycloakListenerAdapter;

/**
 * The listener that should be added to keycloak to maintain cache of tokens. It is also acceptable to extend
 * this class as long as calls to super methods are added as appropriate.
 */
public class TokenCachingListener
  extends KeycloakListenerAdapter
  implements KeycloakListener
{
  /**
   * {@inheritDoc}
   */
  @Override
  public void onAuthRefreshSuccess( @Nonnull final Keycloak keycloak )
  {
    TokenCache.saveTokens( keycloak );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onAuthSuccess( @Nonnull final Keycloak keycloak )
  {
    TokenCache.saveTokens( keycloak );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onReady( @Nonnull final Keycloak keycloak, final boolean authenticated )
  {
    if ( authenticated )
    {
      TokenCache.saveTokens( keycloak );
    }
    else
    {
      TokenCache.resetSavedTokens( keycloak );
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onAuthRefreshError( @Nonnull final Keycloak keycloak )
  {
    TokenCache.resetSavedTokens( keycloak );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void beforeAuthLogout( @Nonnull final Keycloak keycloak )
  {
    TokenCache.resetSavedTokens( keycloak );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onAuthLogout( @Nonnull final Keycloak keycloak )
  {
    TokenCache.resetSavedTokens( keycloak );
  }
}
