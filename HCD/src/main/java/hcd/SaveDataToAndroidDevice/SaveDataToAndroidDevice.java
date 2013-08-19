package hcd.SaveDataToAndroidDevice;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import hcd.GetDataFromBluetoothGadget.R;

public class SaveDataToAndroidDevice extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {
    SimpleCursorAdapter $simpleCursorAdapter;
    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
                GadgetValuesDB.$id,
                GadgetValuesDB.$gadgetName,
                GadgetValuesDB.$gadgetValue,
                GadgetValuesDB.$date,
                GadgetValuesDB.$time
        };
        CursorLoader cursorLoader = new CursorLoader(this,
                BluetoothContentProvider.CONTENT_URI, projection, null, null, null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        $simpleCursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        $simpleCursorAdapter.swapCursor(null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.savedatatoandroiddevice_main);
        String[] columns = new String[] {
                GadgetValuesDB.$id,
                GadgetValuesDB.$gadgetName,
                GadgetValuesDB.$gadgetValue,
                GadgetValuesDB.$date,
                GadgetValuesDB.$time
        };
        int[] to = new int[] {

                R.id.textView_id,
                R.id.textView_gadgetName,
                R.id.textView_gadgetValue,
                R.id.textView_date,
                R.id.textView_time

        };

        $simpleCursorAdapter = new SimpleCursorAdapter(
                this,
                R.layout.gadget_info,
                null,
                columns,
                to,
                0);

        ListView listView = (ListView) findViewById(R.id.listView_GadgetData);
        // Assign adapter to ListView
        listView.setAdapter($simpleCursorAdapter);
        getLoaderManager().initLoader(0, null, this);



        ContentValues values = new ContentValues();
        values.put(GadgetValuesDB.$gadgetName, "BloodPressure");
        values.put(GadgetValuesDB.$gadgetValue,"120:80");
        values.put(GadgetValuesDB.$date, "2013/8/9");
        values.put(GadgetValuesDB.$time, "12:32");

        getContentResolver().insert(BluetoothContentProvider.CONTENT_URI, values);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.save_data_to_android_device, menu);
        return true;
    }
    
}
