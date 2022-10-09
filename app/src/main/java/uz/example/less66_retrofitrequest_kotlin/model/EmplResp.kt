package uz.example.less66_retrofitrequest_kotlin.model

import com.google.gson.annotations.SerializedName

data class EmplResp(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("employee_name")
    val employee_name: String? = null,
    @SerializedName("employee_salary")
    val employee_salary: Int = 0,
    @SerializedName("employee_age")
    val employee_age: Int = 0
)
