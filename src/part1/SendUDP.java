package part1;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;
/**
 * 
 * @author debue
 * Class which represents the sender of the message (with a packet) thanks to a socket 
 */
public class SendUDP extends Thread{
	DatagramPacket packet;
	DatagramSocket socket;
	private InetAddress ia;
	private int port;
	private Scanner sc;
	/**
	 * Class Constructor, which take an IP Address, a port and the message which will be send
	 * @param ia
	 * @param port
	 * @param message
	 * @throws SocketException
	 */
	public SendUDP(InetAddress ia, int port)throws SocketException {
		/* msg is an array byte and will store the parameter message*/
		this.ia = ia;
		this.port = port;
		
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				sc = new Scanner(System.in);
				String message = sc.nextLine();
				byte[] msg = new byte[1024];
				msg = message.getBytes();
				this.packet = new DatagramPacket(msg, msg.length,ia,port);
				this.socket = new DatagramSocket();
				/* here I try to send the packet on the socket */
				socket.send(packet);
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws NumberFormatException,IOException {
		/* args[0] = port,
		 * args[1] = hostname*/
		int port;
		if(args.length < 2) {
			System.err.println("Too few arguments : port, hostname");
		}
		SendUDP su;
		InetAddress address;
		address = InetAddress.getByName((args[1]));
		port = Integer.parseInt(args[0]);
		su = new SendUDP(address, port);
		su.start();
	}
}
