package com.tcs.codetest;

import android.graphics.Bitmap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.URL;

/**
 * Created by 983798 on 6/28/2016.
 */
public class ConnectionClass {

    URL mUrl=null;
    HttpURLConnection mHttpURLConnection=null;
    StringBuilder mStringBuilder=null;


    public String getData(String url){
        try {
            mUrl=new URL(url);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.26.53.11", 8080));
            mHttpURLConnection=(HttpURLConnection)mUrl.openConnection(proxy);
            mHttpURLConnection.setDoInput(true);
            mHttpURLConnection.setDoOutput(true);
            mHttpURLConnection.setRequestMethod("POST");
            mHttpURLConnection.setRequestProperty("Content-Type", "application/json");
            mHttpURLConnection.setReadTimeout(40*1000);

            int respCode=mHttpURLConnection.getResponseCode();

            if(respCode==HttpURLConnection.HTTP_OK){
                BufferedReader mBufferedReader=new BufferedReader(new InputStreamReader(mHttpURLConnection.getInputStream()));
                String read;
                mStringBuilder=new StringBuilder();

                while((read=mBufferedReader.readLine())!=null){
                    mStringBuilder.append(read);
                }

            }
            else{

                BufferedReader mBufferedReader=new BufferedReader(new InputStreamReader(mHttpURLConnection.getErrorStream()));
                String read;
                mStringBuilder=new StringBuilder();

                while((read=mBufferedReader.readLine())!=null){
                    mStringBuilder.append(read);
                }

            }
        }
        catch (ProtocolException e) {
            e.printStackTrace();
        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String jsonString=mStringBuilder!=null?mStringBuilder.toString():"";
        return jsonString;
    }
}
