package com.lytton.androidcourse.components

import android.os.Bundle
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.lytton.androidcourse.base.activity.BaseViewModelActivity
import com.lytton.androidcourse.databinding.ActivityLoginBinding
import com.lytton.androidcourse.entity.UserCredentials
import java.io.File
import java.io.FileWriter
import java.io.IOException

/**
 * @program: AndroidCourse
 * @description:
 * @author: LyttonYang
 * @create: 2024-10-13 10:30
 */
class LoginActivity : BaseViewModelActivity<ActivityLoginBinding>() {
    private val credentialsFile: File by lazy {
        File(getExternalFilesDir(null), "user_credentials.json")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 加载保存的用户凭证
        loadUserCredentials()

        binding.login.setOnClickListener {
            handleLogin()
        }

        binding.remember.setOnCheckedChangeListener { _, isChecked ->
            if (!isChecked) {
                clearUserCredentials()
            }
        }
    }

    private fun loadUserCredentials() {
        if (credentialsFile.exists()) {
            try {
                val content = credentialsFile.readText()
                val gson = Gson()
                val userCredentials = gson.fromJson(content, UserCredentials::class.java)

                binding.account.setText(userCredentials.username)
                binding.password.setText(userCredentials.password)
                binding.remember.isChecked = userCredentials.remember
            } catch (e: JsonSyntaxException) {
                e.printStackTrace()
                Toast.makeText(this, "加载凭证失败", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun handleLogin() {
        val username = binding.account.text.toString()
        val password = binding.password.text.toString()
        val remember = binding.remember.isChecked

        if (remember) {
            saveUserCredentials(username, password, remember)
        }

        // TODO: 进行登录验证逻辑

        Toast.makeText(this, "登录成功！", Toast.LENGTH_SHORT).show()
    }

    private fun saveUserCredentials(username: String, password: String, remember: Boolean) {
        val userCredentials = UserCredentials(username, password, remember)
        val gson = Gson()
        val json = gson.toJson(userCredentials)

        try {
            FileWriter(credentialsFile).use { writer -> writer.write(json) }
            Toast.makeText(this, "凭证已保存", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "保存凭证失败", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearUserCredentials() {
        if (credentialsFile.exists()) {
            credentialsFile.delete()  // 删除凭证文件
            Toast.makeText(this, "凭证已清除", Toast.LENGTH_SHORT).show()
        }
    }
}