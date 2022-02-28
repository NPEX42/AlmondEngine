package dev.npex42.almond.common;

import java.io.PrintStream;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale.Category;

public class Logger {
	private static String fmt = "[%l|%t|%T]: %m";
	private static PrintStream out = System.out;
	
	public static void log(String format, Object... args) {
		out.println(parseFormat("Log", String.format(format, args)));
	}
	
	public static void warn(String format, Object... args) {
		out.println(parseFormat("Warn", String.format(format, args)));
	}
	
	public static void err(String format, Object... args) {
		out.println(parseFormat("Err", String.format(format, args)));
	}
	
	
	private static String parseFormat(String level, String msg) {
		SimpleDateFormat dt = new SimpleDateFormat("HH:mm:ss:SSS");
		Date now = Time.from(Instant.now());
		String result = fmt.replaceAll("%l", level);
		result = result.replaceAll("%t", Thread.currentThread().getName());
		result = result.replaceAll("%T", dt.format(now));
		
		result = result.replaceAll("%m", msg);
		
		return result;
	}
}
