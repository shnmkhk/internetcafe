package org.rabbit.netcafe.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceUtil {
	private static ResourceBundle resouceBundle = ResourceBundle.getBundle("locale", Locale.ENGLISH);

	public static String getMessage(String key, int spaces) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < spaces; ++i) {
			sb.append(" ");
		}
		sb.append(resouceBundle.getString(key));
		for (int i = 0; i < spaces; ++i) {
			sb.append(" ");
		}
		return sb.toString();
	}

	public static String getMessage(String key) {
		return getMessage(key, 0);
	}
}