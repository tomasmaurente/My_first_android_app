package com.example.my_first_app.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.my_first_app.R
import com.example.my_first_app.utils.DeleteDialogCallBack

class DeleteDialogFragment(dialogCallBack: DeleteDialogCallBack) : DialogFragment() {

    private lateinit var authorizationCode: String
    private val dialogCallBack = dialogCallBack

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
            val authorizationText = rootView.findViewById<EditText>(R.id.authCode).text
            //Toast.makeText(activity,authorizationText,Toast.LENGTH_SHORT).show()
            dialogCallBack.onDeleteClicked(authorizationText.toString())
            dismiss()
        }

        return rootView
    }

    companion object {
        fun newInstance(dialogCallBack: DeleteDialogCallBack): DeleteDialogFragment {
            return DeleteDialogFragment(dialogCallBack)
        }
    }
}


