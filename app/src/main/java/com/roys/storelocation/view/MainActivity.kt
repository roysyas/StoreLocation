package com.roys.storelocation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.roys.storelocation.databinding.ActivityMainBinding
import com.roys.storelocation.viewmodel.StoreViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : StoreViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter:StoreAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[StoreViewModel::class.java]

        initRecyclerView()
    }

    private fun initRecyclerView(){
        with(binding){
            rvHome.layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = StoreAdapter()
            rvHome.adapter = adapter
            viewModel.stores.observe(this@MainActivity, Observer {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
            })
            floatingActionButton.setOnClickListener {
                startActivity(Intent(this@MainActivity, StoreDetailActivity::class.java))
            }
        }
    }
}