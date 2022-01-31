package com.example.internetshop.presentation.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.example.internetshop.R
import com.example.internetshop.databinding.ActivityMainBinding
import com.example.internetshop.presentation.AppBarOffsetChangedListener
import com.example.internetshop.presentation.InternetshopApplication
import com.example.internetshop.presentation.activity.fragments.AuthenticationFragment
import com.example.internetshop.presentation.viewModel.AuthenticationViewModel
import com.example.internetshop.presentation.viewModel.MultiViewModuleFactory
import com.example.internetshop.presentation.viewModel.TitleViewModel
import com.google.android.material.appbar.AppBarLayout
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ContainerHolder {
    @Inject
    lateinit var factory: MultiViewModuleFactory

    val viewModel: AuthenticationViewModel by viewModels { factory }

    private val titleViewModel: TitleViewModel by viewModels { factory }

    var binding: ActivityMainBinding? = null

    var offSetListener: AppBarOffsetChangedListener? = null

    override fun onStart() {
        super.onStart()
        binding?.let {
            offSetListener =
                AppBarOffsetChangedListener(it.fragmentContainer)
            it.appBar.addOnOffsetChangedListener(offSetListener)
        }
    }

    override fun onStop() {
        super.onStop()
        binding?.appBar?.removeOnOffsetChangedListener(offSetListener)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        (this.applicationContext as InternetshopApplication).appComponent.inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val appBarLayoutParams = binding?.appBar?.layoutParams as CoordinatorLayout.LayoutParams
        if (appBarLayoutParams.behavior == null) {
            appBarLayoutParams.behavior = AppBarLayout.Behavior()
        }
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(
            (ResourcesCompat.getDrawable(resources, R.drawable.ic_back_arrow, null))
        )
        binding?.let {
            val fragment = AuthenticationFragment()
            supportFragmentManager.beginTransaction()
                .replace(it.fragmentContainer.id, fragment)
                .commit()
        }
        ViewCompat.setOnApplyWindowInsetsListener(binding?.appBar!!) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemGestures())
            val params = view.layoutParams as CoordinatorLayout.LayoutParams
            params.topMargin = insets.top
            view.layoutParams = params
            offSetListener?.updateInsets(insets)
            WindowInsetsCompat.CONSUMED
        }
        titleViewModel.titleLiveData.observe(this, {
            binding?.collapsingLayout?.title = it
        })
        titleViewModel.backArrowVisible.observe(this, {
            supportActionBar?.setDisplayHomeAsUpEnabled(it)
            supportActionBar?.setDisplayShowHomeEnabled(it)
        })
        titleViewModel.isScrollingView.observe(this, {
            (appBarLayoutParams.behavior as AppBarLayout.Behavior)
                .setDragCallback(object : AppBarLayout.Behavior.DragCallback() {
                    override fun canDrag(appBarLayout: AppBarLayout): Boolean {
                        return it
                    }
                })
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.home -> {
                supportFragmentManager.popBackStack()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun getContainerId(): Int? {
        return binding?.fragmentContainer?.id
    }
}