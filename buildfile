require 'buildr/git_auto_version'
require 'buildr/gpg'
require 'buildr/gwt'

Buildr::MavenCentral.define_publish_tasks(:profile_name => 'org.realityforge', :username => 'realityforge')

desc 'GWT Keycloak Library'
define 'gwt-keycloak' do
  project.group = 'org.realityforge.gwt.keycloak'
  project.compile.options.source = '11'
  project.compile.options.target = '11'
  project.iml.jdk_version = '17'
  compile.options.lint = 'all'

  project.version = ENV['PRODUCT_VERSION'] if ENV['PRODUCT_VERSION']

  pom.add_apache_v2_license
  pom.add_github_project('realityforge/gwt-keycloak')
  pom.add_developer('realityforge', 'Peter Donald')
  pom.provided_dependencies.concat [:javax_annotation, :jetbrains_annotations, :gwt_user]

  compile.with :jsinterop_base,
               :akasha,
               :javax_annotation,
               :jetbrains_annotations,
               :gwt_user

  package(:jar).include("#{_(:source, :main, :java)}/*")
  package(:sources)
  package(:javadoc)

  gwt_enhance(project)

  ipr.add_component_from_artifact(:idea_codestyle)
end
