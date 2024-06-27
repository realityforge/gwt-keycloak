package org.realityforge.gwt.keycloak;

import jsinterop.annotations.JsNullable;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Options class to pass to logout action.
 */
@JsType(
        isNative = true,
        namespace = JsPackage.GLOBAL,
        name = "Object"
)
public class KeycloakLogoutOptions
{
    @JsProperty( name = "redirectUri" )
    public native String redirectUri();

    @JsProperty
    public native void setRedirectUri( @JsNullable String redirectUri );
}
