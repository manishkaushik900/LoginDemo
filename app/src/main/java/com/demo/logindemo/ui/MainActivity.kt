package com.demo.logindemo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.demo.logindemo.R
import com.demo.logindemo.dataStore.UserPrefrence
import com.demo.logindemo.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var userPrefrence: UserPrefrence

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userPrefrence = UserPrefrence(this)

        lifecycleScope.launch {

            val displayName =
                intent.getStringExtra("UserName") ?: userPrefrence.readUserName.first().toString()

            binding.tvUser.text =
                String.format("%s %s", getString(R.string.welcome) , displayName)
        }

    }
}