package org.realityforge.gwt.keycloak.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import javax.annotation.Nonnull;
import org.realityforge.gwt.keycloak.Keycloak;

/**
 * Event fired when keycloak completes initialization.
 */
public class ReadyEvent
  extends KeycloakEvent<ReadyEvent.Handler>
{
  public interface Handler
    extends EventHandler
  {
    void onReadyEvent( @Nonnull ReadyEvent event );
  }

  private static final GwtEvent.Type<Handler> TYPE = new GwtEvent.Type<Handler>();

  public static GwtEvent.Type<Handler> getType()
  {
    return TYPE;
  }

  private final boolean _authenticated;

  public ReadyEvent( @Nonnull final Keycloak keycloak, final boolean authenticated )
  {
    super( keycloak );
    _authenticated = authenticated;
  }

  public boolean isAuthenticated()
  {
    return _authenticated;
  }

  @Override
  public GwtEvent.Type<Handler> getAssociatedType()
  {
    return ReadyEvent.getType();
  }

  @Override
  protected void dispatch( @Nonnull final Handler handler )
  {
    handler.onReadyEvent( this );
  }
}
