//package com.example.ge72.castit
//
///**
// * Created by GE72 on 13.08.2017.
// */
//
//
//import retrofit2.Call
//import retrofit2.http.POST
//import retrofit2.http.Query
//import ru.dvs.eshop.data.components.eshop.vendor.ItemInputResponse
//import ru.dvs.eshop.data.components.eshop.vendor.VendorInputResponse
//
//interface EshopApi {
//    @POST("eshop.get.vendor")
//    fun getVendor(@Query("id") id: Int? = 0): Call<VendorInputResponse>
//
//    @POST("eshop.get.item")
//    fun getItem(@Query("id") id: Int? = 0): Call<ItemInputResponse>
//}