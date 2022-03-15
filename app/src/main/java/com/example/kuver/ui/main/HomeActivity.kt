package com.example.kuver.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.text.SpannableString
import android.text.style.AlignmentSpan
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.kuver.R
import com.example.kuver.databinding.ActivityHomeBinding
import com.example.kuver.databinding.ActivityLoginBinding
import com.example.kuver.ui.main.fragment.HomeFragment
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity() {

    private var _binding: ActivityHomeBinding? = null
    private val binding get() = _binding!!
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViews()

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

}