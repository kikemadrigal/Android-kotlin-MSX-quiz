package es.tipolisto.msxquiz

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.util.Log
import es.tipolisto.msxquiz.util.Constants

class MediaPlayerClient(context: Context) {
    private val context:Context = context
    private var mediaPlayer:MediaPlayer = MediaPlayer()
    private var mediaPlayerButton:MediaPlayer = MediaPlayer()
    private var mediaPlayerTikTok:MediaPlayer = MediaPlayer()

    init {
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
        mediaPlayerButton.setAudioStreamType(AudioManager.STREAM_MUSIC)
        mediaPlayerTikTok.setAudioStreamType(AudioManager.STREAM_MUSIC)
    }
    fun playIntro(){
        // if(!preferenceManager.musicOnOff) return
        if(!mediaPlayer.isPlaying) {
            mediaPlayer = MediaPlayer.create(context, R.raw.intro)
            //Log.d("Mensaje", "Repdrouciendo intro ")
            mediaPlayer.start()
            mediaPlayer.setLooping(true);
        }
    }
    fun playIngame(){
        //Log.d("Mensaje", "Repdrouciendo ingame ")
        if(mediaPlayer.isPlaying) mediaPlayer.stop()
        mediaPlayer=MediaPlayer.create(context,R.raw.ingame)
        mediaPlayer.start()
        mediaPlayer.setLooping(true);
    }

    fun playButton(sound:String){
        if(mediaPlayerButton!=null){
            mediaPlayerButton.stop()
            mediaPlayerButton.release()
        }
        if(sound.equals("click")) mediaPlayerButton= MediaPlayer.create(context,R.raw.clickbutton)
        if(sound.equals("success")) mediaPlayerButton= MediaPlayer.create(context,R.raw.success)
        if(sound.equals("failure")) mediaPlayerButton= MediaPlayer.create(context,R.raw.failure)
        mediaPlayerButton.start()
    }
    fun playTikTok(){
        if(mediaPlayerTikTok!=null){
            mediaPlayerTikTok.stop()
            mediaPlayerTikTok.release()
        }
        mediaPlayerTikTok= MediaPlayer.create(context,R.raw.tiktok)
        mediaPlayerTikTok.start()
    }
    fun stopTikTok(){
        //Log.d(Constants.TAG, "Llamado on stop en tiktok")
        if(mediaPlayerTikTok!=null){
            if(mediaPlayerTikTok.isPlaying){
                mediaPlayerTikTok.stop()
                mediaPlayerTikTok.reset()
            }
        }
    }
    fun stop(){
        //Log.d(Constants.TAG, "Llamado on stop")
        if(mediaPlayer!=null){
            if(mediaPlayer.isPlaying){
                mediaPlayer.stop()
                mediaPlayer.reset()
                //mediaPlayer.release()
            }
        }
    }

}