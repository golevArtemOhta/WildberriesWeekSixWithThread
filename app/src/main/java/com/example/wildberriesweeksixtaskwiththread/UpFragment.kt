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
import com.example.wildberriesweeksixtaskwiththread.databinding.FragmentUpBinding
import java.util.*


class UpFragment : Fragment() {
    lateinit var binding: FragmentUpBinding
    lateinit var fragmentViewModel: FragmentViewModel
    var randomValue: Int? = null
    var fullValue: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentViewModel = ViewModelProvider(requireActivity()).get(FragmentViewModel::class.java)
        binding = FragmentUpBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


    @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
    override fun onStart() {
        super.onStart()
        fragmentViewModel.randomValue.observe(activity as LifecycleOwner, Observer {
            randomValue = it
            if (randomValue == 10){
                binding.tvPi.text = "${3.14}"
            }
            else{
                binding.tvPi.text = "${binding.tvPi.text}$randomValue"
            }
        })
    }

    companion object {

        @JvmStatic
        fun newInstance() = UpFragment()
    }
}