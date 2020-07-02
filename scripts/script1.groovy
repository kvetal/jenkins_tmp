#!groovy
// Check home properties
properties([disableConcurrentBuilds()])

pipeline {
    agent { 
        label 'master'
        }
    options {
        buildDiscarder(logRotator(numToKeepStr: '10', artifactNumToKeepStr: '10'))
        timestamps()
    }
    stages {
        stage("First step") {
            steps {
                sh 'ssh test@home \'hostname\''
            }
        }
        stage("Second step") {
            steps {
                sh 'ssh test@home \'uptime\''
            }
        }
		stage("Third step"){
			steps{
				sh 'ls -la'
				def server = Artifactory.server 'home'
				def uploadSpec = """{
					  "files": [
    					{
      						"pattern": "bazinga/*froggy*.zip",
							"target": "bazinga-repo/froggy-files/"
    					}
				 ]
				}"""
server.upload(uploadSpec)
			}
		}
    }
}
