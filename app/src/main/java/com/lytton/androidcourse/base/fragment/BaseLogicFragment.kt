package com.lytton.androidcourse.base.fragment

import com.lytton.androidcourse.base.activity.BaseLogicActivity
import com.lytton.androidcourse.base.fragment.BaseCommonFragment

/**
 * @program: News
 * @description:
 * @author: LyttonYang
 * @create: 2024-09-17 14:50
 */
abstract class BaseLogicFragment : BaseCommonFragment(){
    protected val hostActivity: BaseLogicActivity
        get() = requireActivity() as BaseLogicActivity
}