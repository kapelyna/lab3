package com.example.lab_3.model

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.lab_3.model.Music
import com.example.lab_3.model.ItemTypeInterface
import com.example.lab_3.databinding.MusicLayoutBinding
import com.example.lab_3.databinding.SingersLayoutBinding


class Adapter : RecyclerView.Adapter<Adapter.AppHolder>() {
    private var itemList: List<ItemTypeInterface> = emptyList()

    override fun onBindViewHolder(holder: AppHolder, position: Int) {
        holder.bind(itemList[position])
    }

    fun submit(newList: List<ItemTypeInterface>) {
        itemList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppHolder {
        return when (viewType) {
            ItemTypeInterface.MUSIC_TYPE -> MusicHolder(
                MusicLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
                ItemTypeInterface.SINGER_TYPE -> SingerHolder(
                SingersLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return itemList[position].getType()
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    abstract class AppHolder(itemViewBinding: ViewBinding) : RecyclerView.ViewHolder(itemViewBinding.root) {
        abstract fun bind(item: ItemTypeInterface)
    }

    class MusicHolder(private val itemViewBinding: MusicLayoutBinding) : AppHolder(itemViewBinding) {
        override fun bind(item: ItemTypeInterface) {
            item as Music
            itemViewBinding.musicName.text = item.name
            itemViewBinding.musicSinger.text = item.singer
            itemViewBinding.musicYear.text = item.year
            itemViewBinding.musicAlbum.text = item.album
        }
    }
    class SingerHolder(private val itemViewBinding: SingersLayoutBinding) : AppHolder(itemViewBinding) {
        override fun bind(item: ItemTypeInterface) {
            item as Singer
            itemViewBinding.singerName.text = item.name
            itemViewBinding.singerSurname.text = item.surname
        }
    }
}