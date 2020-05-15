package com.hafhashtad.app.presentation.view.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.hafhashtad.app.R
import com.hafhashtad.app.common.BaseActivity
import com.hafhashtad.app.databinding.ActivityMainBinding
import com.hafhashtad.app.presentation.viewmodel.MainVM
import javax.inject.Inject

class MainActivity : BaseActivity() {

    /**
     * Values
     */

    @Inject
    internal lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    private lateinit var mainVM: MainVM
    private lateinit var dataBinding: ActivityMainBinding

    @Inject
    lateinit var fragmentManager: FragmentManager

    @Inject
    lateinit var productsFragment: ProductsFragment

    @Inject
    lateinit var categoriesFragment: CategoriesFragment

    /****************************************************
     * ACTIVITY LIFECYCLE
     ***************************************************/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        extractIntentParams(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)

        return true
    }

    /****************************************************
     * ACTIVITY STATE
     ***************************************************/

    override fun initializeActivity(savedInstanceState: Bundle?) {
        // Nothing
    }

    override fun extractIntentParams(data: Intent?) {
        // Nothing
    }

    /****************************************************
     * VIEW/DATA BINDING
     ***************************************************/

    override fun setupViews() {
        // Set Content View
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun setupActionBar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar!!.setDisplayShowTitleEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(false)
    }

    override fun setupNavigation() {
        /* It will be set automatically. */

        // Init fragments and then, open [ProductsFragment] as default fragment
        initFragments()
    }

    private fun initFragments() {
        /* Fragments will be injected by Mr. Dagger */

        /* [ProductsFragment] will be showed automatically. */
    }

    /****************************************************
     * OBSERVERS
     ***************************************************/

    override fun setupObservers() {
        super.setupObservers()

        mainVM = ViewModelProvider(this, viewModelProviderFactory).get(MainVM::class.java)
    }

    /****************************************************
     * SERVICE BINDING
     ***************************************************/

    // Nothing

    companion object {
        val TAG = MainActivity::class.java.simpleName
        const val EXTRA_FRAGMENT_ID = "EXTRA_FRAGMENT_ID"
        const val EXTRA_FRAGMENT_TITLE = "EXTRA_FRAGMENT_TITLE"
    }
}
