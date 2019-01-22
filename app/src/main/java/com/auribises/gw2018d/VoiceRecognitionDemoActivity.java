package com.auribises.gw2018d;

import android.app.ProgressDialog;
import android.content.Intent;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class VoiceRecognitionDemoActivity extends AppCompatActivity implements RecognitionListener{

    TextView txtData;
    ProgressDialog progressDialog;

    SpeechRecognizer speechRecognizer;
    TextToSpeech textToSpeech;

    void initViews(){
        txtData = findViewById(R.id.textViewData);

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Listening...");

        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.ERROR){
                    Toast.makeText(VoiceRecognitionDemoActivity.this,"TTS is not Enabled",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    void sayHello(){
        String text = "Hello, Welcome to Auribises Technologies";
        textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);
        //textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null,null);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_recognition_demo);
        initViews();
    }

    public void clickHandler(View view){
        //Intent intent = RecognizerIntent.getVoiceDetailsIntent(this);
        //speechRecognizer.startListening(intent);

        sayHello();
    }

    @Override
    public void onReadyForSpeech(Bundle params) {

    }

    @Override
    public void onBeginningOfSpeech() {
        progressDialog.show();
    }

    @Override
    public void onRmsChanged(float rmsdB) {

    }

    @Override
    public void onBufferReceived(byte[] buffer) {

    }

    @Override
    public void onEndOfSpeech() {
        progressDialog.dismiss();
    }

    @Override
    public void onError(int error) {

    }

    @Override
    public void onResults(Bundle results) {
        ArrayList<String> data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

        if(data!=null && data.size()>0){
            String text = data.get(0);
            txtData.setText(text);
        }
    }

    @Override
    public void onPartialResults(Bundle partialResults) {

    }

    @Override
    public void onEvent(int eventType, Bundle params) {

    }
}
