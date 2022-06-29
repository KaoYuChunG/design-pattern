package com.sme.modelapplication.composite

open class Equipment(
    open val price: Int,
    val name: String
)

open class Computer2(name: String) : Equipment(0, name) {

    private val equipments = ArrayList<Equipment>()

    override val price: Int
        get() = equipments.map { it.price }.sum()


    fun add(equipment: Equipment) =
        apply { equipments.add(equipment) }
}

class PersonalComputer : Computer2("PC")
class Processor : Equipment(1070, "Processor")
class HardDrive : Equipment(250, "Hard Drive")
class Memory : Equipment(280, "Memory")
class Visao : Equipment(2800, "Visao")