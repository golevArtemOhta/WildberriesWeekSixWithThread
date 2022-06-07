package com.example.wildberriesweeksixtaskwiththread

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

import androidx.lifecycle.LiveData
import kotlinx.coroutines.*
import java.lang.Runnable
import java.util.*
import java.util.concurrent.CyclicBarrier
import java.util.concurrent.TimeUnit


class FragmentViewModel: ViewModel() {

    val randomValue = MutableLiveData<Int?>()
    val count = MutableLiveData<Int>()
    private var job: Job? = null

    var i = 0
    var randomNumber: Int? = null

    val mHandlerOne = android.os.Handler()
    val mHandlerTwo = android.os.Handler()

    private val mPauseLock: Object
    private var mPaused: Boolean
    private val mFinished: Boolean

    init {
        mPauseLock = Object()
        mPaused = false
        mFinished = false
    }

    fun start() {


        val threadTwo = Thread {
            while (!mFinished) {
                randomNumber = addRandomNumber()
            //    mHandlerTwo.post(java.lang.Runnable {
                    randomValue.postValue(randomNumber)
              //  })
                synchronized(mPauseLock) {
                    while (mPaused) {
                        try {
                            mPauseLock.wait()
                        } catch (e: InterruptedException) {
                        }
                    }
                }
            }
        }

        val threadOne = Thread {

            while (!mFinished) {
                Thread.sleep(1000)
                i++
                mHandlerOne.post(java.lang.Runnable {
                    count.value = i
                })
                synchronized(mPauseLock) {
                    while (mPaused) {
                        try {
                            mPauseLock.wait()
                        } catch (e: InterruptedException) {
                        }
                    }
                }
            }
        }

        threadTwo.start()
        threadOne.start()

    }

    fun onPlay(){
        synchronized(mPauseLock) {
            mPaused = false
            mPauseLock.notifyAll()
        }
    }

    fun onPause() {
        synchronized(mPauseLock) { mPaused = true }
    }

    fun onReset() {
        onPause()
        randomNumber = 10
        randomValue.postValue(randomNumber)
        i = 0
        count.postValue(i)
    }

    private fun addRandomNumber(): Int?{
        return Random.nextInt(1, 9)
    }

    fun getSelected(): LiveData<Int?>? {
        return randomValue
    }

}


