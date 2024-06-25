package com.ff.funum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ff.funum.ui.screens.AuthViewModel
import com.ff.funum.ui.screens.LoginScreen
import com.ff.funum.ui.screens.RegisterScreen
import com.ff.funum.ui.theme.FunumTheme
import com.ff.funum.utils.changeActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: AuthViewModel by viewModels()

        lifecycleScope.launch (Dispatchers.IO){
            viewModel.dataStore.getRememberMe().collect {
                    response ->
                if (response == true){
                    changeActivity(this@AuthActivity, MainActivity::class.java)
                }
            }
        }

        setContent {
            FunumTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    AuthApp(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun AuthApp(viewModel: AuthViewModel) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "login") {
        composable("register") { RegisterScreen(viewModel, navController) }
        composable("login") { LoginScreen(viewModel, navController) }
    }
}