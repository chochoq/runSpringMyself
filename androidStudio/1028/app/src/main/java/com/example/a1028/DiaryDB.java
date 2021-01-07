package com.example.a1028;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DiaryDB extends SQLiteOpenHelper {
    public DiaryDB(@Nullable Context context) {
        super(context, "diary.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table diary(_id integer primary key autoincrement, date text, subject text,content text)");
        db.execSQL("insert into diary(date,subject,content) values('2020-09-20','안드로이드..','으어어어하ㅏ하')");
        db.execSQL("insert into diary(date,subject,content) values('2020-09-21','!이드..','으어꺄르륵ㅏ하')");
        db.execSQL("insert into diary(date,subject,content) values('2020-09-22','안나이ㅏㅏㄴ','으이;ㅎ;ㅏ하')");
        db.execSQL("insert into diary(date,subject,content) values('2020-09-23','꺄르르르르르르르','웃음소리히히ㅣ')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
