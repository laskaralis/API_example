package com.example.kelas5_belajar_api;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kelas5_belajar_api.service.ApiClient;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditActivity extends AppCompatActivity {

    EditText etTitle, etBody;
    Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        etTitle= findViewById(R.id.et_title);
        etBody=findViewById(R.id.et_body);

        btnUpdate = findViewById(R.id.btn_update);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fieldID = etTitle.getText().toString();
                String fieldBody = etBody.getText().toString();

                if (fieldID.isEmpty()){
                    etTitle.setError("ID wajib diisi");
                    etTitle.requestFocus();
                }if(fieldBody.isEmpty()){
                    etBody.setError("Body wajib diisi");
                    etBody.requestFocus();
                }else {
                    ApiClient.getService().editData(id, etTitle.getText().toString(), etBody.getText().toString(), 1).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                            if (response.isSuccessful()) {
                                Toast.makeText(EditActivity.this, "Berhasil Edit", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(EditActivity.this, "Gagal Edit", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                            Toast.makeText(EditActivity.this, t.toString(), Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });



    }
}