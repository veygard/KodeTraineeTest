package com.example.kodetraineetest.presentation.compose.screens.detail

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.kodetraineetest.R
import com.example.kodetraineetest.presentation.common.model.UserParcelize
import com.example.kodetraineetest.presentation.common.model.toUser
import com.example.kodetraineetest.presentation.compose.screens.destinations.MainScreenDestination
import com.example.kodetraineetest.presentation.compose.ui.theme.caption1Regular
import com.example.kodetraineetest.presentation.compose.ui.theme.headlineMedium
import com.example.kodetraineetest.presentation.compose.ui.theme.title1Bold
import com.example.kodetraineetest.presentation.compose.ui.theme.title3regular
import com.example.kodetraineetest.util.Margin
import com.example.kodetraineetest.util.SpacingHorizontal
import com.example.kodetraineetest.util.SpacingVertical
import com.example.kodetraineetest.util.extention.formatPhone
import com.example.kodetraineetest.util.extention.formatPhoneForDial
import com.example.kodetraineetest.util.extention.toFullString
import com.example.kodetraineetest.util.extention.toLocalDate
import com.example.kodetraineetest.util.makeCall
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.time.LocalDate
import java.time.Period

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Destination
@Composable
fun DetailScreen(
    navigator: DestinationsNavigator,
    userParcelize: UserParcelize
) {
    val user = userParcelize.toUser()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.primaryVariant),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SpacingVertical(24)
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                    SpacingHorizontal(24)
                    Icon(
                        painter = painterResource(id = R.drawable.ic_vector),
                        contentDescription = "back",
                        tint = MaterialTheme.colors.onBackground,
                        modifier = Modifier.clickable {
                            navigator.navigate(MainScreenDestination)
                        }
                    )
                }

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(user.avatarUrl)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.ic_goose),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(72.dp),
                    error = painterResource(R.drawable.ic_goose),
                )
                SpacingVertical(24)
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    /*фио+tag*/
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${user.firstName} ${user.lastName}",
                            style = title1Bold,
                            color = MaterialTheme.colors.onBackground,
                        )
                        SpacingHorizontal(WidthDp = 4)
                        Text(
                            text = user.userTag?.lowercase() ?: "",
                            style = title3regular,
                            color = MaterialTheme.colors.secondaryVariant,
                        )
                    }
                    SpacingVertical(12)
                    Text(
                        text = user.position ?: "",
                        style = caption1Regular,
                        color = MaterialTheme.colors.secondary,
                    )
                }
                SpacingVertical(8)
            }
        },
        backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(start = Margin.horizontalStandard, end = Margin.horizontalStandard),
        ) {
            SpacingVertical(24)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row() {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_star),
                        contentDescription = "star",
                        tint = MaterialTheme.colors.onBackground
                    )
                    SpacingHorizontal(14)
                    Text(
                        text = user.birthday?.toLocalDate()?.toFullString() ?: "",
                        style = headlineMedium,
                        color = MaterialTheme.colors.onBackground
                    )
                }
                SpacingVertical(24)
                Text(
                    text = ageDescription(user.birthday?.toLocalDate(), context),
                    style = headlineMedium,
                    color = MaterialTheme.colors.secondaryVariant
                )
            }
            SpacingVertical(heightDp = 48)

            Row(
                modifier = Modifier.clickable {
                    user.phone?.let { makeCall(context, it.formatPhoneForDial()) }
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_phone),
                    contentDescription = "star",
                    tint = MaterialTheme.colors.onBackground
                )
                SpacingHorizontal(14)
                Text(
                    text = user.phone?.formatPhone() ?: "",
                    style = headlineMedium,
                    color = MaterialTheme.colors.onBackground
                )
            }
        }
    }
}

private fun ageDescription(date: LocalDate?, context: Context): String {
    val age = date.let { Period.between(it, LocalDate.now()).years }

    return when {
        age % 100 in 11..14 -> "$age ${context.getString(R.string.years1)}"
        age % 10 == 1 -> "$age ${context.getString(R.string.years2)}"
        age % 10 in 2..4 -> "$age ${context.getString(R.string.years2)}"
        else -> "$age ${context.getString(R.string.years1)}"
    }
}