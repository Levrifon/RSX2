package part1;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ReceiveUDP extends Thread {
	DatagramSocket ds;
	DatagramPacket dp;

	public ReceiveUDP(int port) throws IOException {
		this.ds = new DatagramSocket(port,InetAddress.getByName("localhost"));

	}

	@Override
	public void run() {
		while(true) {
			try {
				receive();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void receive() throws IOException {
		byte[] buff;
		buff = new byte[1024];
		dp = new DatagramPacket(buff, buff.length);
		ds.receive(dp);
		System.out.println("Message reçu : " + new String(buff)
				+ " de la part de : " + dp.getSocketAddress());
	}

	public static void main(String[] args) throws NumberFormatException,IOException {
		/*
		 * It is easier to launch the two differents main on a command line
		 * instead of eclipse, because of the parameters
		 */

		ReceiveUDP ru;
		int port;
		if (args.length > 1) {
			port = Integer.parseInt(args[0]);
		} else {
			port = 1234;
		}
		ru = new ReceiveUDP(port);
			/* Here we are always listening in order to receive all the message which will be send */
			ru.start();
	}
}
