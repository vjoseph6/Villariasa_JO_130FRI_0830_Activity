package com.eldroid.menu

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class YourDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): AlertDialog {
        val inflater = requireActivity().layoutInflater
        // Inflate the custom layout
        val view = inflater.inflate(R.layout.dialog_fragment_confirmation, null)

        // Find buttons in the inflated layout
        val btnPositive: Button = view.findViewById(R.id.btnDeleteNo)
        val btnNegative: Button = view.findViewById(R.id.btnDeleteYes)

        // Set click listeners for the buttons
        btnPositive.setOnClickListener {
            // Navigate to FirstFragment
            (activity as MainActivity).loadFragment(FirstFragment.newInstance())
            dismiss() // Dismiss the dialog after action
        }

        btnNegative.setOnClickListener {
            // Exit the app
            activity?.finish()
        }

        // Build and return the AlertDialog
        return AlertDialog.Builder(requireActivity())
            .setView(view) // Set the custom view
            .create()
    }

    companion object {
        @JvmStatic
        fun newInstance() = YourDialogFragment()
    }
}