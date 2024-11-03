package com.example.lab_3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.lab_3.model.Music
import com.example.lab_3.model.ItemTypeInterface
import com.example.lab_3.databinding.MusicLayoutBinding
import com.example.lab_3.databinding.SingersLayoutBinding
import com.example.lab_3.model.Singer


class Adapter(
    private val itemList: LiveData<List<ItemTypeInterface>>,
    private var onClickListener: OnClickListener
) : RecyclerView.Adapter<Adapter.AppHolder>() {
    interface OnClickListener{
        fun onClick(item: ItemTypeInterface, num:Int=0)
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
                SingersLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                onClickListener
            )
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }
    override fun getItemCount(): Int {
        return itemList.value!!.size
    }
    override fun onBindViewHolder(holder: AppHolder, position: Int) {
        holder.bind(itemList.value!![position])

    }
    override fun getItemViewType(position: Int): Int {
        return itemList.value!![position].getType()
    }

//    fun submit(newList: List<ItemTypeInterface>) {
//        itemList = newList
//        notifyDataSetChanged()
//    }



    abstract class AppHolder(itemViewBinding: ViewBinding) : RecyclerView.ViewHolder(itemViewBinding.root) {
        abstract fun bind(item: ItemTypeInterface)
    }

    class MusicHolder(private val itemViewBinding: MusicLayoutBinding) : AppHolder(itemViewBinding) {
        override fun bind(item: ItemTypeInterface) {
            item as Music
            itemViewBinding.musicName.text = item.name
            itemViewBinding.inclMusicSinger.singerName.text=item.singer?.singerName ?: "empty"
            itemViewBinding.inclMusicSinger.singerSurname.text=item.singer?.singerSurname ?: "empty"
            itemViewBinding.inclMusicSinger.btnSingerDel.visibility = View.GONE
            itemViewBinding.musicYear.text = item.year
            itemViewBinding.musicAlbum.text = item.album
        }
    }
    class SingerHolder(
        private val itemViewBinding: SingersLayoutBinding,
        private val clickListener: OnClickListener // Додано прийом clickListener
    ) : AppHolder(itemViewBinding) {
        override fun bind(item: ItemTypeInterface) {
            item as Singer
            itemViewBinding.singerName.text = item.singerName
            itemViewBinding.singerSurname.text = item.singerSurname
            itemViewBinding.btnSingerDel.setOnClickListener { clickListener.onClick(item) }
        }
    }
}