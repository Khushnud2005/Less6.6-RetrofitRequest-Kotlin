package uz.example.less66_retrofitrequest_kotlin

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.example.less66_retrofitrequest_kotlin.model.BaseResponse
import uz.example.less66_retrofitrequest_kotlin.model.EmplResp
import uz.example.less66_retrofitrequest_kotlin.model.Employee
import uz.example.less66_retrofitrequest_kotlin.network.RetrofitHttp


class MainActivity : AppCompatActivity() {
    lateinit var tv_res: TextView
    lateinit var tv_message: TextView
    lateinit var et_get_id:EditText
    lateinit var et_create_name:EditText
    lateinit var et_create_salary:EditText
    lateinit var et_create_age:EditText
    lateinit var et_update_name:EditText
    lateinit var et_update_salary:EditText
    lateinit var et_update_age:EditText
    lateinit var et_update_id:EditText
    lateinit var et_delete_id:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }
    fun initViews(){
        val btn_list = findViewById<Button>(R.id.btn_list)
        val btn_get_one = findViewById<Button>(R.id.btn_get_one)
        val btn_create = findViewById<Button>(R.id.btn_create)
        val btn_update = findViewById<Button>(R.id.btn_update)
        val btn_delete = findViewById<Button>(R.id.btn_delete)
        et_get_id = findViewById<EditText>(R.id.et_get_id)
        et_create_name = findViewById<EditText>(R.id.et_create_name)
        et_create_salary = findViewById<EditText>(R.id.et_create_salary)
        et_create_age = findViewById<EditText>(R.id.et_create_age)
        et_update_name = findViewById<EditText>(R.id.et_update_name)
        et_update_salary = findViewById<EditText>(R.id.et_update_salary)
        et_update_age = findViewById<EditText>(R.id.et_update_age)
        et_update_id = findViewById<EditText>(R.id.et_update_id)
        et_delete_id = findViewById<EditText>(R.id.et_delete_id)
        tv_res = findViewById<TextView>(R.id.tv_res)
        tv_message = findViewById<TextView>(R.id.tv_message)

        btn_list.setOnClickListener(View.OnClickListener { getEmployeeList() })
        btn_get_one.setOnClickListener(View.OnClickListener {
            val id = et_get_id.text.toString().trim { it <= ' ' }
            if (!id.isEmpty()) {
                getOne(id.toInt())
            }
        })
        btn_create.setOnClickListener(View.OnClickListener {
            val name = et_create_name.text.toString().trim { it <= ' ' }
            val salary = et_create_salary.text.toString().trim { it <= ' ' }
            val age = et_create_age.text.toString().trim { it <= ' ' }

            if (!salary.isEmpty() && !age.isEmpty()) {
                val emp = Employee(name, salary.toInt(), age.toInt())
                createEmployee(emp)
            }
        })
        btn_update.setOnClickListener(View.OnClickListener {
            val id = et_update_id.text.toString().trim { it <= ' ' }
            val name = et_update_name.text.toString().trim { it <= ' ' }
            val salary = et_update_salary.text.toString().trim { it <= ' ' }
            val age = et_update_age.text.toString().trim { it <= ' ' }

            if (!id.isEmpty() && !salary.isEmpty() && !age.isEmpty()) {
                val emp = Employee(id.toInt(), name, salary.toInt(), age.toInt())
                updateEmployee(emp)
            }
        })
        btn_delete.setOnClickListener(View.OnClickListener {
            val id = et_delete_id.text.toString().trim { it <= ' ' }
            if (!id.isEmpty()) {
                deleteEmployee(id.toInt())
            }
        })
    }

    fun getEmployeeList(){

        RetrofitHttp.employeeService.listEmployee().enqueue(object : Callback<BaseResponse<ArrayList<EmplResp>>> {

            override fun onResponse(
                call: Call<BaseResponse<ArrayList<EmplResp>>>,
                response: Response<BaseResponse<ArrayList<EmplResp>>>
            ) {
                if (response.isSuccessful()) {
                    Log.d("@@@", response.body()!!.data.toString())
                    tv_res.text = response.body()!!.data.toString()
                    tv_message.text = response.body()!!.message
                }else{
                    tv_res.text = "Javob kelmadi. Boshqattan bosing!!!"
                }
            }

            override fun onFailure(call: Call<BaseResponse<ArrayList<EmplResp>>>, t: Throwable) {
                Log.d("@@@", t.message.toString())
                tv_message.text = t.message.toString()
            }
        })
    }
    fun getOne(id:Int){
        RetrofitHttp.employeeService.singleEmployee(id).enqueue(object : Callback<BaseResponse<EmplResp>> {

            override fun onResponse(
                call: Call<BaseResponse<EmplResp>>,
                response: Response<BaseResponse<EmplResp>>
            ) {
                if (response.isSuccessful()) {
                    if (response.body()!!.data !=null){
                        tv_res.text = response.body()!!.data.toString()
                    }else{
                        tv_res.text = "null : To View Fully Response Input ID Less then 25"
                    }

                    tv_message.text = response.body()!!.message
                    et_get_id.text.clear()
                }else{
                    tv_res.text = "Javob kelmadi. Boshqattan bosing!!!"
                }
                Log.d("@@@", response.body().toString())
            }

            override fun onFailure(call: Call<BaseResponse<EmplResp>>, t: Throwable) {
                Log.d("@@@", t.message.toString())
            }
        })
    }
    fun createEmployee(employee: Employee){
        RetrofitHttp.employeeService.createEmployee(employee).enqueue(object : Callback<BaseResponse<EmplResp>> {
            override fun onResponse(call: Call<BaseResponse<EmplResp>>, response: Response<BaseResponse<EmplResp>>) {

                if (response.isSuccessful){
                    tv_res.text = response.body()!!.data.toString()
                    tv_message.text = response.body()!!.message
                    et_create_name.text.clear()
                    et_create_salary.text.clear()
                    et_create_age.text.clear()
                }else{
                    tv_res.text = "Javob kelmadi. Boshqattan bosing!!!"
                }

            }

            override fun onFailure(call: Call<BaseResponse<EmplResp>>, t: Throwable) {
                Log.d("@@@", t.message.toString())
                tv_res.text = t.message.toString()
            }
        })
    }
    fun updateEmployee(employee: Employee){
        RetrofitHttp.employeeService.updateEmployee(employee.id,employee).enqueue(object : Callback<BaseResponse<EmplResp>> {
            override fun onResponse(call: Call<BaseResponse<EmplResp>>, response: Response<BaseResponse<EmplResp>>) {
                if (response.isSuccessful()){
                    Log.d("@@@", response.body()!!.data.toString())
                    Log.d("@@@", response.body()!!.message.toString())
                    tv_res.text = response.body()!!.data.toString()
                    tv_message.text = response.body()!!.message
                    et_update_name.text.clear()
                    et_update_salary.text.clear()
                    et_update_age.text.clear()
                    et_update_id.text.clear()
                }else{
                    tv_res.text = "Javob kelmadi. Boshqattan bosing!!!"
                }

            }

            override fun onFailure(call: Call<BaseResponse<EmplResp>>, t: Throwable) {
                Log.d("@@@", t.message.toString())
                tv_res.text = t.message.toString()
            }
        })
    }
    fun deleteEmployee(id: Int){
        RetrofitHttp.employeeService.deleteEmployee(id).enqueue(object : Callback<BaseResponse<String>> {
            override fun onResponse(call: Call<BaseResponse<String>>, response: Response<BaseResponse<String>>) {
                if (response.isSuccessful()){
                    Log.d("@@@", response.body()!!.data.toString())
                    Log.d("@@@", response.body()!!.message.toString())
                    tv_res.text = response.body()!!.data.toString()
                    tv_message.text = response.body()!!.message
                    et_delete_id.text.clear()
                }else{
                    tv_res.text = "Javob kelmadi. Boshqattan bosing!!!"
                }

            }

            override fun onFailure(call: Call<BaseResponse<String>>, t: Throwable) {
                Log.d("@@@", t.message.toString())
                tv_res.text = t.message.toString()
            }
        })
    }
}