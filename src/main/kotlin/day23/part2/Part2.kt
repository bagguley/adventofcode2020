package day23.part2

fun main() {
    println("Start")
    val t1 = System.currentTimeMillis()

    val nodes = createNodes()
    var ptr = nodes.first()

    println("Run")
    for (i in 1..10_000_000) {
        addAfter(take3(ptr), ptr)
        ptr = ptr.nextNode
    }

    val one = nodes.find { n -> n.value == 1 }!!
    val t2 = System.currentTimeMillis()
    println("Time " + (t2-t1))
    val a = one.nextNode.value.toLong()
    val b = one.nextNode.nextNode.value.toLong()
    println(a)
    println(b)
    println(a*b)
}

fun createNodes(): List<Node> {
    val cups = "389547612".map { it.toString().toInt() }.toMutableList()
    val nodeMap = mutableMapOf<Int,Node>()
    val nodes = mutableListOf<Node>()
    for (p in 10..1_000_000) {
        cups.add(p)
    }

    for (c in cups) {
        val node = Node(c)
        nodes.add(node)
        nodeMap[c] = node
    }

    val cupsSize = cups.size

    nodes.windowed(2, 1).forEach { (a,b) -> a.nextNode = b ; b.previousNode = a}
    nodes.first().previousNode = nodes.last()
    nodes.last().nextNode = nodes.first()
    nodes.forEach { n -> n.nextNumber = nodeMap[next(n.value, cupsSize)]!!}
    nodes.forEach { n -> n.previousNumber = nodeMap[prev(n.value, cupsSize)]!! }

    return nodes
}

fun next(num: Int, cupsSize: Int): Int {
    val x = (num + 1) % (cupsSize + 1)
    return if (x == 0) 1 else x
}

fun prev(num: Int, cupsSize: Int): Int {
    val x = num - 1
    return if (x == 0) cupsSize else x
}

fun take3(ptr: Node): Pair<Node, Node> {
    val first = ptr.nextNode
    val last = first.nextNode.nextNode
    val after = last.nextNode

    ptr.nextNode = after
    after.previousNode = ptr

    return Pair(first, last)
}

fun addAfter(taken: Pair<Node, Node>, current: Node) {
    val takenValues = listOf(taken.first.value, taken.first.nextNode.value, taken.second.value)

    var addAfterNode = current.previousNumber
    while (takenValues.contains(addAfterNode.value)) {
        addAfterNode = addAfterNode.previousNumber
    }

    val addBeforeNode = addAfterNode.nextNode
    addAfterNode.nextNode = taken.first
    taken.first.previousNode = addAfterNode
    addBeforeNode.previousNode = taken.second
    taken.second.nextNode = addBeforeNode
}
