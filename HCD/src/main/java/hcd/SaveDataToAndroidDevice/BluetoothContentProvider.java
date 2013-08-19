package hcd.SaveDataToAndroidDevice;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

/**
 * Created by RayaXP on 8/19/13.
 */
public class BluetoothContentProvider extends ContentProvider {
    BluetoothSQLiteOpenHelper $bluetoothSQLiteOpenHelper;
    private static final String AUTHORITY = "cob.example.contentprovider7";
    public static final Uri CONTENT_URI =Uri.parse("content://" + AUTHORITY + "/gadgetValues");
    @Override
    public boolean onCreate() {
        $bluetoothSQLiteOpenHelper=new BluetoothSQLiteOpenHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings2, String s2) {
        SQLiteDatabase $sQLiteDatabase = $bluetoothSQLiteOpenHelper.getWritableDatabase();
        SQLiteQueryBuilder $sQLiteQueryBuilder = new SQLiteQueryBuilder();
        $sQLiteQueryBuilder.setTables(GadgetValuesDB.$bluGadgetValues);
        Cursor cursor = $sQLiteQueryBuilder.query($sQLiteDatabase, strings, s,
                strings2, null, null, s2);
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        SQLiteDatabase $sQLiteDatabase = $bluetoothSQLiteOpenHelper.getWritableDatabase();
        long id = $sQLiteDatabase.insert(GadgetValuesDB.$bluGadgetValues, null, contentValues);
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(CONTENT_URI + "/" + id);
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        SQLiteDatabase $sQLiteDatabase = $bluetoothSQLiteOpenHelper.getWritableDatabase();
        String id = uri.getPathSegments().get(1);
        s = GadgetValuesDB.$id + "=" + id+ (!TextUtils.isEmpty(s) ?" AND (" + s + ')' : "");
        int deleteCount = $sQLiteDatabase.delete(GadgetValuesDB.$bluGadgetValues, s, strings);
        getContext().getContentResolver().notifyChange(uri, null);
        return deleteCount;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase $sQLiteDatabase = $bluetoothSQLiteOpenHelper.getWritableDatabase();
        String id = uri.getPathSegments().get(1);
        selection = GadgetValuesDB.$id + "=" + id
                + (!TextUtils.isEmpty(selection) ?
                " AND (" + selection + ')' : "");
        int updateCount = $sQLiteDatabase.update(GadgetValuesDB.$bluGadgetValues, values, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return updateCount;
    }
}
