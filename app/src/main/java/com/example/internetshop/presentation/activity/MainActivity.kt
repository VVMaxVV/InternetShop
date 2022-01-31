package com.example.internetshop.presentation.activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.example.internetshop.databinding.ActivityMainBinding
import com.example.internetshop.presentation.activity.fragments.AuthenticationFragment
import com.example.internetshop.presentation.viewModel.AuthenticationViewModel
import com.example.internetshop.presentation.viewModel.MultiViewModuleFactory
import com.google.android.material.appbar.AppBarLayout
import javax.inject.Inject


class MainActivity : AppCompatActivity(), ContainerHolder {
    @Inject
    lateinit var factory: MultiViewModuleFactory

    val viewModel: AuthenticationViewModel by viewModels { factory }

//    private val titleViewModel: TitleViewModel by viewModels { factory }

    var binding: ActivityMainBinding? = null

    var offSetListener: AppBarOffsetChangedListener? = null

    override fun onStart() {
        super.onStart()
        binding?.let {
            offSetListener = AppBarOffsetChangedListener(it.fragmentContainer)
            it.appbar.addOnOffsetChangedListener(offSetListener)
        }
    }

    override fun onStop() {
        super.onStop()
        binding?.appbar?.removeOnOffsetChangedListener(offSetListener)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding?.let {
            val fragment = AuthenticationFragment()
            supportFragmentManager.beginTransaction()
                .replace(it.fragmentContainer.id, fragment)
                .commit()
        }
        ViewCompat.setOnApplyWindowInsetsListener(binding?.appbar!!) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemGestures())
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

class AppBarOffsetChangedListener(
    private val contentView: View,
    private val divider: View? = null
) : AppBarLayout.OnOffsetChangedListener {
    private var windowInsets: Insets? = null
    fun updateInsets(windowInsets: Insets) {
        this.windowInsets = windowInsets
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        val bottomPadding = appBarLayout.totalScrollRange + verticalOffset
        divider?.let {
            val dividerVisibility =
                if (bottomPadding <= 0)
                    View.VISIBLE else View.INVISIBLE

            it.visibility = dividerVisibility
        }
        val insets = windowInsets?.top ?: 0
        contentView.apply {
            setPadding(
                this.paddingLeft,
                this.paddingTop,
                this.paddingRight,
                bottomPadding + insets
            )
        }
    }
}