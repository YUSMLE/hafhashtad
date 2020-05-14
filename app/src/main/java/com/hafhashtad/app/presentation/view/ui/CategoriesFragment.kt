package com.hafhashtad.app.presentation.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.hafhashtad.app.R

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class CategoriesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
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
        const val TITLE: Int = R.string.second_fragment_label
    }
}
