String getPomProperty(final Map args) {
    final String pathToPom = args.pathToPom ?: 'pom.xml'
    final matcher = readFile(pathToPom) =~ "<${args.property}>(.+)</${args.property}>"
    String version = matcher ? matcher[0][1] : null
    return version
}
