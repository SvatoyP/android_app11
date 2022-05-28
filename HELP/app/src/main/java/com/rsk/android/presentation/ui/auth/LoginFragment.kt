package com.rsk.android.presentation.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.rsk.android.R
import com.rsk.android.databinding.FragmentLoginBinding
import com.rsk.android.di.AppComponent
import kotlinx.coroutines.launch

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding

    private val viewModel: AuthViewModel by viewModels(
        factoryProducer = {
            AppComponent.provideAuthViewModel()
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.auth.observe(viewLifecycleOwner, Observer {
            when (it) {
                is AuthViewModel.AuthState.Success -> {
                    lifecycleScope.launch {
                        findNavController().navigate(R.id.navigatorFragment)
                    }
                }
                is AuthViewModel.AuthState.Error -> {
                    Toast.makeText(context, "Введен неверные логин или пароль", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
        binding.buttonLogin.setOnClickListener {
            auth()
        }

        binding.txtDemo.setOnClickListener {
            val login = "test-dev"
            val password = "123456"
            viewModel.auth(login, password)
        }
    }

    private fun auth() {
        val login = binding.loginInput.text.toString()
        val password = binding.passwordInput.text.toString()
        viewModel.auth(login, password)
    }
}