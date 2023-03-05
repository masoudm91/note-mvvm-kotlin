package com.example.noteapp.ui.main.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

import com.example.noteapp.R
import com.example.noteapp.databinding.ItemUpcomingBinding
import com.example.noteapp.ui.main.data.local.entities.NoteEntity


class UpcomingAdapter(private val model: ArrayList<NoteEntity>, private var listener: CardClickListener) : RecyclerView.Adapter<UpcomingAdapter.UpcomingRvViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingRvViewHolder {
        val upcomingRvItemsBinding: ItemUpcomingBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_upcoming,parent,false)
        return UpcomingRvViewHolder(upcomingRvItemsBinding)
    }

    override fun onBindViewHolder(holder: UpcomingRvViewHolder, position: Int) {
        holder.bind(model[position],listener)
    }

    override fun getItemCount() = model.size

    class UpcomingRvViewHolder(private val binding: ItemUpcomingBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(noteEntity: NoteEntity, listener: CardClickListener) {


            binding.upcomingCard.setCardBackgroundColor(Color.parseColor(noteEntity.notesModel.color))
            binding.pinnedtitle.text = noteEntity.notesModel.title
            binding.pinneddescription.text = noteEntity.notesModel.note
            binding.upcomingCard.setOnClickListener {
                listener.onItemClickListener(noteEntity)
            }
            binding.imageFilterButton2.setOnClickListener {
                listener.onMenuItemClickListener(it,noteEntity)
            }
            binding.executePendingBindings()
        }
    }
}