package com.example.littlelemonapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.littlelemonapplication.ui.theme.DarkGray
import com.example.littlelemonapplication.ui.theme.cloud
import com.example.littlelemonapplication.ui.theme.green
import com.example.littlelemonapplication.ui.theme.styleTypography
import com.example.littlelemonapplication.ui.theme.yellow
import java.util.Locale

@Composable
fun HomeScreen(navController: NavController, menuItemDao: MenuItemDao){

    var searchPhrase by remember {
        mutableStateOf("")
    }
    var selectedCategory by remember {
        mutableStateOf("")
    }

    val menuCategoryList by menuItemDao.getAllCategories().observeAsState(emptyList())
    val databaseMenuItems by menuItemDao.getAll().observeAsState(emptyList())
    val imageMap: Map<String, Int> = mapOf(
        "Greek Salad" to R.drawable.greek_salad,
        "Lemon Desert" to R.drawable.lemon_dessert,
        "Grilled Fish" to R.drawable.grilled_fish,
        "Pasta" to R.drawable.pasta,
        "Bruschetta" to R.drawable.bruschetta
    )

    val filteredMenuItems = databaseMenuItems.filter { menuItem ->
        (selectedCategory.isEmpty() || menuItem.category == selectedCategory) &&
                (searchPhrase.isEmpty() || menuItem.title.startsWith(searchPhrase, ignoreCase = true))
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Little Lemon Logo",
                    modifier = Modifier
                        .fillMaxWidth(0.6F)
                        .padding(horizontal = 20.dp)
                )
                androidx.compose.material.IconButton(
                    onClick = { navController.navigate(Profile.route) },
                    modifier = Modifier
                        .height(50.dp)
                        .width(50.dp)
                        .clip(CircleShape)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = ""
                    )
                }
            }

            //Upper Panel
            Column(
                modifier = Modifier
                    .background(green)
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.title),
                    fontSize = 45.sp,
                    color = yellow,
                    fontFamily = FontFamily(fonts = listOf(Font(R.font.markzaki))),
                    lineHeight = 51.sp,
                    textAlign = TextAlign.Center
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.padding(bottom = 10.dp)
                ) {
                    Column {
                        Text(
                            text = stringResource(id = R.string.location),
                            fontSize = 30.sp,
                            color = cloud,
                            modifier = Modifier.padding(bottom = 10.dp),
                            fontFamily = FontFamily(fonts = listOf(Font(R.font.markzaki))),
                        )
                        Text(
                            text = stringResource(id = R.string.description),
                            color = cloud,
                            modifier = Modifier
                                .padding(end = 30.dp)
                                .fillMaxWidth(0.5f),
                            style = styleTypography.body1,
                            fontFamily = FontFamily(fonts = listOf(Font(R.font.markzaki))),
                            fontSize = 18.sp,
                        )
                    }
                    Image(
                        painter = painterResource(id = R.drawable.hero_image),
                        contentDescription = "Upper Panel Image",
                        modifier = Modifier
                            .clip(RoundedCornerShape(15.dp))
                            .size(150.dp)
                    )
                }
                OutlinedTextField(
                    value = searchPhrase,
                    onValueChange = { searchPhrase = it },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = green,
                        cursorColor = green,
                        focusedBorderColor = green,
                        unfocusedBorderColor = green,
                        backgroundColor = cloud
                    ),
                    textStyle = TextStyle(
                        color = green,
                        fontSize = 20.sp,
                        fontFamily = FontFamily(fonts = listOf(Font(R.font.markzaki)))
                    ),
                    leadingIcon = ({
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "",
                            tint = DarkGray
                        )
                    }),
                    singleLine = true,
                    placeholder = ( {
                        Text(
                            text = "Enter Search Phrase",
                            color = DarkGray,
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 20.sp,
                                fontFamily = FontFamily(fonts = listOf(Font(R.font.markzaki)))
                            )
                        )
                    })
                )
            }

            //Category and Items
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(10.dp)) {
                Text(
                    text = "ORDER FOR DELIVERY!",
                    fontFamily = FontFamily(fonts = listOf(Font(R.font.markzaki))),
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold
                )
                LazyRow(state = rememberLazyListState()){
                    items(menuCategoryList) { category ->
                        LazyCategories(
                            category = category,
                            isSelected = selectedCategory == category,
                            onClick = {
                                selectedCategory = if (selectedCategory == category) "" else category
                            }
                        )
                    }
                }
                Divider(
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 10.dp),
                    thickness = 1.dp,
                )
                LazyColumn(state = rememberLazyListState()) {
                    items(filteredMenuItems) { menuItems ->
                        MenuItemsList(menuItems, imageMap)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun MenuItemsList(dish: MenuItemRoom, imagePath:Map<String,Int>) {
    val imageUrl = imagePath[dish.title]
    Card(onClick = { }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Column (modifier = Modifier.padding(end = 5.dp, bottom = 5.dp)){
                Text(
                    text = dish.title,
                    style = styleTypography.h2,
                    fontWeight = FontWeight.Bold
                )
                Row(verticalAlignment = CenterVertically){
                    Column {
                        Text(
                            text = dish.description,
                            style = styleTypography.body1,
                            modifier = Modifier
                                .padding(top = 5.dp, bottom = 5.dp)
                                .fillMaxWidth(.70f),
                            letterSpacing = 0.1.sp
                        )
                        Text(
                            text = "$${dish.price}",
                            style = styleTypography.body2,
                            letterSpacing = 1.sp
                        )
                    }
                    imageUrl?.let { painterResource(id = it) }?.let {
                        Image(
                            painter = it,
                            contentDescription ="",
                            modifier = Modifier.clip(RoundedCornerShape(20.dp)),
                            alignment = CenterEnd
                        )
                    }
                }

            }
        }
    }
    Divider(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
        thickness = 1.dp,
    )
}

@Composable
fun LazyCategories(category:String, isSelected: Boolean, onClick: (String) -> Unit){
    Box(modifier = Modifier.padding(horizontal = 5.dp)){
        Button(
            onClick = { onClick(category) },
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.textButtonColors(
                containerColor = if (isSelected) green else cloud,
                contentColor = green
            )
        ) {
            Text(
                text = category.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
                color = if (isSelected) cloud else green,
                fontFamily = FontFamily(fonts = listOf(Font(R.font.markzaki))),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}