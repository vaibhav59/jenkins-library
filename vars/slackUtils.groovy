#!/usr/bin/groovy
// vars/slackUtils.groovy

def call(Map args) {
//slackSend baseUrl: 'https://mypersonalspace-555.slack.com/services/hooks/jenkins-ci/', channel: '#deploy-notification', color: 'good', message: 'Successfully Deployed', teamDomain: 'mypersonalspace-555', tokenCredentialId: 'slackToken'

// build status of null means successful
buildStatus = "${args.buildStatus}" ?: 'SUCCESS'

  // Default values
  def colorName = 'RED'
  def colorCode = '#FF0000'
  def subject = "${buildStatus}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'"
  def summary = "${subject} (${env.BUILD_URL})"
  def details = """<p>${buildStatus}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
    <p>Check console output at &QUOT;<a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>&QUOT;</p>"""

// Override default values based on build status
  if (buildStatus == 'STARTED') {
    color = 'YELLOW'
    colorCode = '#FFFF00'
  } else if (buildStatus == 'SUCCESS') {
    color = 'GREEN'
    colorCode = '#00FF00'
  } else {
    color = 'RED'
    colorCode = '#FF0000'
  }

try{

def dryRun = ("${args.dryRun}")
if (true) {
   
  slackSend baseUrl: "https://${args.team}.slack.com/services/hooks/jenkins-ci/" , color: 'good' , message: summary, channel: "${args.channel}", teamDomain: 'mypersonalspace-555', tokenCredentialId: 'slackToken'
  //slackSend failOnError: true, baseUrl: "https://${args.team}/services/hooks/jenkins-ci/" ,color: colorCode , message: summary , channel: ${args.channel}, teamDomain: ${args.team}, tokenCredentialId: ${args.credentials}
  slackSend baseUrl: 'https://mypersonalspace-555.slack.com/services/hooks/jenkins-ci/', channel: "${args.channel}", color: 'good', message: 'Deployed Successfully', teamDomain: 'mypersonalspace-555', tokenCredentialId: 'slackToken' 
  }
} catch(e) {
  throw e
  echo "Failed to publish message on Slack channel ${args.channel}. Caused by: ${e.message}"
}
}
