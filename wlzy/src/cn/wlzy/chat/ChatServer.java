package cn.wlzy.chat;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
/**
 * 群聊系统服务器：暂时以群聊为目的
 * @author Administrator
 *
 */
@ServerEndpoint("/chatServer")
public class ChatServer {
	//session也是唯一的
	private static Set<Session> sessions=new HashSet<Session>();
	//登录名不可能重复
	private static Set<String> usernames=new HashSet<String>();
	private String username;//用户名，传过来后就一直占有，这个是多线程的，每个用户一个
	private Gson gson=new Gson();
	private Message message;
	private String index="";
	//弄一个map把对应的session和username加在一起，可以实现私聊和解决IE的bug
	/*private */
	/**
	 * 当用户打开聊天后会把用户名传过来，这里会保存对应的session,和username用两个集合来
	 * usernames返回为聊天的列表，也就是有多少人参与了聊天
	 * sessions则为管道，每个用户独有
	 * @param session
	 */
	@OnOpen
	public void open(Session session){
		
		//获取用户名
		username=session.getQueryString().split("=")[1];
		if(usernames.contains(username)){//若用户名已存在，则不加入并且把session也给关掉
			try {
				//关掉之前先发一个通知
				message=new Message();
				String come=username+"已经加入群聊,此对话框无效";
				message.setCome(come);
				session.getBasicRemote().sendText(gson.toJson(message));
				index=session.getId();
				session.close();//会触发关闭 事件
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//将用户名和session保存到集合里
		sessions.add(session);
		usernames.add(username);
		//返回信息给用户，显示用户列表和谁加入了群聊
		String come=username+"加入群聊";
		message=new Message();
		message.setUsernames(usernames);//联系人列表
		message.setCome(come);//发送用户加入的消息
		broadcast(sessions, message);//广播出去
		System.out.println("sessionID-open:"+session.getId());
		System.out.println("userneme-come:"+username);
		
	}
	@OnMessage
	public void message(Session session,String msg){
		System.out.println("消息："+msg);
		//构造一下消息再发出去
		String content="<font color='orange'>"+username+"</font>"+"<font size='2px'>&nbsp;&nbsp;"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"</font><br/>"+msg;
		message=new Message();
		message.setContent(content);
		broadcast(sessions, message);
	}
	/**
	 * 当用户关闭时，要把sessions和usernames清空
	 * @param session
	 */
	@OnClose
	public void close(Session session){
		System.out.println("sessionID"+session.getId());
		if(index.equals(session.getId())){
			return;
		}else{
			sessions.remove(session);
			usernames.remove(username);
			String leave=username+"离开群聊";
			message=new Message();
			message.setLeave(leave);
			message.setUsernames(usernames);
			broadcast(sessions, message);
			System.out.println("sessionID-close:"+session.getId());
			System.out.println("userneme-leave:"+username);
		}
	}
	/**
	 * 广播
	 * @param sessions
	 * @param msg
	 */
	public void broadcast(Set<Session> sessions,Message message){
		try {
			String jsonMessage=gson.toJson(message);
			System.out.println(jsonMessage);
			Iterator<Session> iterator=sessions.iterator();
			while(iterator.hasNext()){
				iterator.next().getBasicRemote().sendText(jsonMessage);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
