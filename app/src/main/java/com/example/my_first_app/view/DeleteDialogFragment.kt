package com.example.my_first_app.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.my_first_app.R

class DeleteDialogFragment : DialogFragment() {

    private lateinit var authorizationCode: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.layout_delete_dialog, container, false)

        rootView.findViewById<View>(R.id.cancelButton).setOnClickListener{
            dismiss()
        }

        rootView.findViewById<View>(R.id.deleteButton).setOnClickListener {
            val autText = rootView.findViewById<EditText>(R.id.authCode).text
            Toast.makeText(activity,autText,Toast.LENGTH_SHORT).show()
        }

        return rootView
    }

    companion object {
        fun newInstance(): DeleteDialogFragment {
            return DeleteDialogFragment()
        }
    }
}


