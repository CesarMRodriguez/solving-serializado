import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Calendar;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String content = "jBUwXJdjVaHvZuYfaw/cew==";
		byte array[] = Base64.getDecoder().decode(content);

		CipherManagement cipher = new CipherManagement();

		cipher.x1 = 97;
		cipher.x2 = 50;
		cipher.x3 = 102;
		cipher.x4 = 53;
		cipher.x5 = 116;
		cipher.x6 = 113;
		cipher.x7 = 53;
		cipher.x8 = 107;
		cipher.x9 = 57;
		cipher.x10 = 110;
		cipher.x11 = 100;
		cipher.x12 = 116;
		cipher.x13 = 53;
		cipher.x14 = 49;
		cipher.x15 = 48;
		cipher.x16 = 108;

		cipher.generateSecret();
		CharsetEncoder encoder = Charset.forName("US-ASCII").newEncoder();

		//starting time 
		printTime();
		// rotating positions
		for (int pos1 = 0; pos1 < 14; pos1++) {
			for (int pos2 = pos1 + 1; pos2 < 15; pos2++) {
				for (int pos3 = pos2 + 1; pos3 < 16; pos3++) {
					
					setStaticValues(cipher, pos1, pos2, pos3);
					for (byte x = 49; x <= 122; x++) {
						setValue(cipher, pos1, x);
						for (byte y = 49; y <= 122; y++) {
							setValue(cipher, pos2, y);
							for (byte z = 49; z <= 122; z++) {
								setValue(cipher, pos3, z);
								cipher.generateSecret();
								String decrypted;
								try {
									decrypted = cipher.decrypt(array);
									if (encoder.canEncode(decrypted)) {
										System.out.println(decrypted);
										if ("banana_loca".equals(decrypted)) {
											printTime();
											return;
										}
									}
									
								} catch (Exception e) {
									// TODO Auto-generated catch block
								}
							}
						}
					}
				}
			}
		}
	}

	static byte knownValues[] = { 97, 110, 100, 116, 53, 48, 108, 50, 53, 116, 113, 107, 57, };

	private static void printTime() {

		Calendar calendar = Calendar.getInstance();
		// Returns current time in millis
		long timeMilli2 = calendar.getTimeInMillis();
		System.out.println("Time in milliseconds: " + timeMilli2);
	}

	private static void setStaticValues(CipherManagement cipher, int pos1, int pos2, int pos3) {
		// TODO Auto-generated method stub
		byte array[] = new byte[16];
		int contador = 0;
		for (int i = 0; i < 16; i++) {
			if (i == pos1 || i == pos2 || i == pos3) {
				setValue(cipher, i, (byte) 0);
			} else {
				setValue(cipher, i, knownValues[contador]);
				contador++;
			}
		}
	}

	private static void setValue(CipherManagement cipher, int pos, byte value) {
		switch (pos) {
		case 0:
			cipher.x1 = value;
			break;
		case 1:
			cipher.x10 = value;
			break;
		case 2:
			cipher.x11 = value;
			break;
		case 3:
			cipher.x12 = value;
			break;
		case 4:
			cipher.x13 = value;
			break;
		case 5:
			cipher.x14 = value;
			break;
		case 6:
			cipher.x15 = value;
			break;
		case 7:
			cipher.x16 = value;
			break;
		case 8:
			cipher.x2 = value;
			break;
		case 9:
			cipher.x3 = value;
			break;
		case 10:
			cipher.x4 = value;
			break;
		case 11:
			cipher.x5 = value;
			break;
		case 12:
			cipher.x6 = value;
			break;
		case 13:
			cipher.x7 = value;
			break;
		case 14:
			cipher.x8 = value;
			break;
		case 15:
			cipher.x9 = value;
			break;
		}
	}

}
