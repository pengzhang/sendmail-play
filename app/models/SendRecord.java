package models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sendrecord")
public class SendRecord {
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
	/**
	 * 发送成功时间
	 */
	@Column
	public Date create_at = new Date(System.currentTimeMillis());

}
