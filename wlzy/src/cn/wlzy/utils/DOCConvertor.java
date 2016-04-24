package cn.wlzy.utils;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

/**
 * 用来将word ppt excel转化成pdf
 * @author Administrator
 *
 */
public class DOCConvertor {
	/**
	 * 开启openoffice:
	 * 	
	soffice -headless -accept="socket,host=127.0.0.1,port=8100;urp;" -nofirststartwizard

	 * @param sourcePath源文件路径
	 * @param pdfPath目标文件路径
	 * @return
	 */
	
	private static boolean checkfile(String sourcePath) {  
        File file = new File(sourcePath);  
        if (!file.isFile()) {  
            return false;  
            }  
        return true;  
    } 
	public static boolean transfer(String sourcePath,String pdfPath){
		if(checkfile(sourcePath)){//文件存在：进行转换
			File sourceFile=new File(sourcePath);
			File pdfFile=new File(pdfPath);
			OpenOfficeConnection connection=new SocketOpenOfficeConnection(8100);
			//System.out.println("是否已开启服务："+connection.isConnected());
			try {
				//开启openOffice连接，需要先开启openOffice服务
				//获得转换的对象
				DocumentConverter converter=new OpenOfficeDocumentConverter(connection);	
				converter.convert(sourceFile, pdfFile);
				pdfFile.createNewFile();//创建PDF文件,创建失败的话最好抛出异常
				System.out.println("第二步，转换成PDF格式 ，路径"+pdfFile.getPath());
				return true;//转换成功
			} catch (ConnectException e) {
				e.printStackTrace();
				System.out.println("OpenOffice服务器未启动");
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				connection.disconnect();//在这里的话一定能够关闭
			}
			
		}
		return false;//转换失败
		}
	
}
