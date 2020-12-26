package day4

fun main()
{
    println(data.map { it.split(" ", "\n") }.filter{ it.count{ s->s[0]!='c'}==7}.size)
}