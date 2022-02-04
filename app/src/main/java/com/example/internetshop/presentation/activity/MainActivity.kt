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
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.internetshop.R
import com.example.internetshop.databinding.ActivityMainBinding
import com.example.internetshop.presentation.AppBarOffsetChangedListener
import com.example.internetshop.presentation.InternetshopApplication
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (this.applicationContext as InternetshopApplication).appComponent.inject(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding?.root)
        binding?.let {
            it.viewModel = titleViewModel
            it.lifecycleOwner = this
        }

        setupAppBar()
        subscribeToBackArrowVisibility()
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

    private fun subscribeToBackArrowVisibility() {
        titleViewModel.backArrowVisible.observe(this, {
            supportActionBar?.setDisplayHomeAsUpEnabled(it)
        })
    }

    private fun setScrollingView() {
        val appBarLayoutParams = binding?.appBar?.layoutParams as CoordinatorLayout.LayoutParams
        if (appBarLayoutParams.behavior == null) {
            appBarLayoutParams.behavior = AppBarLayout.Behavior()
        }
//        titleViewModel.isScrollingView.observe(this, {
//            (appBarLayoutParams.behavior as AppBarLayout.Behavior)
//                .setDragCallback(object : AppBarLayout.Behavior.DragCallback() {
//                    override fun canDrag(appBarLayout: AppBarLayout): Boolean {
//                        return it
//                    }
//                })
//        })
    }

    private fun setupAppBar() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setSupportActionBar(binding?.toolbar)
        supportActionBar?.setHomeAsUpIndicator(
            (ResourcesCompat.getDrawable(resources, R.drawable.ic_back_arrow, null))
        )
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
}