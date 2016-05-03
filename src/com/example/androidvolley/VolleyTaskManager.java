package com.example.androidvolley;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

public class VolleyTaskManager {

	private static String TAG = "";
	private static String tag_json_obj = "jobj_req";

	/**
	 * 
	 * Making json object request
	 * */
	public static void makeJsonObjReq(int method, String url, final Map<String, String> paramsMap, final Context mContext) {

		Log.v("JSONObject", new JSONObject(paramsMap).toString());

		JsonObjectRequest jsonObjReq = new JsonObjectRequest(method, url, new JSONObject(paramsMap), new Response.Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				Log.d(TAG, response.toString());
			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				VolleyLog.d(TAG, "Error: " + error.getMessage());

				if (error instanceof TimeoutError || error instanceof NoConnectionError) {
					Log.d("error ocurred", "TimeoutError");
					Toast.makeText(mContext, "TimeoutError", Toast.LENGTH_LONG).show();
				} else if (error instanceof AuthFailureError) {
					Log.d("error ocurred", "AuthFailureError");
					Toast.makeText(mContext, "AuthFailureError", Toast.LENGTH_LONG).show();
				} else if (error instanceof ServerError) {
					Log.d("error ocurred", "ServerError");
					Toast.makeText(mContext, "ServerError", Toast.LENGTH_LONG).show();
				} else if (error instanceof NetworkError) {
					Log.d("error ocurred", "NetworkError");
					Toast.makeText(mContext, "NetworkError", Toast.LENGTH_LONG).show();
				} else if (error instanceof ParseError) {
					Log.d("error ocurred", "ParseError");
					Toast.makeText(mContext, "ParseError", Toast.LENGTH_LONG).show();
				}

			}
		}) {

			/**
			 * Passing some request headers
			 * */
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				HashMap<String, String> headers = new HashMap<String, String>();
				headers.put("Content-Type", "application/json; charset=utf-8");
				return headers;
			}

			@Override
			protected Map<String, String> getParams() {
				/*
				 * Map<String, String> params = new HashMap<String, String>();
				 * params.put("name", "Androidhive"); params.put("email",
				 * "abc@androidhive.info"); params.put("pass", "password123");
				 */
				return paramsMap;
			}

		};

		jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(60000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

		// Cancelling request
		// ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_obj);
	}
}
