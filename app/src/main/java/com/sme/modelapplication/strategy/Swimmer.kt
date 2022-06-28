package com.sme.modelapplication.strategy

import org.jetbrains.annotations.NotNull

interface SwimStrategy{
    fun swim(): String
}

class Breaststroke: SwimStrategy{
    override fun swim(): String {
        return "breaststroke ....."
    }
}

class Backstroke: SwimStrategy{
    override fun swim(): String {
        return "backstroke ....."
    }
}

class Freestyle: SwimStrategy{
    override fun swim(): String {
        return "freestyle ....."
    }
}

class Swimmer(val strategy: SwimStrategy){
    fun swim():String{
        return strategy.swim()
    }
}

//depois sao funcao de lambda
class SwimmerLamda(val swimming: () -> String){
    fun swim():String{
       return swimming()
    }
}

fun breaststroke(): String{
    return "breaststroke ....."
}
fun backstroke(): String{
    return "backstroke ....."
}
fun freestyle(): String{
    return "freestyle ....."
}

//Strategy na heranca
class Civil{

    fun execute(ask :()->String):String{
        return this.lineup() + ask() + this.evalute()
    }

    private fun lineup(): String{
        return " lineup "
    }

    private fun evalute():String{
        return " evalute "
    }
}

fun socialSecurity():String{
    return " Social-Security "
}


fun applyCard():String{
    return " Apply-Card "
}