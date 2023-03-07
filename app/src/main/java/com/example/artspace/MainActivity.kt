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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.presentation.ArtSpaceViewModel
import com.example.artspace.ui.theme.ArtSpaceTheme
import com.example.artspace.ui.theme.firaSans

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
            .padding(30.dp)
    ) {
        Column(
            modifier = modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ArtworkImage(viewModel)
            ArtworkDescription(viewModel)
        }
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            ArtworkController(viewModel)
        }
    }
}

@Preview
@Composable
fun ArtworkPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Surface(
            elevation = 10.dp,
            border = BorderStroke(2.dp, Color.Black),
            modifier = Modifier.padding(30.dp)
        ) {
            Image(
                modifier = Modifier.padding(30.dp),
                painter = painterResource(id = R.drawable.vincent_van_gogh_self_portrait),
                contentDescription = null
            )
        }

        Surface(
            elevation = 5.dp,
        ) {
            Column {
                Text(
                    modifier = Modifier.padding(10.dp),
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = firaSans,
                        fontWeight = FontWeight.Medium
                    ),
                    text = "Self-portrait"
                )
                Text(
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = firaSans,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Italic
                    ),
                    text = "Vincent van Gogh, 1889"
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
        ) {
            Button(
                onClick = { },
                modifier = Modifier.width(120.dp)
            ) {
                Text(
                    text = "Previous"
                )
            }
            Button(
                onClick = { },
                modifier = Modifier.width(120.dp)
            ) {
                Text(
                    text = "Next"
                )
            }
        }
    }
}

@Composable
fun ArtworkImage(
    viewModel: ArtSpaceViewModel,
    modifier: Modifier = Modifier
) {
    Surface(
        elevation = 10.dp,
        border = BorderStroke(2.dp, Color.Black),
        modifier = modifier.padding(30.dp)
    ) {
        Image(
            modifier = modifier.padding(30.dp),
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
        elevation = 5.dp,
    ) {
        Column {
            Text(
                modifier = modifier.padding(10.dp),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = firaSans,
                    fontWeight = FontWeight.Medium
                ),
                text = viewModel.currentArtwork.title
            )
            Text(
                modifier = modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = firaSans,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Italic
                ),
                text = "${viewModel.currentArtwork.artist}, ${viewModel.currentArtwork.year}"
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
            modifier = modifier.width(120.dp)
        ) {
            Text(
                text = "Previous"
            )
        }
        Button(
            onClick = { viewModel.goToNextArtwork() },
            modifier = modifier.width(120.dp)
        ) {
            Text(
                text = "Next"
            )
        }
    }
}