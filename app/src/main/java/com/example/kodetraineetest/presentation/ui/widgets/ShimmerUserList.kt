package com.example.kodetraineetest.presentation.ui.widgets

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
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
import com.example.kodetraineetest.presentation.ui.theme.Shapes
import com.example.kodetraineetest.presentation.ui.theme.caption1Regular
import com.example.kodetraineetest.presentation.ui.theme.headlineMedium
import com.example.kodetraineetest.presentation.ui.theme.subheadMedium
import com.example.kodetraineetest.util.SpacingHorizontal
import com.example.kodetraineetest.util.SpacingVertical
import com.skydoves.landscapist.glide.GlideImage
import com.valentinilk.shimmer.*

private val shimmerUserListTheme = defaultShimmerTheme.copy(
    animationSpec = infiniteRepeatable(
        animation = tween(
            durationMillis = 1400,
            delayMillis = 300,
            easing = LinearEasing,
        ),
    ),
    blendMode = BlendMode.Hardlight,
    rotation = 25f,
    shaderColors = listOf(
        Color.White.copy(alpha = 0.5f),
        Color.White.copy(alpha = 0.2f),
        Color.White.copy(alpha = 0.5f),
    ),
    shaderColorStops = null,
    shimmerWidth = 400.dp,
)

@Composable
fun ShimmerUserList(
    size: Int = 10
) {
    CompositionLocalProvider(
        LocalShimmerTheme provides shimmerUserListTheme
    ) {
        SpacingVertical(heightDp = 24)
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        ) {
            repeat((0..size).count()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shimmer()
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .size(72.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colors.secondary)
                        ) {}

                        SpacingHorizontal(16)

                        Column(verticalArrangement = Arrangement.Center) {
                            Box(
                                modifier = Modifier
                                    .size(width = 144.dp, height = 16.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(MaterialTheme.colors.secondary)
                            ) {}
                            SpacingVertical(6)
                            Box(
                                modifier = Modifier
                                    .size(width = 80.dp, height = 12.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(MaterialTheme.colors.secondary)
                            ) {}
                        }
                    }
                }
                SpacingVertical(24)
            }
        }
    }
}


