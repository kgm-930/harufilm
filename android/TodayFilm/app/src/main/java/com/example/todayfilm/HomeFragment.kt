package com.example.todayfilm

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.todayfilm.databinding.FragmentHomeBinding
import java.time.LocalDate

class HomeFragment : Fragment(), View.OnClickListener {
    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 오늘 날짜 넘기기
        val date = LocalDate.now().toString()
        val bundle = Bundle().apply { putString("date", date) }
        val frameFragment = FrameFragment().apply { arguments = bundle }

        childFragmentManager.beginTransaction().add(R.id.fragment_content_home, frameFragment).commit()
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.fragmentContentHome.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.fragment_content_home -> {
            val intent = Intent(activity, CameraActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            }

            R.id.home_to_complete -> {
                // 사진 4장 미만인 경우

                // 이전 다이얼로그에서 예를 눌렀거나 사진 4장인 경우
            }
        }
    }
}