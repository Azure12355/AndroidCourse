package com.lytton.androidcourse.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

/**
 * @program: News
 * @description: 基础ViewModel
 * @author: LyttonYang
 * @create: 2024-09-17 16:44
 */
abstract class BaseViewModel : ViewModel() {
    
    //数据传输管道
    private val _pipeline = MutableSharedFlow<Map<String, Any?>>()
    val pipeline: Flow<Map<String, Any?>> = _pipeline

    /**
     * 通知观察者
     * @param dataKey: 数据关键字
     * @param data: 数据
     */
    fun notifyObserver(dataKey: String, data: Any?) {
        viewModelScope.launch {
            _pipeline.emit(mutableMapOf(dataKey to data))
        }
    }
    
}