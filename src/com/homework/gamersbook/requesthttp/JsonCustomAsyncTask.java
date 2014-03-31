
package com.homework.gamersbook.requesthttp;

import android.os.AsyncTask;

import com.homework.gamersbook.model.AccessTokenAPIResponse;
import com.homework.gamersbook.model.UsherAPIResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class JsonCustomAsyncTask extends AsyncTask<String, Integer, UsherAPIResponse> {

    private JsonListener listener;

    public JsonCustomAsyncTask(JsonListener listener) {
        this.listener = listener;
    }

    /*
     * (non-Javadoc)
     * @see android.os.AsyncTask#doInBackground(java.lang.Object[])
     */
    @Override
    protected UsherAPIResponse doInBackground(String... params) {
        AccessTokenAPIResponse accessToken = getTokenAndSignature(params[0]);

        try {
            String userAPI = "http://usher.twitch.tv/select/"
                    + URLEncoder.encode(params[0], "UTF-8") + ".json?nauthsig="
                    + URLEncoder.encode(accessToken.getSig(), "UTF-8") + "&nauth="
                    + URLEncoder.encode(accessToken.getToken(), "UTF-8") + "&allow_source=true";
            String readJSON = getJSON(userAPI);
            try {
                UsherAPIResponse res = new UsherAPIResponse();
                for (String line : readJSON.split(System.getProperty("line.separator"))) {
                    if (line.matches("https?://.*")) {
                        res.setURL(line);
                    }
                }
                return res;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /* (non-Javadoc)
     * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
     */
    @Override
    protected void onPostExecute(UsherAPIResponse result) {
        listener.onJsonGetFinished(result);
    }

    private String getJSON(String URL) {
        StringBuilder builder = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(URL);
        try {
            HttpResponse response = client.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                    builder.append(System.getProperty("line.separator"));
                }
            } else {
                // Failed to load
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    private AccessTokenAPIResponse getTokenAndSignature(String channelName) {
        final String tokenAPI = "http://api.twitch.tv/api/channels/" + channelName
                + "/access_token";
        AccessTokenAPIResponse res = null;
        String readJSON = getJSON(tokenAPI);
        try {
            JSONObject jsonObject = new JSONObject(readJSON);
            res = new AccessTokenAPIResponse(jsonObject.getString("sig"),
                    jsonObject.getString("token"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
