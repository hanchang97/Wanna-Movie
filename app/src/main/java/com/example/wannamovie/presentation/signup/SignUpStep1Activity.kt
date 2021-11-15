package com.example.wannamovie.presentation.signup

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.wannamovie.R
import com.example.wannamovie.databinding.ActivitySignup1Binding
import com.example.wannamovie.databinding.ActivitySignup2Binding
import com.example.wannamovie.presentation.login.LoginViewModel
import org.koin.android.viewmodel.ext.android.viewModel

// 성별, 직업, 지역(시, 구, 동) 입력받기
class SignUpStep1Activity : AppCompatActivity() {

    private lateinit var binding: ActivitySignup1Binding
    private val viewModel : SignUpStep1ViewModel by viewModel()

    private var Gender = ""
    private var Address = ""
    private var Profession = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignup1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // toolbar 설정!!
        setSupportActionBar(binding.toolbar)  // 액션바로 xml에 만들어준 toolbar를 사용한다
        supportActionBar!!.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_icon_back)
        supportActionBar!!.setDisplayShowTitleEnabled(false) // 기본제목 없애기
        //supportActionBar!!.setTitle("회원가입")

        //var professionList = listOf("초중고생, 대학생, 직장인, 기타")   // 직업 선택 spinner
        var professionData = resources.getStringArray(R.array.profession_dataList)
        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, professionData)
        binding.spinnerProfession.adapter = adapter

        binding.spinnerProfession.setSelection(0)
        binding.spinnerProfession.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                    Log.e("AppTest", "position : ${position}")
                if(position != 0){
                    Profession = professionData[position]
                    Log.e("AppTest", "선택한 직업 : ${Profession}")
                    viewModel.selectProfession(true)
                }
                else{
                    Profession=""
                    viewModel.selectProfession(false)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

        ///////////////////
        // 성별 선택
        binding.cvMan.setOnClickListener {
            binding.cvMan.strokeColor = resources.getColor(R.color.btn_active)
            binding.tvMan.setTextColor(resources.getColor(R.color.btn_active))
            binding.cvWoman.strokeColor = resources.getColor(R.color.cv_unselected)
            binding.tvWoman.setTextColor(resources.getColor(R.color.tv_unselected))
            binding.cvMan.invalidate()
            binding.cvWoman.invalidate()
            Gender = "M"
            viewModel.selectGender()
        }
        binding.cvWoman.setOnClickListener {
            binding.cvWoman.strokeColor = resources.getColor(R.color.btn_active)
            binding.tvWoman.setTextColor(resources.getColor(R.color.btn_active))
            binding.cvMan.strokeColor = resources.getColor(R.color.cv_unselected)
            binding.tvMan.setTextColor(resources.getColor(R.color.tv_unselected))
            binding.cvMan.invalidate()
            binding.cvWoman.invalidate()
            Gender = "W"
            viewModel.selectGender()
        }

        //////////////////////////////
        // 지역 입력
        binding.etAddress.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var addressText = binding.etAddress.text

                if(addressText!!.length > 0){
                    viewModel.fillAddress(true)
                    Address = binding.etAddress.text.toString()
                }
                else{
                    viewModel.fillAddress(false)
                    Address = binding.etAddress.text.toString()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        /////////////////////////////////
        // 다음 버튼 활성화
        viewModel.btnActive.observe(this, {
                if(it){
                    binding.btnNextActive.visibility = View.VISIBLE
                    binding.btnNextInactive.visibility = View.INVISIBLE
                }
                else{
                    binding.btnNextActive.visibility = View.INVISIBLE
                    binding.btnNextInactive.visibility = View.VISIBLE
                }
            }
        )

        ///////////////////////////////
        binding.btnNextActive.setOnClickListener {
            Log.e("AppTest","go to signup2 activity")

            val intent = Intent(this, SignupStep2Activity::class.java)
            intent.putExtra("Gender", Gender)
            intent.putExtra("Address", Address)
            intent.putExtra("Profession", Profession)
            startActivity(intent)
        }


    }

    // toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                // 뒤로가기 버튼 누를 시
                Log.e("AppTest", "toolbar back btn clicked")
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}