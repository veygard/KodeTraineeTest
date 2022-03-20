package com.example.kodetraineetest.presentation.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.kodetraineetest.R
import com.example.kodetraineetest.domain.model.User
import com.example.kodetraineetest.presentation.ui.theme.caption1Regular
import com.example.kodetraineetest.presentation.ui.theme.headlineMedium
import com.example.kodetraineetest.presentation.ui.theme.subheadMedium
import com.example.kodetraineetest.util.SpacingHorizontal
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun UserItemInList(
    user: User,
    userClick: (id: String?) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                userClick(user.id)
            },
        ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
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


//            GlideImage(
//                imageModel = user.avatarUrl,
//                // Crop, Fit, Inside, FillHeight, FillWidth, None
//                contentScale = ContentScale.Crop,
//                // shows an image with a circular revealed animation.
//                // shows a placeholder ImageBitmap when loading.
//                placeHolder = ImageBitmap.imageResource(R.drawable.ic_goose),
//                // shows an error ImageBitmap when the request failed.
//                error = ImageBitmap.imageResource(R.drawable.ic_goose),
//                modifier = Modifier.size(72.dp)
//            )

            SpacingHorizontal(16)

            Column(verticalArrangement = Arrangement.Center) {
                /*фио+tag*/
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${user.firstName} ${user.lastName}",
                        style = headlineMedium,
                        color = MaterialTheme.colors.onBackground
                    )
                    SpacingHorizontal(WidthDp = 4)
                    Text(
                        text = user.userTag?.lowercase() ?: "",
                        style = subheadMedium,
                        color = MaterialTheme.colors.secondaryVariant
                    )
                }
                Text(
                    text = user.position ?: "",
                    style = caption1Regular,
                    color = MaterialTheme.colors.secondary
                )
            }
        }
    }
}
