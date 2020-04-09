package ru.itis.darZam.twofish;

import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    try (Scanner in = new Scanner(System.in)) {
      System.out.print("Key : ");
      String k = in.nextLine();
      System.out.print("Plain text : ");
      String pt = in.nextLine();

      byte[] bytesInputKey = k.getBytes();
      byte[] bytesPlainText = pt.getBytes();
      byte[] key = new byte[32];
      byte[] plainText = new byte[128];

      int i;

      //заполняем 0 если длина ключа меньше 32
      for (i = 0; i < 32; i++) {
        if (i < bytesInputKey.length) key[i] = bytesInputKey[i];
        else key[i] = (byte) 0;
      }

      //заполняем 0 если длина текста меньше 32
      for (i = 0; i < 128; i++) {
        if (i < bytesPlainText.length) plainText[i] = bytesPlainText[i];
        else plainText[i] = (byte) 0;
      }

      Object twoFishKey = Twofish.makeKey(key);
      byte[] ct = Twofish.blockEncrypt(plainText, 0, twoFishKey);
      String cipherText = new String(ct);
      System.out.println("Cipher text : " + cipherText);

      System.out.print("Enter the key : ");
      String k2 = in.nextLine();
      byte[] secondBytesInputKey = k2.getBytes();
      byte[] secondKey = new byte[32];
      for (i = 0; i < 32; i++) {
        if (i < secondBytesInputKey.length) secondKey[i] = secondBytesInputKey[i];
        else secondKey[i] = (byte) 0;
      }

      Object secondTwoFishKey = Twofish.makeKey(secondKey);
      byte[] cpt = Twofish.blockDecrypt(ct, 0, secondTwoFishKey);
      String ot = new String(cpt);
      System.out.println("Decrypted Text : " + ot);
    }
  }
}