package com.kiryantsev.simplelauncher

import android.content.Context
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class SimpleAdapter(var context: Context, var pm: PackageManager) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list = mutableListOf<ResolveInfo>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SimpleViewHolder(
            LayoutInflater.from(context).inflate(R.layout.app_item, parent, false)
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SimpleViewHolder).fill(list[position],pm)
    }


    class SimpleViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        fun fill(info: ResolveInfo, pm: PackageManager) {
            view.findViewById<ImageView>(R.id.image)
                .apply {
                    setImageDrawable(info.loadIcon(pm))
                    setOnClickListener {
                        context.startActivity(pm.getLaunchIntentForPackage(info.activityInfo.processName))
                    }
                }
        }
    }

}