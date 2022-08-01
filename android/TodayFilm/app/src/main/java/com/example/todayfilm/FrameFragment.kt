package com.example.todayfilm

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todayfilm.databinding.FragmentFrameBinding
import java.io.File

class FrameFragment : Fragment() {
    lateinit var binding: FragmentFrameBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFrameBinding.inflate(inflater, container, false)

        // 부모 프래그먼트로부터 날짜 받기
        val bundle = arguments
        val date = bundle!!.getString("date").toString()

        // 같은 날짜의 jpg 파일 탐색
        val bitmaps = searchFiles(date)

        if (bitmaps.size > 0) {
            // 데이터 바인딩으로 처리....
        }

        return binding.root
    }

    private fun searchFiles(date: String): ArrayList<Bitmap> {
        val bitmaps = arrayListOf<Bitmap>()

        File(activity?.getExternalFilesDir(null).toString()).walk().forEach {
            if (it.name.startsWith(date) && it.extension == "jpg" && bitmaps.size < 4) {
                bitmaps.add(BitmapFactory.decodeFile(it.absolutePath))
            }
            if (bitmaps.size >= 4) {
                return bitmaps
            }
        }

        return bitmaps
    }
}