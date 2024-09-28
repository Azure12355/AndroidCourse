package com.lytton.androidcourse.base.activity

import android.content.Intent
import com.lytton.androidcourse.base.activity.BaseActivity

/**
 * @program: News
 * @description: 通用界面的逻辑
 * @author: LyttonYang
 * @create: 2024-09-04 13:59
 */
open class BaseCommonActivity : BaseActivity() {

    /**
     * 启动界面
     */
    open fun startActivity(clazz: Class<*>) {
        //启动界面
        startActivity(Intent(this, clazz))
    }

    /**
     * 启动界面后关闭当前界面
     */
    open fun startActivityAfterFinishThis(clazz: Class<*>) {
        startActivity(clazz)
        //关闭当前界面
        finish()
    }
}