package com.ryutec.pokedexv1.data.adapter.viewpager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.makeramen.roundedimageview.RoundedImageView
import com.ryutec.pokedexv1.R

class SliderAdapter internal constructor(
    sliderItem: MutableList<String>,
    viewPager:ViewPager2
): RecyclerView.Adapter<SliderAdapter.SliderViewHolder>(){

    private val sliderItems: List<String>

    init {
        this.sliderItems = sliderItem
    }

    class SliderViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val imageView:RoundedImageView = itemView.findViewById(R.id.imageSlide)

        fun image(sliderItem: String){
            //imageView.setImageResource(sliderItem.image)
            Glide.with(itemView.context)
                .load(sliderItem)
                .placeholder(R.drawable.icon)
                .into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder =
        SliderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.slider_item_container, parent, false))

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.image(sliderItems[position])
    }

    override fun getItemCount(): Int = sliderItems.size
}