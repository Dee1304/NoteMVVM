package com.example.notemvvm

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notemvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NoteAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainViewModel = MainViewModel(this)
        binding.mainViewModel = mainViewModel

        setUpAdapter()
    }

    private fun setUpAdapter() {
        val lst = mainViewModel.getData().value!!
        adapter = NoteAdapter(this,lst, object: RcvInterface{
            override fun clickItem(pos: Int) {
                val intent = Intent(this@MainActivity, ContentActivity::class.java)
                intent.putExtra("pos", pos)
                intent.putExtra("title", lst[pos].title)
                intent.putExtra("content", lst[pos].contents)
                intent.putExtra("id", lst[pos].id)
                startActivity(intent)
            }

            override fun longCLickItem(pos: Int): Boolean {
                val builder = android.app.AlertDialog.Builder(this@MainActivity)
                builder.setTitle("Delete " + lst[pos].title + " ?")
                builder.setMessage("Are you want to delete ?")
                builder.setPositiveButton("Yes", DialogInterface.OnClickListener { _, _ ->
                    mainViewModel.delete(lst[pos])
                    startActivity(Intent(this@MainActivity, MainActivity::class.java))
                    finish()
                })
                builder.setNegativeButton("No", DialogInterface.OnClickListener { _, _ ->

                })
                builder.create().show()
                return true
            }
        })

        binding.rcv.layoutManager = LinearLayoutManager(this)
        binding.rcv.adapter = adapter
    }

}