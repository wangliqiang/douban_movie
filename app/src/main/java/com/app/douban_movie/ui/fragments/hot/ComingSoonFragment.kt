package com.app.douban_movie.ui.fragments.hot


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.app.douban_movie.data.model.Theaters
import com.app.douban_movie.databinding.FragmentComingSoonBinding
import com.app.douban_movie.ui.adapters.ComingSoonAdapter
import com.app.douban_movie.ui.adapters.decoration.HeaderDecoration
import com.app.douban_movie.ui.viewmodels.HotViewModel
import kotlinx.android.synthetic.main.fragment_coming_soon.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class ComingSoonFragment : Fragment() {

    private val viewModel by viewModel<HotViewModel>()
    private lateinit var binding: FragmentComingSoonBinding
    private lateinit var comingSoonAdapter: ComingSoonAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentComingSoonBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.comingSoonloading()

        comingSoonAdapter = ComingSoonAdapter()

        comingsoon_rv.adapter = comingSoonAdapter

        viewModel.comingSoon.observe(viewLifecycleOwner, Observer {
            it ?: return@Observer
            initializeList(it)
            binding.smartRefreshLayout.finishRefresh()
        })

        binding.smartRefreshLayout.setOnRefreshListener {
            viewModel.comingSoonRefresh()
        }

    }

    private fun initializeList(theaters: Theaters) {
        val subjects = theaters.subjects
        comingSoonAdapter.submitList(subjects);

        if (subjects.isNotEmpty()) {
            val headerList = ArrayList<HeaderDecoration.HeaderBean>()
            for (i in subjects.indices) {
                headerList.add(HeaderDecoration.HeaderBean(subjects[i].mainlandPubdate))
            }
            binding.comingsoonRv.run {
                binding.comingsoonRv.clearDecorations()
                binding.comingsoonRv.addItemDecoration(
                    HeaderDecoration(
                        headerList,
                        requireContext(),
                        object :
                            HeaderDecoration.DecorationCallback {
                            override fun getGroupId(position: Int): String {
                                if (headerList[position].name.isNullOrEmpty()) {
                                    return "-1"
                                } else {
                                    return headerList[position].name
                                }
                            }

                            override fun getGroupFirstLine(position: Int): String {
                                if (headerList[position].name.isNullOrEmpty()) {
                                    return ""
                                } else {
                                    return headerList[position].name
                                }
                            }
                        })
                )
            }
        }
    }

    private fun RecyclerView.clearDecorations() {
        if (itemDecorationCount > 0) {
            for (i in itemDecorationCount - 1 downTo 0) {
                removeItemDecorationAt(i)
            }
        }
    }


}
