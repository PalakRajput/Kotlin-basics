package async

import kotlinx.coroutines.*
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

//Coroutine builders -> launch, runBlocking and async.
//GlobalScope.launch -> will create the coroutine in global scope which will keep on running and it is highly discouraged and should not be used unless really required.
//In case of local scope, the coroutine is destroyed as soon as the scope is destroyed.

//similar to delay function, join and await are suspending function which can be used in coroutine class.

fun main1() = runBlocking { // this: CoroutineScope
    /*    launch { // launch a new coroutine and continue
    //        delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
    //        println("World!") // print after delay
             doWorld()
        }
        println("Hello") // main coroutine continues while a previous one is delayed*/
//    doWorld3()
//    println("Done")


    val job =
        launch { // launch a new coroutine and keep a reference to its Job but doesn't block the thread which runs it.
            delay(1000L)
            println("World!")
        }
    println("Hello")
    job.join() // wait until child coroutine completes
    println("Done")

    val defferedJob: Deferred<Int> = async {
        println("Inside async coroutine")
        println("${Thread.currentThread().name}")
        delay(1000)
        println("Completed execution")
        10
    }

    println("Outside async coroutine")
//    defferedJob.join() //Wait only for coroutine to complete, we can't use the return value
    val valFromCr = defferedJob.await() //Wait for coroutine to complete and we can use the return value.

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

fun main2() {
    val job: Job = GlobalScope.launch {
        println("Hello from launch function")
        delay(1000)
        println("Task executed")
    }
    //above coroutine won't be executed and the program will exit
//    job.join() -> can't be called from here as it can only be called within another coroutine or within coroutine scope.
    runBlocking {
        job.cancel() //Cancel coroutine if it is cooperative
        job.join() //If the cancellation fails then wait for the coroutine to complete.
        //Both the functionality is combined in cancelAndJoin()
        job.cancelAndJoin()
    }
}

//To cancel a coroutine it has to be cooperative. job.cancel()
//To make a coroutine cooperative, periodically invoke a suspending function that checks for cancellation.
//And those suspending function should belong to kotlinx.coroutines package(delay, yield, withContext, withTimeout) and not our custom suspending function
fun main3() = runBlocking {
    println("Main program starts")
    val job = launch {
        val range = 1..500
        for (i in range step 2) {
            println(i)
            yield() //this doesn't delay our coroutine and this is cancellable
//            delay(10)
        }
    }
    //Since we added delay function here, our coroutine will execute for below time and then it will be cancelled.
    delay(10)
//    job.cancel()
//    job.join()
    job.cancelAndJoin()
    println("Main program ends")
}

fun main4() = runBlocking {
    //Another way to cancel coroutine is to use isActive flag, initially it will be true but once cancel is called it will become false and we can use that condition to break out of the coroutine
    println("Main program starts")
    //to cancel using isActive flag we need to pass this Dispatcher as a param to launch function.
    val job = launch(Dispatchers.Default) {
        val range = 1..500
        for (i in range) {
            println(i)
            if (!isActive) {
                break
            }
            Thread.sleep(10)
        }
//        println("Am I executed after the flag becomes false?")
    }
    //Since we added delay function here, our coroutine will execute for below time and then it will be cancelled.
    delay(1)
    job.cancelAndJoin()
    println("Main program ends")
}

//When we cancel coroutine with the help of suspending functions like delay and yield then we will get CancellationException
fun main5() = runBlocking {
    println("Main program starts")
    val job = launch {
        val range = 1..500
        try {
            for (i in range step 2) {
                println(i)
                yield()
            }
        } catch (e: CancellationException) {
            println("Cancellation exception occurred: ${e.message}")
        } finally {
            println("finally")
            //If we are calling suspending function is finally block after the cancellation exception then it won't be executed because the coroutine was already cancelled.
            //Below suspending function will not be executed so to execute it, we should wrap our code within withContext and passing NonCancellable companion object
            withContext(NonCancellable) {
                delay(100)
                println("Finally block is executed as it ran in a separate coroutine")
            }
        }
    }
    //Since we added delay function here, our coroutine will execute for below time and then it will be cancelled.
    delay(10)
    job.cancel(CancellationException("Hey I passed the exception message from cancel method"))
    job.join()
//    job.cancelAndJoin()
    println("Main program ends")
}


//Timeouts: if our coroutine is taking longer than expected then we can use timeout functions such as withTimeout of withTimeoutOrNull. These two are coroutines builders just like launch

fun main6() = runBlocking {
    println("Main program starts")

    withTimeout(10) {
        try {
            for (i in 1..500) {
                println(i)
                delay(1)
            }
        } catch (ex: TimeoutCancellationException) {
            println("Timeout cancellation exception occurred as the coroutine didn't finished execution in given time ${ex.message}")
        }
    }

    println("Main program ends")
}

fun main() = runBlocking {
    println("Main program starts")

    //withTimeoutOrNull doesn't throw any exception but it returns some value
    //If the coroutine doesn't complete in specified time, then we will get null hence the datatype should be nullable
    val value: String? = withTimeoutOrNull(10) {

        for (i in 1..500) {
            println(i)
//            delay(1)
        }
        "Completed execution"
    }
    println(value)
    println("Main program ends")
}