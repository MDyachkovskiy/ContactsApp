package com.test.application.contactsapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.test.application.contactsapp.R
import com.test.application.contactsapp.databinding.ActivityMainBinding
import com.test.application.core.navigation.Navigator
import com.test.application.core.navigation.OnBackPressInDetails

class MainActivity : AppCompatActivity(), Navigator, OnBackPressInDetails {

    private lateinit var binding : ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
            .also{ setContentView(it.root) }

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_container) as NavHostFragment
        navController = navHostFragment.navController
    }

    override fun navigateFromContactsListToDetails(bundle: Bundle) {
        navController.navigate(R.id.action_contactListFragment_to_DetailsFragment)
    }

    override fun onBackButtonPressedInContactDetails() {
        navController.navigate(R.id.action_detailsFragment_to_contactListFragment)
    }
}