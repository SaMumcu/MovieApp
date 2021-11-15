package com.samumcu.movieapp.presentation.movielist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.samumcu.movieapp.R
import com.samumcu.movieapp.presentation.movielist.adapter.ListAdapter
import com.samumcu.movieapp.presentation.movielist.adapter.SliderAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private lateinit var viewModel: MovieListViewModel
    private lateinit var sliderAdapter: SliderAdapter
    private lateinit var listAdapter: ListAdapter
    private lateinit var viewPager2: ViewPager2
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(MovieListViewModel::class.java)
        viewModel.getPageData()
        observeMovieListLiveData()
        observeMoviePagerListLiveData()
        return inflater.inflate(R.layout.movie_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onViewCreated(view, savedInstanceState)
        viewPager2 = view.findViewById(R.id.playingMoviesViewPager)
        recyclerView = view.findViewById(R.id.movieListRV)
        layoutManager = LinearLayoutManager(context)
        viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState==RecyclerView.SCROLL_STATE_IDLE) {
                    if (viewModel.currentPage <= viewModel.totalPages) {
                        viewModel.goToNextPage()
                        viewModel.getPageData()
                        listAdapter.notifyDataSetChanged()
                        sliderAdapter.notifyDataSetChanged()
                    }
                }
                else if (!recyclerView.canScrollVertically(-1) && newState==RecyclerView.SCROLL_STATE_IDLE) {
                    if (viewModel.currentPage > 1) {
                        viewModel.goToPreviousPage()
                        viewModel.getPageData()
                        listAdapter.notifyDataSetChanged()
                        sliderAdapter.notifyDataSetChanged()
                    }
                }
            }
        })
        super.onViewCreated(view, savedInstanceState)
    }

    private fun observeMovieListLiveData() {
        viewModel.movieListDataLiveData.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                val upcoming = it.results
                listAdapter = ListAdapter(upcoming)
                recyclerView.layoutManager = layoutManager
                recyclerView.adapter = listAdapter
                Timber.d(upcoming.toString())
            }
        })
    }

    private fun observeMoviePagerListLiveData() {
        viewModel.moviePagerListDataLiveData.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                val playing = it.results
                sliderAdapter = SliderAdapter(playing)
                viewPager2.adapter = sliderAdapter
                viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
                Timber.d(playing.toString())
            }
        })
    }
}