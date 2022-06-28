package com.sme.modelapplication

class PC(override  val cpu:String = "PC"):Computer
class SERVER(override  val cpu:String = "Server"):Computer

enum class ComputerType{
    PC, SERVER
}

// version 1
//interface Computer {
//    val cpu:String
//}
//
//object ComputerFactory {
//    operator fun invoke(type: ComputerType):Computer{
//        return when(type){
//            ComputerType.PC -> PC()
//            ComputerType.SERVER -> SERVER()
//        }
//    }
//}

// version 2
interface Computer {
    val cpu:String

    companion object Factory{
        operator fun invoke(type: ComputerType):Computer{
            return when(type){
                ComputerType.PC -> PC()
                ComputerType.SERVER -> SERVER()
            }
        }
    }
}