package uk.ac.westminster.mobileappcoursework2;

import android.graphics.Color;
import android.os.AsyncTask;
import android.view.View;

import com.ibm.watson.language_translator.v3.model.TranslateOptions;
import com.ibm.watson.language_translator.v3.model.TranslationResult;
import com.ibm.watson.language_translator.v3.util.Language;


public class TranslateTask extends AsyncTask<String, Void, String> {


    @Override
    protected String doInBackground(String... params) {
        TranslateOptions translateOptions = new TranslateOptions.Builder()
                .addText(params[0])
                .source(Language.ENGLISH)
                .target(Translate.langCode)
                .build();
        TranslationResult result = Translate.translationService.translate(translateOptions).execute().getResult();
        String firstTranslation = result.getTranslations().get(0).getTranslation();
        return firstTranslation;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Translate.text2.setText(s);
        Translate.selectedText.setVisibility(View.INVISIBLE);
        Translate.text2.setVisibility(View.VISIBLE);
        Translate.text2.setTextColor(Color.parseColor("#013220"));



    }
}
