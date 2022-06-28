package com.sme.modelapplication.state

import android.util.Log

//qunado o estado interno mudou tipo de class. aqui show ADT

//era assim
class Off(override val machine: WaterMachine):WaterMachineState(machine)
class Heating(override val machine: WaterMachine):WaterMachineState(machine)
class Cooling(override val machine: WaterMachine):WaterMachineState(machine)

sealed class  WaterMachineState(open val machine: WaterMachine){
    fun turnHeating():String{
        if(this !is Heating){
            return "liga heating"
            machine.state = machine.heating
        }else{
            return "ja esta no modo de heating "
        }
    }

    fun turnCooling():String{
        if(this !is Cooling){
            return "liga cooling"
            machine.state = machine.cooling
        }else{
            return "ja esta no modo de cooling "
        }
    }

    fun turnOff():String{
        if(this !is Off){
            return "liga off"
            machine.state = machine.off
        }else{
            return "ja esta no modo de off "
        }
    }
}

class WaterMachine{
    var state: WaterMachineState
    val off = Off(this)
    val heating = Heating(this)
    val cooling = Cooling(this)

    init {
        this.state = off
    }

    fun turnHeating():String{
        Log.i("BENNN", "turnHeating 52")
        return this.state.turnHeating()
    }

    fun turnCooling():String{
        Log.i("BENNN", "turnCooling 57")
        return this.state.turnCooling()
    }

    fun turnOff():String{
        Log.i("BENNN", "turnOff 58")
        return this.state.turnOff()
    }
}

//otimizando
enum class Moment{
    EARLY_MORNING,
    DRINKING_WATER,
    INSTANCE_NOODLES,
    AFTER_WORK
}

class Off2(override val machine: WaterMachine2):WaterMachineState2(machine)
class Heating2(override val machine: WaterMachine2):WaterMachineState2(machine)
class Cooling2(override val machine: WaterMachine2):WaterMachineState2(machine)

sealed class  WaterMachineState2(open val machine: WaterMachine2){
    fun turnHeating():String{
//        if(this !is Heating2){
            machine.state = machine.heating
            return "liga heating"
//        }else{
//            return "ja esta no modo de heating "
//        }
    }

    fun turnCooling():String{
//        if(this !is Cooling2){
            machine.state = machine.cooling
            return "liga cooling"
//        }else{
//            return "ja esta no modo de cooling "
//        }
    }

    fun turnOff():String{
//        if(this !is Off2){
            machine.state = machine.off
            return "liga off"
//        }
//        else{
//            return "ja esta no modo de off "
//        }
    }
}

class WaterMachine2{
    var state: WaterMachineState2
    val off = Off2(this)
    val heating = Heating2(this)
    val cooling = Cooling2(this)

    init {
        this.state = off
    }

    fun turnHeating():String{
        Log.i("BENNN", "turnHeating 52")
        return this.state.turnHeating()
    }

    fun turnCooling():String{
        Log.i("BENNN", "turnCooling 57")
        return this.state.turnCooling()
    }

    fun turnOff():String{
        Log.i("BENNN", "turnOff 58")
        return this.state.turnOff()
    }
}

fun waterMachineOps(machine: WaterMachine2, moment: Moment):String{
    var txt = ""
    when(moment){
        Moment.EARLY_MORNING,
        Moment.DRINKING_WATER -> when(machine.state){
            !is Cooling2 -> txt = machine.turnCooling()
            else -> txt = "ja esta no modo de cooling "
        }
        Moment.INSTANCE_NOODLES -> when(machine.state){
            !is Heating2-> txt = machine.turnHeating()
            else -> txt = "ja esta no modo de heating "
        }
        Moment.AFTER_WORK -> when(machine.state){
            !is Off2 ->txt = machine.turnOff()
            else -> txt = "ja esta no modo de off "
        }
        else -> Unit
    }
    return txt
}