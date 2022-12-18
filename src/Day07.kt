sealed class FileSystem(var size: Int = 0) {
    class File(val name: String, size: Int) : FileSystem(size)
    data class Directory(
        val name: String,
        val files: MutableSet<String> = mutableSetOf()
    ) : FileSystem()
}

fun main() {
    val input = readInput("Day07")
    val fileSystem = mutableMapOf<String, FileSystem>("/" to FileSystem.Directory("/"))

    val currentPath = mutableListOf<String>()
    input.forEach {
        when {
            it == "$ cd /" -> currentPath.clear()
            it == "$ cd .." -> currentPath.removeLast()
            it == "$ ls" -> Unit
            it.startsWith("dir ") || it.startsWith("$ cd ") -> {
                val directory = currentPath.joinToString("/", "/")
                val name = it.substring((if (it.startsWith("dir ")) "dir " else "$ cd ").length)
                val path = (currentPath + name).joinToString("/", "/")
                (fileSystem[directory] as FileSystem.Directory).files.add(name)
                fileSystem.putIfAbsent(path, FileSystem.Directory(path))
                if (it.startsWith("$ cd ")) currentPath.add(name)
            }
            else -> {
                val (size, name) = it.split(' ')
                val directory = currentPath.joinToString("/", "/")
                val path = (currentPath + name).joinToString("/", "/")
                (fileSystem[directory] as FileSystem.Directory).files.add(name)
                fileSystem[path] = FileSystem.File(name, size.toInt())
            }
        }
    }

    calculateDirectorySize("", fileSystem)
    fileSystem.values.sumOf { if (it is FileSystem.Directory && it.size <= 100000) it.size else 0 }.println()

    val neededSpace = 30000000 - (70000000 - fileSystem["/"]!!.size)
    fileSystem.values.filter { it is FileSystem.Directory }.sortedBy { it.size }.first { it.size >= neededSpace}
        .size.println()
}

private fun calculateDirectorySize(path: String, fileSystem: Map<String, FileSystem>): Int {
    val file = fileSystem[path.ifEmpty { "/" }]!!
    if (file is FileSystem.Directory) {
        file.size = file.files.sumOf { calculateDirectorySize("$path/$it", fileSystem) }
    }
    return file.size
}
