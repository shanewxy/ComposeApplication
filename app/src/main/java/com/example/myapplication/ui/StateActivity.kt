package com.example.myapplication.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class StateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StateLess()
        }
    }

    @Preview
    @Composable
    fun StateLess() {
        println("---- clicked onCreated setContent ")
        Surface() {
            var count = 0 // 无状态
            Button(
                onClick = { count++ }, modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    text = "I have been clicked $count times",
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                println(effect = { println("---- text count = $count ") })
            }
            println(effect = { println("---- out count = $count ") })
        }

    }

    @Composable
    fun State() {
        println("---- clicked onCreated setContent ")
        Surface() {
            var count by remember { mutableStateOf(0) } // 使用remember
            Button(
                onClick = { count++ }, modifier = Modifier
                    .padding(count.dp)
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    text = "I have been clicked $count times",
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                println(effect = { println("---- text count = $count ") })
            }
            println(effect = { println("---- out count = $count ") })
        }

    }

}