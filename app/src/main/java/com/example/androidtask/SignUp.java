package com.example.androidtask;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {
    Button signupBtn;
    TextView login;
    String url="http://192.168.43.38/php/sign.php";
  //  String url="https://unreposeful-rheosta.000webhostapp.com/newSignUp.php";
EditText name,mobile,email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name=(EditText)findViewById(R.id.edName);
        mobile=(EditText)findViewById(R.id.edMobile);
        email=(EditText)findViewById(R.id.edEmail);
        password=(EditText)findViewById(R.id.edPass);
        signupBtn=(Button)findViewById(R.id.signUp);
        login=(TextView)findViewById(R.id.logn_text);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),LoginAcitvity.class);
                startActivity(intent);
            }
        });
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
// create sign up method
    private void signUp() {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        String names=name.getText().toString();
        String mobiles=mobile.getText().toString();
        String emails=email.getText().toString();
        String passs=password.getText().toString();
        if (names.isEmpty()){
            name.setError("Enter user name");
            return;
        }else if(mobiles.isEmpty() ){
            mobile.setError("Enter Mobile Number");
            return;
        }else if(validNumber()==false){
            mobile.setError("Enter correct indian phone number");
            return;
        }
        else if(emails.isEmpty()){
            email.setError("Enter Email Id");
            return;
        }else if(validEmail()==false){
            email.setError("Enter valid Email Id");
            return;
        }
        else if(passs.isEmpty()){
            password.setError("Enter Password");
            return;
        }else if(validPassword()==false){
         password.setError("Password should contain at least 1 lower char(start with lower char) , 2 Upper char, 2 digits and 1 special char ");
        }else if(validName()){
          password.setError("Name and Password should not be same");
        }
        else{
            progressDialog.show();
            StringRequest stringRequest=new StringRequest(Request.Method.POST,url,new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if(response.equalsIgnoreCase("Sign Up successfully")){
                        name.setText("");
                        mobile.setText("");
                        email.setText("");
                        password.setText("");
                        Toast.makeText(getApplicationContext(),"Sign Up successfully",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),LoginAcitvity.class);
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
                    params.put("names",names);
                    params.put("mobiles",mobiles);
                    params.put("emails",emails);
                    params.put("passs",passs);
                    return params;
                }
            };
            RequestQueue requestQueue= Volley.newRequestQueue(SignUp.this);
            requestQueue.add(stringRequest);

        }

    }

    private boolean validPassword() {
        // Regex to check valid password.
        String regex = "^([a-z]+)(?=.*[A-Z]{2})"
                +"(?=.*[0-9]{2})"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,15}$";
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password.getText().toString());
        return matcher.matches();
    }
    private boolean validName() {
        String pass=password.getText().toString();
        String substr=pass.substring(0,4).toLowerCase();
        String name1=name.getText().toString();
       Pattern pattern=Pattern.compile(substr);
       Matcher matcher=pattern.matcher(name1) ;
       return matcher.matches();
    }

    private boolean validNumber() {
        String regex = "(0/91)?[7-9][0-9]{9}";
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher = pattern.matcher(mobile.getText().toString());
        return matcher.matches();
    }

    private boolean validEmail() {
        //Regular Expression
        String regex =  "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z].{4,25}$";
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email.getText().toString());
        return matcher.matches();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
