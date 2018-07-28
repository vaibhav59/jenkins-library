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
            //script.sh "${script.tool 'MAVEN'}/bin/mvn ${args}"
            script.sh (script: "#!/bin/sh -e\n ${script.tool 'MAVEN'}/bin/mvn ${args} \nset -x",returnStdout: true)
        }	
       
        def runcmd(cmd) {
            script.sh (script: '#!/bin/sh -e\n'+ cmd + '\nset -x',returnStdout: true)
        }
		
}
