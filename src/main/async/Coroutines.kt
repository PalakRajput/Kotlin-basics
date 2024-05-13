package async

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

/**
 * runBlocking and coroutineScope builders may look similar because they both wait for their body
 * and all its children to complete.
 * The main difference is that the runBlocking method blocks the current thread for waiting,
 * while coroutineScope just suspends, releasing the underlying thread for other usages.
 * Because of that difference, runBlocking is a regular function and coroutineScope is a suspending function.
 */
//launch is a coroutine builder. It launches a new coroutine with the rest of the code which works independently
//delay -> is a special suspending function which suspend the coroutine for a while
//runBlocking -> is a coroutine builder which bridges the gap b/w normal main function and the code inside launch
//runBlocking means the threads that runs it gets blocked for the duration of the call until all coroutines inside it complete their execution
//Coroutines doesn't map to native OS thread and hence are lightweight and faster than threads

fun main() = runBlocking { // this: CoroutineScope
    /*    launch { // launch a new coroutine and continue
    //        delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
    //        println("World!") // print after delay
             doWorld()
        }
        println("Hello") // main coroutine continues while a previous one is delayed*/
//    doWorld3()
//    println("Done")

    val job = launch { // launch a new coroutine and keep a reference to its Job
        delay(1000L)
        println("World!")
    }
    println("Hello")
    job.join() // wait until child coroutine completes
    println("Done")

    // create 100_000 coroutines and print "."
//    repeat(100_000) {
//        launch { print(".") }
//    }

    // create 100_000 threads and print "."
//    repeat(100_000) {
//        thread { print(".") }
//    }
}

// this is your first suspending function
suspend fun doWorld() {
    delay(1000L)
    println("World!")
}

suspend fun doWorld2() = coroutineScope {  // this: CoroutineScope
    launch {
        delay(1000L)
        println("World!")
    }
    println("Hello")
}

//A coroutine scope builder can be used inside any suspending function to perform multiple concurrent operations
//both launch block executes concurrently
suspend fun doWorld3() = coroutineScope { // this: CoroutineScope
    launch {
        delay(2000L)
        println("World 2")
    }
    launch {
        delay(1000L)
        println("World 1")
    }
    println("Hello")
}

