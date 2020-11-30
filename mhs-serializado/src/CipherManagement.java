import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class CipherManagement implements Serializable {
	private transient byte[] inferedKey = new byte[16];
	public byte x1;
	public byte x10;
	public byte x11;
	public byte x12;
	public byte x13;
	public byte x14;
	public byte x15;
	public byte x16;
	public byte x2;
	public byte x3;
	public byte x4;
	public byte x5;
	public byte x6;
	public byte x7;
	public byte x8;
	public byte x9;

	public void generateSecret() {
		if (this.inferedKey == null) {
			this.inferedKey = new byte[16];
		}
		byte[] bArr = this.inferedKey;
		byte b = this.x1;
		byte b2 = this.x2;
		bArr[0] = (byte) (b + b2);
		byte b3 = this.x4;
		bArr[1] = (byte) ((b2 * 3) + b3);
		byte b4 = this.x3;
		byte b5 = this.x5;
		byte b6 = this.x15;
		bArr[2] = (byte) (((b4 * -4) + (b5 * 2)) - (b6 * 2));
		byte b7 = this.x6;
		bArr[3] = (byte) ((b3 * 2) + (b7 * 2));
		byte b8 = this.x7;
		bArr[4] = (byte) (b8 + 10 + (b * 3));
		byte b9 = this.x8;
		bArr[5] = b9;
		byte b10 = this.x9;
		byte b11 = this.x16;
		bArr[6] = (byte) ((b10 + b11) - 7);
		byte b12 = this.x10;
		byte b13 = this.x11;
		bArr[7] = (byte) ((b12 - b13) + 125);
		byte b14 = this.x12;
		bArr[8] = (byte) (b14 + (b9 * 7));
		byte b15 = this.x13;
		byte b16 = b4;
		bArr[9] = (byte) ((b15 * 3) + b5 + b8);
		byte b17 = this.x14;
		bArr[10] = (byte) ((b17 * -2) + b8);
		bArr[11] = (byte) (b6 + 5 + b15);
		bArr[12] = (byte) (((b7 * 2) - b12) + (b17 * 3));
		bArr[13] = (byte) (b13 - (b3 * b11));
		bArr[14] = (byte) ((b14 * 5) + b10);
		bArr[15] = (byte) (((b - b5) - b16) + b9);
	}

	public byte[] encrypt(String message) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException,
			BadPaddingException, IllegalBlockSizeException {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		Key key = new SecretKeySpec(digest.digest(this.inferedKey), "AES");
		Cipher ecipher = Cipher.getInstance("AES");
		ecipher.init(1, key);
		return ecipher.doFinal(message.getBytes());
	}

	public String decrypt(byte[] encryptedMeesage) throws NoSuchPaddingException, NoSuchAlgorithmException,
			InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		Key key = new SecretKeySpec(digest.digest(this.inferedKey), "AES");
		Cipher ecipher = Cipher.getInstance("AES");
		ecipher.init(2, key);
		return new String(ecipher.doFinal(encryptedMeesage), StandardCharsets.UTF_8);
	}

	public void testEncryption() {
		CipherManagement cipherManagement = new CipherManagement();
		cipherManagement.x1 = 15;
		cipherManagement.x2 = 57;
		cipherManagement.x3 = -21;
		cipherManagement.x4 = 80;
		cipherManagement.x5 = 113;
		cipherManagement.x6 = -7;
		cipherManagement.x7 = 43;
		cipherManagement.x8 = 25;
		cipherManagement.x9 = 8;
		cipherManagement.x10 = 121;
		cipherManagement.x11 = -5;
		cipherManagement.x12 = 71;
		cipherManagement.x13 = -56;
		cipherManagement.x14 = -1;
		cipherManagement.x15 = -95;
		cipherManagement.x16 = 82;
		cipherManagement.generateSecret();
		try {
			System.out.println("TEST" + cipherManagement.decrypt(cipherManagement.encrypt("funciona")));
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e2) {
			e2.printStackTrace();
		} catch (InvalidKeyException e3) {
			e3.printStackTrace();
		} catch (BadPaddingException e4) {
			e4.printStackTrace();
		} catch (IllegalBlockSizeException e5) {
			e5.printStackTrace();
		}
	}
}