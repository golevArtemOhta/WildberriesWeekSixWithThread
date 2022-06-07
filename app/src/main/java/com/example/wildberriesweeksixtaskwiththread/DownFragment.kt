package com.example.wildberriesweeksixtaskwiththread

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.wildberriesweeksixtaskwiththread.databinding.FragmentDownBinding
import java.util.*
import kotlin.properties.Delegates


class DownFragment : Fragment() {
    lateinit var binding: FragmentDownBinding
    lateinit var fragmentViewModel: FragmentViewModel
    var countTimer by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentViewModel = ViewModelProvider(requireActivity()).get(FragmentViewModel::class.java)
        binding = FragmentDownBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentViewModel.start()

        binding.buttonPlay.setOnClickListener {
            fragmentViewModel.onPlay()
        }

        binding.buttonPause.setOnClickListener {
            fragmentViewModel.onPause()
        }

        binding.buttonReset.setOnClickListener {
            fragmentViewModel.onReset()
        }



    }

    @SuppressLint("NotifyDataSetChanged", "ResourceType")
    override fun onStart() {
        super.onStart()
        fragmentViewModel.count.observe(activity as LifecycleOwner, Observer {
            countTimer = it
            if (countTimer % 20 == 0 && countTimer != 0){
                val rnd = Random()
                val color: Int =
                    Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
                binding.fragmentDown.setBackgroundColor(color)
            }
            if (countTimer == 0){
                binding.fragmentDown.setBackgroundColor(Color.WHITE)
            }
            binding.tvTimer.text = countTimer.toString()
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = DownFragment()
    }
}