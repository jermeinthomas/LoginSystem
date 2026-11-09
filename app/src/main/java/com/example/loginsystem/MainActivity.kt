package com.example.loginsystem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginsystem.ui.theme.LoginSystemTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            LoginSystemTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    MainScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

fun loginUser(email: String, password: String): String {

    if (email.isBlank() || password.isBlank()) {
        return "Please fill all fields"
    }

    if (email == "admin@gmail.com" && password == "1234") {
        return "Login successful"
    }

    return "Invalid credentials"
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {

    var emailInput by remember { mutableStateOf("") }
    var passwordInput by remember { mutableStateOf("") }
    var loginMessage by remember { mutableStateOf("") }
    var forgotPassword by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Welcome Back",
            fontSize = 28.sp
        )

        Text(
            text = "Sign in to continue",
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = emailInput,
            onValueChange = { emailInput = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        TextField(
            value = passwordInput,
            onValueChange = { passwordInput = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                loginMessage = loginUser(emailInput, passwordInput)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }

        TextButton(
            onClick = {
                forgotPassword = true
            }
        ) {
            Text("Forgot Password")
        }

        if (forgotPassword) {
            Text("Enter your email to reset your password")
        }

        // Only show the message after the user attempts to log in
        if (loginMessage.isNotEmpty()) {

            Spacer(modifier = Modifier.height(24.dp))

            Text(loginMessage)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewScreen() {

    LoginSystemTheme {
        MainScreen()
    }
}
