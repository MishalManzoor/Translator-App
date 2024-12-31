package com.example.translatorapp.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.translatorapp.language.LanguageUtil
import com.example.translatorapp.R
import com.example.translatorapp.ui.theme.AndroidColor

@Composable
fun HomeScreen() {
    val paddingTop = WindowInsets.statusBars.asPaddingValues()
    val paddingBottom = WindowInsets.navigationBars.asPaddingValues()
    Box(
        Modifier
            .fillMaxSize()
            .padding(
                top = paddingTop.calculateTopPadding(),
                bottom = paddingBottom.calculateBottomPadding()
            )
            .background(AndroidColor)
    ) {
        TransLateToUrdu()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransLateToUrdu() {
    var inputText by remember { mutableStateOf("") }
    var isProgress by remember { mutableStateOf(false) }
    var outputText by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .padding(
                        start = 10.dp,
                        end = 10.dp,
                        top = 10.dp
                    )
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Text(
                        "ENGLISH",
                        fontSize = 14.sp,
                        color = AndroidColor,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(
                            start = 15.dp,
                            top = 10.dp
                        )
                    )

                    Spacer(Modifier.padding(10.dp))

                    OutlinedTextField(
                        value = inputText,
                        onValueChange = {
                            inputText = it
                        },
                        placeholder = {
                            Text(
                                "Tap to Enter Text",
                                fontSize = 16.sp,
                                color = Color.Gray
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedTextColor = Color.Black,
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            cursorColor = Color.Black
                        ),
                        textStyle = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    )
                }

                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .padding(end = 10.dp, bottom = 10.dp)
                        .background(
                            color = AndroidColor,
                            shape = CircleShape
                        )
                        .align(Alignment.BottomEnd)
                        .clickable {
                            isProgress = true
                            LanguageUtil.preDownloadModelForUrdu(inputText) {
                                isProgress = false
                                outputText = this
                            }
                        }
                ) {
                    Image(
                        painter = painterResource(R.drawable.baseline_send_242),
                        contentDescription = "",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            Spacer(Modifier.padding(10.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .padding(
                        start = 10.dp,
                        end = 10.dp,
                        bottom = 10.dp
                    )
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Text(
                        "URDU",
                        fontSize = 14.sp,
                        color = AndroidColor,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(
                            start = 15.dp,
                            top = 10.dp
                        )
                    )

                    Spacer(Modifier.padding(10.dp))

                    if (isProgress){
                        CircularProgressIndicator(
                            color = AndroidColor
                        )
                    }

                    AnimatedVisibility(
                        visible = outputText.isNotEmpty(),
                    ) {
                        Text(
                            text = outputText,
                            fontSize = 16.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        )
                    }
                }
            }
        }
    }
}