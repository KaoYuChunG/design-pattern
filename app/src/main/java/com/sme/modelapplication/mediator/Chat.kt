package com.sme.modelapplication.mediator

import android.util.Log

class ChatUser(private val mediator: ChatMediator, private val name: String) {
    fun send(msg: String) {
        Log.i("BENNN","$name: Sending Message= $msg")
        mediator.sendMessage(msg, this)
    }

    fun receive(msg: String) {
        Log.i("BENNN","$name: Message received: $msg")
    }
}

class ChatMediator {

    private val users: MutableList<ChatUser> = ArrayList()

    fun sendMessage(msg: String, user: ChatUser) {
        users
            .filter { it != user }
            .forEach {
                it.receive(msg)
            }
    }

    fun addUser(user: ChatUser): ChatMediator =
        apply { users.add(user) }

}