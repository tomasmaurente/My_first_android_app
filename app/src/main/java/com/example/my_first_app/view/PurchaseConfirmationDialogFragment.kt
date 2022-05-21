package com.example.my_first_app.view

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.DialogFragment
import com.example.my_first_app.R
import com.example.my_first_app.databinding.LayoutAddReservationBinding
import com.example.my_first_app.databinding.MainRecycleViewItemFreeBinding

class PurchaseConfirmationDialogFragment : DialogFragment(R.layout.main_recycle_view_item_free) {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage(getString(R.string.order_confirmation))
            .setPositiveButton(getString(R.string.ok)) { _, _ -> }
            .create()

    companion object {
        const val TAG = "PurchaseConfirmationDialog"

    }

    private lateinit var binding: MainRecycleViewItemFreeBinding


}