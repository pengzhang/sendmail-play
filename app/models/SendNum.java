package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sendnum")
public class SendNum {

	@Id
	public long id;
	/**
	 * 总记录数
	 */
	@Column
	public long total;
	/**
	 * 昨日记录数
	 */
	@Column
	public long yesterday;
	/**
	 * 今日记录数
	 */
	@Column
	public long today;
	/**
	 * 单日最大记录数
	 */
	@Column
	public long day_max;
	/**
	 * 单日最大记录数日期
	 */
	@Column
	public String day; 
	
	public static void count(){
		
	}
}
