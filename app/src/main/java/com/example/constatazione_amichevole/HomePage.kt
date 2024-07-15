import android.app.Application.ActivityLifecycleCallbacks
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.room.util.createCancellationSignal
import com.example.constatazione_amichevole.R
import com.example.constatazione_amichevole.Screen

@Composable
fun HomePage(navController: NavController) {

    val texts = listOf("Gestisci Macchine", "Crea Costatazione", "Gestisci Costatazioni", "Supporto")
    val routes = listOf(Screen.GestioneMacchine.route,"Screen.CreaCostatazione.route", "Screen.GestisciCostatazioni.route" , "Screen.Supporto.route")


    val ff = FontFamily(
        Font(R.font.lexend_bold, FontWeight.Bold),
        Font(R.font.lexend_light, FontWeight.Light),
        Font(R.font.lexend_medium, FontWeight.Medium),
        Font(R.font.lexend_regular, FontWeight.Normal),
        Font(R.font.lexend_semibold, FontWeight.SemiBold),
        Font(R.font.lexend_thin, FontWeight.Thin),
        Font(R.font.lexend_extralight, FontWeight.ExtraLight),
        Font(R.font.lexend_extrabold, FontWeight.ExtraBold),
        Font(R.font.lexend_black, FontWeight.Black)
        )


    Column(horizontalAlignment = Alignment.CenterHorizontally
        , verticalArrangement = Arrangement.SpaceAround
        , modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
        ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(1)
        ) {
            items(texts.size) { index ->
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.Blue)
                        .clickable { navController.navigate(route = routes[index]) }
                    ,contentAlignment = Alignment.Center
                ){
                    Text(text = texts[index],
                         color = Color.White,
                         fontSize = 30.sp,
                         fontFamily = ff
                        )
                }

            }
        }
    }

    }









