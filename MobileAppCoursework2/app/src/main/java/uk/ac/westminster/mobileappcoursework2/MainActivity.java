package uk.ac.westminster.mobileappcoursework2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TranslationDB translationDB; // declaration

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        translationDB = new TranslationDB(this); //Initialization

        translationDB.close();

    }


        //Function to add phrases

        public void add_phrases(View view) {
            Intent intent = new Intent(MainActivity.this, AddPhrases.class);
            startActivity(intent);


        }
        //Function to display phrases

        public void display_phrases(View view) {
            Intent intent = new Intent(MainActivity.this, DisplayPhrases.class);
            startActivity(intent);

        }
        //Function to edit phrases

        public void edit_phrases(View view) {
            Intent intent = new Intent(MainActivity.this, EditPhrases.class);
            startActivity(intent);

        }
        //Function to subscribe to a language

        public void language_subscription(View view) {
            Intent intent = new Intent(MainActivity.this, LanguageSubscription.class);
            startActivity(intent);

        }
        //Function to translate

        public void translate(View view) {
            Intent intent = new Intent(MainActivity.this, Translate.class);
            startActivity(intent);

        }

        //Exit Button Function
        public void exit(View view) {
            AlertDialog.Builder exitBuilder = new AlertDialog.Builder(this);

            exitBuilder.setTitle("Exit ?");
            exitBuilder.setMessage("Are you sure you want to Exit ?");
            exitBuilder.setCancelable(false);
            exitBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity.this.finish();
                }
            });
            exitBuilder.setNegativeButton("No", null)
                    .show();

        }
    }

