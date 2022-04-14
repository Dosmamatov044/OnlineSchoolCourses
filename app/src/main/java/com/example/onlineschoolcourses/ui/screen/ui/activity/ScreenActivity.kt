package com.example.onlineschoolcourses.ui.screen.ui.activity


import android.graphics.Color
import android.os.Bundle
import android.os.Parcelable
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import com.bumptech.glide.load.Options
import com.example.onlineschoolcourses.R
import com.example.onlineschoolcourses.contract.*
import com.example.onlineschoolcourses.databinding.ActivityScreenBinding
import com.example.onlineschoolcourses.helpers.APP_SCREEN_ACTIVITY
import com.example.onlineschoolcourses.helpers.auth
import com.example.onlineschoolcourses.helpers.replaceFragmentScreen
import com.example.onlineschoolcourses.ui.register.fragment.authorization.AuthorizationFragment
import com.example.onlineschoolcourses.ui.screen.ui.fragment.home.HomeFragment
import com.example.onlineschoolcourses.ui.screen.ui.fragment.personalArea.PersonalAreaFragment
import com.example.onlineschoolcourses.ui.screen.ui.fragment.search.SearchFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScreenActivity : AppCompatActivity(), Navigator {
    lateinit var binding: ActivityScreenBinding
    private val currentFragment: Fragment
        get() = supportFragmentManager.findFragmentById(R.id.fragment_containers)!!
    private val fragmentListener = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(
            fm: FragmentManager,
            f: Fragment,
            v: View,
            savedInstanceState: Bundle?
        ) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
            updateUi()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentListener)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScreenBinding.inflate(layoutInflater)
        auth = Firebase.auth
        APP_SCREEN_ACTIVITY = this
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)




        if (auth.currentUser == null) {
            replaceFragmentScreen(AuthorizationFragment())
        } else {
            bottomNavigationInit()
            if (savedInstanceState == null) {
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_containers, HomeFragment())
                    .commit()
            }

        }




        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentListener, false)
    }


    private fun bottomNavigationInit() {
        binding.navigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_home -> {
                    replaceFragmentScreen(HomeFragment())
                    true
                }
                R.id.action_personal_area -> {
                    replaceFragmentScreen(PersonalAreaFragment())
                    true
                }
                R.id.action_search -> {
                    replaceFragmentScreen(SearchFragment())
                    true
                }
                R.id.action_education -> {

                    true
                }
                else -> false
            }
        }

    }

    override fun showBoxSelectionScreen(options: Options) {

    }

    override fun showOptionsScreen(options: Options) {

    }

    override fun showAboutScreen() {

    }

    override fun showCongratulationsScreen() {

    }

    override fun goBack() {
        onBackPressed()
    }


    fun toolbarGone() {
        binding.toolbar.visibility = View.GONE

    }

    private fun updateUi() {
        val fragment = currentFragment

        if (fragment is HasCustomTitle) {
            binding.toolbar.title = getString(fragment.getTitleRes())
        } else {
            binding.toolbar.title = "Null"
        }

        if (supportFragmentManager.backStackEntryCount > 0) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        } else {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            supportActionBar?.setDisplayShowHomeEnabled(false)
        }

        if (fragment is HasCustomAction) {
            createCustomToolbarAction(fragment.getCustomAction())
        } else {
            binding.toolbar.menu.clear()
        }
    }

    private fun createCustomToolbarAction(action: CustomAction) {
        binding.toolbar.menu.clear() // clearing old action if it exists before assigning a new one

        val iconDrawable = DrawableCompat.wrap(ContextCompat.getDrawable(this, action.iconRes)!!)
        iconDrawable.setTint(Color.WHITE)

        val menuItem = binding.toolbar.menu.add(action.textRes)
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        menuItem.icon = iconDrawable
        menuItem.setOnMenuItemClickListener {
            action.onCustomAction.run()
            return@setOnMenuItemClickListener true
        }
    }

    override fun goToMenu() {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    override fun <T : Parcelable> publishResult(result: T) {
        supportFragmentManager.setFragmentResult(
            result.javaClass.name,
            bundleOf(KEY_RESULT to result)
        )
    }

    override fun <T : Parcelable> listenResult(
        clazz: Class<T>,
        owner: LifecycleOwner,
        listener: ResultListener<T>
    ) {
        supportFragmentManager.setFragmentResultListener(clazz.name, owner) { key, bundle ->
            listener.invoke(bundle.getParcelable(KEY_RESULT)!!)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // we've called setSupportActionBar in onCreate,
        // that's why we need to override this method too
        updateUi()
        return true
    }

    fun setBottomNavigationVisibility(visibility: Int) {
        // get the reference of the bottomNavigationView and set the visibility.
        binding.navigation.visibility = visibility
    }


    companion object {
        @JvmStatic
        private val KEY_RESULT = "RESULT"
    }
}