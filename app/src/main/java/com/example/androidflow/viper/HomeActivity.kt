package com.example.androidflow.viper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.androidflow.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {

    private lateinit var mPresenter: HomePresenter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mPresenter = HomeWireframe.create(this)
        binding.liveDataButton.setOnClickListener {
            mPresenter.triggerLiveData()
        }
        binding.stateFlowButton.setOnClickListener {
            mPresenter.triggerStateFlow()
        }
        binding.flowButton.setOnClickListener {
            lifecycleScope.launch {
                mPresenter.getUser().collectLatest {
                    binding.flowText.text = it
                }
            }
        }
        binding.sharedFlowButton.setOnClickListener {
            mPresenter.triggerShareFlow()
        }
        subscribeToObservables()
    }

    private fun subscribeToObservables() {
        mPresenter.liveData.observe(this) {
            binding.liveDataText.text = it
        }
        lifecycleScope.launchWhenStarted {
            mPresenter.stateFlow.collectLatest {
                binding.stateFlowText.text = it
            }
        }
        lifecycleScope.launchWhenStarted {
            mPresenter.sharedFlow.collectLatest {
                Snackbar.make(
                    binding.root,
                    it,
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

}