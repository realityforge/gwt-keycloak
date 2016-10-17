package org.realityforge.gwt.keycloak.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import javax.annotation.Nonnull;
import org.realityforge.gwt.keycloak.Keycloak;

/**
 * Base class of all events originating from Keycloak.
 */
public abstract class KeycloakEvent<H extends EventHandler>
  extends GwtEvent<H>
{
  private final Keycloak _keycloak;

  protected KeycloakEvent( @Nonnull final Keycloak keycloak )
  {
    _keycloak = keycloak;
  }

  @Nonnull
  public final Keycloak getKeycloak()
  {
    return _keycloak;
  }
}
