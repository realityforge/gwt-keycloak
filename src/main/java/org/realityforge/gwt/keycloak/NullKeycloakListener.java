package org.realityforge.gwt.keycloak;

/**
 * Null listener so as to avoid the need to null value.
 */
final class NullKeycloakListener
  extends KeycloakListenerAdapter
{
  static final NullKeycloakListener LISTENER = new NullKeycloakListener();
}
