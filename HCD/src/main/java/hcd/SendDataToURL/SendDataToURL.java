package hcd.SendDataToURL;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import hcd.GetDataFromBluetoothGadget.R;

public class SendDataToURL extends Activity {
    private static final String SOAP_ACTION = "http://hcw/HeartRate";
    private static final String METHOD_NAME = "HeartRate";
    private static final String NAMESPACE = "http://hcw/";
    private static final String URL = "http://192.168.1.2:8080/HealthCareWebService/HealthCareWebService?WSDL";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.senddatatourl);
        Thread networkThread = new Thread() {
            @Override
            public void run() {
                try {
                    SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
                    request.addProperty("userID","17");
                    request.addProperty("Max","120");
                    request.addProperty("Min","80");
                    request.addProperty("Date","2013/8/5 12:35");
                    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                    envelope.setOutputSoapObject(request);

                    HttpTransportSE ht = new HttpTransportSE(URL);
                    ht.call(SOAP_ACTION, envelope);
                    final SoapPrimitive response = (SoapPrimitive)envelope.getResponse();
                    final String str = response.toString();

                    runOnUiThread (new Runnable(){
                        public void run() {
                            Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                catch (Exception e) {
                    Log.e("Surena", e.toString());

                }
            }
        };
        networkThread.start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.send_data_to_url, menu);
        return true;
    }
    
}
