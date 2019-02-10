package com.example.cst8334project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        login = findViewById(R.id.signin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(name.getText().toString().trim(), pass.getText().toString().trim());
            }
        });
    }

    private void validate(String username, String userpass){
        if(username.equals("volun") && userpass.equals("123")){
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "The username or password you entered was incorrect", Toast.LENGTH_SHORT).show();
        }
    }
}
}
