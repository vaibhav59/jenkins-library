// vars/gitUtils.groovy

String getCommitId(boolean shortId = false, String args = 'HEAD') {
    String commitId = sh(returnStdout: true, script: "git rev-parse ${args}").trim()
    return shortId ? commitId[0..6] : commitId
}



/**
 * Retrieve current branch name.
 * @return branch name.
 */
String getBranchName() {
    //return env.BRANCH_NAME ?: sh(returnStdout: true, script: 'git rev-parse --abbrev-ref HEAD').trim()
    return scmvars.GIT_BRANCH ?: sh(returnStdout: true, script: 'git rev-parse --abbrev-ref HEAD').trim()
}
