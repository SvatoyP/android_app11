package com.rsk.android.presentation.ui.navigator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rsk.android.R
import com.rsk.android.databinding.FragmentNavigatorBinding
import com.rsk.android.utils.setupWithNavController

class NavigatorFragment : Fragment(R.layout.fragment_navigator) {

    private lateinit var binding: FragmentNavigatorBinding

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        setupBottomNavigationBar()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNavigatorBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }
    }

    private fun setupBottomNavigationBar() {
        val navGraphIds = listOf(
            R.navigation.home_nav_graph,
            R.navigation.meter_readings_nav_graph,
            R.navigation.documents_nav_graph,
            R.navigation.reports_nav_graph,
            R.navigation.services_nav_graph
        )

        binding.bottomBar.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = childFragmentManager,
            containerId = R.id.navigatorContainerFragment,
            intent = requireActivity().intent
        )

    }


}