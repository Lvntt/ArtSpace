package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.artspace.presentation.ArtSpaceViewModel
import com.example.artspace.ui.theme.*

class MainActivity : ComponentActivity() {
    private val artSpaceViewModel by viewModels<ArtSpaceViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                ArtSpace(artSpaceViewModel)
            }
        }
    }
}

@Composable
fun ArtSpace(
    viewModel: ArtSpaceViewModel,
    modifier: Modifier = Modifier
) {
    Artwork(viewModel)
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Artwork(
    viewModel: ArtSpaceViewModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(ArtworkPadding)
    ) {
        Column(
            modifier = modifier.weight(ContentWeight),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AnimatedContent(targetState = viewModel.currentArtwork.imageId) { targetState ->
                ArtworkImage(targetState)
            }
            ArtworkDescription(
                viewModel.currentArtwork.title,
                viewModel.currentArtwork.artist,
                viewModel.currentArtwork.year.toString()
            )
        }
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(ControllerBlockHeight)
        ) {
            ArtworkController(
                { viewModel.goToPreviousArtwork() },
                { viewModel.goToNextArtwork() }
            )
        }
    }
}

@Composable
fun ArtworkImage(
    @DrawableRes imageId: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        elevation = ArtworkImageElevation,
        border = BorderStroke(ArtworkBorderStroke, Color.Black),
        modifier = modifier.padding(ArtworkPadding)
    ) {
        Image(
            modifier = modifier.padding(ArtworkPadding),
            painter = painterResource(id = imageId),
            contentDescription = null
        )
    }
}

@Composable
fun ArtworkDescription(
    title: String,
    artist: String,
    year: String,
    modifier: Modifier = Modifier
) {
    Surface(
        elevation = ArtworkDescriptionElevation,
    ) {
        Column {
            Text(
                modifier = modifier.padding(DescriptionTextPadding),
                style = ArtworkTitleTextStyle,
                text = title
            )
            Text(
                modifier = modifier.padding(
                    start = DescriptionTextPadding,
                    end = DescriptionTextPadding,
                    bottom = DescriptionTextPadding
                ),
                style = ArtworkDescriptionTextStyle,
                text = stringResource(id = R.string.artworkDescription, artist, year)
            )
        }
    }
}

@Composable
fun ArtworkController(
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom
    ) {
        Button(
            onClick = { onPreviousClick() },
            modifier = modifier.width(ControllerButtonWidth)
        ) {
            Text(
                text = PreviousButtonLabel
            )
        }
        Button(
            onClick = { onNextClick() },
            modifier = modifier.width(ControllerButtonWidth)
        ) {
            Text(
                text = NextButtonLabel
            )
        }
    }
}