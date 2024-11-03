package com.example.lab_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.lab_3.model.Music
import com.example.lab_3.model.Singer
import com.example.lab_3.databinding.ActivityMainBinding
import com.example.lab_3.model.ItemTypeInterface


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var viewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        val onClc = object : Adapter.OnClickListener {
            override fun onClick(item: ItemTypeInterface, num: Int) {
                when (item) {
                    is Singer -> {
                        viewModel.delSinger(item)
                    }
                }
            }
        }
        val myAdapter = Adapter(viewModel.myList, onClc)

        recyclerView.adapter = myAdapter
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        viewModel.myList.observe(this) {
            myAdapter.notifyDataSetChanged()
        }

        // Додати співака
        binding.addSinger.setOnClickListener {
            viewModel.addSinger(Singer("New Singer", "New Surname"))
        }

        // Додати випадкову пісню
        binding.addMusic.setOnClickListener {
            viewModel.addRandomMusic("New song","2024","album")
        }
    }
}
