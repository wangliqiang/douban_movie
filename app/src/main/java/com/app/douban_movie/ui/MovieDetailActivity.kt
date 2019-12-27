package com.app.douban_movie.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.app.douban_movie.R
import com.app.douban_movie.data.model.Subject
import com.app.douban_movie.databinding.ActivityMovieDetailBinding
import com.app.douban_movie.ui.viewmodels.HotViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MovieDetailActivity : AppCompatActivity() {

    private val viewModel by viewModel<HotViewModel>()
    private lateinit var binding: ActivityMovieDetailBinding

    lateinit var subject: Subject

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)

        subject = intent.getParcelableExtra("subject")

        this.setSupportActionBar(binding.toolbar)
        this.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        this.supportActionBar?.title = subject.title
        binding.toolbar.apply {
            setNavigationOnClickListener {
                finish()
            }
        }

        with(binding) {
            lifecycleOwner = this@MovieDetailActivity
            subject = this@MovieDetailActivity.subject
        }

    }

    companion object {
        fun start(context: Context, subject: Subject) {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra("subject", subject)
            context?.startActivity(intent)
        }
    }
}
