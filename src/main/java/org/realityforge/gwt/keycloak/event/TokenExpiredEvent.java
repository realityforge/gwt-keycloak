package org.realityforge.gwt.keycloak.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import javax.annotation.Nonnull;
import org.realityforge.gwt.keycloak.Keycloak;

/**
 * Event fired when keycloak auth token expires.
 */
public class TokenExpiredEvent
  extends KeycloakEvent<TokenExpiredEvent.Handler>
{
  public interface Handler
    extends EventHandler
  {
    void onTokenExpiredEvent( @Nonnull TokenExpiredEvent event );
  }

  private static final GwtEvent.Type<Handler> TYPE = new GwtEvent.Type<Handler>();

  public static GwtEvent.Type<Handler> getType()
  {
    return TYPE;
  }

  public TokenExpiredEvent( @Nonnull final Keycloak keycloak )
  {
    super( keycloak );
  }

  @Override
  public GwtEvent.Type<Handler> getAssociatedType()
  {
    return TokenExpiredEvent.getType();
  }

  @Override
  protected void dispatch( @Nonnull final Handler handler )
  {
    handler.onTokenExpiredEvent( this );
  }
}
