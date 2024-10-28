package com.example.lemonade

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

@Composable
fun LemonadeMakingWithLemonSqueeze(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        var result by remember { mutableIntStateOf(1) }
        Text(
            text = "Lemonade",
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier
                .background(color = Color(249, 228, 76))
                .fillMaxWidth()
                .padding(vertical = 28.dp)
        )
        Box(
            modifier = modifier
                .clickable {
                    result = if (result < 4) result + 1 else 1
                    Log.d("result = ", "result = $result")
                }
        ) {
            when (result) {
                1 -> LemonadeCenterPiece(
                    R.drawable.lemon_tree,
                    stringResource(R.string.tap_the_lemon_tree)
                )
                2 -> LemonadeCenterPiece(
                    R.drawable.lemon_squeeze,
                    stringResource(R.string.squeeze_lemon)
                )
                3 -> LemonadeCenterPiece(
                    R.drawable.lemon_drink,
                    stringResource(R.string.drink_lemonade)
                )
                4 -> LemonadeCenterPiece(
                    R.drawable.lemon_restart,
                    stringResource(R.string.tap_empty_glass)
                )
            }

        }
    }
}

@Composable
fun LemonadeCenterPiece(
    @DrawableRes imageResource: Int,
    lemonadeMakingText: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(imageResource),
            contentDescription = "lemon tree",
            modifier = modifier
                .clip(shape = RoundedCornerShape(20.dp))
                .border(width = 2.dp, color = Color(195, 236, 210))
                .background(color = Color(195, 236, 210))
        )
        Spacer(modifier = modifier.height(16.dp))
        Text(text = lemonadeMakingText)
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeMakingApp() {
    LemonadeMakingWithLemonSqueeze()
}
