package com.dawnstrive.pixallery.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.dawnstrive.pixallery.MainActivity
import com.dawnstrive.pixallery.R
import com.dawnstrive.pixallery.databinding.ActivitySplashBinding
import com.dawnstrive.pixallery.ui.categories.CategoriesFragmentViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    private lateinit var bind: ActivitySplashBinding

    private val vm: CategoriesFragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(bind.root)

        CoroutineScope(Main).launch {

            val intent = Intent(this@SplashActivity, MainActivity::class.java)

            delay(500)

            startActivity(intent)
            finish()
        }
    }
}