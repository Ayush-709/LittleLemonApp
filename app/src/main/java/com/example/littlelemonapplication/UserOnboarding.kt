package com.example.littlelemonapplication

import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.littlelemonapplication.ui.theme.green
import com.example.littlelemonapplication.ui.theme.yellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserOnBoarding(navController: NavHostController, sharedPreferences: SharedPreferences){

    var firstName by remember {
        mutableStateOf("")
    }
    var lastName by remember {
        mutableStateOf("")
    }
    var emailAddress by remember {
        mutableStateOf("")
    }

    var registrationMessage by remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription ="logo",
            modifier = Modifier.padding(horizontal = 80.dp, vertical = 20.dp).fillMaxWidth(0.8f).align(CenterHorizontally)
        )
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(green)
            .padding(vertical = 15.dp)
        ){
            Text(
                text = "Let's get to know you",
                modifier = Modifier.align(Alignment.Center),
                color = Color.White,
                fontSize = 19.sp
            )
        }
        Text(
            text = "Personal Information",
            modifier = Modifier.padding(top = 30.dp, bottom = 10.dp, start = 15.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold
        )


        //Text Inputs Column
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = "First Name",
                color = Color.Gray,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            TextField(value =firstName ,
                onValueChange = {
                    firstName=it
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Text(
                text = "Last Name",
                color = Color.Gray,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            TextField(value =lastName ,
                onValueChange = {
                    lastName=it
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Text(
                text = "Email Address",
                color = Color.Gray,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            TextField(value =emailAddress ,
                onValueChange = {
                    emailAddress=it
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
        }

        //Button Container Box
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 15.dp, horizontal = 20.dp),
            contentAlignment = Alignment.BottomCenter
        ){
            Column(Modifier.fillMaxSize()) {

                Text(
                    text = registrationMessage,
                    modifier = Modifier.padding(vertical = 10.dp, horizontal = 5.dp),
                    color = if (registrationMessage.contains("unsuccessful")) Color.Red else Color.Green
                )
                Button(
                    onClick = {
                        if (firstName.isBlank() || lastName.isBlank() || emailAddress.isBlank()) {
                            registrationMessage = "Registration unsuccessful."
                        } else {
                            val editor = sharedPreferences.edit()
                            editor.putBoolean("isRegistered", true)
                            // Save the registration data (firstName, lastName, emailAddress) here using editor.putString()
                            editor.putString("firstName", firstName)
                            editor.putString("lastName", lastName)
                            editor.putString("emailAddress", emailAddress)
                            editor.apply()

                            registrationMessage = "Registration successful!"
                            navController.navigate(Home.route)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = yellow),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        text = "REGISTER",
                        color = Color.Black,
                        modifier = Modifier.padding(vertical = 5.dp)
                    )
                }

            }
        }
    }
}

