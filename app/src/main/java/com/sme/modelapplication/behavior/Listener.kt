package com.sme.modelapplication.behavior

import kotlin.properties.Delegates

interface TextChangedListener {

    fun onTextChanged(oldText: String, newText: String)
}

class PrintingTextChangedListener : TextChangedListener {

    var text = ""

    override fun onTextChanged(oldText: String, newText: String) {
        text = "Text is changed: $oldText -> $newText"
    }
}

class ScreeningTextChangedListener : TextChangedListener {

    var text = 0

    override fun onTextChanged(oldText: String, newText: String) {
        text = oldText.length+newText.length
    }
}

class TextView {

    val listeners = mutableListOf<TextChangedListener>()

    var text: String by Delegates.observable("<empty>") { _, old, new ->
        listeners.forEach { it.onTextChanged(old, new) }
    }
}

