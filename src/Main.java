import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class Main {
	
	static GpioManager gm;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hey");
		
		gm = new GpioManager();
		
		gm.getFakeBeacon(0).addListener(new GpioPinListenerDigital() {

			@Override
			public void handleGpioPinDigitalStateChangeEvent(
					
					GpioPinDigitalStateChangeEvent e) {
				// TODO Auto-generated method stub
				if (e.getState() == PinState.HIGH) {
					
					System.out.println("Beacon 0 - send: 'stop' ");
				}
			}
		});
		
		gm.getFakeBeacon(1).addListener(new GpioPinListenerDigital() {

			@Override
			public void handleGpioPinDigitalStateChangeEvent(
					
					GpioPinDigitalStateChangeEvent e) {
				// TODO Auto-generated method stub
				if (e.getState() == PinState.HIGH) {
					
					System.out.println("Beacon 1 - send: 'Oscar' ");
				}
			}
		});
		
		while(true){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
