package com.example.musicplayersystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {

    EditText usernam, passw;
    Button btnlogin;
    TextView linkregist;
    ProgressBar loading;
    private static final String URL = "http://192.168.232.1/music_app/api/login.php";
    private Object StringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        loading = findViewById(R.id.prog);
        usernam = findViewById(R.id.inusername);
        passw= findViewById(R.id.inpassword);
        btnlogin = findViewById(R.id.btnlogin);
        linkregist = findViewById(R.id.regist);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String musername = usernam.getText().toString().trim();
                String mpassword = passw.getText().toString().trim();

                if (!musername.isEmpty() || !mpassword.isEmpty()){
                    Login(musername,mpassword);
                }
                else
                {
                    usernam.setError("Please Insert Username");
                    passw.setError("Please Insert Password");
                }
            }
        });
        linkregist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this,MainActivity.class));
            }
        });

    }

    private void Login(String username,String password) {

        loading.setVisibility(View.VISIBLE);
//        btnlogin.setVisibility(View.GONE);

        StringRequest stringRequest =new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String success = response;
                        if (success.equals("1")) {
                            Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(MainActivity2.this, "Invalid User name password", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.setVisibility(View.GONE);
                        Toast.makeText(MainActivity2.this,"Error"+error.toString(),Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("mobileno",username);
                params.put("passw",password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}