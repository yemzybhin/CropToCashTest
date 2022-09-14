package ade.yemi.croptocashassessment.Adapter

import ade.yemi.croptocashassessment.Model.Exhibit
import ade.yemi.croptocashassessment.R
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.exhibit_item.view.*

class ExhibitAdapter(private val exhibits : List<Exhibit>) : RecyclerView.Adapter<ExhibitAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExhibitAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.exhibit_item, parent, false)
        return MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ExhibitAdapter.MyViewHolder, position: Int) {
        holder.bind(exhibits[position])
    }

    override fun getItemCount(): Int {
        return exhibits.size
    }

    class MyViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        private lateinit var manager: RecyclerView.LayoutManager
        private lateinit var  myAdapter: RecyclerView.Adapter<*>

        val itemTitle = view.findViewById<TextView>(R.id.item_title)
        val recyclerView = view.findViewById<RecyclerView>(R.id.exhibitsrecyclerview)

        fun bind(data: Exhibit) {
            itemTitle.text = data.title.toString()

            manager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

            recyclerView.apply {
                myAdapter = ImageAdapter(view.context, data)
                layoutManager = manager
                adapter = myAdapter
            }
        }
    }
}