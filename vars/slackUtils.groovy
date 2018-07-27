void notify(final Map args) {
    utils.validateArgs args, ['message', 'credentials', 'channel', 'team']

    final boolean addBuildInfo = args.addBuildInfo || args.addBuildInfo == null || args.addBuildInfo == ''
    final String date = sh(returnStdout: true, script: 'date').trim()
    final String message = addBuildInfo ?
            "[${date} :: ${env.JOB_NAME} :: <${env.BUILD_URL}|${env.BUILD_NUMBER}>] ${args.message} (<${env.BUILD_URL}|Open>)" :
            args.message
    String color = 'good'

    withCredentials([string(credentialsId: args.credentials, variable: 'token')]) {
        switch (args.level) {
            case 'error':
            case 'danger':
                color = 'danger'
                break
            case 'warn':
            case 'warning':
                color = 'warning'
                break
            case 'log':
            case 'info':
            case 'good':
                color = 'good'
                break
        }

        def dryRun = (args.dryRun) ?: (env.slackIsInDryRun?.toBoolean()) ?: false

        if (!dryRun) {
            try {
                httpRequest(
                        httpMode: 'POST',
                        url: "https://${args.team}.slack.com/services/hooks/jenkins-ci?token=${env.token}",
                        responseHandle: 'NONE',
                        requestBody: """{
                            "channel": "${args.channel}",
                            "attachments": [
                                {
                                    "text": "${message}",
                                    "fallback": "${message}",
                                    "color": "${color}",
                                    "mrkdwn_in": [
                                        "pretext",
                                        "text",
                                        "fields"
                                    ]
                                }
                            ],
                            "link_names": "1"
                        }"""
                )
            } catch (IllegalStateException ex) {
                echo "Failed to publish message on Slack channel ${args.channel}. Caused by: ${ex.message}"
            }
        }

        echo "[${date} :: ${color}] ${args.message}"
    }
}

return this

