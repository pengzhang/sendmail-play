package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.ebean.Model;
import utils.StringUtils;

@Entity
@Table(name = "sendnum")
public class SendNum extends Model {

	private static final long serialVersionUID = 1L;
	@Id
	public int id;
	/**
	 * 总记录数
	 */
	@Column
	public long stotal = 0;
	/**
	 * 今日记录数
	 */
	@Column
	public long stoday = 0;
	/**
	 * 单日最大记录数
	 */
	@Column
	public long sday_max = 0;
	/**
	 * 单日最大记录数日期
	 */
	@Column
	public String sday = StringUtils.getCurrentDateString();

	public static Model.Finder<Integer, SendNum> find = new Model.Finder<Integer, SendNum>(
			Integer.class, SendNum.class);

	public static void count() {
		if (find.byId(1) != null) {
			SendNum sn = find.byId(1);
			int one = StringUtils.compareDate(sn.sday, null, 0);
			sn.stotal += 1;
			
			if (one >= 1) {
				sn.stoday = 1;
			}else{
				sn.stoday += 1;
			}
			
			if (sn.stoday > sn.sday_max) {
				sn.sday_max = sn.stoday;
			}
			
			sn.update();
		} else {
			SendNum sn = new SendNum();
			sn.stotal = 1;
			sn.stoday = 1;
			sn.sday_max = 1;
			sn.sday = StringUtils.getCurrentDateString();
			sn.save();
		}
	}
	
	public static SendNum getNum(){
		SendNum sn = find.byId(1);
		if(sn==null){
			sn = new SendNum();
			sn.save();
		}
		int one = StringUtils.compareDate(sn.sday, null, 0);
		if (one >= 1) {
			sn.stoday = 0;
		}
		return sn;
	}
}
