## Release Checklist

Here are the steps to follow when making a release.

* Update `CHANGELOG.md`
* Remove the "-SNAPSHOT" from the version "*1.1.0*-SNAPSHOT" in `pom.xml` (where *1.1.0* is the actual version, of course)
  * `$ mvn versions:set -DnewVersion=1.1.0`
* Add new contributors to `pom.xml`, if any
* Commit those changes as "Release 1.1.0"
  * `$ git add pom.xml **/pom.xml`
  * `$ git commit -m "Release 1.1.0"`
* Tag commit as "v1.1.0" with short description of main changes
  * `$ git tag -a v1.1.0 -m "Description of changes"`
* Push to main repo on GitHub
  * `$ git push origin master`
  * `$ git push origin v1.1.0`
* Wait for build to go green
* Publish to Maven Central
  * `$ mvn clean deploy -P release`
* Updated the version to "*1.1.1*-SNAPSHOT" in `pom.xml` (where *1.1.1* is the next release version, of course)
  * `$ mvn versions:set -DnewVersion=1.1.1-SNAPSHOT`
* Commit those changes as "Preparing for next release"
  * `$ git add pom.xml **/pom.xml`
  * `$ git commit -m "Preparing for next release"`
* Push to main repo on GitHub
  * `$ git push origin master`
