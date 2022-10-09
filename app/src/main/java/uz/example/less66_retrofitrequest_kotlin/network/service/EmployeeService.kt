package uz.example.less66_retrofitrequest_kotlin.network.service

import retrofit2.Call
import retrofit2.http.*
import uz.example.less66_retrofitrequest_kotlin.model.BaseResponse
import uz.example.less66_retrofitrequest_kotlin.model.EmplResp
import uz.example.less66_retrofitrequest_kotlin.model.Employee


interface EmployeeService {
    @Headers(
        "Content-type:application/json"
    )

    @GET("employees")
    fun listEmployee(): Call<BaseResponse<ArrayList<EmplResp>>>

    @GET("employee/{id}")
    fun singleEmployee(@Path("id") id: Int): Call<BaseResponse<EmplResp>>

    @POST("create")
    fun createEmployee(@Body employee: Employee): Call<BaseResponse<EmplResp>>

    @PUT("update/{id}")
    fun updateEmployee(@Path("id") id: Int, @Body employee: Employee): Call<BaseResponse<EmplResp>>

    @DELETE("delete/{id}")
    fun deleteEmployee(@Path("id") id: Int): Call<BaseResponse<String>>

    /*@Multipart
    @POST("v1/images/upload")
    fun uploadImage(@Part file: MultipartBody.Part, @Part("sub_id") sub_id: RequestBody): Call<ResponseBody>*/
}
