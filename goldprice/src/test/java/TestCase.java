import org.junit.Test;
import java.util.Date;
import java.text.SimpleDateFormat;
import goldprice.Goldprice;

public class TestCase {

	@Test
	public void testone() throws InterruptedException {
		Goldprice gp =new Goldprice();
		
		//format - 04/December/2023
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMMM/YYYY");
		Date date = new Date();  
		String today_date =(dateFormat.format(date));
		System.out.println("today date: "+today_date);
		Double value = gp.table(today_date);
		if(value < 1){
			System.out.println("failed");
		}else {
			System.out.println("Passed");
		}
		
		gp.exit();
	}
}
