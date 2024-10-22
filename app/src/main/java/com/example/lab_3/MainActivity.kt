package com.example.lab_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.ViewModelProvider
import com.example.lab_3.model.Adapter



class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MyViewModel
    private lateinit var adapter: Adapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var addButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Ініціалізація адаптера
        adapter = Adapter()
        recyclerView.adapter = adapter // Встановлення адаптера

        // Ініціалізація ViewModel через ViewModelProvider
        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        // Спостереження за даними у ViewModel
        viewModel.musics.observe(this) { musics ->
            adapter.submit(musics)
        }

        addButton = findViewById(R.id.addMusic)
        addButton.setOnClickListener {
            viewModel.addMusic()
        }
        addButton = findViewById(R.id.addSinger)
        addButton.setOnClickListener {
            viewModel.addSinger()
        }
    }
}
