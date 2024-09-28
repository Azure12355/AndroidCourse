package com.lytton.androidcourse.utils

import android.view.LayoutInflater
import java.lang.reflect.ParameterizedType

/**
 * @program: News
 * @description: 反射工具类
 * @author: LyttonYang
 * @create: 2024-09-05 22:21
 */
class ViewModelUtil {
    
    companion object {
        fun <VB> newViewBinding(layoutInflater: LayoutInflater, clazz: Class<*>): VB? {
            return try {
                //获取泛型参数对象
                val type: ParameterizedType = try {
                    clazz.genericSuperclass as ParameterizedType
                } catch (e: ClassCastException) {
                    clazz.superclass.genericSuperclass as ParameterizedType
                }

                val clazzVB = type.actualTypeArguments[0] as Class<*>

                //获取inflate方法
                val inflaterMethod = clazzVB.getMethod("inflate", LayoutInflater::class.java)
                inflaterMethod.invoke(clazzVB, layoutInflater) as VB
            } catch (e: Exception) {
                e.printStackTrace()
                throw RuntimeException(e)
            }
        }
    }
}