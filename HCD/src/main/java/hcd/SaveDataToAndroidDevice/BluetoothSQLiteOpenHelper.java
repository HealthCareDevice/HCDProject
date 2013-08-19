package hcd.SaveDataToAndroidDevice;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by RayaXP on 8/19/13.
 */
public class BluetoothSQLiteOpenHelper extends SQLiteOpenHelper {
    public BluetoothSQLiteOpenHelper(Context context) {
        super(context, "BluetoothDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        GadgetValuesDB.onCreate(sqLiteDatabase);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        GadgetValuesDB.onUpgrade(sqLiteDatabase,i,i2);
    }
}
