package com.example.dictionary;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class dict extends ContentProvider {
    static final String PROVIDER_NAME = "com.example.dictionary" ;
    static final String URL = "content://" + PROVIDER_NAME + "/login/" ;
    static final Uri CONTENT_URI = Uri.parse (URL);

    DBHelper helper;
    SQLiteDatabase db;

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the gi ven URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long rowID = db.insert("login", "", values);

        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }

        return null;
    }

    @Override
    public boolean onCreate() {
            helper=new DBHelper(getContext());
            db=helper.getWritableDatabase();
            return (db==null) ?false: true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        db=helper.getReadableDatabase();
        Cursor c=db.query("login",projection,selection,selectionArgs,null,null,null);
        return c;

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}