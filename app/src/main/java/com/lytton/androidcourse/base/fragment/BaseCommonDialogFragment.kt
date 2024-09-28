package com.lytton.androidcourse.base.fragment

import android.view.View
import androidx.annotation.IdRes

/**
 * @program: News
 * @description:
 * @author: LyttonYang
 * @create: 2024-09-14 19:03
 */
abstract class BaseCommonDialogFragment: BaseDialogFragment() {
    fun <T : View?> findViewById(@IdRes id: Int): T {
        return requireView().findViewById(id)
    }
}