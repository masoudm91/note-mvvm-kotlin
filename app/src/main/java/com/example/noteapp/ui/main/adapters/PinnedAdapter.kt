package com.example.noteapp.ui.main.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.PrecomputedTextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.R
import com.example.noteapp.databinding.ItemPinnedBinding
import com.example.noteapp.ui.main.data.local.entities.NoteEntity
import java.util.ArrayList

class PinnedAdapter(private var data: ArrayList<NoteEntity>, private var listener: CardClickListener) : RecyclerView.Adapter<PinnedAdapter.PinnedRvViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PinnedRvViewHolder {
        val pinnedRvItemsBinding: ItemPinnedBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_pinned,parent,false)


        return PinnedRvViewHolder(pinnedRvItemsBinding)
    }

    override fun onBindViewHolder(holder: PinnedRvViewHolder, position: Int) {
        holder.bind(data.get(position),listener)
    }

    override fun getItemCount(): Int {
        return data.size
    }


    class PinnedRvViewHolder(private val binding: ItemPinnedBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(noteEntity: NoteEntity, listener: CardClickListener) {

            binding.pinnedcardview.setCardBackgroundColor(Color.parseColor(noteEntity.notesModel.color))
            binding.pinnedtitle.setTextFuture(
                PrecomputedTextCompat.getTextFuture(
                    noteEntity.notesModel.title,
                    binding.pinnedtitle.textMetricsParamsCompat,
                    null
                )
            )

            binding.pinneddescription.text = noteEntity.notesModel.note
            binding.pinnedcardview.setOnClickListener {
                listener.onItemClickListener(noteEntity)
            }

            binding.imageFilterButton.setOnClickListener {
                listener.onMenuItemClickListener(it,noteEntity)
            }

            binding.executePendingBindings()


        }

    }

}