package hcd.SendDataToURL;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import hcd.GetDataFromBluetoothGadget.R;

public class SendDataToURLByREST extends Activity {
    int resCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_data_to_url_by_rest);

        Thread newt1=new Thread(){
            @Override
            public void run() {
                HttpPost request = new HttpPost("http://192.168.1.3:8080/HealthCareDeviceRESTWebservice/webresources/HCD/heartrate");
                request.setHeader("Content-type", "application/json");
                try {
                    StringEntity entity = new StringEntity("{\"Heart Rate\":{\"UserID\":18,\"Max\":120,\"Min\":80,\"Date\":\"2013/5/8 12:44\"}}");
                    request.setEntity(entity);
                    DefaultHttpClient httpClient = new DefaultHttpClient();
                    HttpResponse response = httpClient.execute(request);
                    resCode = response.getStatusLine().getStatusCode();
                    if (resCode == 200) {
                        BufferedReader in =
                                new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                        String line="";
                        StringBuffer returnFromServer = new StringBuffer();
                        while ((line=in.readLine())!=null)
                        {
                            returnFromServer.append(line);

                        }
                        final String str1=returnFromServer.toString();
                        runOnUiThread (new Runnable(){
                            public void run() {
                                Toast.makeText(getApplicationContext(), str1, Toast.LENGTH_SHORT).show();
                            }
                        });
                        if (entity != null)
                        {
                            entity.consumeContent();
                        }
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        newt1.start();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.send_data_to_urlby_rest, menu);
        return true;
    }
    
}
