package com.hafhashtad.app.presentation.view.ui

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hafhashtad.app.R
import com.hafhashtad.app.common.BaseFragment
import com.hafhashtad.app.common.help.EndlessScrollListener
import com.hafhashtad.app.common.help.observeNonNull
import com.hafhashtad.app.common.help.runIfNull
import com.hafhashtad.app.databinding.FragmentCategoriesBinding
import com.hafhashtad.app.presentation.model.ListItem
import com.hafhashtad.app.presentation.view.adapter.StoreListAdapter
import com.hafhashtad.app.presentation.view.adapter.VerticalSpaceItemDecoration
import com.hafhashtad.app.presentation.view.callback.ListItemCallback
import com.hafhashtad.app.presentation.viewmodel.MainVM
import com.hafhashtad.app.presentation.viewstate.CategoryListVS
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class CategoriesFragment : BaseFragment() {

    /**
     * Values
     */

    @Inject
    internal lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    private lateinit var mainVM: MainVM
    private lateinit var dataBinding: FragmentCategoriesBinding
    private lateinit var categoriesAdapter: StoreListAdapter

    private lateinit var parentProductListItem: ListItem.ProductListItem

    /****************************************************
     * FRAGMENT LIFECYCLE
     ***************************************************/

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainVM = ViewModelProvider(requireActivity(), viewModelProviderFactory)[MainVM::class.java]
        savedInstanceState.runIfNull {
            fetchCategories()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_categories, parent, false)

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return false
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }

    /****************************************************
     * VIEW/DATA BINDING
     ***************************************************/

    override fun setupViews() {
        setupStoreListView()
        setupErrorAnnounce()
    }

    override fun setupStoreListView() {
        categoriesAdapter = StoreListAdapter(mCallback)

        val linearLayoutManager = LinearLayoutManager(context)
        dataBinding.categories.apply {
            adapter = categoriesAdapter
            layoutManager = linearLayoutManager
            addItemDecoration(VerticalSpaceItemDecoration(1))
            addOnScrollListener(object : EndlessScrollListener(linearLayoutManager) {
                override fun onLoadMore(page: Int) {
                    // Nothing
                }
            })
        }

        dataBinding.swipeContainer.setOnRefreshListener {
            categoriesAdapter.clearItems()
            fetchCategories()
        }
    }

    override fun setupErrorAnnounce() {
        dataBinding.errorAnnounce.setFactory {
            val t = TextView(context)
            t.gravity = Gravity.CENTER_VERTICAL or Gravity.CENTER_HORIZONTAL
            t.setTextColor(context!!.resources.getColor(R.color.colorTextDescription))
            t
        }
        dataBinding.errorAnnounce.inAnimation = AnimationUtils.loadAnimation(
            context,
            R.anim.slide_in_down
        )
        dataBinding.errorAnnounce.outAnimation = AnimationUtils.loadAnimation(
            context,
            R.anim.slide_out_up
        )
        dataBinding.errorAnnounce.setCurrentText("")
    }

    private fun renderCategoryListViewState(categoryListVS: CategoryListVS) {
        with(dataBinding) {
            viewState = categoryListVS
            executePendingBindings()
        }

        // Handle showing loading indicator for sequence pages
        categoryListVS.getLoadingListItem()?.let {
            categoriesAdapter.showLoadingItem(it)
        } ?: run {
            categoriesAdapter.hideLoadingItem()
        }

        // Handle showing error for initial page
        if (categoryListVS.shouldShowErrorAnnounce()) {
            dataBinding.errorAnnounce.setText(categoryListVS.getErrorMessage())
        }
        else {
            dataBinding.errorAnnounce.setText("")
        }

        // Handle showing error for sequence pages
        categoryListVS.getErrorListItem()?.let {
            categoriesAdapter.showErrorItem(it)
        } ?: run {
            categoriesAdapter.hideErrorItem()
        }

        // Handle showing products
        if (categoryListVS.page == 1) categoriesAdapter.clearItems()
        categoriesAdapter.addItems(categoryListVS.getCategoryListItems())
    }

    private fun fetchCategories() {
        mainVM.fetchCategories()
    }

    private fun backToProductsPage(item: ListItem.CategoryListItem) {
        mainVM.updateCategoryOfProduct(parentProductListItem, item)
        findNavController().navigateUp()
    }

    /****************************************************
     * OBSERVERS
     ***************************************************/

    override fun setupObservers() {
        super.setupObservers()

        mainVM.getSelectedProductListItem().observeNonNull(viewLifecycleOwner) {
            parentProductListItem = it
        }

        mainVM.getCategoriesStream().observeNonNull(viewLifecycleOwner) {
            renderCategoryListViewState(it)
        }
    }

    /**
     * Categories Adapter Callback
     */
    private val mCallback: ListItemCallback by lazy {
        object : ListItemCallback {
            override fun onClick(item: ListItem.CategoryListItem) {
                if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                    backToProductsPage(item)
                }
            }

            override fun onClick(item: ListItem.ErrorListItem) {
                if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                    fetchCategories()
                }
            }
        }
    }

    companion object {

        /**
         * Use this factory method to create a new instance of this fragment.
         *
         * @return A new instance of fragment [CategoriesFragment].
         */
        fun newInstance(): CategoriesFragment {
            return CategoriesFragment()
        }

        val TAG = CategoriesFragment::class.java.simpleName
        val ID = CategoriesFragment::class.java.simpleName.hashCode()
        const val TITLE: Int = R.string.first_fragment_label
    }
}
