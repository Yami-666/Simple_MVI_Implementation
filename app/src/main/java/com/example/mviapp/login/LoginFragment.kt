package com.example.mviapp.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.mviapp.R
import com.example.mviapp.databinding.FragmentLoginBinding
import com.example.mviapp.extensions.setVisible
import com.example.mviapp.extensions.toStringOrEmpty
import com.example.mviapp.view_states.LoginViewState
import kotlinx.coroutines.flow.collect

class LoginFragment : Fragment() {
    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        lifecycleScope.launchWhenResumed {
            viewModel.viewState.collect { viewState ->
                processViewState(viewState)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_login, container, false)

        binding.apply {
            emailInput.doOnTextChanged { text, _, _, _ ->
                viewModel.emailChanged(text.toStringOrEmpty())
            }

            passwordInput.doOnTextChanged { text, _, _, _ ->
                viewModel.passwordChanged(text.toStringOrEmpty())
            }

            button.setOnClickListener {
                viewModel.signInButtonClicked()
            }
        }

        return binding.root
    }

    private fun processViewState(viewState: LoginViewState) {
        with(binding) {
            loginProgressBar.setVisible(viewState)

            emailInput.isEnabled = !viewState.isLoading
            passwordInput.isEnabled = !viewState.isLoading


            emailInput.error = viewState.emailError
        }
    }


}
