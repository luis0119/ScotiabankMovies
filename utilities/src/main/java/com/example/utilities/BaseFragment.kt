package com.example.utilities

import android.app.AlertDialog
import android.app.ProgressDialog
import android.util.Log
import android.widget.ProgressBar
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    private var progressDialog: ProgressDialog? = null

    fun showAlertDialogInformation(title: Int,message: String){
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(android.R.string.yes) { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }

    open fun createProgressDialog() {
        this.progressDialog = ProgressDialog(activity)
        this.progressDialog!!.setCancelable(false)
    }

    open fun showProgressDIalog(text: Int) {
        activity?.runOnUiThread {
            try {
                progressDialog!!.setMessage(resources.getString(text))
                progressDialog!!.show()
            } catch (e: Exception) {
                Log.e("Exception", e.toString())
            }
        }
    }

    open fun dismissProgressDialog() {
        this.progressDialog!!.dismiss()
    }




}