# Change Log

### Unreleased

### [v0.13](https://github.com/realityforge/gwt-keycloak/tree/v0.13) (2024-06-28) · [Full Changelog](https://github.com/realityforge/gwt-keycloak/compare/v0.12...v0.13)

Changes in this release:

* Update the `org.realityforge.akasha` artifacts to version `0.30`.
* Move to using jsinterop for javascript interoperability.

### [v0.12](https://github.com/realityforge/gwt-keycloak/tree/v0.12) (2021-07-27) · [Full Changelog](https://github.com/spritz/spritz/compare/v0.11...v0.12)

Changes in this release:

* Upgrade the `org.realityforge.akasha` artifacts to version `0.15`.

### [v0.11](https://github.com/realityforge/gwt-keycloak/tree/v0.11) (2021-04-22) · [Full Changelog](https://github.com/spritz/spritz/compare/v0.10...v0.11)

Changes in this release:

* Upgrade the `org.realityforge.akasha` artifact to version `0.10`.

### [v0.10](https://github.com/realityforge/gwt-keycloak/tree/v0.10) (2021-03-24) · [Full Changelog](https://github.com/realityforge/gwt-keycloak/compare/v0.9...v0.10)

Changes in this release:

* Upgrade the `au.com.stocksoftware.idea.codestyle` artifact to version `1.17`.
* Remove infrastructure supporting integration with the historic GWT "events" packages.
* Migrate from using Elemental2 to Akasha when interacting with the Browser API.

### [v0.9](https://github.com/realityforge/gwt-keycloak/tree/v0.9) (2021-01-12) · [Full Changelog](https://github.com/realityforge/gwt-keycloak/compare/v0.8...v0.9)

Changes in this release:

* Change `Keycloak.MIN_TOKEN_VALIDITY_SECONDS` from `15s` to `60s`. It seems that the skew of the clocks between the browser and the keycloak server can be significant in some scenarios. Increasing the minimum time decreases the chance that the skew could cause an expired token to be used by the client and rejected by the server. This does increase the number of validations and re-requests that can occur but the additional safety is considered an acceptable tradeoff.

### [v0.8](https://github.com/realityforge/gwt-keycloak/tree/v0.8) (2020-11-16) · [Full Changelog](https://github.com/realityforge/gwt-keycloak/compare/v0.7...v0.8)

Changes in this release:

* Remove some duplicate code when preparing login.
* Remove unused class `NullKeycloakListener`.
* Avoid sharing `actions` cache between `Keycloak` instances.

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
