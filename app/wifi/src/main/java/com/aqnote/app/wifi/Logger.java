/*
 * WiFi Analyzer
 * Copyright (C) 2016  VREM Software Development <VREMSoftwareDevelopment@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.aqnote.app.wifi;

import android.util.Log;

import androidx.annotation.NonNull;

public class Logger {

    public void error(@NonNull Object object, @NonNull String msg, @NonNull Throwable e) {
        Log.e(object.getClass().getSimpleName() + " ", msg, e);
    }

    public void info(@NonNull Object object, @NonNull String msg) {
        Log.i(object.getClass().getSimpleName() + " ", msg);
    }

}