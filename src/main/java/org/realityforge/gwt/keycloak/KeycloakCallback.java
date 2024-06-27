package org.realityforge.gwt.keycloak;

import jsinterop.annotations.JsFunction;

@SuppressWarnings( "unused" )
@JsFunction
@FunctionalInterface
public interface KeycloakCallback
{
  void onInvoke();
}
