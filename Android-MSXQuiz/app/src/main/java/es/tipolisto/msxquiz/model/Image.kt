package es.tipolisto.msxquiz.model

data class Image (val id:Int,
                  val name:String,
                  val path:String,
                  val date:String,
                  val user:Int,
                  val idQuiz: Int)