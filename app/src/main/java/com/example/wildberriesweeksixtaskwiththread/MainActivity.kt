package com.example.wildberriesweeksixtaskwiththread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.wildberriesweeksixtaskwiththread.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            openFragment(R.id.fragmentUp, UpFragment.newInstance())
            openFragment(R.id.fragmentDown, DownFragment.newInstance())
        }


    }

    private fun openFragment(fragmentXml: Int, fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(fragmentXml, fragment)
            .commit()
    }
}