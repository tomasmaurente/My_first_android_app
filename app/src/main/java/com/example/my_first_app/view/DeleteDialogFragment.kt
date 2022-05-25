package com.example.my_first_app.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.my_first_app.R

class DeleteDialogFragment : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        return inflater.inflate(R.layout.layout_delete_dialog, container, false)
    }

    companion object {
        fun newInstance(): DeleteDialogFragment {
            return DeleteDialogFragment()
        }
    }
}


