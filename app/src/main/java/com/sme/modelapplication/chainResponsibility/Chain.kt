package com.sme.modelapplication.chainResponsibility

data class ApplyEvent(val money:Int, val title:String)

interface ApplyHandler{
    val sucessor: ApplyHandler?
    fun handleEvent(event:ApplyEvent):String
}

class GroupLeader(override val sucessor: ApplyHandler?):ApplyHandler{
    override fun handleEvent(event: ApplyEvent):String {
        return when {
            event.money <= 100 -> "Group Leader -> ${event.title} "
            else -> when (sucessor) {
                is ApplyHandler -> sucessor.handleEvent(event)
                else -> "Group Leader nao pode tratar isso "
            }
        }
    }
}

class President(override val sucessor: ApplyHandler?):ApplyHandler{
    override fun handleEvent(event: ApplyEvent): String {
        return when{
            event.money <= 500 -> "President -> ${event.title}  "
            else -> when (sucessor) {
                is ApplyHandler -> sucessor.handleEvent(event)
                else -> "President nao pode tratar isso "
            }
        }
    }
}

class College(override val sucessor: ApplyHandler?):ApplyHandler{
    override fun handleEvent(event: ApplyEvent): String {
        return when {
            event.money > 1000 -> "College foi recusado."
            else -> "College -> ${event.title}  "
        }
    }
}