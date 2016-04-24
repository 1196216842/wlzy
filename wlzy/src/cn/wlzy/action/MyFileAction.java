package cn.wlzy.action;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.wlzy.base.PageRoll;
import cn.wlzy.domain.MyFile;
import cn.wlzy.domain.Notice;
import cn.wlzy.domain.User;
import cn.wlzy.service.MyFileService;
import cn.wlzy.service.NoticeService;
import cn.wlzy.service.UserService;
@Controller("myFileAction")
@Scope("prototype")
public class MyFileAction extends ActionSupport implements Serializable,
		ModelDriven<MyFile> {
	private static final long serialVersionUID = 1L;
	/**
	 * 返回页面的时候先判断类型
	 * 0：notice
	 * 1:vedio
	 * 2:doc
	 * 3:audio
	 * 4:image
	 */
	private int dataType=MyFile.NOTICE;//默认为通知
	private Long userId;//判断查询的目标，0为全部查询
	//注意，file并不是指前端jsp上传过来的文件本身，而是文件上传过来存放在临时文件夹下面的文件
	private File file;
	//提交过来的file的MIME类型
	private String fileContentType;//用来判断格式是否符合
	//提交过来的file的名字
	private String fileFileName;
	//
	private int currentPage=1;
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

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
	
	/*//显示版块列表()这样的话页面不好分页，只能触发点击，这样效率也比较高
	public String list(){
		List<Notice> notices;
		List<MyFile> videos;
		List<MyFile> docs;
		List<MyFile> audios;
		List<MyFile> images;
		PageRoll pageRoll=new PageRoll();
		pageRoll.setPageSize(8);
		pageRoll.setCurrentPage(currentPage);
		//还需要根据页面的信息查找，比如是查找全部还是查找某一个老师
		//还要分页查询
		if(userId==0){//这证明是查询全部的:分页查询
			//所有公告：
			notices=noticeService.findByUser(null, pageRoll);
			//所有视频
			videos=myFileService.findFileByUserAndTtype(null, MyFile.VIDEO, pageRoll);
			//所有文档
			docs=myFileService.findFileByUserAndTtype(null, MyFile.DOC, pageRoll);
			//所有音频
			audios=myFileService.findFileByUserAndTtype(null, MyFile.AUDIO, pageRoll);
			//所有图片
			images=myFileService.findFileByUserAndTtype(null, MyFile.IMG, pageRoll);
		}else{//证明是选定了老师的那么
			User user=userService.findById(userId);//查询出用户来
			//根据user和类型查找：fileType,
			//公告
			notices=noticeService.findByUser(user, pageRoll);
			//视频
			videos=myFileService.findFileByUserAndTtype(user, MyFile.VIDEO, pageRoll);
			//文档
			docs=myFileService.findFileByUserAndTtype(user, MyFile.DOC, pageRoll);
			//音频
			audios=myFileService.findFileByUserAndTtype(user, MyFile.AUDIO, pageRoll);
			//图片
			images=myFileService.findFileByUserAndTtype(user, MyFile.IMG, pageRoll);
			
		}
		//模拟老师的数据(0：学生，1：老师，2：管理员。)
		int userType=1;
		List<User> teachers=userService.findTeacher(userType);
		//为了在选好老师后可以点击类别查询也能查询对应老师的
		*//**
		 * 为了让上传后还可以判断当前的类型，暂时不理先
		 * ActionContext.getContext().put("currentType", model.getFileType());
		 *//*
		ActionContext.getContext().put("userId", userId);
		System.out.println(userId);
		ActionContext.getContext().put("teachers", teachers);
		ActionContext.getContext().put("notices", notices);
		ActionContext.getContext().put("videos", videos);
		ActionContext.getContext().put("audios", audios);
		ActionContext.getContext().put("docs", docs);
		ActionContext.getContext().put("images", images);
		ActionContext.getContext().put("pageRoll", pageRoll);
		
		return "list";
	}
	*/
	/**
	 * list刚开始显示的是通知
	 * @return
	 */
	public String noticeList(){
		List<Notice> notices;
		int userType=1;
		dataType=MyFile.NOTICE;
		PageRoll pageRoll=new PageRoll();
		pageRoll.setPageSize(30);
		pageRoll.setCurrentPage(currentPage);
		if(userId==0){//这证明是查询全部的:分页查询
			//所有公告：
			notices=noticeService.findByUser(null, pageRoll);
		}else{//证明是选定了老师的那么
			User user=userService.findById(userId);//查询出用户来
			//根据user和类型查找：fileType
			notices=noticeService.findByUser(user, pageRoll);//公告
		}
		ActionContext.getContext().put("userId", userId);//给选择那
		List<User> teachers=userService.findTeacher(userType);
		ActionContext.getContext().put("teachers", teachers);
		ActionContext.getContext().put("notices", notices);
		ActionContext.getContext().put("pageRoll", pageRoll);
		ActionContext.getContext().put("dataType", dataType);
		return "list";
	}
	
	/**
	 * 显示视频
	 * @return
	 */
	public String videoList(){
		int userType=1;
		dataType=MyFile.VIDEO;
		List<MyFile> videos;
		PageRoll pageRoll=new PageRoll();
		pageRoll.setPageSize(30);
		pageRoll.setCurrentPage(currentPage);
		//还要分页查询
		if(userId==0){//这证明是查询全部的:分页查询
			//所有视频
			videos=myFileService.findFileByUserAndTtype(null, MyFile.VIDEO, pageRoll);
		}else{//证明是选定了老师的那么
			User user=userService.findById(userId);//查询出用户来
			//根据user和类型查找：fileType,
			//视频
			videos=myFileService.findFileByUserAndTtype(user, MyFile.VIDEO, pageRoll);
		}
		List<User> teachers=userService.findTeacher(userType);
		ActionContext.getContext().put("userId", userId);
		ActionContext.getContext().put("videos", videos);
		ActionContext.getContext().put("teachers", teachers);
		ActionContext.getContext().put("pageRoll", pageRoll);
		ActionContext.getContext().put("dataType", dataType);
		/**
		 * 判断视频是否转码成功，成功的话播放按钮才能够被点击
		 * 怎样实现这个功能呢，这个需要加一个标记才行
		 */
		return "list";
	}
	
	/**
	 * 显示文档
	 * @return
	 */
	public String docList(){
		int userType=1;
		dataType=MyFile.DOC;
		List<MyFile> docs;
		PageRoll pageRoll=new PageRoll();
		pageRoll.setPageSize(30);
		pageRoll.setCurrentPage(currentPage);
		if(userId==0){//这证明是查询全部的:分页查询
			//所有文档
			docs=myFileService.findFileByUserAndTtype(null, MyFile.DOC, pageRoll);
		}else{//证明是选定了老师的那么
			User user=userService.findById(userId);//查询出用户来
			//文档
			docs=myFileService.findFileByUserAndTtype(user, MyFile.DOC, pageRoll);
		}
		List<User> teachers=userService.findTeacher(userType);
		ActionContext.getContext().put("userId", userId);
		ActionContext.getContext().put("teachers", teachers);
		ActionContext.getContext().put("docs", docs);
		ActionContext.getContext().put("pageRoll", pageRoll);
		ActionContext.getContext().put("dataType", dataType);
		return "list";
	}
	/**
	 * 显示音频
	 * @return
	 */
	
	public String audioList(){
		List<MyFile> audios;
		dataType=MyFile.AUDIO;
		PageRoll pageRoll=new PageRoll();
		pageRoll.setPageSize(15);
		pageRoll.setCurrentPage(currentPage);
		//还需要根据页面的信息查找，比如是查找全部还是查找某一个老师
		//还要分页查询
		if(userId==0){//这证明是查询全部的:分页查询
			//所有音频
			audios=myFileService.findFileByUserAndTtype(null, MyFile.AUDIO, pageRoll);
		}else{//证明是选定了老师的那么
			User user=userService.findById(userId);//查询出用户来
			//根据user和类型查找：fileType,
			//音频
			audios=myFileService.findFileByUserAndTtype(user, MyFile.AUDIO, pageRoll);
			
		}
		//模拟老师的数据(0：学生，1：老师，2：管理员。)
		int userType=1;
		List<User> teachers=userService.findTeacher(userType);
		ActionContext.getContext().put("userId", userId);
		ActionContext.getContext().put("teachers", teachers);
		ActionContext.getContext().put("audios", audios);
		ActionContext.getContext().put("pageRoll", pageRoll);
		ActionContext.getContext().put("dataType", dataType);
		return "list";
	}
	/**
	 * 显示图片
	 * @return
	 */
	public String imageList(){
		List<MyFile> images;
		dataType=MyFile.IMG;
		PageRoll pageRoll=new PageRoll();
		pageRoll.setPageSize(6);
		pageRoll.setCurrentPage(currentPage);
		//还需要根据页面的信息查找，比如是查找全部还是查找某一个老师
		//还要分页查询
		if(userId==0){//这证明是查询全部的:分页查询
			//所有图片
			images=myFileService.findFileByUserAndTtype(null, MyFile.IMG, pageRoll);
		}else{//证明是选定了老师的那么
			User user=userService.findById(userId);//查询出用户来
			//图片
			images=myFileService.findFileByUserAndTtype(user, MyFile.IMG, pageRoll);
		}
		//模拟老师的数据(0：学生，1：老师，2：管理员。)
		int userType=1;
		List<User> teachers=userService.findTeacher(userType);
		ActionContext.getContext().put("userId", userId);
		ActionContext.getContext().put("teachers", teachers);
		ActionContext.getContext().put("images", images);
		ActionContext.getContext().put("pageRoll", pageRoll);
		ActionContext.getContext().put("dataType", dataType);
		return "list";
	}
	
	
	/**
	 * 查询其他资料
	 */
	public String otherList(){
		List<MyFile> others;
		dataType=MyFile.OTHER;
		PageRoll pageRoll=new PageRoll();
		pageRoll.setPageSize(30);
		pageRoll.setCurrentPage(currentPage);
		//还需要根据页面的信息查找，比如是查找全部还是查找某一个老师
		//还要分页查询
		if(userId==0){//这证明是查询全部的:分页查询
			//所有音频
			others=myFileService.findFileByUserAndTtype(null, MyFile.OTHER, pageRoll);
		}else{//证明是选定了老师的那么
			User user=userService.findById(userId);//查询出用户来
			//根据user和类型查找：fileType,
			//音频
			others=myFileService.findFileByUserAndTtype(user, MyFile.OTHER, pageRoll);
			
		}
		//模拟老师的数据(0：学生，1：老师，2：管理员。)
		int userType=1;
		List<User> teachers=userService.findTeacher(userType);
		ActionContext.getContext().put("userId", userId);
		ActionContext.getContext().put("teachers", teachers);
		ActionContext.getContext().put("others", others);
		ActionContext.getContext().put("pageRoll", pageRoll);
		ActionContext.getContext().put("dataType", dataType);
		return "list";
	}
	/**
	 * 查询经典物理学家
	 * 只需要查询用户为0的即可
	 */
	public String authorList(){
		List<MyFile> authors;
		dataType=MyFile.AUTHOR;
		userId=0l;//默认擦查询全部
		PageRoll pageRoll=new PageRoll();
		pageRoll.setPageSize(9);
		pageRoll.setCurrentPage(currentPage);
		//还需要根据页面的信息查找，比如是查找全部还是查找某一个老师
		//还要分页查询
		authors=myFileService.findFileByUserAndTtype(null, MyFile.AUTHOR, pageRoll);
		//模拟老师的数据(0：学生，1：老师，2：管理员。)
		//int userType=1;
		//List<User> teachers=userService.findTeacher(userType);
		//ActionContext.getContext().put("userId", userId);
		//ActionContext.getContext().put("teachers", teachers);
		ActionContext.getContext().put("authors", authors);
		ActionContext.getContext().put("pageRoll", pageRoll);
		ActionContext.getContext().put("dataType", dataType);
		return "author";
	}
	/**
	 * 查询经典物理实验
	 * 只需要查询用户为0的即可
	 */
	public String expList(){
		List<MyFile> exps;
		dataType=MyFile.EXP;
		userId=0l;//默认擦查询全部
		PageRoll pageRoll=new PageRoll();
		pageRoll.setPageSize(9);
		pageRoll.setCurrentPage(currentPage);
		//还需要根据页面的信息查找，比如是查找全部还是查找某一个老师
		//还要分页查询
		exps=myFileService.findFileByUserAndTtype(null, MyFile.EXP, pageRoll);
		//模拟老师的数据(0：学生，1：老师，2：管理员。)
		//int userType=1;
		//List<User> teachers=userService.findTeacher(userType);
		//ActionContext.getContext().put("userId", userId);
		//ActionContext.getContext().put("teachers", teachers);
		ActionContext.getContext().put("exps", exps);
		ActionContext.getContext().put("pageRoll", pageRoll);
		ActionContext.getContext().put("dataType", dataType);
		return "exp";
	}
	/********************************************************************/
	
	
	/**
	 * 删除文件DOC文件的话要找是否有pdf,(swf)文件
	 * 删除视频也要删除两个目录下的
	 * @return
	 */
	public String delete(){
		ServletContext servletContext=ServletActionContext.getServletContext();
		MyFile file=myFileService.findById(model.getId());
		myFileService.delete(file.getId());//删除源文件（数据库中的信息）
		if(file.getFileType()==MyFile.IMG){//图片
			String imgPath=servletContext.getRealPath("/upload/images")+"\\"+file.getUuidName();
			if(new File(imgPath).exists()){
				new File(imgPath).delete();
			}
			return "deleteIMG";
		}
		if(file.getFileType()==MyFile.VIDEO){//视频
			String videoPath=servletContext.getRealPath("/upload/videos/inFile")+"\\"+file.getUuidName();
			String name=file.getUuidName().substring(0, file.getUuidName().lastIndexOf('.'));
			String flvPath=servletContext.getRealPath("/upload/videos/outFile")+"\\"+name+".flv";
			
			if(new File(videoPath).exists()){
				new File(videoPath).delete();
			}
			if(new File(flvPath).exists()){
				new File(flvPath).delete();
			}
			return "deleteVIDEO";
		}
		if(file.getFileType()==MyFile.DOC||file.getFileType()==MyFile.AUTHOR||file.getFileType()==MyFile.EXP){
			String docPath=servletContext.getRealPath("/upload/documents/source")+"\\"+file.getUuidName();
			//想办法删除对应的PDF和SWF文件
			String name=file.getUuidName().substring(0, file.getUuidName().lastIndexOf('.'));
			/*swfName=name+".swf";*/
			String  pdfPath=servletContext.getRealPath("/upload/documents/pdf")+"\\"+name+".pdf";
			/*swfPath=servletContext.getRealPath("/upload/documents/swf")+"\\"+swfName;*/
			if(new File(docPath).exists()){
				new File(docPath).delete();
			}
			if(new File(pdfPath).exists()){
				new File(pdfPath).delete();
			}
			if(file.getFileType()==MyFile.DOC){
				return "deleteDOC";
			}
			/**
			 * 这个在别的页面
			 */
			if(file.getFileType()==MyFile.AUTHOR){
				return "deleteAUTHOR";
			}
			if(file.getFileType()==MyFile.EXP){
				return "deleteEXP";
			}
		}
		if(file.getFileType()==MyFile.AUDIO){
			String audioPath=ServletActionContext.getServletContext().getRealPath("/upload/audios")+"\\"+file.getUuidName();
			if(new File(audioPath).exists()){
				new File(audioPath).delete();
			}
			return "deleteAUDIO";
		}
		if(file.getFileType()==MyFile.OTHER){
			String audioPath=ServletActionContext.getServletContext().getRealPath("/upload/other")+"\\"+file.getUuidName();
			if(new File(audioPath).exists()){
				new File(audioPath).delete();
			}
			return "deleteOTHER";
		}
			return INPUT;//删除失败
	}
	
}
