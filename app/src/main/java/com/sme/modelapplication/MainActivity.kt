package com.sme.modelapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.sme.modelapplication.abstractFactory.AbstractFactory
import com.sme.modelapplication.abstractFactory.Acer
import com.sme.modelapplication.abstractFactory.Asus
import com.sme.modelapplication.abstractFactory.Dell
import com.sme.modelapplication.behavior.StockDisplay
import com.sme.modelapplication.behavior.StockUpdate
import com.sme.modelapplication.builder.Robot
import com.sme.modelapplication.chainResponsibility.*
import com.sme.modelapplication.decorat.Printer
import com.sme.modelapplication.decorat.satrtDraw
import com.sme.modelapplication.state.Moment
import com.sme.modelapplication.state.WaterMachine2
import com.sme.modelapplication.state.waterMachineOps

import com.sme.modelapplication.strategy.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

//  chain od responsibility: depende a orden que colocar, nivel de cargo
    //metodo 1
//    val college = College(null) // o mais superior
//    val president = President(college)
//    val groupLeader = GroupLeader(president)

    //metodo 2
    val applyChain = groupLeader2 orElse president2 orElse college2

//  obserble
    val su = StockUpdate()
    val sd = StockDisplay()


//  state
    lateinit var machine: WaterMachine2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        teste1.setOnClickListener(this)
        teste2.setOnClickListener(this)
        teste3.setOnClickListener(this)
        teste4.setOnClickListener(this)
        teste5.setOnClickListener(this)
        teste6.setOnClickListener(this)
        teste7.setOnClickListener(this)
        teste8.setOnClickListener(this)
        teste9.setOnClickListener(this)
        teste10.setOnClickListener(this)
        teste11.setOnClickListener(this)
        teste12.setOnClickListener(this)
        teste13.setOnClickListener(this)
        teste14.setOnClickListener(this)
        teste15.setOnClickListener(this)
        teste16.setOnClickListener(this)
        teste17.setOnClickListener(this)
        teste18.setOnClickListener(this)
        teste19.setOnClickListener(this)

        state()
        obserble()
        builder()
    }



    fun state(){
        Log.i("BENNN", "state ")
        machine = WaterMachine2()
    }


    fun builder(){
        try{
            val robot = Robot(code = "003", weight = 100)
        }catch ( e:IllegalArgumentException ){
            Log.i("BENNN", "error:" +e.message)
        }
    }

    fun obserble(){
        su.listeners.add(sd)
    }

    fun Computer.Factory.fromCPU(cpu:String):String = when(cpu){
        "PC" -> "Core"
        "Server" -> "Xeon"
        else -> "null"
    }

    override fun onClick(v: View?) {
        when(v){
            teste1 -> {
                val com = Computer.Factory(ComputerType.PC)
                textView.text =  Computer.fromCPU(com.cpu)
            }
            teste2 -> {
                val com = Computer.Factory(ComputerType.SERVER)
                textView.text = Computer.fromCPU(com.cpu)
            }
            teste3 -> {
                val dellFactory = AbstractFactory<Dell>()
                val dell = dellFactory.produce()
                textView.text = dell.cpu
            }
            teste4 -> {
                val dellFactory = AbstractFactory<Asus>()
                val dell = dellFactory.produce()
                textView.text = dell.cpu
            }
            teste5 -> {
                val dellFactory = AbstractFactory<Acer>()
                val dell = dellFactory.produce()
                textView.text = dell.cpu
            }
            teste6 -> {
                su.price = 100
                su.value = 1
                textView.text = su.value.toString()
            }
            teste7 -> {
                su.price = 80
                su.value = -1
                textView.text = su.value.toString()
            }
            teste8 -> {
                //metodo 1
//                val shaw = Swimmer(Breaststroke())
                //metodo 2
                val shaw = SwimmerLamda(::breaststroke)
                textView.text =  shaw.swim()
            }
            teste9 -> {
                //metodo 1
//                val shaw = Swimmer(Backstroke())
                //metodo 2
                val shaw = SwimmerLamda(::backstroke)
                textView.text =  shaw.swim()
            }
            teste10 -> {
                //metodo 1
//                val shaw = Swimmer(Freestyle())
                //metodo 2
                val shaw = SwimmerLamda(::freestyle)
                textView.text =  shaw.swim()
            }
            teste11 ->{
                val civil = Civil()
                textView.text = civil.execute(::socialSecurity)
            }
            teste12 ->{
                val civil = Civil()
                textView.text = civil.execute(::applyCard)
            }
            teste13 ->{
                //metodo 1
//                textView.text = groupLeader.handleEvent(ApplyEvent(edit.text.toString().toInt(), "compra um lapis"))
                //metodo 2
                if(edit.text.toString().isNotEmpty()){
                    textView.text = applyChain(ApplyEvent2(edit.text.toString().toInt(), "compra um lapis"))
                }else{
                    textView.text = "digite numeros "
                }
            }
            teste14 ->{
                textView.text = waterMachineOps(machine, Moment.DRINKING_WATER)
            }
            teste15 ->{
                textView.text = waterMachineOps(machine, Moment.INSTANCE_NOODLES)
            }
            teste16 ->{

                textView.text = waterMachineOps(machine, Moment.AFTER_WORK)
            }
            teste17 ->{
              Printer().run {
                  textView.text = satrtDraw{
                      drawLine()
                  }
              }
            }
            teste18 ->{
                Printer().run {
                    textView.text = satrtDraw{
                        drawDottedLine()
                    }
                }
            }
            teste19 ->{
                Printer().run {
                    textView.text = satrtDraw{
                        drawStars()
                    }
                }
            }
        }
    }

}