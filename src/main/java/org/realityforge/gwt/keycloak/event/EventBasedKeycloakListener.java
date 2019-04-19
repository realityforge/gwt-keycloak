package org.realityforge.gwt.keycloak.event;

import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.google.web.bindery.event.shared.SimpleEventBus;
import javax.annotation.Nonnull;
import org.realityforge.gwt.keycloak.Keycloak;
import org.realityforge.gwt.keycloak.KeycloakListener;

/**
 * An event based KeycloakListener listener.
 */
public class EventBasedKeycloakListener
  implements KeycloakListener
{
  @Nonnull
  private final EventBus _eventBus;

  /**
   * Construct the listener using a SimpleEventBus.
   */
  public EventBasedKeycloakListener()
  {
    this( new SimpleEventBus() );
  }

  /**
   * Construct the listener using specified EventBus.
   *
   * @param eventBus the event bus.
   */
  public EventBasedKeycloakListener( @Nonnull final EventBus eventBus )
  {
    _eventBus = eventBus;
  }

  /**
   * Add listener for AuthError events.
   *
   * @param handler the event handler.
   * @return the HandlerRegistration that manages the listener.
   */
  @Nonnull
  public final HandlerRegistration addAuthErrorHandler( @Nonnull final AuthErrorEvent.Handler handler )
  {
    return _eventBus.addHandler( AuthErrorEvent.getType(), handler );
  }

  /**
   * Add listener for AuthLogout events.
   *
   * @param handler the event handler.
   * @return the HandlerRegistration that manages the listener.
   */
  @Nonnull
  public final HandlerRegistration addAuthLogoutHandler( @Nonnull final AuthLogoutEvent.Handler handler )
  {
    return _eventBus.addHandler( AuthLogoutEvent.getType(), handler );
  }

  /**
   * Add listener for AuthRefreshSuccess events.
   *
   * @param handler the event handler.
   * @return the HandlerRegistration that manages the listener.
   */
  @Nonnull
  public final HandlerRegistration addAuthRefreshSuccessHandler( @Nonnull final AuthRefreshSuccessEvent.Handler handler )
  {
    return _eventBus.addHandler( AuthRefreshSuccessEvent.getType(), handler );
  }

  /**
   * Add listener for AuthSuccess events.
   *
   * @param handler the event handler.
   * @return the HandlerRegistration that manages the listener.
   */
  @Nonnull
  public final HandlerRegistration addAuthSuccessHandler( @Nonnull final AuthSuccessEvent.Handler handler )
  {
    return _eventBus.addHandler( AuthSuccessEvent.getType(), handler );
  }

  /**
   * Add listener for TokenExpired events.
   *
   * @param handler the event handler.
   * @return the HandlerRegistration that manages the listener.
   */
  @Nonnull
  public final HandlerRegistration addTokenExpiredHandler( @Nonnull final TokenExpiredEvent.Handler handler )
  {
    return _eventBus.addHandler( TokenExpiredEvent.getType(), handler );
  }

  /**
   * Add listener for Ready events.
   *
   * @param handler the event handler.
   * @return the HandlerRegistration that manages the listener.
   */
  @Nonnull
  public final HandlerRegistration addReadyHandler( @Nonnull final ReadyEvent.Handler handler )
  {
    return _eventBus.addHandler( ReadyEvent.getType(), handler );
  }

  @Override
  public final void onAuthError( @Nonnull final Keycloak keycloak )
  {
    _eventBus.fireEventFromSource( new AuthSuccessEvent( keycloak ), keycloak );
  }

  @Override
  public final void beforeAuthLogout( @Nonnull final Keycloak keycloak )
  {
    _eventBus.fireEventFromSource( new BeforeAuthLogoutEvent( keycloak ), keycloak );
  }

  @Override
  public final void onAuthLogout( @Nonnull final Keycloak keycloak )
  {
    _eventBus.fireEventFromSource( new AuthLogoutEvent( keycloak ), keycloak );
  }

  @Override
  public final void onAuthRefreshError( @Nonnull final Keycloak keycloak )
  {
    _eventBus.fireEventFromSource( new AuthRefreshErrorEvent( keycloak ), keycloak );
  }

  @Override
  public final void onAuthRefreshSuccess( @Nonnull final Keycloak keycloak )
  {
    _eventBus.fireEventFromSource( new AuthRefreshSuccessEvent( keycloak ), keycloak );
  }

  @Override
  public final void onAuthSuccess( @Nonnull final Keycloak keycloak )
  {
    _eventBus.fireEventFromSource( new AuthSuccessEvent( keycloak ), keycloak );
  }

  @Override
  public final void onTokenExpired( @Nonnull final Keycloak keycloak )
  {
    _eventBus.fireEventFromSource( new TokenExpiredEvent( keycloak ), keycloak );
  }

  @Override
  public final void onReady( @Nonnull final Keycloak keycloak, final boolean authenticated )
  {
    _eventBus.fireEventFromSource( new ReadyEvent( keycloak, authenticated ), keycloak );
  }
}
