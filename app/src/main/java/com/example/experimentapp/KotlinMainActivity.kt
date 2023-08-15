package com.example.experimentapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.internal.ChannelFlow
import okhttp3.internal.concurrent.Task

class KotlinMainActivity : AppCompatActivity() {

    private lateinit var buttonOne: AppCompatButton
    private lateinit var buttonTwo: AppCompatButton
    private var stateFlowExample = MutableStateFlow<Int>(0)
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    val handler = CoroutineExceptionHandler { context, exception ->
        println("Caught $exception")
    }
    private val scope = CoroutineScope(Dispatchers.IO)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val a = listOf(19,2,30,15).sortedDescending()
        val b = listOf("A",8,9,10)

        val c = a + b
        println(c)




        buttonOne = findViewById(R.id.button_one)
        buttonTwo = findViewById(R.id.button_two)
        buttonOne.setOnClickListener {
            /* val job = SupervisorJob()
             try {
                 scope.launch() {


                     (0..20).forEach {
                         delay(1000)
                         Log.d("Farman", "Value $it")
                         if (it == 5) {
                             throw Error("Test Exception")
                         }
                     }


                 }
             } catch (e: Exception) {
                 e.printStackTrace()
             }

             scope.launch(job) {
                 (21..40).forEach {
                     delay(1000)
                     Log.d("Farman", "Value $it")
                 }
             }*/
            /*runBlocking {
                Log.d("Farman","All async Start")
                val task = listOf(
                    async { delay(5000) },
                    async { delay(5000) }
                )
                task.awaitAll()
                Log.d("Farman","All async complete")
            }*/
            /*scope.launch {
                flowTest().combine(secondFlow()){f1,f2->
                    Log.d("Farman", "Collected: $f1 : $f2")
                }.collect()
            }*/
            scope.launch {
                merge(flowTest(),secondFlow()).collect{
                    Log.d("Farman", "Collected: $it")
                }
            }



        }


        /* scope.launch(SupervisorJob()) {
             withContext(NonCancellable)
             {
                 (21..40).forEach {
                     delay(1000)
                     Log.d("Farman", "Value $it")
                     if (it == 5) {
                         throw java.lang.RuntimeException("Test Exception")
                     }
                 }
             }
         }*/
        // CoroutineScope(Dispatchers.Main).launch {
        // repeatOnLifecycle(Lifecycle.State.STARTED) {
        /* flowTest().collectLatest {
             Log.d("Farman", "Collecting: $it")
             delay(3000)
         }*/
        /* flowTest().zip(secondFlow()) { firstFlowValue, SecondFlowValue ->
             Log.d("Farman", "Collecting zip : $firstFlowValue : $SecondFlowValue")
         }.collect()*/
        // }
        /*merge(flowTest(),secondFlow()).collect(){
            Log.d("Farman", "Collected: $it")
        }*/

        /*  combine(flowTest(),secondFlow(),thirddFlow(),thirddFlow(),thirddFlow()){f1,f2,f3,f4,f5->
              Log.d("Farman", "Collected: $f1 : $f2 : $f3")
          }.collect()*/
        //}

        buttonTwo.setOnClickListener {
            //  job.cancel()
        }

        // stateFlowExample()

    }


    private fun stateFlowExample() {
        lifecycleScope.launch {
            (0..20).forEach {
                delay(1000)
                stateFlowExample.value = it
            }
        }
    }


    private fun secondFlow() = flow<Int> {
        (20..35).forEach {
            delay(1000)
            emit(it)
            Log.d("Farman", "Second Emmiting: $it")
        }
        /*   var count = 20
           emit(count)*/
    }

    private fun thirddFlow() = flow<Int> {
        (40..55).forEach {
            delay(1000)
            emit(it)
            Log.d("Farman", "Third Emmiting: $it")
        }
        /*   var count = 20
           emit(count)*/
    }

    private fun flowTest() = flow<Int> {
        /*var count = 1
           emit(count)
           delay(1000)
           count+=1
           emit(count)
           delay(1000)
           count+=1
           emit(count)
           delay(1000)
           count+=1
           emit(count)
           delay(1000)
           count+=1
           emit(count)
           delay(1000)
           count+=1
           emit(count)
           delay(1000)
           count+=1
           emit(count)*/
        (1..5).forEach {
            delay(500)
            emit(it)
            Log.d("Farman", "Emmiting: $it")
        }

    }


    private val channelFlowExample = channelFlow {
        (1..15).forEach {
            delay(400)
            send(it)
            Log.d("Farman", "Emiiting: $it")
        }
    }
}