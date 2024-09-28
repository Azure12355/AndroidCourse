package com.lytton.androidcourse.base.fragment

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.lytton.androidcourse.base.viewmodel.BaseViewModel
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

/**
 * @program: News
 * @description: 跟Fragment相关的视图初始化
 * @author: LyttonYang
 * @create: 2024-09-06 21:51
 */
abstract class BaseViewModelFragment<VB: ViewBinding, VM: BaseViewModel>(
    private val viewModelClass: KClass<VM>
) : BaseViewBindingFragment<VB>() {
    
    private lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        //绑定ViewModel
        viewModel = ViewModelProvider(this)[viewModelClass]
        
        //开始观察ViewModel
        observeViewModel()
        
    }
    
    /**
     * 开启观察者模式观察ViewModel中的数据变化
     */
    private fun observeViewModel() {
        lifecycleScope.launch {
            Log.d("BaseViewModelFragment", "observeViewModel: 开始观察ViewModel里的数据变化。。。。。。。。。。。")
            viewModel.pipeline.collect {
                data -> handleData(data)
            }
        }
    }

    /**
     * 消费传递过来的数据
     */
    abstract fun handleData(data: Map<String, Any?>)

}