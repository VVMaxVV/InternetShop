package com.example.internetshop.presentation.activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.example.internetshop.databinding.ActivityMainBinding
import com.example.internetshop.presentation.activity.fragments.AuthenticationFragment
import com.example.internetshop.presentation.viewModel.AuthenticationViewModel
import com.example.internetshop.presentation.viewModel.MultiViewModuleFactory
import com.google.android.material.appbar.AppBarLayout
import javax.inject.Inject


class MainActivity : AppCompatActivity(), ContainerHolder {
    @Inject
    lateinit var factory: MultiViewModuleFactory

    val viewModel :AuthenticationViewModel by viewModels { factory }

    var binding: ActivityMainBinding? = null

    var offSetListener : AppBarOffsetChangedListener? = null

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

        binding?.let {
            val fragment = AuthenticationFragment()
            supportFragmentManager.beginTransaction()
                .replace(it.fragmentContainer.id,fragment)
                .commit()
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

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        val bottomPadding = appBarLayout.totalScrollRange + verticalOffset
        divider?.let {
            val dividerVisibility =
                if (bottomPadding <= 0)
                    View.VISIBLE else View.INVISIBLE

            it.visibility = dividerVisibility
        }

        contentView.apply {
            setPadding(
                this.paddingLeft,
                this.paddingTop,
                this.paddingRight,
                bottomPadding
            )
        }
        appBarLayout.apply {
            setPadding(
                this.paddingLeft,
                bottomPadding,
                this.paddingRight,
                this.paddingBottom
            )
        }
    }
}