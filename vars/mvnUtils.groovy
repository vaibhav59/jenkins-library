String getPomProperty(final Map args) {
    utils.validateArgs args, ['property']

    final String pathToPom = args.pathToPom ?: 'pom.xml'
    final matcher = readFile(pathToPom) =~ "<${args.property}>(.+)</${args.property}>"
    String version = matcher ? matcher[0][1] : null
    return version
}
s
static void validateArgs(final Map args, final List<String> keys) throws InvalidArgumentsException {
    final List nullProps = []

    for (key in keys) {
        if (args[key] == null) {
            nullProps.add key
        }
    }

    if (nullProps.size()) {
        throw new InvalidArgumentsException("${nullProps.join(', ')} must be defined!")
    }
}
