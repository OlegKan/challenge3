package com.simplaapliko.challenge3.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.simplaapliko.challenge3.R
import com.simplaapliko.challenge3.domain.business.BusinessModel

class BusinessAdapter(
    private val clickListener: (BusinessModel) -> Unit
) : RecyclerView.Adapter<BusinessViewHolder>(), BusinessViewHolder.ClickListener {

    private val items: MutableList<BusinessModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinessViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_business, parent, false)

        return BusinessViewHolder(view, this)
    }

    override fun onBindViewHolder(holder: BusinessViewHolder, position: Int) {
        val model = items[position]
        holder.bind(model)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onItemClicked(position: Int) {
        clickListener(items[position])
    }

    fun setItems(list: List<BusinessModel>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }
}

class BusinessViewHolder(
    itemView: View,
    private val clickListener: ClickListener
) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    init {
        itemView.setOnClickListener(this)
    }

    interface ClickListener {
        fun onItemClicked(position: Int)
    }

    private val name: TextView = itemView.findViewById(R.id.name)
    private val type: TextView = itemView.findViewById(R.id.type)
    private val distance: TextView = itemView.findViewById(R.id.distance)

    fun bind(model: BusinessModel) {
        name.text = model.name
        type.text = model.type
        distance.text = model.formattedDistance
    }

    override fun onClick(view: View?) {
        clickListener.onItemClicked(adapterPosition)
    }
}
