package deps

object Chipmango : Dependency() {
    private const val version = "0.3.3"
    private const val chipmangoCore = "io.github.tiendung717:chipmango:$version"

    override fun implementations() = listOf<String>(
        chipmangoCore
    )
}