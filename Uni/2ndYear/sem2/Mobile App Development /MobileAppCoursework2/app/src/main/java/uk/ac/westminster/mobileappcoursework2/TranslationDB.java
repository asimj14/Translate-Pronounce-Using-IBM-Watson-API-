package uk.ac.westminster.mobileappcoursework2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class TranslationDB extends SQLiteOpenHelper {
    private static final String DATABASE_NAME =
            "Translation . db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME="phrase";
    private static final String TABLE_NAME2 = "language";


    private static final String LanguageColumn1 = "ID";


    private static final String LanguageColumn2 = "language";


    private static final String LanguageColumn3 = "code";


    private static final String LanguageColumn4 = "checkbox";


    public TranslationDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (phraseId INTEGER PRIMARY KEY AUTOINCREMENT,phrase TEXT NOT NULL) " );

        /*db.execSQL("CREATE TABLE " + TABLE_NAME2 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                LanguageColumn2 +" TEXT, " + LanguageColumn3 + " TEXT, "+ LanguageColumn4 + " BOOLEAN)");*/

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(db);
    }

    private String[] LCodes = new String[]{"af","sq","ar","hy","az","ba",
            "eu","be","bn","bs","bg","ca","km","zh","zh-TW","cv",
            "hr","cs","da","nl","en","eo","et","fi","fr","ka","de","el","gu","ht","he","hi",
            "hu","is","id","ga","it","ja","kk","ky","ko","ku","lv","lt","ms","ml","mt","mn","nb","nn","pa","fa","pl","pt","ps","ro","ru","sr",
            "sk", "sl","so","es","sv","ta","te","th","tr","uk","ur","vi"};



    private String[] LNames = new String[]{"Afrikaans","Albanian","Arabic","Armenian","Azerbaijani","Bashkir",
            "Basque","Belarusian","Bengali","Bosnian","Bulgarian","Catalan","Central Khmer", "Chinese (Simplified)","Chinese (Traditional)",
            "Chuvash","Croatian","Czech","Danish","Dutch","English","Esperanto","Estonian","Finnish",
            "French","Georgian","German","Greek","Gujarati","Haitian","Hebrew","Hindi","Hungarian","Icelandic",
            "Indonesian","Irish","Italian","Japanese", "Kazakh","Kirghiz","Korean","Kurdish","Latvian",
            "Lithuanian","Malay","Malayalam","Maltese","Mongolian","Norwegian Bokmal","Norwegian Nynorsk",
            "Panjabi","Persian","Polish","Portuguese","Pushto","Romanian","Russian","Serbian","Slovakian","Slovenian",
            "Somali","Spanish","Swedish","Tamil","Telugu","Thai","Turkish","Ukrainian","Urdu","Vietnamese"};
    public boolean newLanguageData() {

        SQLiteDatabase dBase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();


        long result=0;

        for(int i=0; i<70;i++)
        {
            contentValues.put(LanguageColumn2, LNames[i]);

            contentValues.put(LanguageColumn3, LCodes[i]);

            contentValues.put(LanguageColumn4,true);

            result= dBase.insert(TABLE_NAME2, null, contentValues);
        }



        if (result == -1)
        {
            return false;
        }
        else {
            return true;
        }
    }



}
