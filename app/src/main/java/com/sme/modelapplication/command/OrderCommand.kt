package com.sme.modelapplication.command

import android.util.Log

interface OrderCommand {
    fun execute():String
}

class OrderAddCommand(private val id: Long) : OrderCommand {
    override fun execute() =  "Adding order with id: $id"
}

class OrderPayCommand(private val id: Long) : OrderCommand {
    override fun execute() = "Paying for order with id: $id"
}

class OrderSanCommand(private val id: Long) : OrderCommand {
    override fun execute() = "Saning for order with id: $id"
}

class CommandProcessor {

    private val queue = ArrayList<OrderCommand>()

    fun addToQueue(orderCommand: OrderCommand) =
        apply {
            queue.add(orderCommand)
        }

    fun processCommands() =
        apply {
            val list = arrayListOf<String>()
            queue.forEach {
//                Log.i("BENNN","it.execute() "+it.execute())
                list.add(it.execute())
            }
            queue.clear()
            list
        }
}