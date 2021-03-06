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

package com.aqnote.app.wifi.navigation;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;

import com.aqnote.app.wifi.MainActivity;
import com.aqnote.app.wifi.R;

class FragmentItem implements NavigationMenuItem {
    private final Fragment fragment;

    FragmentItem(@NonNull Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void activate(@NonNull MainActivity mainActivity, @NonNull MenuItem menuItem, @NonNull NavigationMenu navigationMenu) {
        NavigationMenuView navigationMenuView = mainActivity.getNavigationMenuView();
        navigationMenuView.setCurrentNavigationMenu(navigationMenu);
        startFragment(mainActivity);
        mainActivity.setTitle(menuItem.getTitle());
        mainActivity.updateSubTitle();
    }

    private void startFragment(@NonNull MainActivity mainActivity) {
        FragmentManager fragmentManager = mainActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment, fragment).commit();
    }

    Fragment getFragment() {
        return fragment;
    }
}
