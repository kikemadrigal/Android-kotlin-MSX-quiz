package es.tipolisto.msxquiz.model

data class Quiz (val id:Int,
                 val question:String,
                 val answer1:String,
                 val answer2:String,
                 val answer3:String,
                 val correctAnswer:Int,
                 val image:Int,
                 val nameUser:String) {
}