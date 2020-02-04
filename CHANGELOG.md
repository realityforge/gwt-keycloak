# Change Log

### Unreleased

* Remove deployment from TravisCI infrastructure as it is no longer feasible.
* Remove `{@inheritDoc}` as it only explicitly indicates that the default behaviour at the expense of significant visual clutter.
* Change the API for attaching listeners to the `Keycloak` instance. Rather than `Keycloak.setListener(...)` class to allow multiple listeners to be notified when keycloak events occur. This is a breaking API change.

### [v0.04](https://github.com/realityforge/gwt-keycloak/tree/v0.04) (2018-09-27) · [Full Changelog](https://github.com/realityforge/gwt-keycloak/compare/v0.03...v0.04)

* Add `TokenCache` and `TokenCachingListener` classes to cache tokens in browser storage while valid.

### [v0.03](https://github.com/realityforge/gwt-keycloak/tree/v0.03) (2018-09-27) · [Full Changelog](https://github.com/realityforge/gwt-keycloak/compare/v0.02...v0.03)

* Upgrade Buildr to latest release.
* Compile GWT modules as part of build process to ensure that the `.gwt.xml` file is consistent.
* Split the gwt modules into `org.realityforge.gwt.keycloak.Keycloak` and
  `org.realityforge.gwt.keycloak.event.Event` so that the events need not be compiled if not used.

### [v0.02](https://github.com/realityforge/gwt-keycloak/tree/v0.02) (2017-10-30) · [Full Changelog](https://github.com/realityforge/gwt-keycloak/compare/v0.01...v0.02)

* Clear the tokens when the auth refresh fails. Typically this occurs because the refresh token is expired.
* Automate the publishing to Maven Central.

### [v0.01](https://github.com/realityforge/gwt-keycloak/tree/v0.01) (2016-10-17) · [Full Changelog](https://github.com/realityforge/gwt-keycloak/compare/19886f6367309d0e84d03424b3dc5b6b98e77669...v0.01)

* ‎🎉 Initial release ‎🎉
