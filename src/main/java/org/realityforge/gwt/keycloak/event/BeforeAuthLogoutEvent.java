package org.realityforge.gwt.keycloak.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import javax.annotation.Nonnull;
import org.realityforge.gwt.keycloak.Keycloak;

/**
 * Event fired before keycloak attempts to log out.
 */
public class BeforeAuthLogoutEvent
  extends KeycloakEvent<BeforeAuthLogoutEvent.Handler>
{
  public interface Handler
    extends EventHandler
  {
    void onBeforeAuthLogout( @Nonnull BeforeAuthLogoutEvent event );
  }

  private static final GwtEvent.Type<Handler> TYPE = new GwtEvent.Type<Handler>();

  public static GwtEvent.Type<Handler> getType()
  {
    return TYPE;
  }

  public BeforeAuthLogoutEvent( @Nonnull final Keycloak keycloak )
  {
    super( keycloak );
  }

  @Override
  public GwtEvent.Type<Handler> getAssociatedType()
  {
    return BeforeAuthLogoutEvent.getType();
  }

  @Override
  protected void dispatch( @Nonnull final Handler handler )
  {
    handler.onBeforeAuthLogout( this );
  }
}
