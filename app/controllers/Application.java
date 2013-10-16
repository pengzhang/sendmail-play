package controllers;

import java.util.List;

import models.Page;
import models.SEmail;
import models.SendNum;
import models.SendRecord;

import org.codehaus.jackson.JsonNode;

import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import utils.AppConfig;
import utils.SendUtils;
import views.html.*;

public class Application extends Controller {
  
    public static Result index() {
    	String website = AppConfig.WebSiteName;
    	SendNum sn = SendNum.getNum();
        return ok(index.render(website,sn));
    }
    
    public static Result sendtry(String email) {
    	SEmail smail = new SEmail();
    	smail.email = email;
    	smail.subject = AppConfig.TryEmailSubject;
    	smail.content = AppConfig.TryEmailContent;
    	smail.remote_ip = request().remoteAddress();
    	SendUtils.mail(smail);
    	
    	return redirect("/");
    }
    
    public static Result sendLog(int page, int size){
    	String website = AppConfig.WebSiteName;
    	page -= 1;
    	List<SendRecord> sr = SendRecord.getPage(page, size);
    	Page pg = new Page();
		pg.currentPage = page+1;
		pg.totalPage = (int) (SendNum.getNum().stotal/size)+1;
        return ok(sendlog.render(website,sr,pg));
    }
    
    public static Result help(){
    	String website = AppConfig.WebSiteName;
    	return ok(help.render(website));
    }
    
    @BodyParser.Of(BodyParser.Json.class)
    public static Result sendMail() {
      JsonNode json = request().body().asJson();
      
      SEmail semail = new SEmail();
      semail.email = json.findPath("email").getTextValue();
      semail.subject = json.findPath("subject").getTextValue();
      semail.content = json.findPath("content").getTextValue();
      semail.remote_ip = request().remoteAddress();
      SendUtils.mail(semail);
      return ok("{\"status\":\"success\"}").as("application/json");
    }
    
    
  
}
