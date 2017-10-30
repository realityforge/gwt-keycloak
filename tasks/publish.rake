require 'mcrt'

desc 'Publish release on maven central'
task 'publish_to_maven_central' do
  project = Buildr.projects[0].root_project
  password = ENV['MAVEN_CENTRAL_PASSWORD'] || (raise "Unable to locate environment variable with name 'MAVEN_CENTRAL_PASSWORD'")
  MavenCentralPublishTool.buildr_release(project, 'org.realityforge', 'realityforge', password)
end
