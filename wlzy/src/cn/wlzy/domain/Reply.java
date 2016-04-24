package cn.wlzy.domain;

public class Reply extends Article {
	private Topic topic;//回复于主题多对一
	private int floor;//楼层
	

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	
}
