package uk.ac.westminster.mobileappcoursework2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ibm.cloud.sdk.core.http.HttpMediaType;
import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.developer_cloud.android.library.audio.StreamPlayer;
import com.ibm.watson.language_translator.v3.LanguageTranslator;
import com.ibm.watson.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.text_to_speech.v1.model.SynthesizeOptions;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.ibm.cloud.sdk.core.http.HttpMediaType;
import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.developer_cloud.android.library.audio.StreamPlayer;
import com.ibm.watson.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.text_to_speech.v1.model.SynthesizeOptions;

import java.util.ArrayList;

public class Translate extends AppCompatActivity {



    public StreamPlayer player = new StreamPlayer();
    public TextToSpeech textService;
    static LanguageTranslator translationService;

    private TranslationDB translationDB; // declaration
    private static final String phrase = "phrase";
    private static final String TABLE_NAME = "phrase";
    public static TextView selectedText;
    Cursor cursor;
    ArrayList<String> phrasesList;
    ArrayAdapter adapter;
    ListView userList;
    String text1;
    public static TextView text2;

    public static String target;
    public static String langCode;
    public static Spinner spinner1;
    public static Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        translationDB = new TranslationDB(this); //Initialization
        textService = initTextToSpeechService();

        addListenerOnButton();
        addListenerOnSpinnerItemSelection();


        userList = findViewById(R.id.phrases_list);
        selectedText = findViewById(R.id.selectedText_txt);
        text2 = findViewById(R.id.tempTextBox_txt);
        cursor = getPhrases();
        showPhrases(cursor);
        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text = userList.getItemAtPosition(i).toString();
                text1 =phrasesList.get(i);
                selectedText.setText(text1.toString());
                text2.setVisibility(View.INVISIBLE);
                selectedText.setVisibility(View.VISIBLE);
                selectedText.setTextColor(Color.RED);

                Toast.makeText(Translate.this, "" + text1, Toast.LENGTH_SHORT).show();

            }
        });

        translationDB.close();
    }

    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    // get the selected dropdown list value
    public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);


        //Submit on click
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                target = String.valueOf(spinner1.getSelectedItem());
                langCode = target.substring(target.length() - 2); // change here

            }

        });
    }



    private Cursor getPhrases() {
            /* Perform a managed query . The Activity will
            handle closing and re - querying the cursor
            when needed . */
        SQLiteDatabase db = translationDB.getReadableDatabase();
        phrasesList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY " + phrase, null);
        return cursor;
    }

    public void showPhrases(Cursor cursor) {

        if (cursor.getCount() == 0) {

            Toast.makeText(this, "No phrases saved to be displayed!", Toast.LENGTH_LONG).show();

        } else {
            while (cursor.moveToNext()) {

                phrasesList.add(cursor.getString(1));

            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, phrasesList);
            userList.setAdapter(adapter);

        }
    }

    private LanguageTranslator initLanguageTranslatorService() {
        IamAuthenticator authenticator = new IamAuthenticator("V-nw_aGg4PTPdQVbWuce3atXzRff9UH-Wv3JvA-rupTI");
        LanguageTranslator service = new LanguageTranslator("2018-05-01", authenticator);
        service.setServiceUrl("https://api.eu-gb.language-translator.watson.cloud.ibm.com/instances/a07f39a1-2fa7-430b-b482-99fa365c9034");
        return service;
    }
    //tanslate
    public void translate(View view){
        translationService = initLanguageTranslatorService();
        new TranslateTask().execute(text1);

    }


    //text to speech (pronounce)
    public void pronounce(View view) {

        String tv2text = text2.getText().toString();
        new SynthesisTask().execute(tv2text);
    }


    public TextToSpeech initTextToSpeechService() {
        Authenticator authenticator = new IamAuthenticator("NCREanFAwTd2OfM4w698SUd8BGTwLCirzWh_gdC7TNau");
        TextToSpeech service = new TextToSpeech(authenticator);
        service.setServiceUrl("https://api.eu-gb.text-to-speech.watson.cloud.ibm.com/instances/c7f9309f-39d2-4df6-83e7-49a21ad7890b");
        return service;
    }

    private class SynthesisTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            SynthesizeOptions synthesizeOptions = new SynthesizeOptions.Builder()
                    .text(params[0])
                    .voice(SynthesizeOptions.Voice.ES_ES_LAURAV3VOICE)
                    .accept(HttpMediaType.AUDIO_WAV)
                    .build();
            player.playStream(textService.synthesize(synthesizeOptions).execute().getResult());
            return "Did synthesize";
        }
    }

}






