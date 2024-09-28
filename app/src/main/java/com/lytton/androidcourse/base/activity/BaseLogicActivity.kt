package com.lytton.androidcourse.base.activity

/**
 * @program: News
 * @description: 通用的项目逻辑
 * @author: LyttonYang
 * @create: 2024-09-04 14:00
 */
open class BaseLogicActivity : BaseCommonActivity() {
    /**
     * 获取当前界面的方法
     */
    
    protected val hostActivity: BaseLogicActivity
        get() = this
}