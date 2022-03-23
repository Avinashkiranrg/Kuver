package com.example.kuver.ui.main.home

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kuver.Utils.ActivityUtil
import com.example.kuver.Utils.GPSTracker
import com.example.kuver.data.api.ApiHelperImpl
import com.example.kuver.data.api.RetrofitBuilder
import com.example.kuver.data.model.Categories
import com.example.kuver.data.model.Cities
import com.example.kuver.data.model.SubCategories
import com.example.kuver.databinding.FragmentHomeBinding
import com.example.kuver.ui.main.intent.MainIntent
import com.example.kuver.ui.main.login.WelcomeActivity
import com.example.kuver.ui.main.viewModel.CategoryViewModel
import com.example.kuver.ui.main.viewState.MainState
import com.example.mviapp.util.ViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.math.ln

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var adapter: CatAdapter
    private lateinit var adapterSub: SubCatAdapter
    private lateinit var cityAdapter: CustomSpinnerAdapter
    var lat = ""
    var lng = ""
    private val MY_PERMISSIONS_REQUEST = 1

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


        val fm = fragmentManager
        fm!!.addOnBackStackChangedListener(object : FragmentManager.OnBackStackChangedListener {
            override fun onBackStackChanged() {
                //if (fragmentManager!!.backStackEntryCount == 0) context.finish()
                Log.e("teeeetttst","backpressed")
            }

        })

        return binding.root
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true)
            {
                override fun handleOnBackPressed() {
                    // Leave empty do disable back press or
                    // write your code which you want
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            callback
        )
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onStop() {
        super.onStop()
        Log.e("onstate", "onStop")
    }

    override fun onResume() {
        super.onResume()
        Log.e("onstate", "onResume")
        if (context?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
            } != PackageManager.PERMISSION_GRANTED &&
            context?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                )
            } != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(context, "Grant location permission", Toast.LENGTH_SHORT).show()
            ActivityCompat.requestPermissions(
                requireContext() as Activity, arrayOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_NETWORK_STATE
                ), MY_PERMISSIONS_REQUEST
            )
            return
        } else {
            var gpsTracker = GPSTracker(context)
            if (gpsTracker.canGetLocation()) {
                lat = java.lang.String.valueOf(gpsTracker.latitude)
                lng = java.lang.String.valueOf(gpsTracker.longitude)
                Log.e("testLocation", lat + " " + lng)
            } else {
                gpsTracker.showSettingsAlert()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST -> {
                var i = 0
                if (grantResults.size > 0) {
                    i = 0
                    while (i < grantResults.size) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            break
                        }
                        i++
                    }

                }
            }
        }
    }


    override fun onPause() {
        super.onPause()
        Log.e("onstate", "onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onstate", "onDestroy")
    }

    override fun onStart() {
        super.onStart()
        Log.e("onstate", "onStart")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("onstate", "onCreate")
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

                        it.citiesResponseModel.cities?.add(0, Cities("-1", "City"))
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
        binding.location.setOnClickListener {
            val intent = Intent(context, WelcomeActivity::class.java)
            context?.startActivity(intent)
        }
    }
}