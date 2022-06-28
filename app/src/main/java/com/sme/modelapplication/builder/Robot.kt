package com.sme.modelapplication.builder

import android.util.Log

class Robot(val code:String, val battery: String? =null , val height: Int? = null, val weight: Int? = null) {
    init {

        require(weight == null || battery != null ){
            "sorry, the  weight is error"
        }

    }
}