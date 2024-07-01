package org.realityforge.gwt.keycloak;

import jsinterop.annotations.JsNonNull;
import jsinterop.annotations.JsNullable;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType( isNative = true, namespace = JsPackage.GLOBAL )
public class UpdateResponse
{
  @JsNonNull
  public native UpdateResponse success( @JsNullable KeycloakCallback successCallback );

  @JsNonNull
  public native UpdateResponse error( @JsNullable KeycloakCallback failureCallback );
}
