import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;


public class GpioManager {

	final GpioController gpio;
	final GpioPinDigitalInput[] hapticPoints;
	final GpioPinDigitalInput[] fakeBeacons;
	final GpioPinDigitalOutput[] vibesPoints;
	final GpioPinDigitalOutput[] ledPoints;

	public GpioManager() {

		gpio = GpioFactory.getInstance();

		fakeBeacons = new GpioPinDigitalInput[2];

		hapticPoints = new GpioPinDigitalInput[3];
		vibesPoints = new GpioPinDigitalOutput[3];
		ledPoints = new GpioPinDigitalOutput[3];

		fakeBeacons[0] = gpio.provisionDigitalInputPin(PiWedge.G23.pin(),
				PinPullResistance.PULL_DOWN);
		fakeBeacons[1] = gpio.provisionDigitalInputPin(PiWedge.G24.pin(),
				PinPullResistance.PULL_DOWN);

		hapticPoints[0] = gpio.provisionDigitalInputPin(PiWedge.G25.pin(),
				PinPullResistance.PULL_DOWN);
		hapticPoints[1] = gpio.provisionDigitalInputPin(PiWedge.G26.pin(),
				PinPullResistance.PULL_DOWN);
		hapticPoints[2] = gpio.provisionDigitalInputPin(PiWedge.G27.pin(),
				PinPullResistance.PULL_DOWN);

		vibesPoints[0] = gpio.provisionDigitalOutputPin(PiWedge.G4.pin(),
				"Vibe A", PinState.LOW);
		vibesPoints[1] = gpio.provisionDigitalOutputPin(PiWedge.G5.pin(),
				"Vibe B", PinState.LOW);
		vibesPoints[2] = gpio.provisionDigitalOutputPin(PiWedge.G6.pin(),
				"Vibe C", PinState.LOW);

		ledPoints[0] = gpio.provisionDigitalOutputPin(PiWedge.G12.pin(),
				"LED A", PinState.LOW);
		ledPoints[1] = gpio.provisionDigitalOutputPin(PiWedge.G13.pin(),
				"LED B", PinState.LOW);
		ledPoints[2] = gpio.provisionDigitalOutputPin(PiWedge.G16.pin(),
				"LED C", PinState.LOW);

		for (int i = 0; i < 3; i++) {

			if (i < 2) {
				fakeBeacons[i].setShutdownOptions(true);
			}

			hapticPoints[i].setShutdownOptions(true);
			vibesPoints[i].setShutdownOptions(true, PinState.LOW);
			ledPoints[i].setShutdownOptions(true, PinState.LOW);

		}
		System.out.println("Gpio");
	}

	public GpioPinDigitalInput getFakeBeacon(int i) {
		return fakeBeacons[i];
	}

	public GpioPinDigitalInput getHapticPoint(int i) {
		return hapticPoints[i];
	}

	public void shutDown() {
		gpio.shutdown();
	}

}
