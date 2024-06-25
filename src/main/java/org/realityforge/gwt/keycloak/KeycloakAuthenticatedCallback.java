package org.realityforge.gwt.keycloak;

import jsinterop.annotations.JsFunction;

@JsFunction
@FunctionalInterface
public interface KeycloakAuthenticatedCallback {
    void onInvoke( boolean authenticated );
}
