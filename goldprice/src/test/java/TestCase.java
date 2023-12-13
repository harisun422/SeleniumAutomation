import org.junit.Test;

import goldprice.Goldprice;

public class TestCase {

	@Test
	public void testone() throws InterruptedException {
		Goldprice gp =new Goldprice();
		
		//format - 04/December/2023
		
		Double value = gp.table("12/December/2023");
		if(value < 1){
			System.out.println("failed");
		}else {
			System.out.println("Passed");
		}
		
		gp.exit();
	}
}
