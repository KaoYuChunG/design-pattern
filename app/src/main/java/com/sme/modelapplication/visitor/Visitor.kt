package com.sme.modelapplication.visitor

interface ReportVisitable {
    fun <R> accept(visitor: ReportVisitor<R>): R
}

class FixedPriceContract(val costPerYear: Long) : ReportVisitable {
    override fun <R> accept(visitor: ReportVisitor<R>): R = visitor.visit(this)
}

class TimeAndMaterialsContract(val costPerHour: Long, val hours: Long) : ReportVisitable {
    override fun <R> accept(visitor: ReportVisitor<R>): R = visitor.visit(this)
}

class SupportContract(val costPerMonth: Long) : ReportVisitable {
    override fun <R> accept(visitor: ReportVisitor<R>): R = visitor.visit(this)
}

interface ReportVisitor<out R> {

    fun visit(contract: FixedPriceContract): R
    fun visit(contract: TimeAndMaterialsContract): R
    fun visit(contract: SupportContract): R
}

class MonthlyCostReportVisitor : ReportVisitor<Long> {

    override fun visit(contract: FixedPriceContract): Long =
        contract.costPerYear / 12

    override fun visit(contract: TimeAndMaterialsContract): Long =
        contract.costPerHour * contract.hours

    override fun visit(contract: SupportContract): Long =
        contract.costPerMonth
}

class YearlyReportVisitor : ReportVisitor<Long> {

    override fun visit(contract: FixedPriceContract): Long =
        contract.costPerYear

    override fun visit(contract: TimeAndMaterialsContract): Long =
        contract.costPerHour * contract.hours

    override fun visit(contract: SupportContract): Long =
        contract.costPerMonth * 12
}

class TimelyReportVisitor : ReportVisitor<Long> {

    override fun visit(contract: FixedPriceContract): Long =
        contract.costPerYear * 36000

    override fun visit(contract: TimeAndMaterialsContract): Long =
        contract.costPerHour * contract.hours

    override fun visit(contract: SupportContract): Long =
        contract.costPerMonth * 3600
}

class StringyReportVisitor : ReportVisitor<String> {

    override fun visit(contract: FixedPriceContract): String =
        contract.costPerYear.toString()

    override fun visit(contract: TimeAndMaterialsContract): String =
        (contract.costPerHour * contract.hours).toString()

    override fun visit(contract: SupportContract): String =
        (contract.costPerMonth * 3600).toString()
}