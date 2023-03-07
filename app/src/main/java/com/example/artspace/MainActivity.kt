package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
            ArtworkImage(viewModel)
            ArtworkDescription(viewModel)
        }
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(ControllerBlockHeight)
        ) {
            ArtworkController(viewModel)
        }
    }
}

@Composable
fun ArtworkImage(
    viewModel: ArtSpaceViewModel,
    modifier: Modifier = Modifier
) {
    Surface(
        elevation = ArtworkImageElevation,
        border = BorderStroke(ArtworkBorderStroke, Color.Black),
        modifier = modifier.padding(ArtworkPadding)
    ) {
        Image(
            modifier = modifier.padding(ArtworkPadding),
            painter = painterResource(id = viewModel.currentArtwork.imageId),
            contentDescription = null
        )
    }
}

@Composable
fun ArtworkDescription(
    viewModel: ArtSpaceViewModel,
    modifier: Modifier = Modifier
) {
    Surface(
        elevation = ArtworkDescriptionElevation,
    ) {
        Column {
            Text(
                modifier = modifier.padding(DescriptionTextPadding),
                style = ArtworkTitleTextStyle,
                text = viewModel.currentArtwork.title
            )
            Text(
                modifier = modifier.padding(
                    start = DescriptionTextPadding,
                    end = DescriptionTextPadding,
                    bottom = DescriptionTextPadding
                ),
                style = ArtworkDescriptionTextStyle,
                text = stringResource(id = R.string.artworkDescription, viewModel.currentArtwork.artist, viewModel.currentArtwork.year)
            )
        }
    }
}

@Composable
fun ArtworkController(
    viewModel: ArtSpaceViewModel,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom
    ) {
        Button(
            onClick = { viewModel.goToPreviousArtwork() },
            modifier = modifier.width(ControllerButtonWidth)
        ) {
            Text(
                text = PreviousButtonLabel
            )
        }
        Button(
            onClick = { viewModel.goToNextArtwork() },
            modifier = modifier.width(ControllerButtonWidth)
        ) {
            Text(
                text = NextButtonLabel
            )
        }
    }
}