package uz.example.less66_retrofitrequest_kotlin.model

class Employee {
    var id:Int = 0
    var employee_name:String
    var employee_salary:Int
    var employee_age:Int


    constructor(employee_name: String, employee_salary: Int, employee_age: Int) {
        this.employee_name = employee_name
        this.employee_salary = employee_salary
        this.employee_age = employee_age
    }
    constructor(id:Int,employee_name: String, employee_salary: Int, employee_age: Int) {
        this.id = id
        this.employee_name = employee_name
        this.employee_salary = employee_salary
        this.employee_age = employee_age
    }


}