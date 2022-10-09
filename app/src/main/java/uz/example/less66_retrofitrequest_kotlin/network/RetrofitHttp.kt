package uz.example.less66_retrofitrequest_kotlin.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.example.less66_retrofitrequest_kotlin.network.service.EmployeeService


object RetrofitHttp {
    private val IS_TESTER = true
    val SERVER_DEVELOPMENT = "https://dummy.restapiexample.com/api/v1/"
    val SERVER_PRODUCTION = "https://dummy.restapiexample.com/api/v1/"

    val retrofit = Retrofit.Builder().baseUrl(server()).addConverterFactory(GsonConverterFactory.create()).build()

    fun server(): String {
        if (IS_TESTER) return SERVER_DEVELOPMENT
        return SERVER_PRODUCTION
    }

    val employeeService: EmployeeService = retrofit.create(EmployeeService::class.java)
}