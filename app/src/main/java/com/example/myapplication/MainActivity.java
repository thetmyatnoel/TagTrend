package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import java.util.List;

public class MainActivity extends AppCompatActivity implements HashtagFetcherCallback {
    private EditText editTextKeyword;
    private Button buttonGenerate;
    private EditText editTextResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Ensure this layout exists with the correct IDs

        editTextKeyword = findViewById(R.id.editText);
        buttonGenerate = findViewById(R.id.button);
        editTextResult = findViewById(R.id.result);

        buttonGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = editTextKeyword.getText().toString();
                new HashtagFetcher(MainActivity.this).execute(keyword);
            }
        });
    }

    @Override
    public void onResultReceived(String result) {
        Gson gson = new Gson();
        try {
            HashtagResponse response = gson.fromJson(result, HashtagResponse.class);
            List<String> hashtags = response.data.best_30_hashtags.hashtags;

            StringBuilder formattedHashtags = new StringBuilder();
            for (String hashtag : hashtags) {
                formattedHashtags.append("#").append(hashtag).append("\n");
            }

            editTextResult.setText(formattedHashtags.toString());
            editTextResult.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle parsing exceptions
        }
    }

}
