package com.example.androidtask;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginAcitvity extends AppCompatActivity {
    EditText email,pass;
    Button login;
    TextView signUp;
    String url="http://192.168.43.38/php/login.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_acitvity);
        email=(EditText)findViewById(R.id.edEmail);
        pass=(EditText)findViewById(R.id.edPass);
        login=(Button)findViewById(R.id.login_btn);
        signUp=(TextView)findViewById(R.id.sign_text);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),SignUp.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginMethod();
            }
        });
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    private void loginMethod() {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        String emailstr=email.getText().toString();
        String passstr=pass.getText().toString();
        if(emailstr.isEmpty()){
            email.setError("Enter Email Id or Phone Number");
        }else if(passstr.isEmpty()){
            pass.setError("Enter Your Password");
        }else{
            progressDialog.show();
            StringRequest stringRequest=new StringRequest(Request.Method.POST,url,new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if(response.equalsIgnoreCase("Login successfully")){
                        email.setText("");
                        pass.setText("");
                        Toast.makeText(getApplicationContext(),"Login successfully",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);

                        progressDialog.dismiss();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                }
            })
            {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params=new HashMap<String, String>();
                    params.put("email",emailstr);
                    params.put("pass",passstr);
                    return params;
                }
            };
            RequestQueue requestQueue= Volley.newRequestQueue(LoginAcitvity.this);
            requestQueue.add(stringRequest);

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

