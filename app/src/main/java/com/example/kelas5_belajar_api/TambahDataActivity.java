package com.example.kelas5_belajar_api;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.util.EthiopicCalendar;
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

public class TambahDataActivity extends AppCompatActivity {

    EditText etTitle, etBody;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);

        etTitle = findViewById(R.id.et_title);
        etBody = findViewById(R.id.et_body);
        btnSubmit = findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fieldTitle = etTitle.getText().toString();
                String fieldBody = etBody.getText().toString();

                if (fieldTitle.isEmpty()){
                    etTitle.setError("Title wajib diisi");
                    etTitle.requestFocus();
                }if(fieldBody.isEmpty()){
                    etBody.setError("Body wajib diisi");
                    etBody.requestFocus();
                }else {
                    ApiClient.getService().postData(etTitle.getText().toString(), etBody.getText().toString(), 1).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(TambahDataActivity.this, "Berhasil Input", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(TambahDataActivity.this, "Gagal Input", Toast.LENGTH_SHORT).show();

                            }

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                            Toast.makeText(TambahDataActivity.this, t.toString(), Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });

    }



    }


