package com.newroad.user.sns.util;

public class CommonUtils {

  public static String toUnicode(String s) {
    String as[] = new String[s.length()];
    String s1 = "";
    for (int i = 0; i < s.length(); i++) {
      as[i] = Integer.toHexString(s.charAt(i) & 0xffff);
      s1 = s1 + as[i] + "\t";
    }
    return s1;
  }
}
