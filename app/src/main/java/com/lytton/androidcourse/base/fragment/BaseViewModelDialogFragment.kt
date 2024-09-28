package com.lytton.androidcourse.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.lytton.androidcourse.utils.ViewModelUtil

/**
 * @program: News
 * @description: 跟Fragment相关的视图初始化
 * @author: LyttonYang
 * @create: 2024-09-06 21:51
 */
open class BaseViewModelDialogFragment<VB: ViewBinding> : BaseDialogFragment() {
    private var _binding: VB? = null
    open val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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