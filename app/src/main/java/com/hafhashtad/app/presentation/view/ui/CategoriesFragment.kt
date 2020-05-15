package com.hafhashtad.app.presentation.view.ui

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.hafhashtad.app.R
import com.hafhashtad.app.common.BaseFragment
import com.hafhashtad.app.common.help.runIfNull
import com.hafhashtad.app.databinding.FragmentCategoriesBinding
import com.hafhashtad.app.presentation.view.adapter.StoreListAdapter
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
        // TODO implement
    }

    override fun setupErrorAnnounce() {
        // TODO implement
    }

    private fun renderCategoryListViewState(categoryListVS: CategoryListVS) {
        // TODO implement
    }

    private fun fetchCategories() {
        // TODO implements
    }

    private fun backToProductsPage() {
        findNavController().popBackStack()
    }

    /****************************************************
     * OBSERVERS
     ***************************************************/

    override fun setupObservers() {
        super.setupObservers()

        // TODO implement
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