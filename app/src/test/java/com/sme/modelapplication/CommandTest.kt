package com.sme.modelapplication

import android.util.Log
import com.sme.modelapplication.command.CommandProcessor
import com.sme.modelapplication.command.OrderAddCommand
import com.sme.modelapplication.command.OrderPayCommand
import com.sme.modelapplication.command.OrderSanCommand
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import org.junit.Test


class CommandTest {

    @Test
    fun Command() {
        CommandProcessor()
            .addToQueue(OrderAddCommand(1L))
            .addToQueue(OrderAddCommand(2L))
            .addToQueue(OrderPayCommand(2L))
            .addToQueue(OrderPayCommand(1L))
            .addToQueue(OrderSanCommand(1L))
            .addToQueue(OrderSanCommand(2L))
            .processCommands()
    }
}