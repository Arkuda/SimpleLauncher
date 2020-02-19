package com.kiryantsev.simplelauncher

import android.content.Intent
import android.content.pm.ResolveInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        prepareRecycler()
    }


    private fun loadApps(): MutableList<ResolveInfo> =
        packageManager.queryIntentActivities(
            Intent(Intent.ACTION_MAIN, null).apply {
                addCategory(Intent.CATEGORY_LAUNCHER)
            }
        ,0)

    private fun prepareRecycler(){
        findViewById<RecyclerView>(R.id.recycler).apply {
            layoutManager = GridLayoutManager(context,4,GridLayoutManager.VERTICAL,false)
            adapter = SimpleAdapter(context,packageManager).apply { list = loadApps() }
        }
    }
}
