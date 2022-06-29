package com.sme.modelapplication

import com.sme.modelapplication.adapter.CelsiusTemperature
import com.sme.modelapplication.adapter.FahrenheitTemperature
import junit.framework.Assert.assertEquals
import org.junit.Test

class AdapterTest {

    @Test
    fun Adapter() {
        //relaciona celsius e fahrenheit
        val celsiusTemperature = CelsiusTemperature(0.0)
        val fahrenheitTemperature = FahrenheitTemperature(celsiusTemperature)

        //converter entre os dois
        celsiusTemperature.temperature = 36.6
        assertEquals(fahrenheitTemperature.temperature,97.88)
        assertEquals(celsiusTemperature.temperature,36.6)

        fahrenheitTemperature.temperature = 100.0
        assertEquals(celsiusTemperature.temperature,37.78)
    }
}