package com.example.todayfilm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todayfilm.data.SearchUser
import com.example.todayfilm.databinding.FragmentSearchBinding

class SearchFragment : Fragment(),View.OnClickListener {
    lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnclickListner()
    }

    override fun onResume() {
        super.onResume()
        // 검색 수행

        // 검색 결과로 받는 사용자 리스트를 userdatas 변수에 넣음
        val userdatas = ArrayList<SearchUser>()
        initUserRecycler(userdatas)
    }

    private fun initUserRecycler(userdatas: ArrayList<SearchUser>) {
        val userAdapter = SearchUserAdapter(requireActivity())
        binding.searchResultUser.adapter = userAdapter

        userAdapter.datas = userdatas
    }

    private fun setOnclickListner(){
        binding.goProfileList.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id){
            R.id.go_profile_list -> {

                (activity as MainActivity).changeFragment(2)
            }
        }
    }
}



