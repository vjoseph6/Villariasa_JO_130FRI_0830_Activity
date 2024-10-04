package com.eldroid.bottomnavigation.Fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import com.eldroid.bottomnavigation.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {

    private lateinit var editTextFirstName: EditText
    private lateinit var editTextMiddleName: EditText
    private lateinit var editTextLastName: EditText
    private lateinit var radioGroupGender: RadioGroup
    private lateinit var checkBoxSubscribe: CheckBox
    private lateinit var buttonSave: Button

    // SharedPreferences
    private lateinit var sharedPreferences: SharedPreferences
    private val prefsName = "UserProfilePrefs"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize SharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences(prefsName, Context.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // Bind the UI elements
        editTextFirstName = view.findViewById(R.id.editTextFirstName)
        editTextMiddleName = view.findViewById(R.id.editTextMiddleName)
        editTextLastName = view.findViewById(R.id.editTextLastName)
        radioGroupGender = view.findViewById(R.id.radioGroupGender)
        checkBoxSubscribe = view.findViewById(R.id.checkBoxSubscribe)
        buttonSave = view.findViewById(R.id.buttonSave)

        // Load saved data
        loadProfileData()

        // Handle button click
        buttonSave.setOnClickListener {
            saveProfileData()
        }

        return view
    }

    private fun saveProfileData() {
        val firstName = editTextFirstName.text.toString()
        val middleName = editTextMiddleName.text.toString()
        val lastName = editTextLastName.text.toString()

        // Get selected gender
        val selectedGenderId = radioGroupGender.checkedRadioButtonId
        val gender = when (selectedGenderId) {
            R.id.radioMale -> "Male"
            R.id.radioFemale -> "Female"
            R.id.radioOther -> "Other"
            else -> "Not specified"
        }

        // Check subscription status
        val isSubscribed = checkBoxSubscribe.isChecked

        // Save data to SharedPreferences
        val editor = sharedPreferences.edit()
        editor.putString("firstName", firstName)
        editor.putString("middleName", middleName)
        editor.putString("lastName", lastName)
        editor.putString("gender", gender)
        editor.putBoolean("isSubscribed", isSubscribed)
        editor.apply() // Save changes

        Toast.makeText(requireContext(), "Profile Saved", Toast.LENGTH_SHORT).show()
        clearInputs()
    }

    private fun loadProfileData() {
        val firstName = sharedPreferences.getString("firstName", "")
        val middleName = sharedPreferences.getString("middleName", "")
        val lastName = sharedPreferences.getString("lastName", "")
        val gender = sharedPreferences.getString("gender", "")
        val isSubscribed = sharedPreferences.getBoolean("isSubscribed", false)

        // Populate the EditTexts
        editTextFirstName.setText(firstName)
        editTextMiddleName.setText(middleName)
        editTextLastName.setText(lastName)

        // Set the RadioButton based on saved gender
        when (gender) {
            "Male" -> radioGroupGender.check(R.id.radioMale)
            "Female" -> radioGroupGender.check(R.id.radioFemale)
            "Other" -> radioGroupGender.check(R.id.radioOther)
        }

        // Set the CheckBox state
        checkBoxSubscribe.isChecked = isSubscribed
    }

    private fun clearInputs() {
        editTextFirstName.text.clear()
        editTextMiddleName.text.clear()
        editTextLastName.text.clear()
        radioGroupGender.clearCheck()
        checkBoxSubscribe.isChecked = false
    }

    companion object {
        @JvmStatic
        fun newInstance() = ProfileFragment()
    }
}
