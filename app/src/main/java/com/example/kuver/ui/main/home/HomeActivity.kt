package com.example.kuver.ui.main.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import com.example.kuver.R
import com.example.kuver.Utils.Constants.HOME_FRAGMENT
import com.example.kuver.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private var _binding: ActivityHomeBinding? = null
    private val binding get() = _binding!!
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViews()
        setUpHome()

    }

    private fun setUpViews() {
        setUpDrawerLayout()
    }

    private fun setUpDrawerLayout() {
        setSupportActionBar(binding.homeToolbar)
        actionBarDrawerToggle = ActionBarDrawerToggle(this,binding.drawerLayout,R.string.app_name,R.string.app_name)
        actionBarDrawerToggle.syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setUpHome() {

        fragmentTransition(HomeFragment.newInstance(), HOME_FRAGMENT)
    }

    protected fun fragmentTransition(fragment: Fragment?, tag: String?) {
        if (fragment != null && !fragment.isVisible) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.layout_main, fragment, tag)
                .addToBackStack(null)
                .commit()
        }
    }
}