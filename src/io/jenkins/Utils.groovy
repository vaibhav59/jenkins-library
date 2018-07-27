package io.jenkins

class Utils implements Serializable {

	def script

	Utils(script) {
		this.script = script
	}

	/**
	 * @return GIT config for devteam-tools
	 */
	def gitTools() {
	    return [branch: 'master']
	}

        def mvn(args) {
            script.sh "${script.tool 'MAVEN'}/bin/mvn ${args}"
        }	
       
        def runcmd(cmd) {
            script.sh (script: '#!/bin/sh -e\n'+ cmd,returnStdout: true)
        }
		
}
