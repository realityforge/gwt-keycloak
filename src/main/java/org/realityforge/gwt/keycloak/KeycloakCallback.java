package org.realityforge.gwt.keycloak;

import jsinterop.annotations.JsFunction;

@JsFunction
@FunctionalInterface
public interface KeycloakCallback {
    void onInvoke();
}
