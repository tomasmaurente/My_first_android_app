package com.example.my_first_app.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment

class DialogFragmentTest : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v: View = inflater.inflate(com.example.my_first_app.R.layout.layout_dialog_fragment_test, container, false)
        val tv = v.findViewById<View>(com.example.my_first_app.R.id.testText)
        (tv as TextView).text = "Hello World"
        return v
    }

    companion object {
        fun newInstance(): DialogFragmentTest {
            return DialogFragmentTest()
        }
    }
}


