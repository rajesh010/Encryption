package com.example.encryption;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Decrypt extends AppCompatActivity {

    //place for variables
    String To_be_decrypted;
    String Key;
    String extended_Key;
    char [] Decrypted_message;
    String final_decrypted;


    //buttons and others defining
    Button Btn_d_Ok,Btn_d_cancel;
    EditText ET_decrypt, ET_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decrypt);

        Btn_d_Ok = findViewById(R.id.Btn_d_Ok);
        Btn_d_cancel = findViewById(R.id.Btn_d_cancel);

        ET_decrypt = findViewById(R.id.ET_decrypt);
        ET_key = findViewById(R.id.ET_key);
        Btn_d_Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                To_be_decrypted = ET_decrypt.getText().toString();
                Key = ET_key.getText().toString();
                int times = To_be_decrypted.length()/6 + 1;
                extended_Key = key_extender(Key,times);
                final_decrypted = Decrypt(To_be_decrypted, extended_Key);
                copy_text(final_decrypted);

            }

        });


        Btn_d_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Decrypt.this, MainActivity.class);
                startActivity(i);
            }
        });

        //adding action bar in the app
        ActionBar actionBar = getSupportActionBar();   //calling the action bar
        actionBar.setDisplayHomeAsUpEnabled(true);    // showing the back button in action bar



    }  //-------------------------------------- End of OnCreate----------------------------

    private String key_extender(String x, int numbers){
        String added_key = "";
        for(int i = 0; i < numbers; i++){
            added_key = added_key + x;
        }
        return added_key;
    }

    private String Decrypt(String To_be_encrypted, String added_key){
        Decrypted_message = To_be_encrypted.toCharArray();
        char [] final_key = added_key.toCharArray();
        try {
            for (int i = 0; i < To_be_encrypted.length(); i++) {
                Decrypted_message[i] = (char) (Decrypted_message[i] - Character.getNumericValue(final_key[i]));
            }
        } catch (Exception e){
            Toast.makeText(Decrypt.this, "Key needs to be at least 6 digits" , Toast.LENGTH_SHORT).show();
        }
        String converted_message = new String(Decrypted_message);
        return converted_message;
    }

    public void copy_text(String x){

        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Decrypted Text", x);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(Decrypt.this, "The text is copied to clipboard and the text is: " + x , Toast.LENGTH_LONG).show();

    }

    public void Dailog_Box(){
        // Create the object of
        // AlertDialog Builder class
        AlertDialog.Builder builder = new AlertDialog.Builder(Decrypt.this);


        builder.setMessage("The decrypted message is: "+ final_decrypted + ". It also has been copied to your clipboard");


        builder.setTitle("Decrypted text");

        // Set Cancelable false
        // for when the user clicks on the outside
        // the Dialog Box then it will remain show
        builder.setCancelable(false);

        // Set the positive button with yes name
        // OnClickListener method is use of
        // DialogInterface interface.

        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // When the user click yes button
                // then app will close
                finish();
            }
        });


        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();

        // Show the Alert Dialog box
        alertDialog.show();
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                Intent i = new Intent(Decrypt.this, MainActivity.class);
                startActivity(i);


                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}