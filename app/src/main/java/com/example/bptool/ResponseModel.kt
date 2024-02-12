package com.example.bptool

import com.google.gson.annotations.SerializedName

class ResponseModel {

  @SerializedName("Encryption_alogrithm")
  var encrption : List<Encryption_alogrithm>?=null

   @SerializedName("Hash_algorith")
   var HashAlgo : List<Hash_algorith>?=null
}
