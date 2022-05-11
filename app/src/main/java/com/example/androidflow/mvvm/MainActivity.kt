package com.example.androidflow.mvvm

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.androidflow.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.liveDataButton.setOnClickListener {
            viewModel.triggerLiveData()
        }
        binding.stateFlowButton.setOnClickListener {
            viewModel.triggerStateFlow()
        }
        binding.flowButton.setOnClickListener {
            lifecycleScope.launch {
                viewModel.getUser().collectLatest {
                    binding.flowText.text = it
                }
            }
        }
        binding.sharedFlowButton.setOnClickListener {
            viewModel.triggerShareFlow()
        }

        subscribeToObservables()
    }

    private fun subscribeToObservables() {
        viewModel.liveData.observe(this) {
            binding.liveDataText.text = it
        }
        lifecycleScope.launchWhenStarted {
            viewModel.stateFlow.collectLatest {
                binding.stateFlowText.text = it
            }
        }
        // launchWhenStarted es para el cuidado del ciclo de vida
        lifecycleScope.launchWhenStarted {
            viewModel.sharedFlow.collectLatest {
                Snackbar.make(
                    binding.root,
                    it,
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }
}