package com.app.douban_movie.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.douban_movie.data.model.Subject
import com.app.douban_movie.databinding.ItemInTheatersRecyclerviewBinding
import com.app.douban_movie.utils.Logger
import com.google.common.base.Strings

class InTheatersAdapter(private val delegate: ViewHolder.Delegate) :
    ListAdapter<Subject, InTheatersAdapter.ViewHolder>(diffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemInTheatersRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), delegate
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.apply {
            bind(item)
            itemView.tag = item
        }


        val pubCountry = StringBuffer()
        for (i in item.pubdates.indices) {
            item.pubdates[i].indexOf("(")
            pubCountry.append(
                item.pubdates[i].substring(
                    item.pubdates[i].indexOf("(") + 1,
                    item.pubdates[i].indexOf(")")
                ) + " "
            )
        }

        val genres = StringBuffer()
        for (i in item.genres.indices) {
            genres.append(item.genres[i] + " ")
        }

        val casts = StringBuffer()
        for (i in item.casts.indices) {
            casts.append(item.casts[i].name + " ")
        }

        holder.binding.itemDescription.text = (
                item.year + " / " + pubCountry.toString() + " / " + genres.toString() + (if (item.directors.isEmpty()) " " else " / " +
                        item.directors.get(0).name) + if (Strings.isNullOrEmpty(casts.toString())) " " else " / $casts"
                )

    }

    class ViewHolder(
        val binding: ItemInTheatersRecyclerviewBinding,
        private val delegate: Delegate
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.root.setOnClickListener(this)
        }

        private lateinit var subject: Subject
        fun bind(subjects: Subject) {
            this.subject = subjects
            binding.apply {
                subject = subjects
                executePendingBindings()
            }
        }

        interface Delegate {
            fun onItemClick(subject: Subject)
        }

        override fun onClick(v: View?) {
            delegate.onItemClick(subject)
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Subject>() {
            override fun areItemsTheSame(oldItem: Subject, newItem: Subject): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Subject, newItem: Subject): Boolean =
                oldItem == newItem
        }
    }
}