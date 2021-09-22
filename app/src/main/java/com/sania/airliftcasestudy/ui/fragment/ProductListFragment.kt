package com.sania.airliftcasestudy.ui.fragment

import CurrencyListAdapter
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.sania.airliftcasestudy.R
import com.sania.airliftcasestudy.databinding.FragmentProductListBinding
import com.sania.airliftcasestudy.models.ProductModel
import com.sania.airliftcasestudy.screen.states.ProductListScreenState
import com.sania.airliftcasestudy.ui.activities.AddCurrencyActivity
import com.sania.airliftcasestudy.viewmodels.ProductListViewModel
import kotlinx.android.synthetic.main.fragment_product_list.*

class ProductListFragment : Fragment() {

    lateinit var viewModel: ProductListViewModel
    private val productList = ArrayList<ProductModel>()

    private var recyclerAdapter = CurrencyListAdapter(productList)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentProductListBinding>(
            inflater, R.layout.fragment_product_list, container, false
        )

        viewModel = ViewModelProvider(this).get(ProductListViewModel::class.java)


        setupViews(binding)
        initObservations()
        viewModel.fetchAllCryptoListFromDb()

        return binding.root
    }

    fun initObservations() {
        viewModel.getObservedScreenState().observe(viewLifecycleOwner, Observer {
            onScreenStateChanged(it)
        })

        viewModel.getObservedPostsList().observe(viewLifecycleOwner, Observer {
            productList.clear()
            productList.addAll(it)
            recyclerAdapter.notifyDataSetChanged()
        })
    }

    private fun onScreenStateChanged(state: ProductListScreenState?) {
        state?.let {
            when (true) {
                it.showEmpty -> showEmpty()
                it.showError -> showFailure(it.errorMessage)
                it.showLoading -> showLoading()
                it.showContent -> showContent()
            }
        }
    }


    private fun setupViews(view: FragmentProductListBinding) {
        view.rvProductList.adapter = recyclerAdapter
        view.rvProductList.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        view.fab.setOnClickListener {
            openAddCurrencyActivity()
        }
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                viewModel.addCrypto(
                    getString(R.string.API_ACCESS_KEY),
                    data?.getIntExtra("amount",1) as Int,
                    data.getStringExtra("crypto") as String,
                    data.getStringExtra("currency") as String
                )
            }
        }

    private fun openAddCurrencyActivity() {
        val intent = Intent(activity, AddCurrencyActivity::class.java)
        resultLauncher.launch(intent)
    }


    fun showEmpty() {
        layoutEmptyList.visibility = View.VISIBLE
        rvProductList.visibility = View.GONE
        shimmerLayout.visibility = View.GONE
    }

    fun showFailure(message: String) {
        Snackbar.make(requireView(),message,Snackbar.LENGTH_LONG).show()
        shimmerLayout.visibility = View.GONE
        rvProductList.visibility = View.VISIBLE

    }

    fun showLoading() {
        rvProductList.visibility = View.GONE
        shimmerLayout.visibility = View.VISIBLE
    }

    fun showContent() {
        shimmerLayout.visibility = View.GONE
        layoutEmptyList.visibility = View.GONE
        rvProductList.visibility = View.VISIBLE
        shimmerLayout.stopShimmer()
        recyclerAdapter.notifyDataSetChanged()
    }

}