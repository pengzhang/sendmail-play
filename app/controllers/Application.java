package controllers;

import models.SEmail;
import play.mvc.Controller;
import play.mvc.Result;
import utils.AppConfig;
import utils.SendUtils;
import views.html.index;

public class Application extends Controller {
  
    public static Result index() {
    	String website = AppConfig.WebSiteName;
        return ok(index.render(website));
    }
    
    public static Result sendtry(String email) {
    	
    	SEmail smail = new SEmail();
    	smail.email = email;
    	smail.subject = "欢迎使用大土狗邮件发送系统";
    	smail.content = "大土狗邮件发送系统";
    	SendUtils.mail(smail);
    	return redirect("/");
    }
  
}
