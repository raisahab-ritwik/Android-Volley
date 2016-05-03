package com.example.androidvolley;

import java.util.HashMap;

import com.android.volley.Request.Method;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	public void onWebServiceClick(View v) {

		HashMap<String, String> requestMap = new HashMap<String, String>();
		
		requestMap.put("t_CustomerName", "Praveen");
		requestMap.put("t_MobileNumber1", "7044742773");
		requestMap.put("n_GenderId", "1");
		requestMap.put("t_Password", "111111");
		requestMap.put("n_DealerPointId", "1");
		requestMap.put("n_OTPCode", "1234");
		requestMap.put("t_CreatedIP", "192.165.1.10");
		VolleyTaskManager.makeJsonObjReq(Method.POST,
				"http://www.moderndukanapi.urbanlibas.com/ModernDukan/APIData/PostRegisterCustomerViaAPP", requestMap, MainActivity.this);
	}
}
