package com.app.douban_movie.ui.fragments.hot


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.app.douban_movie.R
import com.app.douban_movie.data.model.Subject
import com.app.douban_movie.databinding.FragmentInTheatersBinding
import com.app.douban_movie.ui.MovieDetailActivity
import com.app.douban_movie.ui.adapters.InTheatersAdapter
import com.app.douban_movie.ui.viewmodels.HotViewModel
import com.app.douban_movie.utils.Logger
import kotlinx.android.synthetic.main.fragment_in_theaters.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class InTheatersFragment : Fragment(), InTheatersAdapter.ViewHolder.Delegate {

    private val viewModel by viewModel<HotViewModel>()
    private lateinit var binding: FragmentInTheatersBinding
    private lateinit var inTheatersAdapter: InTheatersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_in_theaters, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.inTheaterloading()

        inTheatersAdapter = InTheatersAdapter(this)

        intheater_rv.adapter = inTheatersAdapter

        viewModel.inTheaters.observe(viewLifecycleOwner, Observer {
            inTheatersAdapter.submitList(it?.subjects);
            binding.smartRefreshLayout.finishRefresh()
        })

        binding.smartRefreshLayout.setOnRefreshListener {
            viewModel.inTheaterRefresh()
        }

    }

    override fun onItemClick(subject: Subject) {
        MovieDetailActivity.start(requireContext(), subject)
    }

}
