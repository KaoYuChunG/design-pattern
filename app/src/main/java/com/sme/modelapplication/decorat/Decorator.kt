package com.sme.modelapplication.decorat

class Printer {

    fun drawLine():String{
        return "------------"
    }

    fun drawDottedLine():String{
        return "-  -  -  -  -  -"
    }

    fun drawStars():String{
        return "****************"
    }
}

fun Printer.satrtDraw(decorated : Printer.() -> String):String{
    return this.decorated()
}