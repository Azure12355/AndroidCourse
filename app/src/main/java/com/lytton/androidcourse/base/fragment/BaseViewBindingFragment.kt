package com.lytton.androidcourse.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.lytton.androidcourse.utils.ViewModelUtil

/**
 * @program: News
 * @description:
 * @author: LyttonYang
 * @create: 2024-09-17 17:15
 */
abstract class BaseViewBindingFragment<VB: ViewBinding> : BaseCommonFragment() {
    private var _binding: VB? = null
    open val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //绑定ViewBinding
        _binding = ViewModelUtil.newViewBinding(layoutInflater, this.javaClass)
    }


    override fun getLayoutView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}