package com.sme.modelapplication

import com.sme.modelapplication.composite.*
import junit.framework.Assert.assertEquals
import org.junit.Test

class CompositeTest {

    @Test
    fun CompositeTest() {
        val pc = PersonalComputer()
            .add(Processor())
            .add(HardDrive())
            .add(Memory())

        assertEquals(pc.name,"PC")
        assertEquals(pc.price,1600)
    }

    @Test
    fun ComputerDiffrent() {
        val pcs = arrayOf(Computer2("PC1"),Computer2("PC2"), Computer2("PC3"))
        pcs.map { computer ->
            when(computer.name) {
                "PC1"->{
                    computer
                        .add(Processor())
                        .add(HardDrive())
                        .add(Memory())
                }
                "PC2"->{
                    computer
                        .add(Processor())
                        .add(Processor())
                        .add(HardDrive())
                        .add(Memory())
                        .add(Visao())
                }
                else -> computer
            }
        }.forEach { computer ->
            if(computer.name.equals("PC1")){
                assertEquals(computer.price,1600)
            }else if(computer.name.equals("PC2")){
                assertEquals(computer.price,5470)
            }else{
                assertEquals(computer.price,0)
            }
        }
    }
}