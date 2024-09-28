package com.lytton.androidcourse.base.activity

import android.os.Bundle
import android.util.Log
import androidx.viewbinding.ViewBinding
import com.lytton.androidcourse.utils.ViewModelUtil

/**
 * @program: News
 * @description: 通用的ViewModelActivity, 用于进行视图绑定和视图初始化
 * @author: LyttonYang
 * @create: 2024-09-05 22:02
 */
open class BaseViewModelActivity<VB: ViewBinding> : BaseLogicActivity() {
    open lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("TAG", "onCreate: 开始进行ViewBinding的反射填充")
        //调用inflate方法, 创建ViewBinding
        binding = ViewModelUtil.newViewBinding<VB>(layoutInflater, javaClass)!!
        Log.d("TAG", "onCreate: ViewBinding填充完毕!!! $binding")
        
        setContentView(binding.root)
    }
}