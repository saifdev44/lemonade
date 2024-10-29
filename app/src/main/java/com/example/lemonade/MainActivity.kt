package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonadeMakingWithLemonSqueeze()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeMakingWithLemonSqueeze(modifier: Modifier = Modifier) {
    var lemonadeMakingSteps by remember { mutableIntStateOf(1) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color(249,228,76)
                )
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(color = Color.White),
            color = Color.White
        ) {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Button(
                    onClick = {
                        lemonadeMakingSteps = if (lemonadeMakingSteps < 4) lemonadeMakingSteps + 1 else 1
                    },
                    shape = RoundedCornerShape(dimensionResource(R.dimen.padding_vertical)),
                    modifier = modifier,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.Black
                    )
                ) {
                    when (lemonadeMakingSteps) {
                        1 -> LemonadeCenterPiece(
                            R.drawable.lemon_tree,
                            R.string.tap_the_lemon_tree
                        )
                        2 -> LemonadeCenterPiece(
                            R.drawable.lemon_squeeze,
                            R.string.squeeze_lemon
                        )
                        3 -> LemonadeCenterPiece(
                            R.drawable.lemon_drink,
                            R.string.drink_lemonade
                        )
                        4 -> LemonadeCenterPiece(
                            R.drawable.lemon_restart,
                            R.string.tap_empty_glass
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LemonadeCenterPiece(
    imageResource: Int,
    lemonadeMakingText: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(imageResource),
            contentDescription = null,
            modifier = modifier
                .clip(shape = RoundedCornerShape(20.dp))
                .border(width = 2.dp, color = Color(195, 236, 210))
                .background(color = Color(195, 236, 210))
                .width(dimensionResource(R.dimen.button_image_width))
                .height(dimensionResource(R.dimen.button_image_height))
                .padding(dimensionResource(R.dimen.button_interior_padding))
        )
        Spacer(modifier.height(dimensionResource(R.dimen.padding_vertical)))
        Text(text = stringResource(lemonadeMakingText))
    }
}


@Preview(showBackground = true)
@Composable
fun LemonadeMakingApp() {
    LemonadeMakingWithLemonSqueeze()
}
