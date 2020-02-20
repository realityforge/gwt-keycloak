# Change Log

### Unreleased

### [v0.7](https://github.com/realityforge/gwt-keycloak/tree/v0.7) (2020-02-20) · [Full Changelog](https://github.com/realityforge/gwt-keycloak/compare/v0.6...v0.7)

Changes in this release:

* Specify a `Keycloak.MIN_TOKEN_VALIDITY_SECONDS` that has a reasonable default for minimum validity and change `Keycloak.updateToken(Runnable)` to use default value rather than `0`.
* Add `Keycloak.updateTokenAndExecute(Runnable)` override that uses `Keycloak.MIN_TOKEN_VALIDITY_SECONDS` for minimum validity time.

### [v0.6](https://github.com/realityforge/gwt-keycloak/tree/v0.6) (2020-02-20) · [Full Changelog](https://github.com/realityforge/gwt-keycloak/compare/v0.5...v0.6)

Changes in this release:

* Backport `Keycloak.updateTokenAndExecute(...)` which was implemented in some variation for every downstream project.

### [v0.5](https://github.com/realityforge/gwt-keycloak/tree/v0.5) (2020-02-04) · [Full Changelog](https://github.com/realityforge/gwt-keycloak/compare/v0.4...v0.5)

Changes in this release:

* Remove deployment from TravisCI infrastructure as it is no longer feasible.
* Remove `{@inheritDoc}` as it only explicitly indicates that the default behaviour at the expense of significant visual clutter.
* Change the API for attaching listeners to the `Keycloak` instance. Rather than `Keycloak.setListener(...)` class to allow multiple listeners to be notified when keycloak events occur. This is a breaking API change.

### [v0.4](https://github.com/realityforge/gwt-keycloak/tree/v0.4) (2018-09-27) · [Full Changelog](https://github.com/realityforge/gwt-keycloak/compare/v0.3...v0.4)

* Add `TokenCache` and `TokenCachingListener` classes to cache tokens in browser storage while valid.

### [v0.3](https://github.com/realityforge/gwt-keycloak/tree/v0.3) (2018-09-27) · [Full Changelog](https://github.com/realityforge/gwt-keycloak/compare/v0.2...v0.3)

* Upgrade Buildr to latest release.
* Compile GWT modules as part of build process to ensure that the `.gwt.xml` file is consistent.
* Split the gwt modules into `org.realityforge.gwt.keycloak.Keycloak` and
  `org.realityforge.gwt.keycloak.event.Event` so that the events need not be compiled if not used.

### [v0.2](https://github.com/realityforge/gwt-keycloak/tree/v0.2) (2017-10-30) · [Full Changelog](https://github.com/realityforge/gwt-keycloak/compare/v0.1...v0.2)

* Clear the tokens when the auth refresh fails. Typically this occurs because the refresh token is expired.
* Automate the publishing to Maven Central.

### [v0.1](https://github.com/realityforge/gwt-keycloak/tree/v0.1) (2016-10-17) · [Full Changelog](https://github.com/realityforge/gwt-keycloak/compare/19886f6367309d0e84d03424b3dc5b6b98e77669...v0.1)

* ‎🎉 Initial release ‎🎉
