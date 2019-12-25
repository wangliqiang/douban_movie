package com.app.douban_movie.ui.fragments.hot


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.app.douban_movie.R
import com.app.douban_movie.ui.viewmodels.HotViewModel
import com.app.douban_movie.utils.Logger
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class InTheatersFragment : Fragment() {

    private val viewModel by viewModel<HotViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_in_theaters, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.inTheaters.observe(viewLifecycleOwner, Observer {
        })
    }

}
