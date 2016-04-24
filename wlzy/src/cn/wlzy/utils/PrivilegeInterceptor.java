package cn.wlzy.utils;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import cn.wlzy.domain.User;
/**
 * 拦截没有权限的用户从url处输入的action
 */
public class PrivilegeInterceptor implements Interceptor{
	private static final long serialVersionUID = 1L;
	public void destroy(){}
	public void init(){}
	
	//生成四个静态数组存储权限虽然效率低，但是也不错
	private static  List<String> NOTLOGIN=new ArrayList<String>(); 
	private static  List<String> STUDENT=new ArrayList<String>(); 
	private static  List<String> TEACHER=new ArrayList<String>(); 
	//private static  List<String> MANAGER=new ArrayList<String>();
	//放到一个静态块里为他们赋值
	static{
		System.out.println("权限--------------------------------权限");
		/**
		 * 未登录的用户所含有的权限
		 */
		NOTLOGIN.add("homeAction_list");
		NOTLOGIN.add("userAction_registerUI");
		NOTLOGIN.add("userAction_register");
		NOTLOGIN.add("userAction_other");
		NOTLOGIN.add("userAction_login");
		NOTLOGIN.add("userAction_loginUI");
		NOTLOGIN.add("forumAction_list");
		NOTLOGIN.add("forumAction_show");
		NOTLOGIN.add("topicAction_show");
		//NOTLOGIN.add("myFileAction_list");
		NOTLOGIN.add("myFileAction_noticeList");
		NOTLOGIN.add("myFileAction_videoList");
		NOTLOGIN.add("myFileAction_docList");
		NOTLOGIN.add("myFileAction_audioList");
		NOTLOGIN.add("myFileAction_imageList");
		NOTLOGIN.add("myFileAction_otherList");
		NOTLOGIN.add("myFileAction_authorList");
		NOTLOGIN.add("myFileAction_expList");
		NOTLOGIN.add("noticeAction_show");
		NOTLOGIN.add("fileHandleAction_play");
		NOTLOGIN.add("fileHandleAction_readDoc");

		
		
		/**
		 * 学生所含有的权限
		 */
		STUDENT.add("homeAction_list");
		STUDENT.add("homeAction_chat");
		STUDENT.add("userAction_other");
		STUDENT.add("userAction_self");
		STUDENT.add("userAction_selfEditUI");
		STUDENT.add("userAction_selfEdit");
		STUDENT.add("userAction_cancel");
		/*STUDENT.add("userAction_addFriend");*/
		STUDENT.add("userAction_changePasswordUI");
		STUDENT.add("userAction_changePassword");
		STUDENT.add("userAction_initPassword");
		STUDENT.add("forumAction_list");
		STUDENT.add("forumAction_show");
		STUDENT.add("topicAction_show");
		STUDENT.add("topicAction_add");
		STUDENT.add("replyAction_add");
		//STUDENT.add("myFileAction_list");
		STUDENT.add("fileHandleAction");//文件下载
		STUDENT.add("fileHandleAction_play");//文件观看
		STUDENT.add("fileHandleAction_readDoc");//文件下载
		STUDENT.add("myFileAction_noticeList");
		STUDENT.add("myFileAction_videoList");
		STUDENT.add("myFileAction_docList");
		STUDENT.add("myFileAction_audioList");
		STUDENT.add("myFileAction_imageList");
		STUDENT.add("myFileAction_otherList");
		STUDENT.add("myFileAction_authorList");
		STUDENT.add("myFileAction_expList");
		STUDENT.add("noticeAction_show");
		STUDENT.add("examTopicAction_list");
		STUDENT.add("examOptionAction_show");
		
		/**
		 * 老师所含有的权限
		 */
		TEACHER.add("homeAction_list");
		TEACHER.add("homeAction_chat");
		TEACHER.add("userAction_other");
		TEACHER.add("userAction_self");
		TEACHER.add("userAction_selfEditUI");
		TEACHER.add("userAction_selfEdit");
		TEACHER.add("userAction_cancel");
		/*TEACHER.add("userAction_addFriend");*/
		TEACHER.add("userAction_chat");
		TEACHER.add("userAction_changePasswordUI");
		TEACHER.add("userAction_changePassword");
		TEACHER.add("userAction_initPasswordUI");
		TEACHER.add("forumAction_list");
		TEACHER.add("forumAction_show");
		TEACHER.add("topicAction_show");
		TEACHER.add("topicAction_add");
		TEACHER.add("replyAction_add");
		//TEACHER.add("myFileAction_list");
		/*TEACHER.add("myFileAction_delete");*/
		TEACHER.add("myFileAction_noticeList");
		TEACHER.add("myFileAction_videoList");
		TEACHER.add("myFileAction_docList");
		TEACHER.add("myFileAction_audioList");
		TEACHER.add("myFileAction_imageList");
		TEACHER.add("myFileAction_otherList");
		TEACHER.add("myFileAction_authorList");
		TEACHER.add("myFileAction_expList");
		TEACHER.add("fileHandleAction");//下载
		TEACHER.add("fileHandleAction_uploadVIDEO");
		TEACHER.add("fileHandleAction_uploadDOC");
		TEACHER.add("fileHandleAction_uploadAUDIO");
		TEACHER.add("fileHandleAction_uploadIMG");
		TEACHER.add("fileHandleAction_uploadOTHER");
		TEACHER.add("fileHandleAction_uploadAUTHOR");
		TEACHER.add("fileHandleAction_uploadEXP");
		TEACHER.add("fileHandleAction_play");
		TEACHER.add("fileHandleAction_readDoc");
		
		TEACHER.add("noticeAction_addUI");
		TEACHER.add("noticeAction_add");
		TEACHER.add("noticeAction_findNoticeByUser");
		TEACHER.add("noticeAction_show");
		TEACHER.add("examTopicAction_list");
		TEACHER.add("examTopicAction_uploadUI");
		TEACHER.add("examTopicAction_upload");
		TEACHER.add("examTopicAction_delete");
		TEACHER.add("examTopicAction_editUI");
		TEACHER.add("examTopicAction_edit");
		TEACHER.add("examOptionAction_show");
		/**
		 * 管理员具有所有权限
		 *noticeAction_editUI
		 *noticeAction_edit
		 *noticeAction_delete
		 */
	}
	
	private User user;
	private String url;
	public String intercept(ActionInvocation arg0) throws Exception {
		//获取当前登录的用户
		user=(User) ActionContext.getContext().getSession().get("user");
		//获取请求的url
		url=arg0.getProxy().getActionName();
		System.out.println(url);
		if(user==null){//用户未登录
			for (int i = 0; i < NOTLOGIN.size(); i++) {
				if(url.equals(NOTLOGIN.get(i))){
					return arg0.invoke();
				}
			}
		}
		if(user!=null){//用户已登录
			if(user.getUserType()==0){//学生
				for (int i = 0; i < STUDENT.size(); i++) {
					if(url.equals(STUDENT.get(i))){
						return arg0.invoke();//结束
					}
				}
			}
			if(user.getUserType()==1){//老师
				for (int i = 0; i < TEACHER.size(); i++) {
					if(url.equals(TEACHER.get(i))){
						return arg0.invoke();//结束
					}
				}
			}
			if(user.getUserType()==2){//管理员
				String result=arg0.invoke();
				return result;
			}
			
		}
		
		//只要能到这里，证明是没有权限
		return "noPrivilege";
	}

	

}
