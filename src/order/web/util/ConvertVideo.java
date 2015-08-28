package order.web.util;
import java.io.File;
import java.util.List;
public class ConvertVideo {
	
	private String mencoder_home = "";//mencoder.exe所放的路径
	private String tempFile_home;//存放rm,rmvb等无法使用ffmpeg直接转换为flv文件先转成的avi文件
	 
	 public ConvertVideo(String mencoderHome,String tempFilePath){
		 this.mencoder_home = mencoderHome;
		 this.tempFile_home = tempFilePath;
	 }
	 
	    /** 
	     *  功能函数 
	     * @param inputFile 待处理视频，需带路径 
	     * @param outputFile 处理后视频，需带路径 
	     * @return 
	     */  
	    public  boolean convert(String inputFile, String outputFile)  
	    {  
	        if (!checkfile(inputFile)) {  
	            System.out.println(inputFile + " is not file");  
	            return false;  
	        }  
	        if (process(inputFile,outputFile)) {  
	            System.out.println("ok"); 
				//将非目标文件删除
				File file = new File(inputFile);
				if(file.exists()) file.delete();
	            return true;  
	        }  
	        return false;  
	    }  
	    //检查文件是否存在  
	    private  boolean checkfile(String path) {  
	        File file = new File(path);  
	        if (!file.isFile()) {  
	            return false;  
	        }  
	        return true;  
	    }  
	    /** 
	     * 转换过程 ：先检查文件类型，在决定调用 processFlv还是processAVI 
	     * @param inputFile 
	     * @param outputFile 
	     * @return 
	     */  
	    private  boolean process(String inputFile,String outputFile) {  
	        int type = checkContentType( inputFile);  
	        boolean status = false;  
	        if (type == 0) {  
	            status = processMP4(inputFile,outputFile);// 直接将文件转为flv文件  
	        } else if (type == 1) {  
	            String avifilepath = processAVI(type,inputFile);  
	            if (avifilepath == null)  
	                return false;// avi文件没有得到  
	            status = processMP4(avifilepath,outputFile);// 将avi转为flv
				File tempFile = new File(avifilepath);//将临时文件删除
				if(tempFile.exists())tempFile.delete();
	        }  
	        return status;  
	    }  
	    /** 
	     * 检查视频类型 
	     * @param inputFile 
	     * @return ffmpeg 能解析返回0，不能解析返回1 
	     */  
	    private  int checkContentType(String inputFile) {  
	        String type = inputFile.substring(inputFile.lastIndexOf(".") + 1,inputFile.length()).toLowerCase();  
	        // ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）  
	        if (type.equals("avi")) {  
	            return 0;  
	        } else if (type.equals("mpg")) {  
	            return 0;  
	        } else if (type.equals("wmv")) {  
	            return 0;  
	        } else if (type.equals("3gp")) {  
	            return 0;  
	        } else if (type.equals("mov")) {  
	            return 0;  
	        } else if (type.equals("mp4")) {  
	            return 0;  
	        } else if (type.equals("asf")) {  
	            return 0;  
	        } else if (type.equals("asx")) {  
	            return 0;  
	        } else if (type.equals("flv")) {  
	            return 0;  
	        }  
	        // 对ffmpeg无法解析的文件格式(wmv9，rm，rmvb等),  
	        // 可以先用别的工具（mencoder）转换为avi(ffmpeg能解析的)格式.  
	        else if (type.equals("wmv9")) {  
	            return 1;  
	        } else if (type.equals("rm")) {  
	            return 1;  
	        } else if (type.equals("rmvb")) {  
	            return 1;  
	        }  
	        return 9;  
	    }  
	    /** 
	     *  ffmepg: 能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等） 
	     * @param inputFile 
	     * @param outputFile 
	     * @return 
	     */  
	    private  boolean processMP4(String inputFile,String outputFile) {  
	        if (!checkfile(inputFile)) {  
	            System.out.println(inputFile + " is not file");  
	            return false;  
	        } 
	        File file = new File(outputFile);
	        if(file.exists()){
	        	System.out.println("mp4文件已经存在！无需转换");
	        	return true;
	        } else {
	        	System.out.println("正在转换成mp4文件……");
	        	
	        	List<String> commend = new java.util.ArrayList<String>();
	        	commend.add(mencoder_home); // 添加转换工具路径
	        	commend.add(inputFile); // 添加要转换格式的视频文件的路径
	        	commend.add("-of"); 
	        	commend.add("lavf");
	        	commend.add("-lavfopts"); 
	        	commend.add("format=mp4");
	        	commend.add("-vf"); 
	        	commend.add("scale=576:320,dsize=576:320,harddup");
	        	commend.add("-ovc");
	        	commend.add("x264"); 
	        	commend.add("-x264encopts");
	        	commend.add("bitrate=1381:vbv_maxrate=1500:vbv_bufsize=2000:nocabac:me=umh:trellis=1:level_idc=30:global_header:threads=2:pass=1:turbo"); 
	        	commend.add("-oac");
	        	commend.add("faac");
	        	commend.add("-faacopts");
	        	commend.add("mpeg=4:object=2:br=160:raw");
	        	commend.add("-channels");
	        	commend.add("2");
	        	commend.add("-srate");
	        	commend.add("48000");
	        	commend.add("-o");
	        	commend.add(outputFile);
	        	commend.add("-slang");
	        	commend.add("zh,ch,tw");
	        	commend.add("-sws");
	        	commend.add("9");

//	        	commend.add(ffmpeg_home); // 添加转换工具路径
//	        	commend.add("-i"); // 添加参数＂-i＂，该参数指定要转换的文件
//	        	commend.add(inputFile); // 添加要转换格式的视频文件的路径
//	        	commend.add("-qscale"); // 指定转换的 清晰度 -qscale 4 为最好但文件大, -qscale 6就可以了
//	        	commend.add("6");
//	        	commend.add("-ab"); // 设置音频码率
//	        	commend.add("64");
//	        	commend.add("-ac"); // 设置声道数
//	        	commend.add("2");
//	        	commend.add("-ar"); // 设置声音的采样频率
//	        	commend.add("22050");
//	        	commend.add("-r"); // 设置帧频
//	        	commend.add("24");
//	        	commend.add("-y"); // 添加参数＂-y＂，该参数指定将覆盖已存在的文件
//	        	commend.add(outputFile);	        	
	        	
	 	        try {  
	 				Process videoProcess = new ProcessBuilder(commend).redirectErrorStream(true).start();
	 				new PrintStream(videoProcess.getErrorStream()).start();
	 				new PrintStream(videoProcess.getInputStream()).start();
	 				videoProcess.waitFor();	 	        	
	 	            return true;  
	 	        } catch (Exception e) {  
	 	            e.printStackTrace();  
	 	            return false;  
	 	        }  
	 	       
	        }
	       
	    }  
	    /** 
	     * Mencoder: 
	     * 对ffmpeg无法解析的文件格式(wmv9，rm，rmvb等),可以先用别的工具（mencoder）转换为avi(ffmpeg能解析的)格式. 
	     * @param type 
	     * @param inputFile 
	     * @return 
	     */  
	    private  String processAVI(int type,String inputFile) {  
	        File file =new File(tempFile_home);  
	        if(file.exists()){
	        	System.out.println("avi文件已经存在！无需转换");
	        	return tempFile_home;
	        }  
	        List<String> commend = new java.util.ArrayList<String>();  
	        commend.add(mencoder_home);  
	        commend.add(inputFile);  
	        commend.add("-oac");  
	        commend.add("mp3lame");  
	        commend.add("-lameopts");  
	        commend.add("preset=64");  
	        commend.add("-ovc");  
	        commend.add("xvid");  
	        commend.add("-xvidencopts");  
	        commend.add("bitrate=600");  
	        commend.add("-of");  
	        commend.add("avi");  
	        commend.add("-o");  
	        commend.add(tempFile_home);  
	        try   
	        {
				ProcessBuilder builder = new ProcessBuilder();
				Process process = builder.command(commend).redirectErrorStream(true).start();
				new PrintStream(process.getInputStream());
				new PrintStream(process.getErrorStream());
				process.waitFor();	 
	            return tempFile_home;  
	        }catch (Exception e){   
	            System.err.println(e);   
	            return null;  
	        }   
	    }  
	}  
class PrintStream extends Thread {
	java.io.InputStream __is = null;

	public PrintStream(java.io.InputStream is) {
		__is = is;
	}

	public void run() {
		try {
			while (this != null) {
				int _ch = __is.read();
				if (_ch != -1)
					System.out.print((char) _ch);
				else
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
