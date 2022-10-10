package com.example.moviedb.ui.profile

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.navigation.fragment.findNavController
import com.example.moviedb.R
import com.example.moviedb.databinding.FragmentHomeBinding
import com.example.moviedb.databinding.FragmentLoginBinding
import com.example.moviedb.databinding.FragmentProfileBinding
import com.example.moviedb.model.AccountEntity
import com.example.moviedb.ui.login.LoginViewModel

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var sharedPreferences: SharedPreferences
    private val spLogin = "spLogin"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireContext().getSharedPreferences(spLogin, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        binding.btnLogout.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
            editor.clear()
            editor.apply()
        }

        binding.btnUpdate.setOnClickListener{ update() }

    }

    private fun update() {
        if(cekLogin()) {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            profileViewModel.updateAccount(
                AccountEntity(username = username, password = password)
            )
        }
    }

    private fun cekLogin(): Boolean {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val konpassword = binding.etKonpassword.text.toString()
        var passwordIsCorrect = false

        profileViewModel.getAccountByEmail(email).observe(viewLifecycleOwner){
            if (it != null && it.password == password && password == konpassword){
                passwordIsCorrect = true
            }
            else{
                Toast.makeText(requireContext(), "Password salah", Toast.LENGTH_SHORT).show()
            }
        }
        return passwordIsCorrect
    }

}