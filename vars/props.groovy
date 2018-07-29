// vars/props.groovy
def logrotate( Map args, final String option) {
    properties([buildDiscarder(logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: "${args.day}", numToKeepStr: "${args.build}")), disableConcurrentBuilds(), [$class: 'RebuildSettings', autoRebuild: false, rebuildDisabled: false]], option) 
}
