package day10.part1

import day10.data

fun main() {
    val ad = data.toMutableList().let{it.add(0,0);it.add(it.size,it.last()+joltageDifference);it}
    val count:(ad:List<Int>)->Map<Int,Int> = {ad.windowed(2,1,false).map{(l,r)->r-l}.groupBy{it}.mapValues{(_,v)->v.size}}
    count(ad).let{println(it[1]!!*it[3]!!)}
}

