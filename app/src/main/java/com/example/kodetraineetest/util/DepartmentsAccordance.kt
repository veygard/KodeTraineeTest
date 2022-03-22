package com.example.kodetraineetest.util

import android.content.Context
import com.example.kodetraineetest.R
import javax.inject.Inject

data class DepartmentsAccordance @Inject constructor(
private val context: Context
){
    private val accordance:Map<String,String> = mapOf(
        "android" to context.getString(R.string.departments_accordance_android),
        "ios" to context.getString(R.string.departments_accordance_ior),
        "design" to context.getString(R.string.departments_accordance_design),
        "management" to context.getString(R.string.departments_accordance_management),
        "qa" to context.getString(R.string.departments_accordance_qa),
        "back_office" to context.getString(R.string.departments_accordance_back),
        "frontend" to context.getString(R.string.departments_accordance_front),
        "hr" to context.getString(R.string.departments_accordance_hr),
        "pr" to context.getString(R.string.departments_accordance_pr),
        "backend" to context.getString(R.string.departments_accordance_backend),
        "support" to context.getString(R.string.departments_accordance_supp),
        "analytics" to context.getString(R.string.departments_accordance_analytics),
    )
    fun getAccordanceName(dep:String):String{
        return accordance[dep] ?: dep
    }
    fun getOriginalName(acc:String):String{
        return accordance.filter { acc == it.value }.keys.firstOrNull() ?: acc
    }
}