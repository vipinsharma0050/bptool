package com.example.bptool

import com.google.gson.annotations.SerializedName

class Encryption_alogrithm {

 @SerializedName("RSA")
 var RSA : List<RSA>?=null
 @SerializedName("DES")
 var DES : List<DES>?=null
 @SerializedName("TDES")
 var TDES :List<TDES>?=null
@SerializedName("AES")
var AES :List<AES>?=null

}

data class RSA(var key_size:List<String>?=null,)
data class DES(var padding_type:List<String>?=null)
data class TDES(var mode:List<String>?=null,var padding_type:List<String>?=null)
data class AES(var title: String?,var bit_size :List<String>?=null,var mode: List<String>?)

data class ListTitle(var title :String ?=null,
                     var combined : List<CombinedTitle>,
                     var mode: List<String>?,
                     var bitSize : List<String>?,
                     var modeHash: Boolean = false)

data class CombinedTitle(var title :String ?=null,
                         var combined : List<String>)


