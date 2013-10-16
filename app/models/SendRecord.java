package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.ebean.Model;
import utils.StringUtils;

@Entity
@Table(name="sendrecord")
public class SendRecord extends Model{
	
	private static final long serialVersionUID = 1L;
	@Id
	public long id;
	/**
	 * 发送ip
	 */
	@Column
	public String remote_ip;
	/**
	 * 发送的email
	 */
	@Column
	public String email;
	
	public boolean status;
	/**
	 * 发送成功时间
	 */
	@Column
	public String create_at = StringUtils.getStanderDate();
	
	public static Model.Finder<Long, SendRecord> find = new Model.Finder<Long, SendRecord>(Long.class, SendRecord.class);
	
	public static void save(SendRecord sr){
		sr.save();
		SendNum.count();
	}
	
	public static List<SendRecord> getPage(int page,int size){
		return find.findPagingList(size).getPage(page).getList();
	}

}
