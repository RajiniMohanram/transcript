package com.dmi.sjbu.proj.transcript.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class AppUtil {
	public static Connection getConnection() {
		Connection con = null;
		try {
			Properties prop = getSettings();
			Class.forName(prop.getProperty("db.driver"));
			con = DriverManager.getConnection(prop.getProperty("db.url"), 
					prop.getProperty("db.user"), prop.getProperty("db.password"));
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}
	
	public static Properties getSettings() {
		Properties prop = new Properties();
		try(InputStream is = AppUtil.class.getClassLoader().getResourceAsStream("appsetting.properties")) {
			if(is == null) {
				throw new Exception("app setting properties not found");
			}
			prop.load(is);
		} catch (Exception e) {
			System.out.println(e);
		}
		return prop;
	}
}
