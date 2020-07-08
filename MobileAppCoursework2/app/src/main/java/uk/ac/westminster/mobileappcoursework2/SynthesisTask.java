package uk.ac.westminster.mobileappcoursework2;

import android.os.AsyncTask;

import com.ibm.cloud.sdk.core.http.HttpMediaType;
import com.ibm.watson.text_to_speech.v1.model.SynthesizeOptions;
import com.ibm.watson.developer_cloud.android.library.audio.StreamPlayer;
import com.ibm.watson.text_to_speech.v1.TextToSpeech;



public class SynthesisTask extends AsyncTask<String, Void, String> {
    //text to speech
    public StreamPlayer player = new StreamPlayer();
    public TextToSpeech textService;
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
