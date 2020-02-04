## Unreleased

* Remove deployment from TravisCI infrastructure as it is no longer feasible.
* Remove `{@inheritDoc}` as it only explicitly indicates that the default behaviour at the expense of significant visual clutter.
* Change the API for attaching listeners to the `Keycloak` instance. Rather than `Keycloak.setListener(...)` class to allow multiple listeners to be notified when keycloak events occur. This is a breaking API change.

## 0.4

* Add `TokenCache` and `TokenCachingListener` classes to cache tokens in browser storage while valid.

## 0.3

* Upgrade Buildr to latest release.
* Compile GWT modules as part of build process to ensure that the `.gwt.xml` file is consistent.
* Split the gwt modules into `org.realityforge.gwt.keycloak.Keycloak` and
  `org.realityforge.gwt.keycloak.event.Event` so that the events need not be compiled if not used.

## 0.2

* Clear the tokens when the auth refresh fails. Typically this occurs because the refresh token is expired.
* Automate the publishing to Maven Central.

## 0.1:

* ‎🎉 Initial release ‎🎉
