package com.example.onlineschoolcourses.ui.screen.ui.fragment.personalArea.innerFragment.additionalMaterials

import android.Manifest
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.example.onlineschoolcourses.R
import com.example.onlineschoolcourses.base.BaseFragment
import com.example.onlineschoolcourses.databinding.AdditionOfFoodMaterialsBinding
import com.example.onlineschoolcourses.helpers.askUserForOpeningAppSettings
import com.example.onlineschoolcourses.helpers.getImageFromFile
import com.example.onlineschoolcourses.helpers.showToast
import com.example.onlineschoolcourses.ui.screen.ui.activity.ScreenActivity
import com.example.onlineschoolcourses.ui.screen.ui.fragment.personalArea.innerFragment.additionalMaterials.viewModel.AdditionOfFoodMaterialsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdditionOfFoodMaterialsFragment : BaseFragment(), AdapterView.OnItemSelectedListener {
    var coursePhoto: Uri? = null
    var fileUri: Uri? = null
    var youtubeUrl: String? = null
    var professionSpinner: String? = null
    var priceSpinner: String? = null
    lateinit var binding: AdditionOfFoodMaterialsBinding
    val viewModel: AdditionOfFoodMaterialsViewModel by viewModels()

    override var bottomNavigationViewVisibility = View.GONE

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AdditionOfFoodMaterialsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            (it as ScreenActivity).toolbarGone()
        }

        initView()


    }

    private fun initView() {
        binding.courseNamesImg.setOnClickListener {
            requestCameraPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            loadImageUser.launch("image/*")
        }




        binding.addLinkOnVideoBtn.setOnClickListener {
            val textUrl = binding.addLinkOnVideoEdit.text.toString()
            if (textUrl.startsWith("www")) {
                activity?.showToast("Добавлен")
                youtubeUrl = textUrl
            } else {
                activity?.showToast("Провал")
            }
        }
        binding.getFileBtn.setOnClickListener {
            loadFileUser.launch(
                arrayOf(
                    "application/docx", "application/pdf",
                    "application/msword",
                    "application/ms-doc",
                    "application/doc",
                    "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
                    "text/plain"
                )
            )
        }


        initPriceSpinner()
        initProfessionSpinner()


        binding.saveDataBtn.setOnClickListener {

            val courseNames = binding.addCourseNamesEdit.text.toString()
            val description = binding.addDescriptionTxt.text.toString()
            val homework = binding.addHomeWorkEdit.text.toString()
            val test = binding.addTestEdit.text.toString()
            val file = binding.getFileTxtBtn.text.toString()

            if (courseNames.isNotEmpty() && description.isNotEmpty() && homework.isNotEmpty() && test.isNotEmpty() &&
                binding.courseNamesImg.drawable != null && binding.addLinkOnVideoEdit.text.isNotEmpty() && file.isNotEmpty()
            ) {
                viewModel.realtimeDataBaseAdd(
                    coursePhoto,
                    courseNames,
                    description,
                    professionSpinner,
                    priceSpinner,
                    youtubeUrl,
                    fileUri,
                    homework,
                    test
                )
            } else {
                this.activity?.showToast("Заполните все поля")
            }
        }
    }

    private fun onCameraGranted() {
        this.activity?.showToast("Получен")
    }

    private val requestCameraPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),    // contract for requesting 1 permission
        ::onGotImagePermissionResult
    )
    private val loadImageUser = registerForActivityResult(ActivityResultContracts.GetContent()) {
        getImageFromFile(it, binding.courseNamesImg.context, binding.courseNamesImg)
        coursePhoto = it
        viewModel.storageRefImageAdd(it)
    }

    private val loadFileUser = registerForActivityResult(ActivityResultContracts.OpenDocument()) { it ->
        binding.getFileTxtBtn.text = it.toString()
        fileUri = it

        fileUri?.let {
            viewModel.storageRefFileAdd(it)
        }

    }


    private fun onGotImagePermissionResult(granted: Boolean) {
        if (granted) {
            onCameraGranted()
        } else {
            // example of handling 'Deny & don't ask again' user choice
            if (!shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                askUserForOpeningAppSettings(requireContext(), this)
            } else {
                Toast.makeText(requireContext(), R.string.permission_denied, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onItemSelected(a: AdapterView<*>, p1: View?, pos: Int, p3: Long) {
        priceSpinner = a.getItemAtPosition(pos).toString()
        professionSpinner = a.getItemAtPosition(pos).toString()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    private fun initPriceSpinner() {
        val spinner = binding.priceSpinner
        spinner.onItemSelectedListener = this
// Create an ArrayAdapter using the string array and a default spinner layout


        viewModel.price.observe(viewLifecycleOwner) {

            val arrayAdapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_list_item_1, it
            )
            // Specify the layout to use when the list of choices appears
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = arrayAdapter
        }


    }

    private fun initProfessionSpinner() {
        val spinner = binding.professionSpinner
        spinner.onItemSelectedListener = this
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.professions_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

    }


}