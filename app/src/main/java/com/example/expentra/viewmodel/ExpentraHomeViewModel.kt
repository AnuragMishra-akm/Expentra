package com.example.expentra.viewmodel

import androidx.lifecycle.ViewModel
import com.example.expentra.data.dao.ExpenseDao
import com.example.expentra.data.model.ExpenseEntity

class ExpentraHomeViewModel(expenseDao: ExpenseDao): ViewModel() {
    val expenses = expenseDao.getAllExpenses()

    fun getBalance(list: List<ExpenseEntity>): String{
        var total = 0.0
        list.forEach {
            if(it.type == "Income"){
                total += it.amount
            }else{
                total -= it.amount
            }
        }
        return "₹ $total"
    }

    fun getTotalExpense(list: List<ExpenseEntity>): String{
        var total = 0.0
        list.forEach {
            if(it.type == "Expense"){
                total += it.amount
            }
        }
        return "₹ $total"
    }

    fun getTotalIncome(list: List<ExpenseEntity>): String{
        var total = 0.0
        list.forEach {
            if(it.type == "Income"){
                total += it.amount
            }
        }
        return "₹ $total"
    }

}