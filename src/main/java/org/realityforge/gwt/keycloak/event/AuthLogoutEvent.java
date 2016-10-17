package org.realityforge.gwt.keycloak.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import javax.annotation.Nonnull;
import org.realityforge.gwt.keycloak.Keycloak;

/**
 * Event fired when keycloak successfully logs out.
 */
public class AuthLogoutEvent
  extends KeycloakEvent<AuthLogoutEvent.Handler>
{
  public interface Handler
    extends EventHandler
  {
    void onAuthLogout( @Nonnull AuthLogoutEvent event );
  }

  private static final GwtEvent.Type<Handler> TYPE = new GwtEvent.Type<Handler>();

  public static GwtEvent.Type<Handler> getType()
  {
    return TYPE;
  }

  public AuthLogoutEvent( @Nonnull final Keycloak keycloak )
  {
    super( keycloak );
  }

  @Override
  public GwtEvent.Type<Handler> getAssociatedType()
  {
    return AuthLogoutEvent.getType();
  }

  @Override
  protected void dispatch( @Nonnull final Handler handler )
  {
    handler.onAuthLogout( this );
  }
}
