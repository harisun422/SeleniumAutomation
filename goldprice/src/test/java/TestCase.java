import org.junit.Test;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.util.Date;
import java.text.SimpleDateFormat;
import goldprice.Goldprice;

public class TestCase {

	@Test
	public void testone() throws InterruptedException {
		Goldprice gp =new Goldprice();
		String[] value = {"",""};
		String fetched_date = null;
		//format - 04/December/2023
		String provided_day = System.getProperty("DAY");
		if(provided_day == null || provided_day.isEmpty() || provided_day.length() < 6 ) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/YYYY");
		Date date = new Date();  
		String today_date =(dateFormat.format(date));
		System.out.println("today date: "+today_date);
		value = gp.table(today_date);
		fetched_date = today_date;
		}else {
			fetched_date = provided_day;
			System.out.println("requested date: "+provided_day+";");
			value = gp.table(provided_day);
			
		}
		
		gp.exit();
		
		
		if(value[0] == ""|| value[0] == null || value[0]==" "){
			System.out.println("failed");
			try {
			send_sms_twilio("Couln't find Gold rate fetching failed, check mail for any exception");
			}catch(com.twilio.exception.ApiException e) {
				System.out.println(e);
			}
		}else {
			System.out.println("Passed");
			send_sms_twilio("Gold Rate on "+ fetched_date + "\n for 24K is "+value[0]+ "\n for 22K is "+value[1]);
		}
		
		
	}

	
	
	public void send_sms_twilio(String message) {
			
			Twilio.init(
				    System.getenv("TWILIO_ACCOUNT_SID"),
				    System.getenv("TWILIO_AUTH_TOKEN"));
			
			Message.creator(
				    new PhoneNumber("+919789803687"),
				    new PhoneNumber("+12018175692"),
				    message)
				  .create();
	}
}
