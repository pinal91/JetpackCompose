package com.luxsh.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.luxsh.myapplication.ui.*
import com.luxsh.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            /*   MyApplicationTheme {
                   // A surface container using the 'background' color from the theme
                   Surface(color = MaterialTheme.colors.background) {
                       Greeting("Android", "New Android",SampleData.conversationSample)
                   }
               }*/

            MyApplicationTheme {
                Scaffold() {
                   AppMainScreen()

                   // MainScreen()
                }

            }
        }
    }

}

@Composable
fun TextInput() {
    val context = LocalContext.current
    Column() {
        val focusRequester = remember { FocusRequester() }
        Text(text = "Text Inputs", style = typography.h6, modifier = Modifier.padding(8.dp))
        var text by remember { mutableStateOf(TextFieldValue("")) }
        // Normal Text Input field with floating label
        // placeholder is same as hint in xml of edit text
        OutlinedTextField(
            value = text,
            onValueChange = { newValue -> text = newValue },

            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            label = { Text("label") },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text,

            ),
            keyboardActions = KeyboardActions(
                onNext = { focusRequester.requestFocus() }
            ),

            placeholder = { Text("placeholder") },
        )

        var text1 by remember { mutableStateOf(TextFieldValue("")) }

        val focusRequester1 = remember { FocusRequester() }
        // Outlined Text Input Field
        OutlinedTextField(
            value = text1,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .focusRequester(focusRequester = focusRequester),
            label = { Text(text = "Password") },
            placeholder = { Text(text = "12334444") },
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = {
                text1 = it
            },
            keyboardActions = KeyboardActions(
                onNext = { focusRequester1.requestFocus() }
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password,imeAction = ImeAction.Next,)

        )

        // Outlined Input text with icon on the left
        // inside leadingIcon property add the icon
        val focusRequester2 = remember { FocusRequester() }
        var text2 by remember { mutableStateOf(TextFieldValue("")) }
        OutlinedTextField(
            value = text2,
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .focusRequester(focusRequester = focusRequester1),
            keyboardActions = KeyboardActions(
                onNext = { focusRequester2.requestFocus() }
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text,imeAction = ImeAction.Next,),
            label = { Text(text = "Email address") },
            placeholder = { Text(text = "Your email") },
            onValueChange = {
                text2 = it
            }
        )

        // Outlined Input text with icon on the left and right
        // inside leadingIcon property add the left icon
        // inside trailingIcon property add right icon
        val focusManager = LocalFocusManager.current
        var text3 by remember { mutableStateOf(TextFieldValue("")) }
        OutlinedTextField(
            value = text3,
            leadingIcon = { Icon(imageVector = Icons.Default.Phone, contentDescription = null) },
            trailingIcon = { Icon(imageVector = Icons.Default.Phone, contentDescription = null) },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .focusRequester(focusRequester = focusRequester2),

            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone,imeAction = ImeAction.Done,),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus()  }
            ),
            label = { Text(text = "Phone") },
            placeholder = { Text(text = "Your Phone Number") },
            onValueChange = {
                text3 = it
            }
        )

        Button(onClick =
        { showToast(context,""+text.text +", "+text1.text+", "+text2.text+", " +
                ""+text3.text)
            context.startActivity(Intent(context, MapActivity::class.java))
        },
            modifier = Modifier
                .padding(8.dp)
                .align(CenterHorizontally)
                .defaultMinSize(minWidth = 300.dp)) {
            Text(text = "Submit",textAlign = TextAlign.Center )


    }




    }

}
@Composable
fun BoxExample() {
    Box(Modifier.fillMaxSize()) {
        Box(
            Modifier
                .align(Alignment.TopCenter)
                .fillMaxHeight()
                .fillMaxWidth()
                .background(Color.White)
        )

        Text("This is first text", modifier = Modifier.align(Alignment.TopCenter))

        Text("This is second text", modifier = Modifier.align(Alignment.Center))
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(12.dp),
            onClick = {}
        ) {
            Text("+")
        }
    }
}
fun showToast(context: Context, msg:String){
    Toast.makeText(context,msg,Toast.LENGTH_LONG).show()
}
/*this is message list */
@Composable
fun Greeting(name: String, s: String, messages: List<Message>) {

    Column(modifier = Modifier.padding(all = 10.dp)) {


        Row() {
            Image(
                painter = painterResource(R.drawable.ic_launcher),
                contentDescription = "Profile Pic",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
            )

            Column {
                Text(
                    text = "Hello $name!",
                    color = colorResource(id = R.color.purple_700),
                    style = MaterialTheme.typography.subtitle1
                )
                Spacer(modifier = Modifier.height(4.dp))

                Surface(shape = MaterialTheme.shapes.medium, elevation = 1.dp) {
                    Text(
                        text = "Hello $s!",
                        modifier = Modifier.padding(all = 4.dp),
                        color = colorResource(id = R.color.teal_700),
                        style = MaterialTheme.typography.subtitle2
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
            }

        }
        LazyRow {
            items(messages) { message ->
                MessageCard(message)
            }
        }
    }


}


@Composable
fun AppMainScreen() {
    val navController = rememberNavController()
    Surface(color = MaterialTheme.colors.background) {
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        val openDrawer = {
            scope.launch {
                drawerState.open()
            }
        }
        ModalDrawer(
            drawerState = drawerState,
            gesturesEnabled = drawerState.isOpen||drawerState.isClosed,
            drawerContent = {
                Drawer(

                    onDestinationClicked = { route ->
                        scope.launch {
                            drawerState.close()
                        }
                        navController.navigate(route) {
                            popUpTo = navController.graph.startDestinationId
                            launchSingleTop = true
                        }
                    }

                )
            }
        ) {
            NavHost(
                navController = navController,
                startDestination = DrawerScreens.Home.route
            ) {
                composable(DrawerScreens.Home.route) {
                    Home(
                        openDrawer = {
                            openDrawer()
                        }
                    )
                }
                composable(DrawerScreens.Account.route) {
                    Account(
                        openDrawer = {
                            openDrawer()
                        }
                    )
                }
                composable(DrawerScreens.Help.route) {
                    Help(
                        navController = navController
                    )
                }

                composable(DrawerScreens.Retrofit.route) {
                    RetrofitClass( navController = navController)

                }
            }
        }
    }
}


@Composable
fun MessageCard(msg: Message) {
    Spacer(modifier = Modifier.height(10.dp))
    Row() {
        Image(
            painter = painterResource(R.drawable.ic_launcher),
            contentDescription = "Profile Pic",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, colors.secondary, CircleShape)
        )

        // We keep track if the message is expanded or not in this
        // variable
        var isExpanded by remember { mutableStateOf(false) }

        val surfaceColor: Color by animateColorAsState(
            if (isExpanded) MaterialTheme.colors.primary
            else MaterialTheme.colors.surface,
        )
        val context= LocalContext.current
        Column(modifier = Modifier
            .clickable {
                isExpanded = !isExpanded
                context.startActivity(Intent(context, DetailActivity::class.java))
            }
            .padding(10.dp)) {
            Text(text = msg.author)
            Surface(color = surfaceColor) {
                Text(
                    text = msg.body,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1
                )
            }

        }


    }

}


data class Message(val author: String, val body: String)


/*
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {

        Greeting("Android", "New Android",SampleData.conversationSample)
    }
}
*/



data class Recipe(
    @DrawableRes val imageResource: Int,
    val title: String,
    val ingredients: List<String>
)

@Composable
fun RecipeCard(recipe: Recipe, modifier: Modifier) {
    val context = LocalContext.current
    Surface(
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp, modifier = modifier

    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .clickable {
                context.startActivity(Intent(context, ScattfoldMainActivity::class.java))
            }) {

            Image(
                painter = painterResource(recipe.imageResource),
                contentDescription = "Profile Pic",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)

            )


            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    recipe.title, style = typography.h4,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                for (ingredient in recipe.ingredients) {
                    Text(
                        "• $ingredient",
                        color = colorResource(id = R.color.purple_200)
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun DefaultRecipeCard() {
    TextInput()
    /*LazyColumn {
        items(defaultRecipes) { defaultRecipes ->
            RecipeCard(defaultRecipes, Modifier.padding(16.dp))
        }
    }*/
}