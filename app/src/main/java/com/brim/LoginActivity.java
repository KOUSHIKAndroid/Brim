package com.brim;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.ObbInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.brim.ApiHelper.HTTP_POST;
import com.brim.AppContant.ApiConstant;
import com.brim.AppContant.BrimApplication;
import com.brim.Font.AxiformaBookEditText;
import com.brim.Utils.AppAlert;
import com.brim.Utils.AppProgerssDialog;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    AppProgerssDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressDialog=new AppProgerssDialog(this);
        progressDialog.SetMessage("Please Wait...");
        progressDialog.SetTitle("Login");

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Card_Login:

                if (((AxiformaBookEditText) findViewById(R.id.EDX_UserName)).getText().toString().trim().equals("")) {
                    ((AxiformaBookEditText) findViewById(R.id.EDX_UserName)).setHintTextColor(Color.RED);
                } else if (((AxiformaBookEditText) findViewById(R.id.Edx_Password)).getText().toString().trim().equals("")) {
                    ((AxiformaBookEditText) findViewById(R.id.Edx_Password)).setHintTextColor(Color.RED);
                } else {
                    progressDialog.Show();
                    String Body = "{\"email\":\"" + ((AxiformaBookEditText) findViewById(R.id.EDX_UserName)).getText().toString().trim() + "\",\"password\":\"" +
                            ((AxiformaBookEditText) findViewById(R.id.Edx_Password)).getText().toString() + "\"}";

                    new HTTP_POST(ApiConstant.LOGIN, Body) {
                        @Override
                        protected void OnSucess(String Response) {
                            progressDialog.Dismiss();
                            try {
                                JSONObject OBject = new JSONObject(Response);

                                if(OBject.has("code") && OBject.getString("code").equals("invalid_credentials"))
                                {
                                    new AppAlert(LoginActivity.this).Error(OBject.getString("message"));
                                }else  if(OBject.has("code") && OBject.getString("code").equals("validation_failed")){
                                    new AppAlert(LoginActivity.this).Error(OBject.getJSONObject("errors").getJSONObject("email").getString("message"));
                                }
                                else {
                                    BrimApplication.getInstnace().SetAuthToken(OBject.getString("auth_token"));
                                    BrimApplication.getInstnace().SetUserId(OBject.getString("id"));

                                    startActivity(new Intent(LoginActivity.this, BaseActivity.class));
                                    finish();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        protected void OnErrorApi(String Error) {
                            progressDialog.Dismiss();
                            new AppAlert(LoginActivity.this).Error(Error);
                        }

                        @Override
                        protected void OnHttPError(String HttpError) {
                            progressDialog.Dismiss();

                        }
                    };
                }


                break;
        }

    }
}
