package com.sme.modelapplication.behavior

import android.util.Log
import kotlin.properties.Delegates

interface StockUpdateListener {
    fun onRise(price: Int):String
    fun onFall(price: Int):String
}

class StockDisplay: StockUpdateListener{
    override fun onRise(price: Int) = "Rise ${price}"
    override fun onFall(price: Int) = "fall ${price}"
}

class StockUpdate{
    var listeners = mutableSetOf<StockUpdateListener>()

    var price: Int by Delegates.observable(0){ _, old, new ->
        listeners.forEach {
            if (new > old){
                Log.i("BENNN","onRise -->"+it.onRise(price))
            } else{
                Log.i("BENNN","onFall -->"+it.onFall(price))
            }
        }
    }

    //有否決功能的
    var value: Int by Delegates.vetoable(0){ prop, old, new ->
        Log.i("BENNN","value new   ${new}")
        new > 0
    }
}