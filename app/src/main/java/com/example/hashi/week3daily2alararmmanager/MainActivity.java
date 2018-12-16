package com.example.hashi.week3daily2alararmmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hashi.week3daily2alararmmanager.model.MyPlaces;
import com.example.hashi.week3daily2alararmmanager.service.MyService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button;
    EditText editText;
    MyReceiver myReceiver;
    public ImageView iv_user;
    static ArrayList<MyPlaces> arrayList;
    public static final String FILTER_ACTION_KEY = "any_key";
    private int PICK_IMAGE_REQUEST = 1;

    static PlacesAdapter categoryAdapter;
    static RecyclerView recyclerView;
    Context context;
    EditText editName, editEmail, editPhone;
    public static final int PICK_IMAGE = 1;
    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        context = MainActivity.this;
//        textView = findViewById(R.id.textView);
        button = findViewById(R.id.btn_submit);

        editName = findViewById(R.id.et_name);
        editEmail = findViewById(R.id.et_email);
        editPhone = findViewById(R.id.et_phone);
        iv_user = findViewById(R.id.iv_user);

        arrayList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(recyclerView.getContext(), 1, GridLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        setReceiver();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString();
                String email = editEmail.getText().toString();
                String phone = editPhone.getText().toString();
                Toast.makeText(context, "Wait 5 sec then data will come to recyclerview", Toast.LENGTH_LONG).show();
                Utils.savePreferences("started", "true", context);
                Intent intent = new Intent(MainActivity.this, MyService.class);
                intent.putExtra("message", name);
                intent.putExtra("email", email);
                intent.putExtra("phone", phone);

                //intent.putExtra("imageByteArray", getImageByteArrayFromBitmap(bitmap));

                byte[] image = getImageByteArrayFromBitmap(iv_user);
                intent.putExtra("imageByteArray", image);

                startService(intent);
            }
        });

        iv_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
   /*             Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
   */

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

            }
        });

    }

    private void setReceiver() {
        myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(FILTER_ACTION_KEY);

        LocalBroadcastManager.getInstance(this).registerReceiver(myReceiver, intentFilter);
    }


    @Override
    protected void onStart() {

        super.onStart();
    }

    @Override
    protected void onStop() {
        try {
            unregisterReceiver(myReceiver);
        } catch (Exception e) {

        }

        super.onStop();
    }

    private byte[] getImageByteArrayFromBitmap(ImageView imageView) {
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        return stream.toByteArray();
    }

    /* private byte[] getImageByteArrayFromBitmap(Bitmap bitmap) {
         ByteArrayOutputStream stream = new ByteArrayOutputStream();
         bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
         byte[] byteArray = stream.toByteArray();
         bitmap.recycle();
         return byteArray;

     }
    */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));


                iv_user.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String name = intent.getStringExtra("broadcastMessage");
            String email = intent.getStringExtra("email");
            String phone = intent.getStringExtra("phone");
            byte[] imageByteArray = intent.getByteArrayExtra("imageByteArray");

//            textView.setText(textView.getText() + "\n" + message);
// Log.d("name","name "+name);

            String started=Utils.getPreferences("started",context);
            if(started.equalsIgnoreCase("true")) {
                Utils.savePreferences("started", "false", context);
                MyPlaces ob = new MyPlaces(name, email, phone, imageByteArray);
                arrayList.add(ob);
                categoryAdapter = new PlacesAdapter(context, arrayList);
                recyclerView.setAdapter(categoryAdapter);
                categoryAdapter.notifyDataSetChanged();
//                Intent intent1=new Intent(context,MainActivity.class);
//                context.startActivity(intent1);
            }


        }

    }
}
