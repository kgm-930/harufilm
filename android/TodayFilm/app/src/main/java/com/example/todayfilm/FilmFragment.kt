package com.example.todayfilm

import CustomDialogFragment
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.camera.core.impl.utils.ContextUtil.getApplicationContext
import androidx.core.os.bundleOf
import com.example.todayfilm.databinding.FragmentFilmBinding
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.fragment_film.*

class FilmFragment : Fragment(), View.OnClickListener,PopupMenu.OnMenuItemClickListener{
    lateinit var binding: FragmentFilmBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childFragmentManager.beginTransaction().add(R.id.fragment_content_film, FrameFragment()).commit()
        setOnClickListener()
    }

    private fun setOnClickListener() {

        binding.menu.setOnClickListener(this)
    }



    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.menu -> { showPopup(binding.menu)




            }
        }
    }
    private fun showPopup(v: View) {

        val popup = PopupMenu(context,v,  Gravity.TOP, 0, R.style.popup) // PopupMenu 객체 선언
        popup.menuInflater.inflate(R.menu.popup, popup.menu) // 메뉴 레이아웃 inflate
        popup.setOnMenuItemClickListener(this)
        popup.show() // 팝업 보여주기
    }

    override fun onMenuItemClick(p0: MenuItem?): Boolean {
        val builder = AlertDialog.Builder(activity)
        val dialog = CustomDialogFragment()
        val normaldialog = NormalDialogFragment()
        val duration = Toast.LENGTH_SHORT



        when (p0?.itemId) { // 메뉴 아이템에 따라 동작 다르게 하기
            R.id.delete -> {
                val btn= arrayOf("네","아니오")
                normaldialog.arguments = bundleOf(

                    "bodyContext" to "삭제하면 복구되지 않습니다",
                    "bodyTitle" to "정말 삭제하시겠습니까?",
                    "btnData" to btn
                )
                normaldialog.show((activity as MainActivity).supportFragmentManager, "CustomDialog")

                normaldialog.setButtonClickListener(object :
                    NormalDialogFragment.OnButtonClickListener {
                    override fun onButton1Clicked() {
                        //취소버튼을 눌렀을 때 처리할 곳
                        Toast.makeText(context, "삭제가 완료되었습니다.", duration).show()
                    }

                    override fun onButton2Clicked() {
                        //확인버튼을 눌렀을 때 처리할 곳
                        Toast.makeText(context, "삭제가 취소되었습니다.", duration).show()
                    }
                })


            }

            R.id.share -> {

                dialog.show((activity as MainActivity).supportFragmentManager, "CustomDialog")

            }

            R.id.save -> {
                val btn= arrayOf("네","아니오")
                normaldialog.arguments = bundleOf(

                    "bodyTitle" to "정말 저장하시겠습니까?",
                    "bodyContext" to "프레임이 갤러리에 저장됩니다.",
                    "btnData" to btn
                )

                normaldialog.setButtonClickListener(object :
                    NormalDialogFragment.OnButtonClickListener {
                    override fun onButton1Clicked() {
                        //취소버튼을 눌렀을 때 처리할 곳
                        Log.d("눌렸냐?","눌렸냐?")

                        Toast.makeText(context, "저장이 완료되었습니다.", duration).show()


                    }

                    override fun onButton2Clicked() {
                        //확인버튼을 눌렀을 때 처리할 곳
                        Toast.makeText(context, "저장이 취소되었습니다.", duration).show()



                    }
                })
                normaldialog.show((activity as MainActivity).supportFragmentManager, "CustomDialog")


            }






            }


            return p0 != null
    }




}