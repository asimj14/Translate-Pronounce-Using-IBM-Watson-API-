package uk.ac.westminster.mobileappcoursework2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayPhrases extends AppCompatActivity {

    private TranslationDB translationDB; // declaration
    private static final String phrase = "phrase";
    private static final String TABLE_NAME="phrase";
    TextView result;
    Cursor cursor;
    ArrayList <String> phrasesList;
    ArrayAdapter adapter;
    ListView userList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_phrases);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        translationDB = new TranslationDB(this); //Initialization


        //linking varaibles with components

        userList = findViewById(R.id.phrases_list);
        cursor = getPhrases();
        showPhrases(cursor);
        userList.setOnItemClickListener(new AdapterView.OnItemClickListener(){


            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text = userList.getItemAtPosition(i).toString();
                Toast.makeText(DisplayPhrases.this,""+text, Toast.LENGTH_SHORT).show();

            }
        });

        translationDB.close();


    }
        private Cursor getPhrases () {
            /* Perform a managed query . The Activity will
            handle closing and re - querying the cursor
            when needed . */
            SQLiteDatabase db = translationDB . getReadableDatabase ();
            phrasesList = new ArrayList<>();
            Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME+" ORDER BY "+phrase,null);
            //Cursor cursor = db.rawQuery("DELETE FROM "+TABLE_NAME,null);
            return cursor ;
        }



        public void showPhrases(Cursor cursor){

            if(cursor.getCount()==0){

                Toast.makeText(this,"No phrases saved to be displayed!",Toast.LENGTH_LONG).show();

            }else{
                while(cursor.moveToNext()){

                    phrasesList.add(cursor.getString(1));

                }
                adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,phrasesList);
                userList.setAdapter(adapter);



            }

        }
}


