package com.example.cst8334project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.File;
import android.os.Environment;
import java.io.FileOutputStream;
import java.io.*;
import android.net.*;
import android.content.Intent;

public class extract_data_to_csv extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extract_data_to_csv);
    }

    private void startAc() {
        String columnString = "\"PersonName\",\"Gender\",\"Street1\",\"postOffice\",\"Age\"";
        //String dataString   =   "\"" + currentUser.userName +"\",\"" + currentUser.gender + "\",\"" + currentUser.street1 + "\",\"" + currentUser.postOFfice.toString()+ "\",\"" + currentUser.age.toString() + "\"";
        String dataString = "\"" + "Dash Xu" + "\",\"" + "female" + "\",\"" + "Baseline rd" + "\",\"" + "POO" + "\",\"" + "21" + "\"";
        String combinedString = columnString + "\n" + dataString;

        File file = null;
        File root = Environment.getExternalStorageDirectory();
        if (root.canWrite()) {
            File dir = new File(root.getAbsolutePath() + "/PersonData");
            dir.mkdirs();
            file = new File(dir, "Data.csv");
            FileOutputStream out = null;
            try {
                out = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                out.write(combinedString.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    Uri u1 = null;
    u1  =Uri.fromFile(file);

    Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT,"Person Details");
        sendIntent.putExtra(Intent.EXTRA_STREAM,u1);
        sendIntent.setType("text/html");

    startActivity(sendIntent);
}

}
