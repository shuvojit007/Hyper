package io.geeteshk.hyper;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import io.geeteshk.hyper.util.PermissionUtil;
import io.geeteshk.hyper.util.TypefaceUtil;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TypefaceUtil.setDefaultFont(getApplicationContext(), "SERIF", "fonts/Roboto-Medium.ttf");
        TypefaceUtil.setDefaultFont(getApplicationContext(), "MONOSPACE", "fonts/RobotoCondensed-BoldItalic.ttf");
        TypefaceUtil.setDefaultFont(getApplicationContext(), "SANS_SERIF", "fonts/RobotoCondensed-Regular.ttf");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(0xFFE64A19);
        }

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                PermissionUtil.getRequiredPermissions(SplashActivity.this);
            }
        }, 800);
    }

    @SuppressLint("InlinedApi")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PermissionUtil.WRITE_STORAGE_REQUEST_CODE) {
            if (PermissionUtil.checkPermission(SplashActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                PermissionUtil.getPermission(SplashActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE, R.string.permission_storage_rationale, PermissionUtil.WRITE_STORAGE_REQUEST_CODE);
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

        if (PermissionUtil.checkAllPermissions(SplashActivity.this)) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }
}
