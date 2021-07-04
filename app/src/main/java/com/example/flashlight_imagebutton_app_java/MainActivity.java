package com.example.flashlight_imagebutton_app_java;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MainActivity extends AppCompatActivity {
    ImageButton imageButton;
    CameraManager cameraManager = (CameraManager)getSystemService(CAMERA_SERVICE);
    private boolean flashlightStateChanged = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageButton = findViewById(R.id.imageButton3);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (!flashlightStateChanged) {
                    try {
                        String cameraIdForFlashlight = cameraManager.getCameraIdList()[0];
                        cameraManager.setTorchMode(cameraIdForFlashlight, true);
                        imageButton.setImageResource(R.drawable.on);
                        flashlightStateChanged = true;
                    } catch (CameraAccessException exception) {
//                        System.out.println(exception);
                    }
                } else {
                    try {
                        String cameraIdForFlashlight = cameraManager.getCameraIdList()[0];
                        cameraManager.setTorchMode(cameraIdForFlashlight, false);
                        imageButton.setImageResource(R.drawable.off);
                        flashlightStateChanged = false;
                    } catch (CameraAccessException exception) {
//                        System.out.println(exception);
                    }
                }
            }
        });
    }
}