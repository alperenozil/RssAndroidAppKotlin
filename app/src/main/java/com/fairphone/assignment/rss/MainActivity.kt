/*
 * Copyright 2019 Fairphone B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fairphone.assignment.rss

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.fairphone.assignment.rss.ui.FeedViewModel
import com.fairphone.assignment.rss.ui.composable.ListItem
import com.fairphone.assignment.rss.ui.composable.WebViewPage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen(viewModel : FeedViewModel = hiltViewModel()) {
    val state = viewModel.feedState.collectAsState()
    val urlState = viewModel.urlState.collectAsState()
    Column(Modifier.fillMaxSize()) {
        LazyColumn(Modifier.fillMaxSize()) {
            state.value.items?.let { list ->
                items(list) { item ->
                    ListItem(item, viewModel::setUrl)
                }
            }
        }
        if(state.value.error?.isNotBlank() == true){
            Text("Error!")
        }
        if(state.value.isLoading == true){
            Text("Loading...")
        }
    }
    if(urlState.value.length>0) WebViewPage(urlState.value)
}

@Composable
@Preview
fun MainScreen_Preview() {
    MainScreen()
}
