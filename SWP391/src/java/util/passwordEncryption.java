/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.security.MessageDigest;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 *
 * author: LamHP
 */
public class passwordEncryption {

    public static String toSHA1(String str) {
        // Thêm vào sau mật khẩu một đoạn để nâng cao độ phức tạp của mật khẩu
        String salt = "ahsbdajnsbdj21ek;ádjuadawdwd231";
        String result = null;

        // Kết hợp chuỗi SHA-1 với chuỗi salt 
        str = str + salt;
        try {
            // Truyền vào bảng mã đang sử dụng 
            // Chuyển chuỗi kết hợp (chuỗi đầu vào + muối) thành mảng byte sử dụng mã hóa UTF-8.
            byte[] dataBytes = str.getBytes("UTF-8");

            MessageDigest md = MessageDigest.getInstance("SHA-1");
            // Truyền vào chuỗi cần mã hóa và method mã hóa
            result = Base64.encodeBase64String(md.digest(dataBytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
