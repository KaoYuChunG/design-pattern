package com.sme.modelapplication

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sme.modelapplication.visitor.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

class VisitorTest {

    @Test
    fun month() {
        val projectAlpha = FixedPriceContract(costPerYear = 10000)
        val projectBeta = SupportContract(costPerMonth = 500)
        val projectGamma = TimeAndMaterialsContract(hours = 150, costPerHour = 10)
        val projectKappa = TimeAndMaterialsContract(hours = 50, costPerHour = 50)

        val projects = arrayOf(projectAlpha, projectBeta, projectGamma, projectKappa)

        val monthlyCostReportVisitor = MonthlyCostReportVisitor()
        val monthlyCost = projects.map { it.accept(monthlyCostReportVisitor) }
            .sum()
        assertEquals(monthlyCost,5333)

    }

    @Test
    fun year() {
        val projectAlpha = FixedPriceContract(costPerYear = 10000)
        val projectBeta = SupportContract(costPerMonth = 500)
        val projectGamma = TimeAndMaterialsContract(hours = 150, costPerHour = 10)
        val projectKappa = TimeAndMaterialsContract(hours = 50, costPerHour = 50)

        val projects = arrayOf(projectAlpha, projectBeta, projectGamma, projectKappa)

        val yearlyReportVisitor = YearlyReportVisitor()
        val yearlyCost = projects.map { it.accept(yearlyReportVisitor) }
            .sum()

        assertEquals(yearlyCost,20000)
    }

    @Test
    fun time() {
        val projectAlpha = FixedPriceContract(costPerYear = 10000)
        val projectBeta = SupportContract(costPerMonth = 500)
        val projectGamma = TimeAndMaterialsContract(hours = 150, costPerHour = 10)
        val projectKappa = TimeAndMaterialsContract(hours = 50, costPerHour = 50)

        val projects = arrayOf(projectAlpha, projectBeta, projectGamma, projectKappa)

        val timelyReportVisitor = TimelyReportVisitor()
        val timelyCost = projects.map { it.accept(timelyReportVisitor) }
            .sum()

        assertEquals(timelyCost,361804000)
    }

    @Test
    fun string() {
        val projectAlpha = FixedPriceContract(costPerYear = 10000)
        val projectBeta = SupportContract(costPerMonth = 500)
        val projectGamma = TimeAndMaterialsContract(hours = 150, costPerHour = 10)
        val projectKappa = TimeAndMaterialsContract(hours = 50, costPerHour = 50)

        val projects = arrayOf(projectAlpha, projectBeta, projectGamma, projectKappa)
        val test = arrayOf("10000", "1800000", "1500", "2500")

        val stringyReportVisitor = StringyReportVisitor()
        projects.map { it.accept(stringyReportVisitor) }
            .map {
                assertTrue(test.contains(it))
            }
    }
}