package uk.ac.westminster.mobileappcoursework2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EditPhrases extends AppCompatActivity {

    private TranslationDB translationDB; // declaration
    private static final String phrase = "phrase";
    private static final String phraseId = "phraseId";


    private static final String TABLE_NAME="phrase";

    Cursor cursor;
    ArrayList <String> editPhrase_List;
    EditText editPhraseText;
    RadioGroup radiogroup;
    RadioButton[] radiobutton;
    Button editBtn;
    Button saveBtn;
    String editableText;
    String newEditedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_phrases);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        translationDB = new TranslationDB(this); //Initialization

        cursor = getPhrases();
        showPhrases(cursor);
        //Edit Button Code
        editBtn = (Button) findViewById(R.id.editPhrase_btn);
        editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                 public void onClick(View v) {

                if(!(radiogroup.getCheckedRadioButtonId()==-1)) {


                    // get selected radio button from radioGroup
                    int selectedId = radiogroup.getCheckedRadioButtonId();


                    // find the radiobutton by returned id
                    radiobutton[selectedId] = (RadioButton) findViewById(selectedId);



                    Toast.makeText(EditPhrases.this,
                            radiobutton[selectedId].getText().toString() + " selected to be edited", Toast.LENGTH_SHORT).show();
                    editPhraseText = (EditText) findViewById(R.id.editPhrase_edit);
                    editPhraseText.setText(radiobutton[selectedId].getText().toString());
                    editPhraseText.setVisibility(View.VISIBLE);
                    editableText = editPhraseText.getText().toString();



                }else{
                    Toast.makeText(EditPhrases.this,"No phrases selected to edit!",Toast.LENGTH_SHORT).show();

                }
            }

         });
        //Save Button Code
        saveBtn = (Button) findViewById(R.id.savePhrase_btn);

        saveBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(editableText)|| editableText.equals(" ")){

                    Toast.makeText(EditPhrases.this,"No phrase selected to be updated!",Toast.LENGTH_SHORT).show();

                }else{
                    newEditedText = editPhraseText.getText().toString();
                    Cursor data = getPhraseId(editableText);//get id associated with that phrase
                    int itemID = -1;
                    while(data.moveToNext()){
                        itemID = data.getInt(0);

                    }
                    if(itemID > -1){
                        Log.d("", "on item click the ID is : "+itemID);
                        updatePhrase(newEditedText,itemID,editableText);
                        Toast.makeText(EditPhrases.this,editableText+" updated as : "+newEditedText,Toast.LENGTH_SHORT).show();
                        //cursor = getPhrases();
                       // showPhrases(cursor);
                        finish();
                        startActivity(getIntent());


                    }else{

                        Toast.makeText(EditPhrases.this,"No Id associated with that name!",Toast.LENGTH_SHORT).show();

                    }

                }

            }
        });



        translationDB.close();


    }
    private Cursor getPhrases () {

        SQLiteDatabase db = translationDB . getReadableDatabase ();
        editPhrase_List = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME+" ORDER BY "+phrase,null);
        return cursor ;
    }



    public void showPhrases(Cursor cursor){

        if(cursor.getCount()==0){

            Toast.makeText(this,"No phrases saved to be displayed!",Toast.LENGTH_SHORT).show();

        }else{
            while(cursor.moveToNext()){

                editPhrase_List.add(cursor.getString(1));


            }
            RadioButton radioBtn;
            final RadioButton[] rb = new RadioButton[editPhrase_List.size()];
            radiobutton = new RadioButton[editPhrase_List.size()];
            radiogroup = new RadioGroup(this);


            for (int i = 0; i < editPhrase_List.size(); i++) {

                radiobutton[i] = new RadioButton(this);
                radiobutton[i].setText(editPhrase_List.get(i));
                radiobutton[i].setId(i);
                radiogroup.addView(radiobutton[i]);


            }
            LinearLayout myLayout = (LinearLayout) findViewById(R.id.mylayout_linearlayout);
            myLayout.addView(radiogroup);
            myLayout.setGravity(Gravity.CENTER_VERTICAL);

        }
    }


    //logic id extract from db
    //then update that text in db in database with new text

    public Cursor getPhraseId(String  Textphrase){
        SQLiteDatabase db = translationDB . getReadableDatabase ();
        String query = "SELECT phraseId FROM "+TABLE_NAME+" WHERE "+phrase+" ='"+Textphrase+ "'";
        Cursor data = db.rawQuery(query,null);
        return data;
    }
    //update phrase
    public void updatePhrase(String newName, int id, String oldName){
        SQLiteDatabase db = translationDB . getReadableDatabase ();

        String query = "UPDATE "+TABLE_NAME+" SET "+phrase+" = '"+newName+
                "' WHERE "+phraseId+" = '"+id+ "'" +" AND "+phrase+ " = '"+oldName+"'";

        //Toast.makeText(EditPhrases.this,"newName: "+newName+" id: "+id+" oldName: "+oldName,Toast.LENGTH_LONG).show();

        db.execSQL(query);
    }

}


