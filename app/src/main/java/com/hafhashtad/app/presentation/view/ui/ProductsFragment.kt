package com.hafhashtad.app.presentation.view.ui

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hafhashtad.app.R
import com.hafhashtad.app.common.BaseFragment
import com.hafhashtad.app.common.help.EndlessScrollListener
import com.hafhashtad.app.common.help.observeNonNull
import com.hafhashtad.app.common.help.runIfNull
import com.hafhashtad.app.databinding.FragmentProductsBinding
import com.hafhashtad.app.presentation.model.ListItem
import com.hafhashtad.app.presentation.view.adapter.StoreListAdapter
import com.hafhashtad.app.presentation.view.adapter.VerticalSpaceItemDecoration
import com.hafhashtad.app.presentation.view.callback.ListItemCallback
import com.hafhashtad.app.presentation.viewmodel.MainVM
import com.hafhashtad.app.presentation.viewstate.ProductListVS
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ProductsFragment : BaseFragment() {

    /**
     * Values
     */

    @Inject
    internal lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    private lateinit var mainVM: MainVM
    private lateinit var dataBinding: FragmentProductsBinding
    private lateinit var productsAdapter: StoreListAdapter

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
            fetchProducts()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_products, parent, false)

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
        productsAdapter = StoreListAdapter(mCallback)

        val linearLayoutManager = LinearLayoutManager(context)
        dataBinding.products.apply {
            adapter = productsAdapter
            layoutManager = linearLayoutManager
            addItemDecoration(VerticalSpaceItemDecoration(1))
            addOnScrollListener(object : EndlessScrollListener(linearLayoutManager) {
                override fun onLoadMore(page: Int) {
                    // Nothing
                }
            })
        }

        dataBinding.swipeContainer.setOnRefreshListener {
            productsAdapter.clearItems()
            fetchProducts()
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

    private fun renderProductListViewState(productListVS: ProductListVS) {
        with(dataBinding) {
            viewState = productListVS
            executePendingBindings()
        }

        // Handle showing loading indicator for sequence pages
        productListVS.getLoadingListItem()?.let {
            productsAdapter.showLoadingItem(it)
        } ?: run {
            productsAdapter.hideLoadingItem()
        }

        // Handle showing error for initial page
        if (productListVS.shouldShowErrorAnnounce()) {
            dataBinding.errorAnnounce.setText(productListVS.getErrorMessage())
        }
        else {
            dataBinding.errorAnnounce.setText("")
        }

        // Handle showing error for sequence pages
        productListVS.getErrorListItem()?.let {
            productsAdapter.showErrorItem(it)
        } ?: run {
            productsAdapter.hideErrorItem()
        }

        // Handle showing products
        productsAdapter.setDataset(productListVS.getProductListItems() as ArrayList<ListItem>)
    }

    private fun fetchProducts() {
        mainVM.fetchProducts()
    }

    private fun navigateToCategoriesPage(item: ListItem.ProductListItem) {
        mainVM.setSelectedProductListItem(item)
        findNavController().navigate(R.id.action_ProductsFragment_to_CategoriesFragment)
    }

    /****************************************************
     * OBSERVERS
     ***************************************************/

    override fun setupObservers() {
        super.setupObservers()

        mainVM.getProductsStream().observeNonNull(viewLifecycleOwner) {
            renderProductListViewState(it)
        }
    }

    /**
     * Products Adapter Callback
     */
    private val mCallback: ListItemCallback by lazy {
        object : ListItemCallback {
            override fun onClick(item: ListItem.ProductListItem) {
                if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                    navigateToCategoriesPage(item)
                }
            }

            override fun onClick(item: ListItem.ErrorListItem) {
                if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                    fetchProducts()
                }
            }
        }
    }

    companion object {

        /**
         * Use this factory method to create a new instance of this fragment.
         *
         * @return A new instance of fragment [ProductsFragment].
         */
        fun newInstance(): ProductsFragment {
            return ProductsFragment()
        }

        val TAG = ProductsFragment::class.java.simpleName
        val ID = ProductsFragment::class.java.simpleName.hashCode()
        const val TITLE: Int = R.string.first_fragment_label
    }
}
