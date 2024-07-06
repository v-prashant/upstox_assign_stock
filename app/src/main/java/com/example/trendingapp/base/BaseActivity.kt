package com.example.trendingapp.base

import com.example.trendingapp.api.Resource
import com.example.trendingapp.api.Status
import com.example.trendingapp.utils.ProgressUtil
import com.example.trendingapp.utils.ToastUtils
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

/**
 * Created by Prashant Verma
 */
open class BaseActivity : AppCompatActivity(){

    private lateinit var progressDialog: ProgressUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        initProgress()
    }

    private fun initProgress() {
        progressDialog = ProgressUtil(this)
    }

    open fun showProgress() {
        progressDialog.showDialog("")
    }

    open fun showProgress(progressMessage: String?) {
        if (!progressDialog.isShowing) {
            progressDialog.showDialog(progressMessage ?: "")
        }
    }

    open fun hideProgress() {
        if (progressDialog.isShowing) {
            progressDialog.dismissDialog()
        }
    }

    open fun showErrorMessage(message: String?) {
        if(message != null)
            showToastMessage(message, ToastUtils.ToastType.ERROR, ToastUtils.HeaderToastType.ERROR)
    }

    open fun showSuccessMessage(message: String) {
        ToastUtils.makeToast(
            ToastUtils.ToastType.SUCCESS,
            ToastUtils.HeaderToastType.SUCCESS, message)
    }

    open fun showToastMessage(message: String, type: ToastUtils.ToastType, headerToastType: ToastUtils.HeaderToastType) {
        ToastUtils.makeToast(type,headerToastType, message, Toast.LENGTH_SHORT)
    }

    open fun <T> MutableLiveData<Resource<T>>.observeLiveData(
        success: (t: T) -> Unit
    ) {
        observe(this@BaseActivity) {
            it?.let {
                when (it.status) {
                    Status.LOADING -> {
                        showProgress()
                    }
                    Status.SUCCESS -> {
                        hideProgress()
                        it.data?.let { response ->
                            success.invoke(response)
                        }
                    }
                    Status.FAILURE, Status.ERROR -> {
                        hideProgress()
                        showErrorMessage(it.message)
                    }
                }
            }
        }
    }


}