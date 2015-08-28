package com.wechat;

/**
 * 音乐消息 
 * xidian.wq.autoreply.message.respond
 * @author  WQ
 * 2013-8-8 下午9:43:16
 */
public class MusicMessage extends RespBaseMessage{
	// 音乐  
    private Music Music;  
  
    public Music getMusic() {  
        return Music;  
    }  
  
    public void setMusic(Music music) {  
        Music = music;  
    }  
}
