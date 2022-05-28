package com.rsk.android.presentation.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.rsk.android.R
import com.rsk.android.di.AppComponent

class MainFragment : Fragment(R.layout.fragment_main) {

    private val mainViewModel: MainViewModel by viewModels(
        factoryProducer = {
            AppComponent.provideMainViewModel()
        }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.mainNavCommand.observe(
            viewLifecycleOwner, Observer { mainNavCommand ->
                val navController = Navigation.findNavController(
                    requireActivity(),
                    R.id.activityNavHost
                )

                val mainGraph = navController.navInflater.inflate(R.navigation.app_nav_graph)

                mainGraph.startDestination = when (mainNavCommand) {
                    MainViewModel.MainNavCommand.NAVIGATE_TO_NAVIGATOR -> R.id.navigatorFragment
                    MainViewModel.MainNavCommand.NAVIGATE_TO_AUTH -> R.id.toolbarAuthFragment
                    null -> throw IllegalArgumentException("Illegal splash navigation command")
                }
                navController.graph = mainGraph
            }
        )

    }
}