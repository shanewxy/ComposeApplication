package com.example.myapplication

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.children

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
            transverse(window.decorView, 1)
        }
    }

    private fun transverse(view: View, index: Int) {
        Log.d("debug", "第${index}层：" + view)
        if (view is ViewGroup) {
            view.children.forEach { transverse(it, index + 1) }
        }
    }

    @Composable
    fun OnboardingScreen(onContinueClicked: () -> Unit) {
        // TODO: This state should be hoisted
        var shouldShowOnboarding by remember { mutableStateOf(true) }

        Surface {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Welcome to the Basics Codelab!")
                Button(
                    modifier = Modifier.padding(vertical = 24.dp),
                    onClick = onContinueClicked
                ) {
                    Text("Continue")
                }
            }
        }
    }

    @Preview(
        showBackground = true,
        widthDp = 320,
        heightDp = 320,
        uiMode = Configuration.UI_MODE_NIGHT_YES
    )
    @Composable
    fun OnboardingPreview() {
        OnboardingScreen(onContinueClicked = {})
    }

    @Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
    @Composable
    fun MyApp() {

        var shouldShowOnboarding by remember { mutableStateOf(true) }

        if (shouldShowOnboarding) {
            OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
        } else {
            Greetings()
        }
    }


    @Composable
    private fun Greetings(names: List<String> = listOf("World", "Compose")) {
        Column(modifier = Modifier.padding(vertical = 4.dp)) {
            for (name in names) {
                Greeting(name = name)
            }
        }
    }

    @Composable
    private fun Greeting(name: String) {

        val expanded = remember { mutableStateOf(false) }

        val extraPadding = if (expanded.value) 48.dp else 0.dp

        Surface(
            color = MaterialTheme.colors.primary,
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 8.dp)
        ) {
            Row(modifier = Modifier.padding(24.dp)) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = extraPadding)
                ) {
                    Text(text = "Hello, ")
                    Text(text = name)
                }
                OutlinedButton(
                    onClick = { expanded.value = !expanded.value }
                ) {
                    Text(if (expanded.value) "Show less" else "Show more")
                }
            }
        }
    }
}
