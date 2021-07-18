package com.uogames.i_have_quest.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.annotations.NotNull

abstract class SimpleRecyclerAdapter<L,T : SimpleViewHolder<L>> private constructor() : RecyclerView.Adapter<T>() {

    private var layout: Int = 0
    private var items: List<L> = ArrayList()

    constructor(
        @IdRes layout: Int,
        items: List<L>,
    ) : this() {
        this.layout = layout
        this.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T {
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return createViewHolder(view)
    }

    abstract fun createViewHolder(@NotNull view: View): T

    override fun onBindViewHolder(holder: T, position: Int) {
        holder.setData(holder.itemView, items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }


}

