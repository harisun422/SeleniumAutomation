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
		Double value = 0.0;
		String fetched_date = null;
		//format - 04/December/2023
		String provided_day = System.getProperty("DAY");
		if(provided_day == null || provided_day == "") {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMMM/YYYY");
		Date date = new Date();  
		String today_date =(dateFormat.format(date));
		System.out.println("today date: "+today_date);
		value = gp.table(today_date);
		fetched_date = today_date;
		}else {
			fetched_date = provided_day;
			System.out.println("requested date: "+provided_day);
			value = gp.table(provided_day);
			
		}
		
		gp.exit();
		
		
		if(value < 1){
			System.out.println("failed");
			try {
			//send_sms_twilio(fetched_date,false);
			}catch(com.twilio.exception.ApiException e) {
				System.out.println(e);
			}
		}else {
			System.out.println("Passed");
			//send_sms_twilio(fetched_date,value);
		}
		
		
	}

	
public void send_sms_twilio(String date, boolean result) {
		
		System.out.println(System.getenv("TWILIO_ACCOUNT_SID")+ System.getenv("TWILIO_AUTH_TOKEN"));
		Twilio.init(
			    System.getenv("TWILIO_ACCOUNT_SID"),
			    System.getenv("TWILIO_AUTH_TOKEN"));
		
		Message.creator(
			    new PhoneNumber("+919789803687"),
			    new PhoneNumber("+12018175692"),
			    "Gold Rate not available on "+ date)
			  .create();

	}
	
	public void send_sms_twilio(String date, double rate) {
			
			Twilio.init(
				    System.getenv("TWILIO_ACCOUNT_SID"),
				    System.getenv("TWILIO_AUTH_TOKEN"));
			
			Message.creator(
				    new PhoneNumber("+919789803687"),
				    new PhoneNumber("+12018175692"),
				    "Gold Rate on "+ date + " is "+rate)
				  .create();
	}
}
