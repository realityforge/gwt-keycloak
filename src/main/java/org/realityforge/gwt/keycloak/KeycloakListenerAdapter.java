package org.realityforge.gwt.keycloak;

import javax.annotation.Nonnull;

/**
 * Adapter listener to make sub-classing easier.
 */
public class KeycloakListenerAdapter
  implements KeycloakListener
{
  @Override
  public void onReady( @Nonnull final Keycloak keycloak, final boolean authenticated )
  {
  }

  @Override
  public void onAuthSuccess( @Nonnull final Keycloak keycloak )
  {
  }

  @Override
  public void onAuthError( @Nonnull final Keycloak keycloak )
  {
  }

  @Override
  public void onAuthRefreshSuccess( @Nonnull final Keycloak keycloak )
  {
  }

  @Override
  public void onAuthRefreshError( @Nonnull final Keycloak keycloak )
  {
  }

  @Override
  public void beforeAuthLogout( @Nonnull final Keycloak keycloak )
  {
  }

  @Override
  public void onAuthLogout( @Nonnull final Keycloak keycloak )
  {
  }

  @Override
  public void onTokenExpired( @Nonnull final Keycloak keycloak )
  {
  }
}
