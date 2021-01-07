package com.example.a1105;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.a1105.RemoteService.BASE_URL;

public class MainActivity2 extends AppCompatActivity {
    EditText edtName,edtPrice,edtCode;
    ImageView image;

    String strFile;

    Retrofit retrofit;
    RemoteService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getSupportActionBar().setTitle("상품등록");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //레트로핏 실행
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        service = retrofit.create(RemoteService.class);

        edtName = findViewById(R.id.edtName);
        edtPrice = findViewById(R.id.edtPrice);
        edtCode = findViewById(R.id.edtCode);
        image = findViewById(R.id.image);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            switch (requestCode){
                case 1: //앨범 사진 선택시 앨범에 있는 사진 출력
                    image.setImageURI(data.getData());//미리보기

                    //파일명가져오기
                    Cursor cursor = getContentResolver().query(data.getData(),null,null,null,null);
                    cursor.moveToFirst();
                    strFile=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                    System.out.println("파일명"+strFile);

                    break;

                case 0: //사진촬영 업로드
                    image.setImageBitmap(BitmapFactory.decodeFile(strFile));
                    break;


            }
        }
    }

    //버튼눌렀을때
    public void mClick(View v){
        switch (v.getId()){
            //글 저장버튼눌렀을떄
            case R.id.btnSave:
                File image = new File(strFile);
                RequestBody mFile = RequestBody.create(MediaType.parse("multipart/form-data"),image);

                MultipartBody.Part uploadFile=MultipartBody.Part.createFormData("image",image.getName(),mFile);

                RequestBody strCode = RequestBody.create(MediaType.parse("multipart/form-data"),edtCode.getText().toString());
                RequestBody strName = RequestBody.create(MediaType.parse("multipart/form-data"),edtName.getText().toString());
                RequestBody strPrice = RequestBody.create(MediaType.parse("multipart/form-data"),edtPrice.getText().toString());

                Call<Void> call = service.insert(strCode,strName,strPrice,uploadFile);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        setResult(RESULT_OK);
                        finish();

                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                    }
                });
                break;

                //사진올리는 버튼 눌렀을때
            case R.id.btnCamera:
                AlertDialog.Builder box = new AlertDialog.Builder(this);
                box.setTitle("이미지 선택");
                box.setPositiveButton("사진촬영", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        try{
                            File storageDir=getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                            File file=File.createTempFile("img_", ".jpg", storageDir);
                            strFile=file.getAbsolutePath();
                            System.out.println("strFile............." + strFile);
                            Uri photoURI= FileProvider.getUriForFile(MainActivity2.this,
                                    getApplicationContext().getPackageName(), file);
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                            startActivityForResult(intent, 0);
                        }catch(Exception e){
                            System.out.println("error..........." + e.toString());
                        }
                    }
                });
                box.setNegativeButton("앨범선택", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent,1);
                    }
                });
                box.show();
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}