package com.example.my_first_app.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.domain.entities.Reservation
import com.example.my_first_app.R
import com.example.my_first_app.utils.DeleteDialogCallBack

// FIXME rename to DeleteReservationConfirmationDialog
class DeleteDialogFragment(dialogCallBack: DeleteDialogCallBack, reservation: Reservation) : DialogFragment() {

    // FIXME move to constructor
    private val dialogCallBack = dialogCallBack
    private val reservation = reservation

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // FIXME: Use view binding
        val rootView: View = inflater.inflate(R.layout.layout_delete_dialog, container, false)

        rootView.findViewById<View>(R.id.cancelButton).setOnClickListener{
            dismiss()
        }

        rootView.findViewById<View>(R.id.deleteButton).setOnClickListener {
            val authorizationText = rootView.findViewById<EditText>(R.id.authCode).text
            dialogCallBack.onDeleteClicked(authorizationText.toString(), reservation)
            dismiss()
        }
        return rootView
    }

    companion object {
        fun newInstance(dialogCallBack: DeleteDialogCallBack, reservation: Reservation): DeleteDialogFragment {
            return DeleteDialogFragment(dialogCallBack,reservation)
        }
    }
}


