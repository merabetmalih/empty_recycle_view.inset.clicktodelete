package com.example.myapplicationdemo

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplicationdemo.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random
import kotlinx.android.parcel.Parcelize
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), ExampleAdapter.OnItemClickListener {
    private val exampleList = ArrayList<ExampleItem>()
    private val adapter = ExampleAdapter(exampleList, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
    }

    fun insertItem(view: View) {

        val newFragment =TaskDateTimePicker(object:PickerListener{
            override fun onDateSet(year: Int, month: Int, day: Int) {
                val index = exampleList.size
                val cal =Calendar.getInstance()
                cal.set(year,month,day)
                val newItem = ExampleItem(
                    R.drawable.ic_android,
                    "New tache at position ${index+1}",
                    cal
                )
                exampleList.add(index, newItem)
                adapter.notifyItemInserted(index)
            }
        })
        newFragment.show(supportFragmentManager, "datePicker")

    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "tache $position terminé ", Toast.LENGTH_SHORT).show()
        exampleList.removeAt(position)
        adapter.notifyDataSetChanged()
    }

}