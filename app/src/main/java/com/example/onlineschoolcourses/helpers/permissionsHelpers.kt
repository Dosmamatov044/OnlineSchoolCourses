package com.example.onlineschoolcourses.helpers

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.onlineschoolcourses.R

fun askUserForOpeningAppSettings(requireContext:Context,fragment: Fragment) {
    val appSettingsIntent = Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", fragment.activity?.packageName, null)
    )
    val packageManager: PackageManager =requireContext.packageManager
    if (packageManager.resolveActivity(appSettingsIntent, PackageManager.MATCH_DEFAULT_ONLY) == null) {
        Toast.makeText(requireContext, R.string.permission_denied, Toast.LENGTH_SHORT).show()
    } else {
        AlertDialog.Builder(requireContext)
            .setTitle(R.string.permission_denied)
            .setMessage(requireContext.getString(R.string.close))
            .setPositiveButton(requireContext.getString(R.string.open)) { _, _ ->
               requireContext.startActivity(appSettingsIntent)
            }
            .create()
            .show()
    }
}