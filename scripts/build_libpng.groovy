#!groovy
//TEST JOB Build and install libpng
properties([disableConcurrentBuilds()])

pipeline{
	agent {
	label 'master'
	}
	options {
		buildDiscarder(logRotator(numToKeepStr: '10', artifactNumToKeepStr: '10'))
		timestamps()
	}
	stages {
		stage("Preparations"){
			ws('workspace/mylibpng') {
				steps {
					git 'git://git.code.sf.net/p/libpng/code'
				}
			}
		}
	}
}
