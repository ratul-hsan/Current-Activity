/*
 *   Copyright (C) 2022 Ratul Hasan
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package io.github.ratul.topactivity.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.content.ClipboardManager;
import android.content.ClipData;
import androidx.appcompat.app.AppCompatActivity;
import io.github.ratul.topactivity.App;

/**
 * Created by Ratul on 04/05/2022.
 */
@TargetApi(Build.VERSION_CODES.O)
public class BackgroundActivity extends AppCompatActivity {
    public static String STRING_COPY = "io.github.ratul.topactivity.COPY_STRING";
    public static String COPY_MSG = "io.github.ratul.topactivity.COPY_STRING_MSG";
	public static boolean isAlive;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		if (!getIntent().hasExtra(STRING_COPY))
			finish();
        String str = getIntent().getStringExtra(STRING_COPY);
        String msg = getIntent().getStringExtra(COPY_MSG);
		msg = (msg == null || msg.trim().isEmpty()) ? "Copied" : msg;
        
        if (str != null) {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clip = new ClipData(ClipData.newPlainText("", str));
            clipboard.setPrimaryClip(clip);
        }
		finish();
    }

	@Override
	protected void onStop() {
		isAlive = false;
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		isAlive = false;
		super.onDestroy();
	}

	@Override
	protected void onStart() {
		isAlive = true;
		super.onStart();
	}
}
