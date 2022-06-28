package com.sme.modelapplication.behavior

import android.util.Log
import kotlin.properties.Delegates

interface StockUpdateListener {
    fun onRise(price: Int)
    fun onFall(price: Int)
}

class StockDisplay: StockUpdateListener{
    override fun onRise(price: Int) {
        Log.i("BENNN","Rise ${price}")
    }

    override fun onFall(price: Int) {
       Log.i("BENNN","fall ${price}")
    }
}

class StockUpdate{
    var listeners = mutableSetOf<StockUpdateListener>()

    var price: Int by Delegates.observable(0){ _, old, new ->
        listeners.forEach {
            Log.i("BENNN","old   ${old}")
            Log.i("BENNN","new   ${new}")
            if (new > old) it.onRise(price) else it.onFall(price)
        }
    }

    //有否決功能的
    var value: Int by Delegates.vetoable(0){ prop, old, new ->
        Log.i("BENNN","value new   ${new}")
        new > 0
    }
}