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
			steps {
				ws('workspace/mylibpng') {
					git 'git://git.code.sf.net/p/libpng/code'
					cmake arguments: 'CMAKE_INSTALL_PREFIX=/libpng', installation: 'InSearchPath'
//					cmakeBuild buildDir: 'build', installation: 'InSearchPath'
				}	
			}
		}
	}
}
