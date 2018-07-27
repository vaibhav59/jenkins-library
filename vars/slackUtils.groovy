void notify(final Map args) {
if (!args.dryRun){

  slackSend (baseUrl: "https://${args.team}/services/hooks/jenkins-ci/" ,color: args.level , message: "Success: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})", channel: args.channel, teamDomain: args.team, tokenCredentialId: args.credentials)
  }

}
