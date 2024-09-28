package com.lytton.androidcourse.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * @program: News
 * @description: 所有Activity的父类
 * @author: LyttonYang
 * @create: 2024-09-04 13:49
 */
open class BaseActivity : AppCompatActivity() {
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
     * 后置创建方法
     * @param savedInstanceState
     */
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        initViews()
        initDatum()
        initListeners()
    }
}