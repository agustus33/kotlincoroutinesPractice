package com.example.kotlincoroutines

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import com.example.kotlincoroutines.ui.theme.KotlincoroutinesTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlincoroutinesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
        lifecycleScope.launch(Dispatchers.Main){
            doSomething()
            doSuspendTask()
            doNormalTask()
        }
    }

    private fun doSomething() {
        Log.e("doSomething-Current Thread",Thread.currentThread().name)
        Log.e("doSomething",Dispatchers.Main.isActive.toString())
        println(Dispatchers.Main.isActive)

    }

    private suspend fun doSuspendTask(){
        withContext(Dispatchers.Default){
            Log.e("doSuspendTask-Current Thread before delay",Thread.currentThread().name)
            delay(2000)
            Log.e("doSuspendTask-Current Thread after delay",Thread.currentThread().name)
        }

    }

    private fun doNormalTask(){
        Log.e("doNormalTask-Current Thread",Thread.currentThread().name)
        Log.e("doNormalTask",Dispatchers.Main.isActive.toString())

    }
    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Welcome to Kotlin Coroutines Practice",
            modifier = modifier
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        KotlincoroutinesTheme {
            Greeting("Kotlin Coroutines")
        }
    }
}


