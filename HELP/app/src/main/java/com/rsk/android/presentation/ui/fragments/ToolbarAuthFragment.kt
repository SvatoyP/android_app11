package com.rsk.android.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rsk.android.R
import com.rsk.android.databinding.FragmentToolbarAuthBinding
import com.rsk.android.presentation.ui.auth.LoginFragment

class ToolbarAuthFragment : Fragment(R.layout.fragment_toolbar_auth) {

    private lateinit var binding: FragmentToolbarAuthBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentToolbarAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        childFragmentManager.beginTransaction()
            .replace(R.id.container_toolbar, LoginFragment())
            .commit()
        binding.toolbarTitle.text = "Вход"
    }
}


