
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import goldprice.Goldprice;

public class TestCase {

	@Test
	public void testone() throws InterruptedException, IOException {
		Goldprice gp =new Goldprice();
		String[] value = {"",""};
		String fetched_date = null;
		//format - 04/December/2023
		String provided_day = System.getProperty("DAY");
		if(provided_day == null || provided_day.isEmpty() || provided_day.length() < 6 ) {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MMM/YYYY");
		LocalDateTime today = LocalDateTime.now();
		String today_date =today.minusHours(10).format(dateFormat);
		System.out.println("today date: "+today_date);
		
		value = gp.table(today_date);
		fetched_date = today_date;
		}else {
			fetched_date = provided_day;
			
			System.out.println("requested date: "+provided_day+";");
			System.out.println("message will be sent from : "+ System.getProperty("twilioVirtualNum"));
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
			try {
			send_sms_twilio("Gold Rate on "+ fetched_date + "\n for 24K is "+value[0]+ "\n for 22K is "+value[1]);
			}catch(com.twilio.exception.ApiException e) {
				System.out.println(e);
			}
		}
		
		
	}

	
	
	public void send_sms_twilio(String message) {
			
		//System.out.println("twilio_account : "+System.getProperty("twilio_account"));
		//System.out.println("twilio_token : "+System.getProperty("twilio_token"));
		String ACCOUNT = System.getenv("TWILIO_ACCOUNT_SID")!=null ? System.getenv("TWILIO_ACCOUNT_SID") : System.getProperty("twilio_account") ;
		String TOKEN = System.getenv("TWILIO_AUTH_TOKEN")!=null ? System.getenv("TWILIO_AUTH_TOKEN") : System.getProperty("twilio_token");
		
		Twilio.init(
			    ACCOUNT,
			    TOKEN);
		
		String toPhNumber = System.getProperty("receiverNum");
		String fromPhNumber = System.getProperty("twilioVirtualNum");
		System.out.println("receiverNum: " + toPhNumber);
		System.out.println("twilioVirtualNum: " + fromPhNumber);
		Message.creator(
			    new PhoneNumber(toPhNumber), //+919789803687
			    new PhoneNumber(fromPhNumber), //This is the twilio virtual phone number (+12346010578)
			    message)
			  .create();
	}
}
