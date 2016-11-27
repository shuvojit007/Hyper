/*
 * Copyright 2016 Geetesh Kalakoti <kalakotig@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.geeteshk.hyper.license;

import android.content.Context;

import de.psdev.licensesdialog.licenses.License;
import io.geeteshk.hyper.R;

public class EclipseDistributionLicense10 extends License {

    @Override
    public String getName() {
        return "Eclipse Distribution License 1.0";
    }

    @Override
    public String readSummaryTextFromResources(Context context) {
        return getContent(context, R.raw.edl_v10);
    }

    @Override
    public String readFullTextFromResources(Context context) {
        return getContent(context, R.raw.edl_v10);
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public String getUrl() {
        return "http://www.eclipse.org/org/documents/edl-v10.php";
    }
}