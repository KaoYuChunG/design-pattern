package com.sme.modelapplication

import com.sme.modelapplication.mediator.ChatMediator
import com.sme.modelapplication.mediator.ChatUser
import org.junit.Test

class MediatorTest {
    @Test
    fun Mediator() {
        val mediator = ChatMediator()

        val john = ChatUser(mediator, "John")

        mediator
            .addUser(ChatUser(mediator, "Alice"))
            .addUser(ChatUser(mediator, "Bob"))
            .addUser(john)

        john.send("Hi everyone!")
    }
}