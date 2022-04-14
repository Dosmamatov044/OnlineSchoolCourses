package com.example.onlineschoolcourses.ui.screen.ui.fragment.personalArea.innerFragment.updateInformation

import android.Manifest
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.onlineschoolcourses.R
import com.example.onlineschoolcourses.base.BaseFragment
import com.example.onlineschoolcourses.databinding.FragmentUpdateInformationAboutUserBinding
import com.example.onlineschoolcourses.helpers.askUserForOpeningAppSettings
import com.example.onlineschoolcourses.helpers.getImageFromFile
import com.example.onlineschoolcourses.helpers.loadImage
import com.example.onlineschoolcourses.helpers.showToast
import com.example.onlineschoolcourses.ui.screen.ui.activity.ScreenActivity
import com.example.onlineschoolcourses.ui.screen.ui.fragment.personalArea.innerFragment.updateInformation.updateInformation.UpdateInformationAboutUserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UpdateInformationAboutUserFragment : BaseFragment(), AdapterView.OnItemSelectedListener {
    private var userImgUri: Uri? = null
    var diplomaAndCertificateUri: Uri? = null

    var spinnerText: String? = null
    lateinit var binding: FragmentUpdateInformationAboutUserBinding
    val viewModel: UpdateInformationAboutUserViewModel by viewModels()

    override var bottomNavigationViewVisibility = View.GONE
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpdateInformationAboutUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            (it as ScreenActivity).toolbarGone()
        }
        initSpinner()


        lifecycleScope.launch {
            viewModel.userInfoData.collectLatest {



binding.aboutCareerEditInfoAboutUser.setText(it[0].career + it[0].profession.toString())
binding.changeNameEditInfoAboutUser.setText(it[0].name)
binding.changeSurnameEditInfoAboutUser.setText(it[0].surname)
binding.diplomasAndCertificatesDescriptionEditInfoAboutUser.setText(it[0].diplomaAndCertificateDescription)
binding.diplomasAndCertificatesImageImgInfoAboutUser.loadImage(it[0].diplomaAndCertificateImages)
binding.experienceEditInfoAboutUser.setText(it[0].experience)
binding.professionSpinnerInfoAboutUser.setSelection(it[0].professionPosition.toInt())
  binding.userImgInfoAboutUser.loadImage(
      it[0].user_image
  )
            }

        }


        binding.updateBtnInfoAboutUser.setOnClickListener {
            val career = binding.aboutCareerEditInfoAboutUser.text.toString()
            val experience = binding.experienceEditInfoAboutUser.text.toString()
            val description =
                binding.diplomasAndCertificatesDescriptionEditInfoAboutUser.text.toString()
            val name = binding.changeNameEditInfoAboutUser.text.toString()
            val surname = binding.changeSurnameEditInfoAboutUser.text.toString()
            viewModel.readKey.observe(requireActivity()) { key ->


                userImgUri?.let { userImgUri ->
                    viewModel.uploadStorageDataUserImages(
                        key,
                        userImgUri
                    )
                }

                diplomaAndCertificateUri?.let { diplomasUri ->
                    viewModel.uploadStorageDataDiplomasImages(
                        key,
                        diplomasUri
                    )
                }

                viewModel.initFireBase(
                    name, surname,
                    career,
                    experience,
                    description,
                    spinnerText,
                    diplomaAndCertificateUri,
                    userImgUri,
                    key = key
                )
            }

        }


    }


    private fun initSpinner() {
        val spinner = binding.professionSpinnerInfoAboutUser
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
        initView()
    }

    private fun initView() {
        binding.userImgInfoAboutUser.setOnClickListener {
            requestImagePermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            loadImageUser.launch("image/*")
        }

        binding.diplomasAndCertificatesImageImgInfoAboutUser.setOnClickListener {
            requestImagePermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            loadImageDiplomasAndCertificatesImage.launch("image/*")
        }

    }

    override fun onItemSelected(a: AdapterView<*>, p1: View?, pos: Int, p3: Long) {

        spinnerText = a.getItemAtPosition(pos).toString()


    }

    private fun onGotCameraPermissionResult(granted: Boolean) {
        if (granted) {
            onCameraPermissionGranted()
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

    private fun onCameraPermissionGranted() {
        this.activity?.showToast("Получен")
    }

    private val requestImagePermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),    // contract for requesting 1 permission
        ::onGotCameraPermissionResult
    )
    private val loadImageUser = registerForActivityResult(
        ActivityResultContracts.GetContent()
    )
    {
        getImageFromFile(it, binding.userImgInfoAboutUser.context, binding.userImgInfoAboutUser)
        userImgUri = it
    }
    private val loadImageDiplomasAndCertificatesImage = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) {
        getImageFromFile(
            it,
            binding.diplomasAndCertificatesImageImgInfoAboutUser.context,
            binding.diplomasAndCertificatesImageImgInfoAboutUser
        )
        diplomaAndCertificateUri = it
    }


    override fun onNothingSelected(a: AdapterView<*>?) {}

    companion object {
        fun progressDialog(context: Context, text: String): Dialog {
            val dialog = Dialog(context)
            val txt: TextView
            val inflate = LayoutInflater.from(context).inflate(R.layout.progress_dialog, null)
            txt = inflate.findViewById(R.id.progress_text)
            txt.text = text
            dialog.setContentView(inflate)
            dialog.setCancelable(false)

            dialog.window!!.setBackgroundDrawable(
                ColorDrawable(Color.TRANSPARENT)
            )
            return dialog
        }
    }
}