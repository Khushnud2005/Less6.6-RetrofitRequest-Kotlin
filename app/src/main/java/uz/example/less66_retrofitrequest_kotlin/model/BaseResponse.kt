package uz.example.less66_retrofitrequest_kotlin.model

data class BaseResponse<T>(
    var status:String,
    var data:T,
    var message:String
){}
