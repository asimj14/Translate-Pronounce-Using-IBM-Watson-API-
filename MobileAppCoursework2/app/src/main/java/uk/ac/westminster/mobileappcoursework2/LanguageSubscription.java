package uk.ac.westminster.mobileappcoursework2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LanguageSubscription extends AppCompatActivity {
    private TranslationDB translationDB; // declaration

    public static ArrayList<String> langList;
    public static ArrayAdapter langadapter;
    public static ListView langListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_subscription);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        translationDB = new TranslationDB(this); //Initialization
        //AddDataLanguage();
        langListView = findViewById(R.id.languages_listView);

        langList = new ArrayList<>();
        langList.add("Italian - it \n");
        langList.add("Spanish - es \n");
        langList.add("Greek - el \n");
        langList.add("French - fr \n");
        langList.add("German - de");


        langadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, langList);
        langListView.setAdapter(langadapter);


    }
}
