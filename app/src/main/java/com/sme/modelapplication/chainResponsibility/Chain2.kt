package com.sme.modelapplication.chainResponsibility

import java.lang.IllegalArgumentException

class PartialFunction <in P1, out R>(private val defineAt:(P1) -> Boolean, private val f: (P1) -> R) : (P1) -> R{
    override  fun invoke(p1:P1):R{
        if(defineAt(p1)){
            return f(p1)
        }else{
            throw IllegalArgumentException("valor: ($p1) nao suporta pelo esse funcao ")
        }
    }
    fun isDefinedAt(p1:P1) = defineAt(p1)
}

infix  fun <P1,R> PartialFunction<P1,R>.orElse(that: PartialFunction<P1,R>):
        PartialFunction<P1,R>{
    return PartialFunction({this.isDefinedAt(it) || that.isDefinedAt(it)}){
        when{
            this.isDefinedAt(it) -> this(it)
            else -> that(it)
        }
    }
}

data class ApplyEvent2(val money:Int, val title:String)

val groupLeader2 = {
    val defineAt:(ApplyEvent2) -> Boolean = { it.money <= 100 }
    val handler: (ApplyEvent2) -> String = {"Group Leader -> ${it.title} "}

    PartialFunction(defineAt, handler)
}()

val president2 = {
    val defineAt:(ApplyEvent2) -> Boolean = { it.money <= 500 }
    val handler: (ApplyEvent2) -> String = {"President -> ${it.title}  "}

    PartialFunction(defineAt, handler)
}()

val college2 = {
    val defineAt:(ApplyEvent2) -> Boolean = { true }
    val handler: (ApplyEvent2) -> String = {
        when {
            it.money > 1000 -> "College foi recusado."
            else -> "College -> ${it.title}  "
        }
    }

    PartialFunction(defineAt, handler)
}()


