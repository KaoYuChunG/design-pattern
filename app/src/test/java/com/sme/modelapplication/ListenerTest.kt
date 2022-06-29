package com.sme.modelapplication

import com.sme.modelapplication.behavior.PrintingTextChangedListener
import com.sme.modelapplication.behavior.ScreeningTextChangedListener
import com.sme.modelapplication.behavior.TextView
import junit.framework.Assert.assertEquals
import org.junit.Test

class ListenerTest {

    @Test
    fun Listener() {
        val print = PrintingTextChangedListener()
        val screen = ScreeningTextChangedListener()

        val textView = TextView().apply {
            listeners.add(print)
            listeners.add(screen)
        }

        with(textView) {
            text = "Lorem ipsum"
            text = "dolor sit amet"
            text = "quem sou eu"
        }

        assertEquals(print.text,"Text is changed: dolor sit amet -> quem sou eu")
        assertEquals(screen.text,25)
    }
}