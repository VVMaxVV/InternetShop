package com.example.internetshop.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.internetshop.R
import com.example.internetshop.databinding.ActivityMainBinding
import com.example.internetshop.presentation.AppBarOffsetChangedListener
import com.example.internetshop.presentation.InternetshopApplication
import com.example.internetshop.presentation.service.FetchFavoritesService
import com.example.internetshop.presentation.service.FetchFavoritesServiceState
import com.example.internetshop.presentation.viewModel.AuthenticationViewModel
import com.example.internetshop.presentation.viewModel.BottomNavViewModel
import com.example.internetshop.presentation.viewModel.MultiViewModuleFactory
import com.example.internetshop.presentation.viewModel.ToolBarViewModel
import com.google.android.material.appbar.AppBarLayout
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ContainerHolder {
    @Inject
    lateinit var factory: MultiViewModuleFactory

    @Inject
    lateinit var serviceState: FetchFavoritesServiceState

    val viewModel: AuthenticationViewModel by viewModels { factory }

    private val bottomNavViewModel: BottomNavViewModel by viewModels { factory }

    private val toolBarViewModel: ToolBarViewModel by viewModels { factory }

    var binding: ActivityMainBinding? = null

    private var offSetListener: AppBarOffsetChangedListener? = null

    override fun onStart() {
        super.onStart()
        binding?.let {
            offSetListener =
                AppBarOffsetChangedListener(it.linearContainerLayout)
            it.appBar.addOnOffsetChangedListener(offSetListener)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (this.applicationContext as InternetshopApplication).appComponent.inject(this)
        startService(Intent(this, FetchFavoritesService::class.java))
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController
        setContentView(binding?.root)
        binding?.lifecycleOwner = this

        updateFavoriteDate()
        setupAppBar(navController)
        subscribeToBottomNavVisibility()
        setScrollingView()
        applyInsetsToAppBar()
    }

    override fun onStop() {
        super.onStop()
        binding?.appBar?.removeOnOffsetChangedListener(offSetListener)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                if (!findNavController(R.id.fragment_container).popBackStack()) {
                    onBackPressed()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setScrollingView() {
        val appBarLayoutParams = binding?.appBar?.layoutParams as CoordinatorLayout.LayoutParams
        if (appBarLayoutParams.behavior == null) {
            appBarLayoutParams.behavior = AppBarLayout.Behavior()
        }
    }

    private fun setupAppBar(navController: NavController) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setSupportActionBar(binding?.toolbar)
        val appBarConfig = AppBarConfiguration.Builder(
            toolBarViewModel.topLevelDestinations
        ).build()
        binding?.let {
            it.bottomNavBar.setupWithNavController(navController)
            it.collapsingLayout.setupWithNavController(
                it.toolbar,
                navController,
                appBarConfig
            )
        }
        toolBarViewModel.expanded.observe(this, {
            binding?.appBar?.setExpanded(it)
        })
    }

    private fun subscribeToBottomNavVisibility() {
        bottomNavViewModel.visibility.observe(this, {
            val visibility = if (it) View.VISIBLE
            else View.INVISIBLE
            binding?.bottomNavBar?.visibility = visibility
        })
    }

    private fun applyInsetsToAppBar() {
        ViewCompat.setOnApplyWindowInsetsListener(binding?.appBar!!) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            val params = view.layoutParams as CoordinatorLayout.LayoutParams
            params.topMargin = insets.top
            view.layoutParams = params
            offSetListener?.updateInsets(insets)
            WindowInsetsCompat.CONSUMED
        }
    }

    override fun getContainerId(): Int? {
        return binding?.fragmentContainer?.id
    }

    private fun updateFavoriteDate() {
        serviceState.events.observe(this, {
            when (it) {
                is FetchFavoritesServiceState
                .Event
                .ServiceDestroying -> {
                    Log.i(FetchFavoritesService::class.java.name, "Service destroyed")
                }
            }
        })
    }
}