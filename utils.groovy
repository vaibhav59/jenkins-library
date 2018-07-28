//vars/utils.groovy

void addUser() {

        def USER = wrap([$class: 'BuildUser']) {
            return env.BUILD_USER
         }

        manager.addShortText("${USER}", "black", "white", "1px", "green");

}
