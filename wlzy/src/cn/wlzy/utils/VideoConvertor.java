package cn.wlzy.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 用来将视频文件转码为.flv
 * @author Administrator
 *
 */
public class VideoConvertor {
	private final static String FFMPEG="D:\\tools\\ffmpeg\\ffmpeg";
	/**
	 * 检查是否是文件
	 * @param inFile 转换之前的文件路径
	 * @return
	 */
	private static boolean checkfile(String inPath) {  
        File file = new File(inPath);  
        if (!file.isFile()) {  
            return false;  
            }  
        return true;  
    } 
	/**
	 * 先判断文件的类型，因为有些文件不能直接转换成.flv
	 * @param inFile 转换之前的文件路径
	 * @return 转换之前的文件路径.avi
	 */
	/*private static int checkContentType(String inPath) {  
        String type = inPath.substring(inPath.lastIndexOf(".") + 1, inPath.length()).toLowerCase();  
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
        // 对ffmpeg无法解析的文件格式(wmv9，rm，rmvb等),  :我试过明明就可以：可能版本更新了
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
	*/
	/**"D:\\tools\\ffmpeg\\mencoder"
	 * 对ffmpeg无法解析的文件格式(wmv9，rm，rmvb等), 可以先用别的工具（mencoder）转换为avi(ffmpeg能解析的)格式.  
	 * @param inFile
	 * @return这个暂时实现不了
	 */
   /* private static String processAVI(String mencoderPath, String inFile) {
    	String inFile2=inFile.substring(0,inFile.lastIndexOf("."))+".avi";
    	 String toAvi = mencoderPath+" i "+inFile+" -oac lavc -lavcopts acodec=mp3:abitrate=64 -ovc xvid -xvidencopts  bitrate=600 of -o "+inFile2;
        try {  
        	Runtime rt = Runtime.getRuntime();
		    Process proc = rt.exec(toAvi);
            return inFile2;  
       } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  */
    /**
     * 转换
     * @param inFile
     * @param ffmpegPath:命令所在路径D:\\tools\\ffmpeg\\ffmpeg
     * @param outFile
     * @return
     */
    public static boolean processFLV(String inPath,String outPath){
    	 String toFlv = FFMPEG+" -i "+inPath+" -ar 22050 -ab 56 -f flv -y -s 1920*1080 "+outPath;
    	 try {
			Runtime rt = Runtime.getRuntime();
		    Process proc = rt.exec(toFlv);
			InputStream stderr = proc.getErrorStream();
			 InputStreamReader isr = new InputStreamReader(stderr);
			BufferedReader br = new BufferedReader(isr);
			 String line = null;
			 while ( (line = br.readLine()) != null)
			     System.out.println(line);
			 int exitVal = proc.waitFor();
			System.out.println("Process exitValue: " + exitVal);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    	 return true;
    }
    /**
     * 这里进行转换
     * @param inFile：输入的路径
     * @param mencoderPath：特殊解码命令路径
     * @param ffmpegPath：flv解码命令路径
     * @param outFile：输出的路径
     * @return
     */
	public static boolean transfer(String inPath,String outPath){
		//先判断文件是否存在
		if(checkfile(inPath)){
			//判断文件的类型
			//int type=checkContentType(inFile);
			//if(type==0){//文件可直接转换侧成.flv：不然实现不了
				if(processFLV(inPath, outPath)){
					return true;//转换成功
				}
			//}
			/*if(type==1){//先转换成avi
				String inFile2=processAVI(mencoderPath,inFile);
				//在转换成flv
				if(processFLV(ffmpegPath,inFile2, outFile)){
					return true;//转换成功
				}
			}*/
		}
		return false;//转换失败
	}
	
  
}
