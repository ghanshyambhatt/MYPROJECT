package com.java.Helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import com.java.Helper.KeyStoreUtils;
import com.java.resource.property.PropertyLoader;
import com.java.rest.webservice.FileService;

public class Encryption {
	private String encryptedFilePath;
	private String dcryptedFilePath;
	private String directoryPath = PropertyLoader.getPropertyValue("encryptedfilepath");
	private String directoryPath2 = PropertyLoader.getPropertyValue("dcryptedfilepath");
	private String secretkeyfilepath =PropertyLoader.getPropertyValue("secretkeyfilepath");
	KeyGenerator keyGenerator = null;
	SecretKey secretKey = null;
	Cipher cipher = null;

	public Encryption() {
		
		try {
			File file = new File(secretkeyfilepath);
			secretKey = KeyStoreUtils.loadKey(file);
			cipher = Cipher.getInstance("AES");
		} catch (NoSuchPaddingException ex) {
			System.out.println(ex);
		} catch (NoSuchAlgorithmException ex) {
			System.out.println(ex);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String encryptFile(String fileToEncrypt, String filename) {
		// this method is used for encrypting a file.
		Encryption encryptFile = new Encryption();
		encryptFile.encrypt(fileToEncrypt, directoryPath + filename);
		encryptedFilePath = directoryPath + filename;
		return encryptedFilePath;
	}

	public String dcryptFile(String filename) {
		// this method is used for dcrypting a file.
		Encryption encryptFile = new Encryption();
		encryptFile
				.decrypt(directoryPath + filename, directoryPath2 + filename);
		dcryptedFilePath = directoryPath2 + filename;
		
		FileService service=new FileService();
		service.delete(directoryPath+filename);//deleting old encrypted file from server.
		
		return dcryptedFilePath;
	}

	private void encrypt(String srcPath, String destPath) {
		File rawFile = new File(srcPath);
		File encryptedFile = new File(destPath);
		InputStream inStream = null;
		OutputStream outStream = null;
		try {
			/**
			 * Initialize the cipher for encryption
			 */
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			/**
			 * Initialize input and output streams
			 */
			inStream = new FileInputStream(rawFile);
			outStream = new FileOutputStream(encryptedFile);
			byte[] buffer = new byte[1024];
			int len;
			while ((len = inStream.read(buffer)) > 0) {
				outStream.write(cipher.update(buffer, 0, len));
				outStream.flush();
			}
			outStream.write(cipher.doFinal());
			inStream.close();
			outStream.close();
		} catch (IllegalBlockSizeException ex) {
			System.out.println(ex);
		} catch (BadPaddingException ex) {
			System.out.println(ex);
		} catch (InvalidKeyException ex) {
			System.out.println(ex);
		} catch (FileNotFoundException ex) {
			System.out.println(ex);
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}

	private void decrypt(String srcPath, String destPath) {
		File encryptedFile = new File(srcPath);
		File decryptedFile = new File(destPath);
		InputStream inStream = null;
		OutputStream outStream = null;
		try {

			/**
			 * Initialize the cipher for decryption
			 */
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			/**
			 * Initialize input and output streams
			 */
			inStream = new FileInputStream(encryptedFile);
			outStream = new FileOutputStream(decryptedFile);
			byte[] buffer = new byte[1024];
			int len;
			while ((len = inStream.read(buffer)) > 0) {
				outStream.write(cipher.update(buffer, 0, len));
				outStream.flush();
			}
			outStream.write(cipher.doFinal());
			inStream.close();
			outStream.close();
		} catch (IllegalBlockSizeException ex) {
			System.out.println(ex);
		} catch (BadPaddingException ex) {
			System.out.println(ex);
		} catch (InvalidKeyException ex) {
			System.out.println(ex);
		} catch (FileNotFoundException ex) {
			System.out.println(ex);
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}
}
