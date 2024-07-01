package org.realityforge.gwt.keycloak;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

import javax.annotation.Nullable;

@JsType( isNative = true, namespace = JsPackage.GLOBAL )
public class UpdateResponse
{
    public native UpdateResponse success( @Nullable KeycloakCallback successCallback );
    public native UpdateResponse error( @Nullable KeycloakCallback failureCallback );
}
