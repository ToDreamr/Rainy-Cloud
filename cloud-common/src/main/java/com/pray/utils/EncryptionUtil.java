package com.pray.utils;

import java.util.Arrays;
import java.util.Base64;

/**
 * EncryptionUtil
 * 加密算法工具类
 * @author 春江花朝秋月夜
 * @since 2024/3/21 15:23
 */
public class EncryptionUtil {

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,9,5,6,10,11,13,15,17};
        int[] mos = new int[arr.length];
        System.out.println(Arrays.toString(mos));
        findMiddle(arr,mos);
        System.out.println(Arrays.toString(mos));
        for (int i = 0; i < arr.length; i++) {
            if (mos[i] == 2) {
                System.out.print(arr[i]);
                System.out.print(" ");
            }
        }
    }

    static void findMiddle(int[] arr,int[] mos) {
        if (null == arr || arr.length <= 2) {
            return;
        }
        int max = 0;
        int min = arr.length-1;
        for (int i = 0; i < arr.length; i++) {
            max=arr[max]<=arr[i]?i:max;
            if (max == i) {
                mos[i]++;
            }
        }
        for (int i = arr.length-1; i >=0; i--) {
            min=arr[min]>=arr[i]?i:min;
            if (min == i) {
                mos[i]++;
            }
        }
    }

    //Base64加密
    /**
     * Base64工具类，提供Base64的编码和解码方案<br>
     * base64编码是用64（2的6次方）个ASCII字符来表示256（2的8次方）个ASCII字符，<br>
     * 也就是三位二进制数组经过编码后变为四位的ASCII字符显示，长度比原来增加1/3。
     *
     * @author Rainy
     */
    public Base64.Encoder getBase64Encryption(){
        return Base64.getEncoder();
    }
}
/**
 * HMAC算法类型<br>
 * see: https://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#Mac
 *
 * @author Looly
 */
