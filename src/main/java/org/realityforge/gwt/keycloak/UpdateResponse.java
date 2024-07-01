package org.realityforge.gwt.keycloak;

import javax.annotation.Nullable;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType( isNative = true, namespace = JsPackage.GLOBAL )
public class UpdateResponse
{
  public native UpdateResponse success( @Nullable KeycloakCallback successCallback );

  public native UpdateResponse error( @Nullable KeycloakCallback failureCallback );
}
