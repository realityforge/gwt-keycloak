package org.realityforge.gwt.keycloak;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;

final class KeycloakListenerBroker
{
  @Nonnull
  private final KeycloakListener _listener = new ForwardingListener();
  @Nonnull
  private final List<KeycloakListener> _listeners = new ArrayList<>();

  void addKeycloakListener( @Nonnull final KeycloakListener listener )
  {
    _listeners.add( listener );
  }

  void removeKeycloakListener( @Nonnull final KeycloakListener listener )
  {
    _listeners.remove( listener );
  }

  @Nonnull
  KeycloakListener getListener()
  {
    return _listener;
  }

  private final class ForwardingListener
    implements KeycloakListener
  {
    @Override
    public void onReady( @Nonnull Keycloak keycloak, boolean authenticated )
    {
      for ( final KeycloakListener listener : new ArrayList<>( _listeners ) )
      {
        listener.onReady( keycloak, authenticated );
      }
    }

    @Override
    public void onAuthSuccess( @Nonnull Keycloak keycloak )
    {
      for ( final KeycloakListener listener : new ArrayList<>( _listeners ) )
      {
        listener.onAuthSuccess( keycloak );
      }
    }

    @Override
    public void onAuthError( @Nonnull Keycloak keycloak )
    {
      for ( final KeycloakListener listener : new ArrayList<>( _listeners ) )
      {
        listener.onAuthError( keycloak );
      }
    }

    @Override
    public void onAuthRefreshSuccess( @Nonnull Keycloak keycloak )
    {
      for ( final KeycloakListener listener : new ArrayList<>( _listeners ) )
      {
        listener.onAuthRefreshSuccess( keycloak );
      }
    }

    @Override
    public void onAuthRefreshError( @Nonnull Keycloak keycloak )
    {
      for ( final KeycloakListener listener : new ArrayList<>( _listeners ) )
      {
        listener.onAuthRefreshError( keycloak );
      }
    }

    @Override
    public void beforeAuthLogout( @Nonnull Keycloak keycloak )
    {
      for ( final KeycloakListener listener : new ArrayList<>( _listeners ) )
      {
        listener.beforeAuthLogout( keycloak );
      }
    }

    @Override
    public void onAuthLogout( @Nonnull Keycloak keycloak )
    {
      for ( final KeycloakListener listener : new ArrayList<>( _listeners ) )
      {
        listener.onAuthLogout( keycloak );
      }
    }

    @Override
    public void onTokenExpired( @Nonnull Keycloak keycloak )
    {
      for ( final KeycloakListener listener : new ArrayList<>( _listeners ) )
      {
        listener.onTokenExpired( keycloak );
      }
    }
  }
}
