import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import platform.posix.perror
import platform.posix.stat

private const val HelpMessage = "Usage: -c filename"

@OptIn(ExperimentalForeignApi::class)
fun main(args: Array<String>) {
    if(args.count() < 2) {
        println(HelpMessage)
    } else {
        if(args[0] == "-c") {
            memScoped {
                val filename = args[1]
                val statData = alloc<stat>()
                if(stat(filename, statData.ptr) == 0) {
                    println("${statData.st_size}")
                } else {
                    perror("Stat")
                }
            }
        }
    }
}
