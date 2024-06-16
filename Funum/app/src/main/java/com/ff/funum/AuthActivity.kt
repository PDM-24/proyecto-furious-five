package com.ff.funum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.ff.funum.ui.screens.AuthViewModel
import com.ff.funum.ui.screens.RegisterScreen
import com.ff.funum.ui.theme.FunumTheme

class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: AuthViewModel by viewModels()

        setContent {
            FunumTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    RegisterScreen(viewModel = viewModel)
                }
            }
        }
    }
}