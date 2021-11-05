package com.simplaapliko.challenge3.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.simplaapliko.challenge3.R
import com.simplaapliko.challenge3.domain.business.BusinessModel
import com.simplaapliko.challenge3.livedata.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var emptyView: View
    private lateinit var loadingIndicator: View
    private lateinit var refreshLayout: SwipeRefreshLayout

    private val viewModel: SearchViewModel by viewModels()

    private val itemClickListener: (BusinessModel) -> Unit = {
        viewModel.onBusinessSelected(it)
    }

    private var adapter = BusinessAdapter(itemClickListener)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        bindViewModel()
    }

    private fun initViews(view: View) {
        emptyView = view.findViewById(R.id.empty_view)
        loadingIndicator = view.findViewById(R.id.loading_indicator)
        refreshLayout = view.findViewById(R.id.refreshLayout)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        recyclerView.adapter = adapter

        refreshLayout.setOnRefreshListener { viewModel.refresh() }
    }

    private fun bindViewModel() {
        viewModel.searchResultPublisher
            .observe(viewLifecycleOwner) { wrapper ->
                when (wrapper.status) {
                    Status.LOADING -> updateLoadingIndication(true)
                    Status.SUCCESS -> updateSearch(wrapper.data!!)
                    Status.ERROR -> showEmptyListIndication()
                }
            }
    }

    private fun updateSearch(items: List<BusinessModel>) {
        adapter.setItems(items)
        updateLoadingIndication(false)
    }

    private fun showEmptyListIndication() {
        emptyView.visibility = View.VISIBLE
        updateLoadingIndication(false)
    }

    private fun updateLoadingIndication(isLoading: Boolean) {
        refreshLayout.isRefreshing = false
        loadingIndicator.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}
