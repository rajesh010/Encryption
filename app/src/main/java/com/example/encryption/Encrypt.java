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

public class Encrypt extends AppCompatActivity {

    //global variable defining place
    String added_key;
    String To_be_encrypted = "";
    static int Key ;
    static int times = 0;
    String extended_Key;
    static String Encrypt_message;
    char [] Encrypted_message;
    String converted_message;
    String final_converted;


    //Initializing all buttons and Edit text
    Button Btn_ok, Btn_cancel;
    EditText ET_encrypt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypt);

        Btn_ok = findViewById(R.id.Btn_ok);
        Btn_cancel = findViewById(R.id.Btn_cancel);
        ET_encrypt = findViewById(R.id.ET_encrypt);





        Btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int min = 111111, max = 999999;

              To_be_encrypted = ET_encrypt.getText().toString();
              Key = (int) Math.floor(Math.random()*(max-min+1)+min);
              times = To_be_encrypted.length()/6 + 1;
              extended_Key = key_extender(String.valueOf(Key),times);
              final_converted = Encrypt(To_be_encrypted, extended_Key);

                for(int i =0; i<3 ; i++) {
                Toast.makeText(Encrypt.this, "Decrypted message is: " + final_converted + " and key= " + Key + " and extended key is: " + extended_Key, Toast.LENGTH_LONG).show();
                }
                copy_text(final_converted);

                Dailog_Box();


            }
        });


        Btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Encrypt.this, MainActivity.class);
                startActivity(i);
            }
        });

        //adding action bar in the app
        ActionBar actionBar = getSupportActionBar();   //calling the action bar
        actionBar.setDisplayHomeAsUpEnabled(true);    // showing the back button in action bar



    }  //-------------------------------------- End of OnCreate----------------------------

    private String key_extender(String x, int numbers){
        added_key = "";
        for(int i = 0; i < numbers; i++){
            added_key = added_key + x;
        }
        return added_key;
    }

    private String Encrypt(String To_be_encrypted, String added_key){
        Encrypted_message = To_be_encrypted.toCharArray();
        char [] final_key = added_key.toCharArray();

        for(int i = 0; i< To_be_encrypted.length(); i++){
            Encrypted_message[i] = (char)(Encrypted_message[i] + Character.getNumericValue(final_key[i]));
        }
        converted_message = new String(Encrypted_message);
        return converted_message;
    }


    public void copy_text(String x){

        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Encrypted Text", x);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(Encrypt.this, "The text is already copied to clipboard and the text is: " + x , Toast.LENGTH_LONG).show();

    }

    public void Dailog_Box(){
        // Create the object of
        // AlertDialog Builder class
        AlertDialog.Builder builder = new AlertDialog.Builder(Encrypt.this);


        builder.setMessage("The key is: "+ Key + "   Please remember this for future reference");


        builder.setTitle("Key!!!");

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

                Intent i = new Intent(Encrypt.this, MainActivity.class);
                startActivity(i);


                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

