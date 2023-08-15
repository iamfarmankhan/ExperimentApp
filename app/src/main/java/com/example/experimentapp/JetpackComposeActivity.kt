package com.example.experimentapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.CalendarContract.Colors
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
class JetpackComposeActivity : AppCompatActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jetpack_compose)
        findViewById<ComposeView>(R.id.my_composable).setContent {

            MaterialTheme {
                //   DemoSnackBar()
                //ImageSlider()
                Column (verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                    Button(onClick = { }, content = {
                        Text(text = "Show Date Picker")
                    }, modifier = Modifier
                        .wrapContentWidth()
                        .height(50.dp)
                    )
                }

            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ImageSlider() {
    val imageList = listOf(
        "https://cdn.kimkim.com/files/a/content_articles/featured_photos/dd5e170751215b7c8d8cf0e637ec0cb454e4d9b7/big-a54205d7c9dddff87a6a63b5fd299f58.jpg",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTwt_p4u0d-ZXlM5ktlSS4qn0XiRG0CrQi-ALd7xVb6kuGwPbgxH3GbbW2oN7EkpduIgaU&usqp=CAU",
        "https://www.tibettravel.org/blog/wp-content/uploads/2017/09/yadong-valley.jpg"
    )


    val pagerState = rememberPagerState()
    var key by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = key) {
        //  delay(2000)
        Log.d("Farman", "Current Page ${pagerState.currentPage}")
        var currentPage = pagerState.currentPage
        if (currentPage == imageList.size - 1) {
            currentPage = 0
        } else
            currentPage += 1

        Log.d("Farman", "moved to $currentPage")
        // pagerState.animateScrollToPage(page = currentPage)
        launch {
            delay(2000)
            with(pagerState) {
                animateScrollToPage(page = currentPage)
                key = !key
            }
        }
    }
    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            HorizontalPager(pageCount = imageList.size, state = pagerState) {
                AsyncImage(
                    model = imageList[it],
                    contentDescription = "Test Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    contentScale = ContentScale.FillBounds
                )
            }
            Row(
                Modifier
                    .height(30.dp)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(imageList.size) { iteration ->
                    val color =
                        if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                    Box(
                        modifier = Modifier
                            .padding(3.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(8.dp)

                    )
                }
            }

        }
    }


}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DemoSnackBar() {
    val snackBarHostState = remember {
        SnackbarHostState()
    }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    Scaffold(content = {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                // Toast.makeText(context,"I am toast",Toast.LENGTH_SHORT).show()
                /* coroutineScope.launch {

                     val snackBarResult = snackBarHostState.showSnackbar(
                         message = "Snackbar is here",
                         actionLabel = "Undo",
                         duration = SnackbarDuration.Short
                     )
                     when (snackBarResult) {
                         SnackbarResult.ActionPerformed -> {
                             Log.d("Snackbar", "Action Performed")
                         }
                         else -> {
                             Log.d("Snackbar", "Snackbar dismissed")
                         }
                     }
                 }*/

            }) {
                Text(text = "Show Snack Bar", color = Color.White)
            }
        }
    }, snackbarHost = { SnackbarHost(hostState = snackBarHostState) })
}

@Composable
fun Body(snackbarHostState: SnackbarHostState, coroutineScope: CoroutineScope) {

}

@Composable
fun BottomNav() {
    var selectedIndex by remember {
        mutableStateOf(0)
    }
    val navigationItemList = listOf(
        BottomNavItem(name = "Home", route = "Home", icon = Icons.Default.Home),
        BottomNavItem(name = "Profile", route = "Profile", icon = Icons.Default.Person),
        BottomNavItem(name = "Settings", route = "Settings", icon = Icons.Default.Settings)
    )
    NavigationBar() {
        navigationItemList.forEachIndexed { index, bottomNavItem ->
            NavigationBarItem(selected = selectedIndex == index,
                onClick = { selectedIndex = index },
                icon = {
                    Icon(
                        imageVector = bottomNavItem.icon, contentDescription = bottomNavItem.name
                    )
                },
                label = { Text(text = bottomNavItem.name) }

            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun TabLayout() {
    val pagerState = rememberPagerState()
    val tabList = listOf("Status", "Message", "Group", "Call")
    val coroutineScope = rememberCoroutineScope()
    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(selectedTabIndex = pagerState.currentPage,
            modifier = Modifier.fillMaxWidth(),
            containerColor = Color.Blue,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    color = Color.Red,
                    height = 4.dp,
                    modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage])
                )
            }) {
            tabList.forEachIndexed { index, title ->
                Tab(selected = pagerState.currentPage == index,
                    modifier = Modifier.height(70.dp),
                    onClick = {
                        if (pagerState.currentPage != index) {
                            coroutineScope.launch {
                                pagerState.scrollToPage(index)
                            }

                        }
                    },
                    text = {
                        Text(
                            text = title,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp
                        )
                    })
            }
        }
        HorizontalPager(pageCount = 4, state = pagerState) { page ->
            when (page) {
                0 -> {
                    Status()
                }
                1 -> {
                    Message()
                }
                2 -> {
                    Group()
                }
                3 -> {
                    Call()
                }

            }
        }
    }

}

@Composable
fun Status() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan),
    ) {
        Text(
            text = "Status", color = Color.White, textAlign = TextAlign.Center, fontSize = 40.sp
        )
    }
}

@Composable
fun Message() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan),
    ) {
        Text(
            text = "Message", color = Color.White, textAlign = TextAlign.Center, fontSize = 40.sp
        )
    }
}

@Composable
fun Group() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Magenta),
    ) {
        Text(
            text = "Group", color = Color.White, textAlign = TextAlign.Center, fontSize = 40.sp
        )
    }
}

@Composable
fun Call() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow),
    ) {
        Text(
            text = "Call", color = Color.White, textAlign = TextAlign.Center, fontSize = 40.sp
        )
    }

}