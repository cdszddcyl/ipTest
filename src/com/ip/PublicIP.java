package com.ip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;

public class PublicIP {
	
	public static void main(String[] args) {  
        try {  
            String ip1 = getMyIP();  
            System.out.println("myIP:" + ip1);  
            String ip2 = getMyIPLocal();  
            System.out.println("myLocalIP:" + ip2);  
        } catch (IOException e1) {  
            e1.printStackTrace();  
        }  
    }  
      
    private static String getMyIP() throws IOException {  
        InputStream ins = null;  
        try {  
            URL url = new URL("https://ipip.yy.com/get_ip_info.php");  
            URLConnection con = url.openConnection();  
            ins = con.getInputStream();  
            InputStreamReader isReader = new InputStreamReader(ins, "UTF-8");  
            BufferedReader bReader = new BufferedReader(isReader);  
            StringBuffer webContent = new StringBuffer();  
            String str = null;  
            while ((str = bReader.readLine()) != null) {  
                webContent.append(str);  
            }  
            int start = webContent.indexOf("=") + 1; 
            Gson gson=new Gson();
            Model model= gson.fromJson(webContent.substring(start).replace(" ", "").replace(";", ""), Model.class);
            return model.getCip();  
        } finally {  
            if (ins != null) {  
                ins.close();  
            }  
        }  
    }  
  
    private static String getMyIPLocal() throws IOException {  
        InetAddress ia = InetAddress.getLocalHost();  
        return ia.getHostAddress();  
    }  
    
    class Model{
    	private String cip;

		public String getCip() {
			return cip;
		}

		public void setCip(String cip) {
			this.cip = cip;
		}
    	
    	
    }

}
