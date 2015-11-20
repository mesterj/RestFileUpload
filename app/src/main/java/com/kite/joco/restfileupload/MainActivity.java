package com.kite.joco.restfileupload;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kite.joco.restfileupload.rest.FileAPI;
import com.kite.joco.restfileupload.rest.FileService;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;

import org.w3c.dom.Text;

import java.io.File;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final int SELECT_PICTURE = 1;

    private String selectedImagePath;
    private ImageView img;
    private TextView tvPicturePath;
    private String res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvPicturePath = (TextView) findViewById(R.id.tvKepPath);
    }

    public void onClick(View v){
        switch (v.getId()){
            case (R.id.btnSelect):
                selectFromGallery();
                break;
            case (R.id.btnSend):
                sendPic();
                break;
            default:
        }
    }

    public void selectFromGallery(){
        Intent selectIntent = new Intent();
        selectIntent.setType("image/*");
        selectIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(selectIntent,"Válassz ki egy képet"),SELECT_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK){
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);
                Log.i("Path of image ",selectedImagePath);
                if (!selectedImagePath.equals(null)) {
                    tvPicturePath.setText(selectedImagePath);
                }
                else
                    tvPicturePath.setText("Null lett???");
            }
        }
    }

    public String getPath(Uri uri){
        String [] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    public void sendPic(){
        //Toast.makeText(this,"Ezt fogja elküldeni: "+selectedImagePath,Toast.LENGTH_LONG).show();
        File file = new File(selectedImagePath);
        FileAPI fileAPIService = FileService.createFileService(FileAPI.class);

        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);

        Call<String> call = fileAPIService.getImageOne(requestBody);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Response<String> response, Retrofit retrofit) {
                Log.i("Válasz siker esetén", response.toString());
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("Hiba esetén", t.getMessage());
            }
        });
    }

    public void sendPic2(){
        File file = new File(selectedImagePath);
        FileAPI fileAPIService = FileService.createFileService(FileAPI.class);

        String description = "This is the description";

        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);

        Call<String> call = fileAPIService.getImageTwo(requestBody);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Response<String> response, Retrofit retrofit) {
                Log.i("Válasz siker esetén",response.toString());
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("Hiba esetén",t.getMessage());
            }
        });
    }


}
