package main.java.com.rectus29.catfeeder;

import com.pi4j.io.gpio.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class CatFeeder{

	public static int MOTORRUNNINGMILLIS = 2000;

	private static final Logger logger = LogManager.getLogger(CatFeeder.class);
	
	public static void main(String[] args) {
		
			//init spring application
			SpringApplication.run(CatFeeder.class);
	}

	@Scheduled(cron = "* 30 8/18 * * *")
	public void CatFeedTask (){
		System.out.println("start distribution");
		try {
			// write your code here
			GpioController gpioController = GpioFactory.getInstance();
			GpioPinDigitalOutput motorRelayCommand = gpioController.provisionDigitalOutputPin(
					RaspiPin.GPIO_07,
					"motorRelayCommand",
					PinState.LOW
			);
			System.out.println("distribution inprogress");
			motorRelayCommand.setState(PinState.HIGH);
			this.wait(MOTORRUNNINGMILLIS);
			motorRelayCommand.setState(PinState.LOW);
			System.out.println("distribution done");
		} catch (InterruptedException e) {
			System.out.println("distribution error");
			e.printStackTrace();
		}
	}
	
}
