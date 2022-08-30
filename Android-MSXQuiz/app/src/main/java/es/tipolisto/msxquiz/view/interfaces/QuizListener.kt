package es.tipolisto.msxquiz.view.interfaces

interface QuizListener {
    fun playIntro()
    fun playInGame()
    fun playGameOver()
    fun playButton(value:String)
    fun playTikTok()
    fun stopMusic()
    fun stopTikTok()
    fun setPreferenceMusic(value:Boolean)
    fun setPreferenceTheme(value:Boolean)

}