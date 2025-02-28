package org.realityforge.gwt.keycloak;

import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * A minimal Promise implementation just for Keycloak token updates.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Promise")
public class PromiseImpl<T>
{
    /**
     * A callback function used for Promise resolution.
     */
    @JsFunction
    public interface ThenCallback<T, R>
    {
        R onInvoke(T value);
    }

    /**
     * A callback function used for Promise rejection.
     */
    @JsFunction
    public interface CatchCallback<R>
    {
        R onInvoke(Object error);
    }

    /**
     * Register a callback to be invoked when promise resolves successfully.
     *
     * @param onFulfilled the callback to invoke when promise is fulfilled
     * @return a Promise that resolves with the result of the callback
     */
    @JsMethod
    public native <R> PromiseImpl<R> then(ThenCallback<T, R> onFulfilled);

    /**
     * Register a callback to be invoked when promise is rejected.
     *
     * @param onRejected the callback to invoke when promise is rejected
     * @return a Promise that resolves with the result of the callback
     */
    @JsMethod(name = "catch")
    public native <R> PromiseImpl<R> catch_(CatchCallback<R> onRejected);
}