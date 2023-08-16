package com.example.notemvvm

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.notemvvm.databinding.ActivityContentBinding

class ContentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContentBinding
    private lateinit var contentViewModel: ContentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_content)

        contentViewModel = ContentViewModel(this)
        binding.contentViewModel = contentViewModel

        getData()

        binding.btnAdd.setOnClickListener {
            contentViewModel.onClickAdd()
            finish()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun getData() {
        val id = intent.getIntExtra("id", -1)
        val content =  intent.getStringExtra("content")
        val title = intent.getStringExtra("title")
        if (title != null) {
            contentViewModel.title = title
        }
        if (content != null) {
            contentViewModel.contents = content
        }
        contentViewModel.id.value = id
        binding.edtTitle.setText(contentViewModel.title)
        binding.edtContents.setText(contentViewModel.contents)
    }
}