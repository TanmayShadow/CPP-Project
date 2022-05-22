package com.example.videoplayer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundMYSQL extends AsyncTask<String,Void,String> {

    Context context;
    AlertDialog a;
    String type;
    SQLiteDatabaseClass db;
    public String names,location,des,cname,like,dislike,vid;

    BackgroundMYSQL(Context c1)
    {
        this.context=c1;
        db = new SQLiteDatabaseClass(c1);
    }
    @Override
    protected String doInBackground(String... params) {
        type=params[0];
        if(type.equals("login"))
        {
            String email = params[1];
            String pass = params[2];
            String login_url="https://haematopoietic-memo.000webhostapp.com/login.php";
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"+
                        URLEncoder.encode("pass","UTF-8")+"="+URLEncoder.encode(pass,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line=bufferedReader.readLine())!=null)
                {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if(type.equals("signup"))
        {
            String mail=params[1];
            String pass=params[2];
            String fname=params[3];
            String lname=params[4];

            String login_url="https://haematopoietic-memo.000webhostapp.com/signup.php";
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(mail,"UTF-8")+"&"+
                        URLEncoder.encode("pass","UTF-8")+"="+URLEncoder.encode(pass,"UTF-8")+"&"+
                        URLEncoder.encode("fname","UTF-8")+"="+URLEncoder.encode(fname,"UTF-8")+"&"+
                        URLEncoder.encode("lname","UTF-8")+"="+URLEncoder.encode(lname,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line=bufferedReader.readLine())!=null)
                {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if(type.equals("uploadI")) {
            String id = params[1];
            String ctype = params[2];
            String name = params[3];
            String description = params[4];
            String location=params[5];

            String login_url = "https://haematopoietic-memo.000webhostapp.com/addEncryptImage.php";
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8") + "&" +
                        URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(ctype, "UTF-8") + "&" +
                        URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                        URLEncoder.encode("description", "UTF-8") + "=" + URLEncoder.encode(description, "UTF-8")+ "&" +
                        URLEncoder.encode("location", "UTF-8") + "=" + URLEncoder.encode(location, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("upload")) {
            String id = params[1];
            String ctype = params[2];
            String name = params[3];
            String description = params[4];
            String location=params[5];

            String login_url = "https://haematopoietic-memo.000webhostapp.com/addImage.php";
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8") + "&" +
                        URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(ctype, "UTF-8") + "&" +
                        URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                        URLEncoder.encode("description", "UTF-8") + "=" + URLEncoder.encode(description, "UTF-8")+ "&" +
                        URLEncoder.encode("location", "UTF-8") + "=" + URLEncoder.encode(location, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("getImage")) {
            String login_url = "https://haematopoietic-memo.000webhostapp.com/getImages.php";
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("ctype", "UTF-8") + "=" + URLEncoder.encode("image", "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("getEncrypted")) {
            String login_url = "https://haematopoietic-memo.000webhostapp.com/getEncrypted.php";
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("ctype", "UTF-8") + "=" + URLEncoder.encode("image", "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("getVideo")) {
            String login_url = "https://haematopoietic-memo.000webhostapp.com/getVideo.php";
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("ctype", "UTF-8") + "=" + URLEncoder.encode("image", "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("report")) {
            String login_url = "https://haematopoietic-memo.000webhostapp.com/addReport.php";
            try {
                String vid1 = params[1];
                String uid1 = params[2];
                String reason1 = params[3];

                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("uid","UTF-8")+"="+URLEncoder.encode(uid1,"UTF-8")+"&"+
                        URLEncoder.encode("vid","UTF-8")+"="+URLEncoder.encode(vid1,"UTF-8")+"&"+
                        URLEncoder.encode("reason","UTF-8")+"="+URLEncoder.encode(reason1,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("addlike"))
        {
            String vid=params[1];


            String login_url="https://haematopoietic-memo.000webhostapp.com/addLike.php";
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("vid","UTF-8")+"="+URLEncoder.encode(vid,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line=bufferedReader.readLine())!=null)
                {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return "liked";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if(type.equals("minuslike"))
        {
            String vid=params[1];


            String login_url="https://haematopoietic-memo.000webhostapp.com/minusLike.php";
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("vid","UTF-8")+"="+URLEncoder.encode(vid,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line=bufferedReader.readLine())!=null)
                {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return "liked";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if(type.equals("addDislike"))
        {
            String vid=params[1];


            String login_url="https://haematopoietic-memo.000webhostapp.com/addDislike.php";
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("vid","UTF-8")+"="+URLEncoder.encode(vid,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line=bufferedReader.readLine())!=null)
                {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return "disliked";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if(type.equals("minusDislike"))
        {
            String vid=params[1];


            String login_url="https://haematopoietic-memo.000webhostapp.com/minusDislike.php";
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("vid","UTF-8")+"="+URLEncoder.encode(vid,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line=bufferedReader.readLine())!=null)
                {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return "disliked";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if(type.equals("follow"))
        {
            String fromUser = params[1];
            String toUser=params[2];


            String login_url="https://haematopoietic-memo.000webhostapp.com/addFollow.php";
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(fromUser,"UTF-8")+"&"+
                        URLEncoder.encode("toUser","UTF-8")+"="+URLEncoder.encode(toUser,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line=bufferedReader.readLine())!=null)
                {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if(type.equals("login"))
        {
            //getting the string from json
            a= new AlertDialog.Builder(context).create();
            a.setTitle("Login Status");
            try {
                JSONObject obj = new JSONObject(result);
                String id=obj.getString("id");//this is the main id of user that we have to pass to new intent
                String status=obj.getString("success");
//                Toast.makeText(context, ""+status, Toast.LENGTH_LONG).show();
                if(status.equals("1"))
                {
                    a.setMessage("Login Success");
                    a.show();
                    db.insertLogin(Integer.parseInt(id));
                    Intent i = new Intent(context,VideoActivity.class);
                    context.startActivity(i);
                }
                else
                {
                    a.setMessage("Login Failed");
                    a.show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                a.setMessage("Invalid email or password");
                a.show();
            }
        }
        else if(type.equals("signup"))
        {
            //getting the string from json
            a= new AlertDialog.Builder(context).create();
            a.setTitle("Signup Status");
            try {
                JSONObject obj = new JSONObject(result);
                String status=obj.getString("success");
//                Toast.makeText(context, ""+status, Toast.LENGTH_LONG).show();
                if(status.equals("1"))
                {
                    a.setMessage("Sign Up Success");
                    a.show();
                    Intent i = new Intent(context,LoginActivity.class);
                    context.startActivity(i);
                }
                else if(status.equals("0"))
                {
                    a.setMessage("Sign Up Failed");
                    a.show();
                }
                else if(status.equals("2"))
                {
                    a.setMessage("Please choose another username");
                    a.show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                a.setMessage("Invalid email or password");
                a.show();
            }
        }
        else if(type.equals("uploadI"))
        {
            //getting the string from json
            try {
                JSONObject obj = new JSONObject(result);
                String status=obj.getString("success");
//                Toast.makeText(context, ""+status, Toast.LENGTH_LONG).show();
            } catch (JSONException e) {
                e.printStackTrace();

            }
        }
        else if(type.equals("upload"))
        {
            //getting the string from json
            a= new AlertDialog.Builder(context).create();
            a.setTitle("Upload");
            try {
                JSONObject obj = new JSONObject(result);
                String status=obj.getString("success");
//                Toast.makeText(context, ""+status, Toast.LENGTH_LONG).show();
                if(status.equals("1"))
                {
                    a.setMessage("Upload Success");
                    a.show();
                }
                else
                {
                    a.setMessage("Upload Failed");
                    a.show();
                }
            } catch (JSONException e) {
                e.printStackTrace();

            }
        }
        else if(type.equals("getImage"))
        {
            //getting the string from json
            try {
                JSONObject obj = new JSONObject(result);
                String status=obj.getString("success");
                location=obj.getString("location");
                names=obj.getString("name");
                Intent i = new Intent(context,ImageActivity.class);
                i.putExtra("Location",location);
                i.putExtra("Name",names);
                context.startActivity(i);

            } catch (JSONException e) {
                e.printStackTrace();

            }
        }
        else if(type.equals("getEncrypted"))
        {
            //getting the string from json
            try {
                JSONObject obj = new JSONObject(result);
                String status=obj.getString("success");
                location=obj.getString("location");
                names=obj.getString("name");
                Intent i = new Intent(context,ImageActivity.class);
                i.putExtra("Location",location);
                i.putExtra("Name",names);
                context.startActivity(i);

            } catch (JSONException e) {
                e.printStackTrace();

            }
        }
        else if(type.equals("getVideo"))
        {
            //getting the string from json
            try {
                JSONObject obj = new JSONObject(result);
                String status=obj.getString("success");
                location=obj.getString("location");
                names=obj.getString("name");
                cname=obj.getString("vname");
                des=obj.getString("des");
                like=obj.getString("vlike");
                dislike=obj.getString("vdislike");
                vid=obj.getString("vid");
//                LikeDB l = new LikeDB(context);
//                String lid = l.getLike();
                Intent i = new Intent(context,VideoActivity.class);
                i.putExtra("Location",location);
                i.putExtra("Name",names);
                i.putExtra("VName",cname);
                i.putExtra("des",des);
                i.putExtra("like",like);
                i.putExtra("dislike",dislike);
                i.putExtra("vid",vid);
//                i.putExtra("lid",lid);
                context.startActivity(i);

            } catch (JSONException e) {
                e.printStackTrace();

            }
        }
        else if(type.equals("report"))
        {
            try{
                JSONObject obj = new JSONObject(result);
                String status = obj.getString("success");
                if(status.equals("1"))
                    Toast.makeText(context, "Video Reported", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(context, "Video Report Failed", Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("addlike"))
        {
            Toast.makeText(context, "Like", Toast.LENGTH_SHORT).show();
        }
        else if(type.equals("addDislike"))
        {
            Toast.makeText(context, "Dislike", Toast.LENGTH_SHORT).show();
        }
        else if(type.equals("follow"))
        {
            //getting the string from json
            try {
                JSONObject obj = new JSONObject(result);
                String status=obj.getString("success");
//                Toast.makeText(context, ""+status, Toast.LENGTH_LONG).show();
                if(status.equals("1"))
                {
                    Toast.makeText(context, "Followed", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(context,LoginActivity.class);
                    context.startActivity(i);
                }
                else if(status.equals("0"))
                {
                    Toast.makeText(context, "Failed to Follow", Toast.LENGTH_SHORT).show();
                }
                else if(status.equals("2"))
                {
                    Toast.makeText(context, "Already Followed", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();

            }
        }






    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
