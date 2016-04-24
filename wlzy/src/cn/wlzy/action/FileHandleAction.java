package cn.wlzy.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.wlzy.domain.MyFile;
import cn.wlzy.domain.User;
import cn.wlzy.service.MyFileService;
import cn.wlzy.service.NoticeService;
import cn.wlzy.service.UserService;
import cn.wlzy.utils.DOCConvertor;
import cn.wlzy.utils.VideoConvertor;
@Controller("fileHandleAction")
@Scope("prototype")
public class FileHandleAction extends ActionSupport implements Serializable,
		ModelDriven<MyFile> {
	private static final long serialVersionUID = 1L;
	private int size=5;//获取视频的数目
	/**
	 * 返回页面的时候先判断类型
	 * 0：notice
	 * 1:vedio
	 * 2:doc
	 * 3:audio
	 * 4:image
	 */
	private Long userId;//判断查询的目标，0为全部查询
	//注意，file并不是指前端jsp上传过来的文件本身，而是文件上传过来存放在临时文件夹下面的文件
	private File file;
	//提交过来的file的MIME类型
	private String fileContentType;//用来判断格式是否符合
	//提交过来的file的名字
	private String fileFileName;


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	private MyFile model=new MyFile();
	@Resource(name="myFileService")
	private MyFileService myFileService;
	@Resource(name="userService")
	private UserService userService;
	@Resource(name="noticeService")
	private NoticeService noticeService;
	public MyFile getModel() {
		return model;
	}
	
	/**
	 * 上传：图片，文档，音频，视频
	 */
	public String uploadIMG(){
		//先要判断类型符不符合，这里暂时不弄
		String directory="/upload/images";
		int fileType=MyFile.IMG;
		if(upload(directory, fileType)){
			myFileService.save(model);
			return "toImgList";
		}else{
			return ERROR;
		}
	}
	public String uploadDOC(){
		//先要判断类型符不符合，这里暂时不弄
		String directory="/upload/documents/source";
		int fileType=MyFile.DOC;
		if(upload(directory, fileType)){
			myFileService.save(model);
			return "toDocList";
		}else{
			return ERROR;
		}
	}
	public String uploadAUDIO(){
		//先要判断类型符不符合，这里暂时不弄
		String directory="/upload/audios";
		int fileType=MyFile.AUDIO;
		if(upload(directory, fileType)){
			myFileService.save(model);
			return "toAudioList";
		}else{
			return ERROR;
		}
	}
	public String uploadVIDEO(){
		//先要判断类型符不符合，这里暂时不弄
		String directory="/upload/videos/inFile";
		int fileType=MyFile.VIDEO;
		if(upload(directory, fileType)){
			myFileService.save(model);
			return "toVideoList";
		}else{
			return ERROR;
		}
	}
	/**
	 * 上传其他资料
	 */
	public String uploadOTHER(){
		//先要判断类型符不符合，这里暂时不弄
		String directory="/upload/other";
		int fileType=MyFile.OTHER;
		if(upload(directory, fileType)){
				myFileService.save(model);
				return "toOtherList";
		}else{
				return ERROR;
		}
	}
	/**
	 * 上传物理学家信息
	 */
	public String uploadAUTHOR(){
		//先要判断类型符不符合，这里暂时不弄
		String directory="/upload/documents/source";
		int fileType=MyFile.AUTHOR;
		if(upload(directory, fileType)){
				myFileService.save(model);
				return "toAuthorList";
		}else{
				return ERROR;
		}
	}
	/**
	 * 上传物理实验信息
	 */
	public String uploadEXP(){
		//先要判断类型符不符合，这里暂时不弄
		String directory="/upload/documents/source";
		int fileType=MyFile.EXP;
		if(upload(directory, fileType)){
				myFileService.save(model);
				return "toExpList";
		}else{
				return ERROR;
		}
	}
	public boolean upload(String directory,int fileType){//上传的时候应该有选择，一个图片1，文档2，音频3，视频4，上传后还要判断类型，类型不符不给上传
		try {
			System.out.println("文件类型"+fileContentType);
			//找到文件真实存放路径
			String root=ServletActionContext.getServletContext().getRealPath(directory);
			//构造文件路径
			String uuid=UUID.randomUUID().toString();
			//获取后缀
			String type=fileFileName.substring(fileFileName.lastIndexOf('.'));
			//文件名
			String uuidName=uuid+type;
			String filePath=root+"\\"+uuidName;
			//构建输入输出流
			InputStream is=new FileInputStream(file);
			OutputStream os=new FileOutputStream(filePath);
			byte b[] =new byte[1024];
			int len=-1;
			while((len=is.read(b))!=-1){//读入
				os.write(b, 0, len);//输出
			}
			os.flush();//刷新
			is.close();
			os.close();
			
			model.setUser((User)ActionContext.getContext().getSession().get("user"));
			//设置文件类型图片，文档，视频，音频
			model.setFileType(fileType);
			model.setTrueName(fileFileName);
			model.setUuidName(uuidName);
			model.setUploadTime(new Date());
			
			
			//如果文件类型是doc则在这里进行转换:经典物理实验
			if(fileType==MyFile.DOC||fileType==MyFile.AUTHOR||fileType==MyFile.EXP){
				//如果源文件本来就是pdf结尾。则没必要转换
				if(!".pdf".equals(type.toLowerCase())){
					//否则要进行转换
					final String sourcePath=filePath;
					final String pdfPath=ServletActionContext.getServletContext().getRealPath("/upload/documents/pdf")+"\\"+uuid+".pdf";
					//启动一个新线程来转换
					new Thread(new Runnable() {
						public void run() {
							boolean flag=DOCConvertor.transfer(sourcePath, pdfPath);
							if(flag)System.out.println("转换成功");
						}
					}).start();
				}
			}
			
			
			//如果文件类型是视频，则在这里要进行转换
			if(fileType==MyFile.VIDEO){
				if(type.toLowerCase()!=".flv"){//否则没必要转
					final String inPath=filePath;
					final String outPath=ServletActionContext.getServletContext().getRealPath("/upload/videos/outFile")+"\\"+uuid+".flv";
					//启动一个新的线程
					new Thread(new Runnable() {
						public void run() {
							boolean flag=VideoConvertor.transfer(inPath, outPath);
							if(flag)System.out.println("转换成功");
						}
					}).start();
				}
			}
			
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;//上传失败
	}
	
	
	
	
	
	//文件下载
	private InputStream inputStream;
	public InputStream getInputStream() throws Exception
	
    {
		ServletContext servletContext=ServletActionContext.getServletContext();
		String str=new String(model.getTrueName().getBytes("ISO-8859-1"),"UTF-8");
		model.setTrueName(new String(str.getBytes(),"ISO8859-1"));
		if(model.getFileType()==MyFile.IMG){
			inputStream= servletContext.getResourceAsStream("upload/images/"+model.getUuidName());
		}
		if(model.getFileType()==MyFile.DOC){
			//下载的话只下载源文件
			inputStream=servletContext.getResourceAsStream("upload/documents/source/"+model.getUuidName());
		}
		if(model.getFileType()==MyFile.AUTHOR){
			//下载的话只下载源文件
			inputStream=servletContext.getResourceAsStream("upload/documents/source/"+model.getUuidName());
		}
		if(model.getFileType()==MyFile.EXP){
			//下载的话只下载源文件
			inputStream=servletContext.getResourceAsStream("upload/documents/source/"+model.getUuidName());
		}
		if(model.getFileType()==MyFile.AUDIO){
			inputStream=servletContext.getResourceAsStream("upload/audios/"+model.getUuidName());
		}
		if(model.getFileType()==MyFile.VIDEO){
			inputStream=servletContext.getResourceAsStream("upload/videos/"+model.getUuidName());
		}
		if(model.getFileType()==MyFile.OTHER){
			inputStream=servletContext.getResourceAsStream("upload/other/"+model.getUuidName());
		}
		return inputStream;
    }
	
    @Override
    public String execute() throws Exception
    {
        return SUCCESS;
    }

	/*
    
    
    
    
    *//**
    *	转换成pdf就可以在页面上显示了
     * 文档显示，只支持word,excel,ppt.并且，如果一个文件产生了pdf和swf就不要删除它了，因为别人会第二次看的
     */
    public String readDoc(){
    	MyFile doc=myFileService.findById(model.getId());
    	//定义属性
    	String docPath="";//返回到页面的路径
    	//获得路径
    	ServletContext servletContext=ServletActionContext.getServletContext();
		final String sourcePath=servletContext.getRealPath("/upload/documents/source")+"\\"+doc.getUuidName();
		String uuid=doc.getUuidName().substring(0, doc.getUuidName().lastIndexOf('.'));
		final String pdfPath=servletContext.getRealPath("/upload/documents/pdf")+"\\"+uuid+".pdf";
		//获得文件
		String type=doc.getUuidName().substring(doc.getUuidName().lastIndexOf(".")+1, doc.getUuidName().length());
		if(new File(sourcePath).exists()){
			if("pdf".equals(type.toLowerCase())){//无需转换，直接返回路径
				docPath="upload/documents/source/"+doc.getUuidName();
			}else{
				if(new File(pdfPath).exists()){//判断pdf文件存不存在，若存在则无需再转
					docPath="upload/documents/pdf/"+uuid+".pdf";
				/***********************这个一般不执行，因为在上传的时候就已经转好了****************************/
				}else{	
					//启动一个新线程来转换
					new Thread(new Runnable() {
						public void run() {
							boolean flag=DOCConvertor.transfer(sourcePath, pdfPath);
							if(flag)System.out.println("转换成功");
						}
					}).start();
				
				}
				/***************************************************/
			}
		}
		ActionContext.getContext().put("docPath", docPath);
		ActionContext.getContext().put("doc", doc);
		return "readDoc";
    }
    
    /**
     * 播放视频
     * 先读取有没有该视频的.flv文件，有则直接返回到显示页面，没有的话就进行转码为.flv
     * 这样的话就只需呀转一次码就够了
     * 但是这样第一次那个人要等好久因此还有一个建议是上传的时候就转码
     */
    
    public String play(){
    	MyFile video=myFileService.findById(model.getId());//获得视频文件
    	//还要获得最新十条视频
//    	List<MyFile> videos=myFileService.findNewVideo(size,MyFile.VIDEO);
    	List<MyFile> videos=myFileService.findNewMyFile(size,MyFile.VIDEO);
    	String truePath="";//保存文件的真实路径
    	//构造文件路径
    	final String inPath=ServletActionContext.getServletContext().getRealPath("/upload/videos/inFile")+"\\"+video.getUuidName();
    	String uuid=video.getUuidName().substring(0, video.getUuidName().lastIndexOf("."));
    	final String outPath=ServletActionContext.getServletContext().getRealPath("/upload/videos/outFile")+"\\"+uuid+".flv";
    	
    	if(new File(inPath).exists()){//存在
    		//判断原文件的结尾是否是.flv,是的话就直接返回了
    		if("flv".equals(inPath.substring(inPath.lastIndexOf(".")+1, inPath.length()).toLowerCase())){
    			truePath="upload/videos/inFile/"+inPath.substring(inPath.lastIndexOf("\\")+1, inPath.length());
    		}else{
    			//判断输出文件有没有
    			if(new File(outPath).exists()){//一般都会存在
    				truePath="upload/videos/outFile/"+outPath.substring(outPath.lastIndexOf("\\")+1, outPath.length());
    			/***********************这个一般不执行，因为在上传的时候就已经转好了****************************/
    			}else{//若没有的话证明是在上传的时候转换失败，这里再转
    				new Thread(new Runnable() {
						public void run() {
							if(VideoConvertor.transfer(inPath, outPath))System.out.println("转码成功");
						}
					}).start();
    				
    			}
    			/*******************************************************************************/
    		}
    		ActionContext.getContext().put("truePath", truePath);//将路径传回	
        	ActionContext.getContext().put("video", video);
        	ActionContext.getContext().put("videos", videos);
        	return "play";//视频加载成功
    	}
    	return "errorPlay";//视频加载失败
    }
}
