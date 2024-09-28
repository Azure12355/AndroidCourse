package com.lytton.androidcourse.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

/**
 * @program: News
 * @description:
 * @author: LyttonYang
 * @create: 2024-09-04 14:27
 */
abstract class BaseFragment : DialogFragment(){
    /**
     * 控件相关
     */
    protected open fun initViews() {}

    /**
     * 数据相关
     */
    protected open fun initDatum() {}

    /**
     * 设置监听器
     */
    protected open fun initListeners() {}

    /**
     * 返回要显示的控件
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //获取view
        val view = getLayoutView(inflater, container, savedInstanceState)
        return view
        
    }

    abstract fun getLayoutView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?

    /**
     * View创建以后调用
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initDatum()
        initListeners()
    }
}