/*
* Copyright (C) 2013 The OmniROM Project
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 2 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program. If not, see <http://www.gnu.org/licenses/>.
*
*/
package org.lineageos.device.DeviceSettings;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.text.TextUtils;
import androidx.preference.PreferenceManager;

import org.lineageos.device.DeviceSettings.FileUtils;

public class Startup extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent bootintent) {
        boolean enabled = false;
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);

        DeviceSettings.restoreSliderStates(context);
        DeviceSettings.restoreVibStrengthSetting(context);

        enabled = sharedPrefs.getBoolean(DeviceSettings.KEY_HBM_SWITCH, false);
        if (enabled) {
            restore(HBMModeSwitch.getFile(), enabled);
        }

        enabled = sharedPrefs.getBoolean(DeviceSettings.KEY_SRGB_SWITCH, false);
        if (enabled) {
            restore(SRGBModeSwitch.getFile(), enabled);
        }

        enabled = sharedPrefs.getBoolean(DeviceSettings.KEY_DCI_SWITCH, false);
        if (enabled) {
            restore(DCIModeSwitch.getFile(), enabled);
        }

        enabled = sharedPrefs.getBoolean(DeviceSettings.KEY_WIDE_SWITCH, false);
        if (enabled) {
            restore(WideModeSwitch.getFile(), enabled);
        }

        enabled = sharedPrefs.getBoolean(DeviceSettings.KEY_DC_SWITCH, false);
        if (enabled) {
            restore(DCModeSwitch.getFile(), enabled);
        }

        enabled = sharedPrefs.getBoolean(DeviceSettings.KEY_FPS_INFO, false);
        if (enabled) {
            context.startService(new Intent(context, FPSInfoService.class));
        }

    }

    private void restore(String file, boolean enabled) {

        if (file == null) {
            return;
        }

        if (enabled) {
            FileUtils.writeValue(file, "1");
        }
    }

    private void restore(String file, String value) {

        if (file == null) {
            return;
        }

        FileUtils.writeValue(file, value);
    }
}
