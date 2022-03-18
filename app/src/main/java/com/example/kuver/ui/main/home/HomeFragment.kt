package com.example.kuver.ui.main.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kuver.data.api.ApiHelperImpl
import com.example.kuver.data.api.RetrofitBuilder
import com.example.kuver.data.model.Categories
import com.example.kuver.data.model.Cities
import com.example.kuver.data.model.SubCategories
import com.example.kuver.databinding.FragmentHomeBinding
import com.example.kuver.ui.main.intent.MainIntent
import com.example.kuver.ui.main.viewModel.CategoryViewModel
import com.example.kuver.ui.main.viewState.MainState
import com.example.mviapp.util.ViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var adapter: CatAdapter
    private lateinit var adapterSub: SubCatAdapter
    private lateinit var cityAdapter: CustomSpinnerAdapter

    companion object {
        @JvmStatic
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        setupUI()
        setupViewModel()
        observeViewModel()
        setupClicks()

        return binding.root
    }

    private fun setupUI() {
        binding.catRecycleView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.catRecycleView.run {
            addItemDecoration(
                DividerItemDecoration(
                    binding.catRecycleView.context,
                    (binding.catRecycleView.layoutManager as LinearLayoutManager).orientation
                )
            )
        }

        binding.catRecycleViewSub.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

    }

    private fun setupViewModel() {
        categoryViewModel = ViewModelProviders.of(
            this, ViewModelFactory(
                ApiHelperImpl(
                    RetrofitBuilder.apiService
                )
            )
        ).get(CategoryViewModel::class.java)
    }

    private fun observeViewModel() {
        lifecycleScope.launch {

            categoryViewModel.state.collect {

                when (it) {
                    is MainState.Idel -> {
                        Log.e("CatResponse", " GetCatData")
                    }
                    is MainState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is MainState.CatagoryStatus -> {
                        binding.progressBar.visibility = View.GONE

                        adapter = CatAdapter(
                            it.categoryResponseModel.categories,
                            object : CatAdapter.OnCategoryClick {
                                override fun OnItemClick(pos: Int, categoryId: String) {
                                    lifecycleScope.launch {
                                        categoryViewModel.catIntent.send(
                                            MainIntent.SubCategoryMain(
                                                categoryId
                                            )
                                        )
                                    }
                                    Log.e("CategoryId", categoryId)
                                }

                            })
                        binding.catRecycleView.adapter = adapter
                        renderList(it.categoryResponseModel.categories)
                    }
                    is MainState.SubCatagoryStatus -> {
                        binding.progressBar.visibility = View.GONE

                        adapterSub = SubCatAdapter(context, it.subCatResponseModel)

                        binding.catRecycleViewSub.adapter = adapterSub

                        renderSubList(it.subCatResponseModel.category?.subcategories)

                    }
                    is MainState.CitiesStatus -> {
                        binding.progressBar.visibility = View.GONE

                        it.citiesResponseModel.cities?.add(0,Cities("-1","City"))
                        Log.e("CitiesValue!!!", it.citiesResponseModel.cities.toString())

                        SetAdapter(it.citiesResponseModel.cities)
                    }
                    is MainState.Error -> {
                        binding.progressBar.visibility = View.GONE

                        Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun SetAdapter(cities: ArrayList<Cities>?) {



        cityAdapter = CustomSpinnerAdapter(context, true, cities, View.OnClickListener {
            binding.spCity.performClick()
        })

        binding.spCity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                cityAdapter.updateSelection(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        binding.spCity.adapter = cityAdapter
    }

    private fun renderList(categories: ArrayList<Categories>?) {

        binding.catRecycleView.visibility = View.VISIBLE

        categories.let { listofCat -> listofCat.let { adapter.setList(it) } }
        adapter.notifyDataSetChanged()

    }

    private fun renderSubList(subCategories: ArrayList<SubCategories>?) {

        binding.catRecycleViewSub.visibility = View.VISIBLE

        subCategories.let { listOfSubcat -> listOfSubcat.let { adapterSub.setSubList(it) } }
        adapterSub.notifyDataSetChanged()

    }

    private fun setupClicks() {

        lifecycleScope.launch {
            categoryViewModel.catIntent.send(MainIntent.CategoryMain())

            categoryViewModel.catIntent.send(MainIntent.CitiesMain())

        }
    }
}