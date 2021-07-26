package com.example.kelas5_belajar_api;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kelas5_belajar_api.Adapter.ApiAdapter;
import com.example.kelas5_belajar_api.model.ModelPost;
import com.example.kelas5_belajar_api.service.ApiClient;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    RecyclerView rvApi;
    List<ModelPost> modelPosts;
    ApiAdapter adapter;
    LinearLayoutManager layoutManager;
    Button btnTambah;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        btnTambah = findViewById(R.id.btn_create);
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , TambahDataActivity.class);
                startActivity(intent);

            }
        });


        textView = findViewById(R.id.tv_data);

        rvApi = findViewById(R.id.rv_siswa);
        rvApi.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        rvApi.setLayoutManager(layoutManager);
        rvApi.addOnItemTouchListener(new RecyclerTouchListener(this, rvApi, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                showActionsDialog(position, modelPosts.get(position).getId());
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));






        getData();
    }

    private void showActionsDialog(int position, int id){
        CharSequence pilih[] = new CharSequence[]{"Edit", "Delete"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose opption");
        builder.setItems(pilih, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which==0){
                    //get dialog

                    Intent intent = new Intent(MainActivity.this, EditActivity.class);
                    intent.putExtra("id", id);
                    startActivity(intent);

                }else{
                    //delete

                    ApiClient.getService().deleteData(id).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()){
                                Toast.makeText(MainActivity.this, "Berhasil Delete", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(MainActivity.this, "Gagal Delete", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });

                }
            }
        });
        builder.show();

    }

    private void getData(){
        ApiClient.getService().getPost().enqueue(new Callback<List<ModelPost>>() {
            @Override
            public void onResponse(Call<List<ModelPost>> call, Response<List<ModelPost>> response) {
                if (response.isSuccessful()){
                    modelPosts  = response.body();

                    adapter = new ApiAdapter(MainActivity.this, modelPosts);
                    rvApi.setAdapter(adapter);
                    adapter.notifyDataSetChanged();



                }else{
                    Toast.makeText(MainActivity.this,"gagal",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ModelPost>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}