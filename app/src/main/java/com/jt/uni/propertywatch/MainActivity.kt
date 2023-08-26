package com.jt.uni.propertywatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jt.uni.propertywatch.database.PropertyRepository

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PropertyRepository.initialize(this)

        if (savedInstanceState == null) {
            loadFragment(PropertyListFragment.newInstance())
        }
    }

    fun loadFragment(fragment: Fragment)
    {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    override fun onBackPressed() {

        val currentFragment: Fragment? =
            supportFragmentManager.findFragmentById(R.id.container)

//        if (currentFragment != null && currentFragment.view?.id == R.id.property_details_fragment){
//            val frag = currentFragment as PropertyDetailsFragment
//            frag.checkDone(true)
//
//        } else {
            finish()	// use this if it will not exit app for back pressed on list page
//        }

    }
}