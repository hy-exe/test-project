package cn.pattern.demo.pattern;

/**
 * 适配器模式
 * 
 * 意图：将一个类的接口转换成客户希望的另外一个接口。适配器模式使得原本由于接口不兼容而不能一起工作的那些类可以一起工作。
 * 如何解决：继承或依赖
 * 主要解决：主要解决在软件系统中，常常要将一些"现存的对象"放到新的环境中，而新环境要求的接口是现对象不能满足的。
 * 
 * 适配器继承或依赖已有的对象，实现想要的目标接口。
 * 
 * 应用实例： 
 *  1、美国电器 110V，中国 220V，就要有一个适配器将 110V 转化为 220V。
 *  2、JAVA JDK 1.1 提供了 Enumeration 接口，而在 1.2 中提供了 Iterator 接口，想要使用 1.2 的 JDK，则要将以前系统的 Enumeration 接口转化为 Iterator 接口，这时就需要适配器模式。 
 *  3、在 LINUX 上运行 WINDOWS 程序。 
 *  4、JAVA 中的 jdbc。
 * 
 * @author Administrator
 *
 */
public interface AdapterPattern {
	
}
/**
 * 
 * 我们还有另一个接口 AdvancedMediaPlayer 和实现了 AdvancedMediaPlayer 接口的实体类。
 * 该类可以播放 vlc 和 mp4 格式的文件。
 * 我们想要让 AudioPlayer 播放其他格式的音频文件。为了实现这个功能，
 * 我们需要创建一个实现了 MediaPlayer 接口的适配器类 MediaAdapter，并使用 AdvancedMediaPlayer 对象来播放所需的格式。
 * AudioPlayer 使用适配器类 MediaAdapter 传递所需的音频类型，不需要知道能播放所需格式音频的实际类。
 * AdapterPatternDemo，我们的演示类使用 AudioPlayer 类来播放各种格式。
 * @author Administrator
 *
 */
interface MediaPlayer  {   //一个默认只能播放mp3的接口
	public void play(String audioType, String fileName);
}
interface AdvancedMediaPlayer {  //可以播放  vlc 和   mp4
	public void playVlc(String fileName);
	public void playMp4(String fileName);
}
class VlcPlayer implements AdvancedMediaPlayer{  // VlcPlayer
	   @Override
	   public void playVlc(String fileName) {
	      System.out.println("Playing vlc file. Name: "+ fileName);		
	   }

	   @Override
	   public void playMp4(String fileName) {
	      //什么也不做
	   }
}
class Mp4Player implements AdvancedMediaPlayer{  // Mp4Player
	   @Override
	   public void playVlc(String fileName) {
	      //什么也不做
	   }

	   @Override
	   public void playMp4(String fileName) {
	      System.out.println("Playing mp4 file. Name: "+ fileName);		
	   }
}
class MediaAdapter implements MediaPlayer {  //MediaAdapter 适配器类

	   AdvancedMediaPlayer advancedMusicPlayer;

	   public MediaAdapter(String audioType){
	      if(audioType.equalsIgnoreCase("vlc") ){
	         advancedMusicPlayer = new VlcPlayer();			
	      } else if (audioType.equalsIgnoreCase("mp4")){
	         advancedMusicPlayer = new Mp4Player();
	      }	
	   }

	   @Override
	   public void play(String audioType, String fileName) {
	      if(audioType.equalsIgnoreCase("vlc")){
	         advancedMusicPlayer.playVlc(fileName);
	      }else if(audioType.equalsIgnoreCase("mp4")){
	         advancedMusicPlayer.playMp4(fileName);
	      }
	   }
}
class AudioPlayer implements MediaPlayer {  //播放器类，使用适配器播放别的文件     ？？？？？？
	   MediaAdapter mediaAdapter; 

	   @Override
	   public void play(String audioType, String fileName) {		

	      //播放 mp3 音乐文件的内置支持
	      if(audioType.equalsIgnoreCase("mp3")){
	         System.out.println("Playing mp3 file. Name: "+ fileName);			
	      } 
	      //mediaAdapter 提供了播放其他文件格式的支持
	      else if(audioType.equalsIgnoreCase("vlc") 
	         || audioType.equalsIgnoreCase("mp4")){
	         mediaAdapter = new MediaAdapter(audioType);
	         mediaAdapter.play(audioType, fileName);
	      }
	      else{
	         System.out.println("Invalid media. "+
	            audioType + " format not supported");
	      }
	   }   
}