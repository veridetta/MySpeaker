package com.vr.myspeaker.adapter
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vr.myspeaker.R
import com.vr.myspeaker.model.SpeakerModel
import java.util.Locale


class SpeakerAdapter(
    private var barangList: ArrayList<SpeakerModel> = ArrayList(),
    val context: Context,
    private val onClickCard: (SpeakerModel) -> Unit,
) : RecyclerView.Adapter<SpeakerAdapter.ProductViewHolder>() {

    public var filteredBarangList : ArrayList<SpeakerModel> = ArrayList()
    init {
        filteredBarangList.addAll(barangList)
    }
    override fun getItemViewType(position: Int): Int {
        return if (position == 0 && filteredBarangList.isEmpty()) {
            1 // Return 1 for empty state view
        } else {
            0 // Return 0 for regular product view
        }
    }
    fun filter(query: String) {
        filteredBarangList.clear()
        if (query !== null || query !=="") {
            val lowerCaseQuery = query.toLowerCase(Locale.getDefault())
            for (product in barangList) {
                val nam = product.tipe?.toLowerCase(Locale.getDefault())?.contains(lowerCaseQuery)
                if (nam == true) {
                    filteredBarangList.add(product)
                    Log.d("Ada ", product.tipe.toString())
                }
            }
        } else {
            filteredBarangList.addAll(barangList)
        }
        notifyDataSetChanged()
        Log.d("Data f",filteredBarangList.size.toString())
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_speaker, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return filteredBarangList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentBarang = filteredBarangList[position]

        holder.tvTipe.text = currentBarang.tipe
        holder.tvSeries.text = currentBarang.series
        holder.tvDeskripsi.text = currentBarang.deskripsi
        Glide.with(context)
            .load(currentBarang.gambar)
            .override(270,270).centerCrop()
            .placeholder(R.drawable.no_image)
            .into(holder.imgCover)
        Log.d("gambar", currentBarang.gambar.toString())
        holder.cardSpeaker.setOnClickListener { onClickCard(currentBarang) }
    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTipe: TextView = itemView.findViewById(R.id.tvTipe)
        val tvSeries: TextView = itemView.findViewById(R.id.tvSeries)
        val tvDeskripsi: TextView = itemView.findViewById(R.id.tvDeskripsi)
        val imgCover: ImageView = itemView.findViewById(R.id.imgCover)
        val cardSpeaker: CardView = itemView.findViewById(R.id.cardSpeaker)
    }
}
