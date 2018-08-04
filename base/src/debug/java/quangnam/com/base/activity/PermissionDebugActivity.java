/*
 * MIT License
 *
 * Copyright (c) 2017 Tran Quang Nam
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package quangnam.com.base.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import quangnam.com.base.service.FloatingViewService;

/**
 * This activity request permission {@link Settings#ACTION_MANAGE_OVERLAY_PERMISSION}
 * for debug purpose
 */
public class PermissionDebugActivity extends AppCompatActivity {
    private static final int REQUEST_PERMISSION_OVERLAY = 1;
    private static final int REQUEST_PERMISSION_RW = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        setContentView(R.layout.activity_permission_debug);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && !Settings.canDrawOverlays(this)) {
            Intent i = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(i, REQUEST_PERMISSION_OVERLAY);
        } else if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION_RW);
        } else {
            startDebugService();
        }
    }

    private void startDebugService() {
        startService(new Intent(this, FloatingViewService.class));
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PERMISSION_OVERLAY) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                    && Settings.canDrawOverlays(this)) {
                startDebugService();
            } else {
                Toast.makeText(PermissionDebugActivity.this,
                        "Permission doesn't allowed.",
                        Toast.LENGTH_SHORT)
                        .show();
                finish();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION_RW) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                    && Settings.canDrawOverlays(this)) {
                startDebugService();
            } else {
                Toast.makeText(PermissionDebugActivity.this,
                        "Permission doesn't allowed.",
                        Toast.LENGTH_SHORT)
                        .show();
                finish();
            }
        }
    }
}
