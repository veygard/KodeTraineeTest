package com.example.kodetraineetest.presentation.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
import com.example.kodetraineetest.domain.model.User
import com.example.kodetraineetest.presentation.ui.theme.*
import com.example.kodetraineetest.util.SpacingHorizontal
import com.example.kodetraineetest.util.extention.toDayMonthString
import com.example.kodetraineetest.util.extention.toLocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun UserItemInList(
    user: User,
    showBornDate: Boolean = false,
    userClick: (user:User) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                userClick(user)
            },
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
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

                SpacingHorizontal(16)

                Column(verticalArrangement = Arrangement.Center) {
                    /*фио+tag*/
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${user.firstName} ${user.lastName}",
                            style = headlineMedium,
                            color = MaterialTheme.colors.onBackground,
                        )
                        SpacingHorizontal(WidthDp = 4)
                        Text(
                            text = user.userTag?.lowercase() ?: "",
                            style = subheadMedium,
                            color = MaterialTheme.colors.secondaryVariant,
                        )
                    }
                    Text(
                        text = user.position ?: "",
                        style = caption1Regular,
                        color = MaterialTheme.colors.secondary,
                    )
                }
            }
            if(showBornDate){             val date = user.birthday?.toLocalDate()

                date?.let { d ->
                    val str = d.toDayMonthString()
                    Text(
                        text = str ?: "",
                        style = textRegular,
                        color = MaterialTheme.colors.secondary
                    )
                }

            }

        }
    }
}
