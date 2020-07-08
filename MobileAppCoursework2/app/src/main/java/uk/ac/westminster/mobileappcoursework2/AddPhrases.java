package uk.ac.westminster.mobileappcoursework2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddPhrases extends AppCompatActivity {

    private TranslationDB translationDB; // declaration
    private static final String phrase = "phrase";
    private static final String TABLE_NAME="phrase";

    EditText phrase_text;
    Button btnInsertPhrase;
    TextView result;
    String insertedPhrase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_phrases);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        translationDB = new TranslationDB(this); //Initialization

        //linking varaibles with components
        phrase_text = (EditText) findViewById(R.id.editText_add);
        btnInsertPhrase = (Button)findViewById(R.id.save_btn);
        result = (TextView) findViewById(R.id.result_txt);
        translationDB.close();

    }

    private boolean addPhrase(String Phrase){
        /* Inserting a new record into our trnslationDB database*/

        SQLiteDatabase db = translationDB.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(phrase,Phrase);
        long result = db.insert(TABLE_NAME,null, values);
            if(result == -1)
                return false;
            else
                return true;

    }
    boolean isInserted = true;

    public void save(View view){



        insertedPhrase = phrase_text.getText().toString();
        if(TextUtils.isEmpty(insertedPhrase) || insertedPhrase.equals(" ") || isInserted == false){
        //if ((insertedPhrase.equals(" ")|| (insertedPhrase.equals("")))){
            isInserted= false;
            Toast.makeText(this,"Please insert the phrase in order to save!",Toast.LENGTH_SHORT).show();

        }else{
            isInserted = addPhrase(insertedPhrase);
            Toast.makeText(this,"Phrase saved successfully!",Toast.LENGTH_SHORT).show();



        }

    }

}
