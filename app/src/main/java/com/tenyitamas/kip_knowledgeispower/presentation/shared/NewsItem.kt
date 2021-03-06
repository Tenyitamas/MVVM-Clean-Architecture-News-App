@file:OptIn(ExperimentalCoilApi::class)

package com.tenyitamas.kip_knowledgeispower.presentation.shared

import android.os.Build
import android.text.Html
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.request.ImageRequest

import com.tenyitamas.kip_knowledgeispower.R
import com.tenyitamas.kip_knowledgeispower.domain.model.Article
import com.tenyitamas.kip_knowledgeispower.ui.theme.LocalSpacing

@ExperimentalCoilApi
@Composable
fun NewsItem(
    article: Article?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(15.dp))
            .padding(4.dp)
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(15.dp)
            )
            .background(MaterialTheme.colors.surface)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        Spacer(modifier = Modifier.height(4.dp))

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(article?.urlToImage)
                .crossfade(1000)
                .error(R.drawable.ic_launcher)
                .fallback(R.drawable.ic_launcher)
                .build(),
            contentDescription = article?.title,
            placeholder = painterResource(id = R.drawable.ic_baseline_downloading_24),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
        )





        Spacer(modifier = Modifier.height(4.dp))

        article?.title?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onSurface,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = LocalSpacing.current.spaceSmall)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))



        article?.description?.let {
            val descriptionDisplayText = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml("<div>$it</div>", Html.FROM_HTML_MODE_COMPACT).toString()
            } else {
                it
            }
            Text(
                text = descriptionDisplayText,
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.primary.copy(alpha = 0.8f),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = LocalSpacing.current.spaceSmall)
            )
        }

        Spacer(modifier = Modifier.height(LocalSpacing.current.spaceSmall))
    }

}

