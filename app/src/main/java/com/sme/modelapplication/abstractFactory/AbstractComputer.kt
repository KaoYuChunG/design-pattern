package com.sme.modelapplication.abstractFactory

import java.lang.IllegalArgumentException


interface AbstractComputer {
    val cpu:String
}

class Dell(override  val cpu:String = "Dell"): AbstractComputer
class Asus(override  val cpu:String = "Asus"): AbstractComputer
class Acer(override  val cpu:String = "Acer"): AbstractComputer

class DellFactory: AbstractFactory(){
    override fun produce() = Dell()
}

class AsusFactory: AbstractFactory(){
    override fun produce() = Asus()
}

class AcerFactory: AbstractFactory(){
    override fun produce() = Acer()
}

abstract class AbstractFactory{
    abstract fun produce(): AbstractComputer

    companion object{
        inline operator fun <reified  T : AbstractComputer> invoke() : AbstractFactory =
            when(T::class){
                Dell::class -> DellFactory()
                Asus::class -> AsusFactory()
                Acer::class -> AcerFactory()
                else -> throw IllegalArgumentException()
            }

    }
}