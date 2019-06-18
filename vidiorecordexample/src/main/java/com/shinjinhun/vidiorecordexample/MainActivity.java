package com.shinjinhun.vidiorecordexample;

import android.Manifest;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

// MediaRecorder 동영상 녹화
public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private Camera camera;  // hardware Camera
    private MediaRecorder mediaRecorder;
    private Button btn_record;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private boolean recording = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TedPermission.with(this)
                .setPermissionListener(permission)
                .setRationaleMessage("녹화를 위하여 권한을 허용해주세요.")
                .setDeniedMessage("권한이 거부되었습니다., 설정 > 권한에서 허용해주세요.")
                .setPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO)
                .check();

        btn_record = (Button)findViewById(R.id.btn_record);
        btn_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recording) {
                    mediaRecorder.stop();
                    mediaRecorder.release();
                    camera.lock();
                    recording = false;
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "녹화가 시작되었습니다.", Toast.LENGTH_SHORT).show();
                            try {
                                mediaRecorder = new MediaRecorder();
                                camera.unlock();    // 카메라 언락
                                mediaRecorder.setCamera(camera);    // 카메라 변수 사용
                                mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);  // 동영상 찰영시 소리가 나게
                                mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA); // 동영상 비디오
                                mediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_720P));  // 저장화질
                                mediaRecorder.setOrientationHint(90);   // 저장각도
                                mediaRecorder.setOutputFile("/sdcard/test.mp4");    // 저정경로 파일
                                mediaRecorder.setPreviewDisplay(surfaceHolder.getSurface());    // 동영상 프리뷰 ( 녹화시 나오는 화면
                                mediaRecorder.prepare();    // 준비
                                mediaRecorder.start();  // 시작
                                recording = true;
                            } catch (Exception e) {
                                e.printStackTrace();
                                mediaRecorder.release();
                            }

                        }
                    });
                }
            }
        });


    }

    PermissionListener permission = new PermissionListener() {
        // 권한이 허용되었을때
        @Override
        public void onPermissionGranted() {
            Toast.makeText(MainActivity.this,"권한 허가", Toast.LENGTH_SHORT).show();


            camera = Camera.open();
            camera.setDisplayOrientation(90);
            surfaceView = (SurfaceView)findViewById(R.id.surfaceView);
            surfaceHolder = surfaceView.getHolder();
            surfaceHolder.addCallback(MainActivity.this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }

        // 권한이 거부되었을때
        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            Toast.makeText(MainActivity.this,"권한 거부", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    private void refreshCamera(Camera camera) {
        if (surfaceHolder.getSurface() == null) {
            return;
        }

        try {
            camera.stopPreview();
        } catch (Exception e) {
            e.printStackTrace();
        }

        setCamera(camera);
    }

    private void setCamera(Camera cam) {
        camera = cam;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        refreshCamera(camera);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
