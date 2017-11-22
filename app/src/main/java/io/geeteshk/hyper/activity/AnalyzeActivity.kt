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

package io.geeteshk.hyper.activity

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import io.geeteshk.hyper.R
import io.geeteshk.hyper.adapter.AnalyzeAdapter
import io.geeteshk.hyper.fragment.analyze.AnalyzeFileFragment
import io.geeteshk.hyper.helper.Styles
import kotlinx.android.synthetic.main.activity_analyze.*
import kotlinx.android.synthetic.main.widget_toolbar.*
import java.io.File

class AnalyzeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(Styles.getThemeInt(this))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analyze)
        toolbar.title = File(intent.getStringExtra("project_file")).name
        setSupportActionBar(toolbar)
        setupPager(analyzePager)
        analyzeTabs.setupWithViewPager(analyzePager)
    }

    private fun setupPager(analyzePager: ViewPager?) {
        val adapter = AnalyzeAdapter(supportFragmentManager)
        adapter.addFragment(AnalyzeFileFragment(), "FILES", intent.extras)
        analyzePager!!.adapter = adapter
    }
}