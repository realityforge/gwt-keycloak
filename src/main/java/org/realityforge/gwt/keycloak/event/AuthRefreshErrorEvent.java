package org.realityforge.gwt.keycloak.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import javax.annotation.Nonnull;
import org.realityforge.gwt.keycloak.Keycloak;

/**
 * Event fired when keycloak fails to refresh token.
 */
public class AuthRefreshErrorEvent
  extends KeycloakEvent<AuthRefreshErrorEvent.Handler>
{
  public interface Handler
    extends EventHandler
  {
    void onAuthRefreshError( @Nonnull AuthRefreshErrorEvent event );
  }

  private static final GwtEvent.Type<Handler> TYPE = new GwtEvent.Type<Handler>();

  public static GwtEvent.Type<Handler> getType()
  {
    return TYPE;
  }

  public AuthRefreshErrorEvent( @Nonnull final Keycloak keycloak )
  {
    super( keycloak );
  }

  @Override
  public GwtEvent.Type<Handler> getAssociatedType()
  {
    return AuthRefreshErrorEvent.getType();
  }

  @Override
  protected void dispatch( @Nonnull final Handler handler )
  {
    handler.onAuthRefreshError( this );
  }
}
