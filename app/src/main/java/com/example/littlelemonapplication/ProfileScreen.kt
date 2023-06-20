package com.example.littlelemonapplication

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.littlelemonapplication.ui.theme.DarkGray
import com.example.littlelemonapplication.ui.theme.yellow

@Composable
fun ProfileScreen(navController: NavController){
    val sharedPreferences = LocalContext.current.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)


    val firstName = sharedPreferences.getString("firstName","")?:""

    val lastName = sharedPreferences.getString("lastName","")?:""

    val emailAddress = sharedPreferences.getString("emailAddress","")?:""


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color.White)) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription ="logo",
                modifier = Modifier.padding(horizontal = 80.dp, vertical = 20.dp)
            )

            Text(
                text = "Personal Information",
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 40.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )


            //Text Inputs Column
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(
                    text = "First Name",
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = Color.Black
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .border(
                            width = 0.4.dp,
                            color = Color.Gray,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .height(45.dp),
                    contentAlignment = Alignment.CenterStart
                ){
                    Text(
                        text = firstName,
                        modifier = Modifier.padding(10.dp),
                        color = DarkGray
                    )
                }


                Text(
                    text = "Last Name",
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = Color.Black
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .border(
                            width = 0.4.dp,
                            color = Color.Gray,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .height(45.dp),
                    contentAlignment = Alignment.CenterStart

                ){
                    Text(
                        text = lastName,
                        modifier = Modifier.padding(10.dp),
                        color = DarkGray
                    )
                }


                Text(
                    text = "Email Address",
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = Color.Black
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .border(
                            width = 0.4.dp,
                            color = Color.Gray,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .height(45.dp),
                    contentAlignment = Alignment.CenterStart

                ){
                    Text(
                        text = emailAddress,
                        modifier = Modifier.padding(10.dp),
                        color = DarkGray
                    )
                }
            }

            //Button Container Box

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 10.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Button(
                    onClick = {
                        val editor = sharedPreferences.edit()
                        editor.remove("isRegistered")
                        editor.remove("firstName")
                        editor.remove("lastName")
                        editor.remove("emailAddress")
                        editor.apply()
                        navController.navigate(OnBoard.route)
                    },
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = yellow,
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text = "LOG OUT", color = Color.Black)
                }
            }
        }
    }
}