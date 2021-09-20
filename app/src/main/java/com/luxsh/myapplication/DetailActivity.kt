package com.luxsh.myapplication

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.luxsh.myapplication.room.AddNote
import com.luxsh.myapplication.room.NotesViewModel
import com.luxsh.myapplication.room.ShowNotes
import com.luxsh.myapplication.ui.theme.MyApplicationTheme

class DetailActivity : ComponentActivity() {
     val notesViewModel by viewModels<NotesViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {


                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                    Column() {
                        Greeting2("Android")
                        AddNote(getString(R.string.app_name)) {
                            notesViewModel.addNote(it)
                        }
                        ShowNotes(notesViewModel.notes) {
                            notesViewModel.removeNote(it)
                        }
                        SimpleSwitch()
                        WebPageScreen("http://www.google.com")
                    }

                }
            }
        }
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebPageScreen(urlToRender: String) {
    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            loadUrl(urlToRender)
        }
    }, update = {
        it.loadUrl(urlToRender)
    })
}

@Composable
fun Greeting2(name: String) {
  //  Text(text = "Hello $name!")
    Column {
        val appBarHorizontalPadding = 4.dp
        val titleIconModifier = Modifier
            .fillMaxHeight()
            .width(72.dp - appBarHorizontalPadding)

        val activity = (LocalContext.current as? Activity)

        TopAppBar(
            backgroundColor = colorResource(id = R.color.purple_700),
            elevation = 0.dp,
            modifier= Modifier.fillMaxWidth()) {

            //TopAppBar Content
            Box(Modifier.height(32.dp)) {

                //Navigation Icon
                Row(titleIconModifier, verticalAlignment = Alignment.CenterVertically)
                {
                    CompositionLocalProvider(
                        LocalContentAlpha provides ContentAlpha.high,
                    ) {
                        IconButton(
                            onClick = { activity!!.finish() },
                            enabled = true,
                        ) {
                            Icon(
                                imageVector =  Icons.Filled.ArrowBack,
                                contentDescription = "Back",
                            )
                        }
                    }
                }

                //Title
                Row(Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically) {

                    ProvideTextStyle(value = MaterialTheme.typography.h6) {
                        CompositionLocalProvider(
                            LocalContentAlpha provides ContentAlpha.high,
                        ){
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                maxLines = 1,
                                text = "Detail"
                            )
                        }
                    }
                }


            }
        }

       // SimpleCircularProgressComponent()

    }
}
@Composable
fun SimpleSwitch() {
    val mRemember = remember { mutableStateOf(false) }

    Switch(checked = mRemember.value, onCheckedChange = { mRemember.value = it })
}

@Composable
fun SimpleCircularProgressComponent() {
    // CircularProgressIndicator is generally used
    // at the loading screen and it indicates that
    // some progress is going on so please wait.
    Column(
        // we are using column to align our
        // imageview to center of the screen.
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),

        // below line is used for specifying
        // vertical arrangement.
        verticalArrangement = Arrangement.Center,

        // below line is used for specifying
        // horizontal arrangement.
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        // below line is use to display
        // a circular progress bar.
        CircularProgressIndicator(
            // below line is use to add padding
            // to our progress bar.
            modifier = Modifier.padding(16.dp),

            // below line is use to add color
            // to our progress bar.
            color = colorResource(id = R.color.purple_200),

            // below line is use to add stroke
            // width to our progress bar.
            strokeWidth = Dp(value = 4F)
        )
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        Greeting2("Android")
    }
}