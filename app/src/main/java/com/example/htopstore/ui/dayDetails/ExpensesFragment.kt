package com.example.htopstore.ui.dayDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.domain.model.Expense
import com.example.htopstore.databinding.FragmentExpensesBinding
import com.example.htopstore.util.adapters.ExpenseAdapter
import com.example.htopstore.util.helper.DialogBuilder

class ExpensesFragment private constructor(): Fragment() {
    private val vm: DayDetailsViewModel by  activityViewModels()
    private lateinit var binding: FragmentExpensesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    companion object{
        fun newInstance(date: String):ExpensesFragment {
            val args = Bundle()
            args.putString("date",date)
            val fragment = ExpensesFragment()
            fragment.arguments = args
            return fragment
    }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentExpensesBinding.inflate(inflater, container, false)
        val date = arguments?.getString("date")
        if (date != null) {
            getExpenses(date)
        }
        return binding.root
    }
    private fun getExpenses(date:String){
        vm.getExpensesListByDate(date)
        vm.expensesList.observe(viewLifecycleOwner){
            binding.adapter.adapter = ExpenseAdapter(it as ArrayList<Expense>,::onItemClick) }
    }
    private fun onItemClick(expense: Expense) {
        DialogBuilder.showExpensesDetailsDialog(expense, requireContext())
    }

}