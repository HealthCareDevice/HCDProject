package hcd.SaveDataToAndroidDevice;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by RayaXP on 8/19/13.
 */
public class GadgetValuesDB {
    static String $bluGadgetValues="bluGadgetValues";
    static String $id="_id";
    static String $gadgetName="gadgetName";
    static String $gadgetValue="gadgetValue";
    static String $date="date";
    static String $time="time";
    static String  $createDataBase="CREATE TABLE IF NOT EXISTS "+$bluGadgetValues+
            " ("+$id+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            $gadgetName+","+
            $gadgetValue+","+
            $date+","+
            $time+");";
    public static void onCreate(SQLiteDatabase $sQLiteDatabase){
        $sQLiteDatabase.execSQL($createDataBase);
    }
    public static void onUpgrade(SQLiteDatabase $sQLiteDatabase, int oldVersion, int newVersion) {
        $sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + $bluGadgetValues);
        onCreate($sQLiteDatabase);
    }
}
