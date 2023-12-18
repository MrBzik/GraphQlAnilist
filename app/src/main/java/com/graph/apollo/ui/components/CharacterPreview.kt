package com.graph.apollo.ui.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.graph.apollo.R
import com.graph.apollo.domain.models.AnimeCharacterPageItem
import com.graph.apollo.utils.Logger
import com.skydoves.orbital.Orbital
import com.skydoves.orbital.OrbitalScope
import com.skydoves.orbital.animateBounds
import com.skydoves.orbital.animateSharedElementTransition
import com.skydoves.orbital.rememberContentWithOrbitalScope
import com.skydoves.orbital.rememberMovableContentOf

@Composable
fun OrbitalScope.CharacterPreview(
    character: AnimeCharacterPageItem,
    shareImage : (sharedContent: @Composable () -> Unit) -> Unit,
//    onItemClicked : (Boolean) -> Unit
){

    var isTransformed by rememberSaveable { mutableStateOf(false) }

    val sizeAnim = spring<IntSize>(stiffness = Spring.StiffnessLow)
    val positionAnim = spring<IntOffset>(stiffness = Spring.StiffnessLow)

    val sharedImage = rememberMovableContentOf {

        AsyncImage(
            modifier =
            Modifier
                .animateBounds(
                    modifier = if(isTransformed){
                        Modifier
                            .fillMaxWidth()
                            .height(345.dp)
                    } else
                        Modifier
                            .width(100.dp)
                            .height(150.dp),
                    sizeAnimationSpec = sizeAnim,
                    positionAnimationSpec = positionAnim
                ),
//            if (isTransformed) {
//                Modifier
//                    .fillMaxWidth()
//                    .height(345.dp)
//            } else {
//                Modifier
//                    .width(100.dp)
//                    .height(150.dp)
//            }.animateSharedElementTransition(
//                orbitalScope = this,
//                movementSpec = SpringSpec(stiffness = 500f),
//                transformSpec = SpringSpec(stiffness = 500f)
//            ),
            model = ImageRequest.Builder(LocalContext.current)
                .data(character.imageUrl)
                .crossfade(400)
                .build(),
            contentDescription = "",
        )
    }


        Card(modifier =  Modifier.fillMaxWidth()
//            .height(150.dp)

            ,
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp,
                pressedElevation = 8.dp,
                focusedElevation = 8.dp,
                hoveredElevation = 8.dp
            ),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {


            Box(modifier = Modifier
                .fillMaxSize()
                .clickable {
                    isTransformed = !isTransformed

                }, contentAlignment = Alignment.BottomEnd){

                Row(modifier = Modifier.fillMaxWidth()) {

                    if(isTransformed)
                        shareImage {
                            sharedImage()
                        }
                    else
                        sharedImage()


                    Spacer(modifier = Modifier.width(16.dp))

                    Column {
                        Text(text = character.name, color = Color.DarkGray, fontSize = 25.sp)

                        Spacer(modifier = Modifier.height(20.dp))

                        Strings.AnnotatedString(resId = R.string.character_age, value = character.age)

                        Strings.AnnotatedString(resId = R.string.character_gender, value = character.gender)

                    }
                }

                Row (modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp, bottom = 8.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End) {

                    Text(text = character.favourites.toString())

                    Spacer(modifier = Modifier.width(4.dp))

                    Icon(painter = rememberVectorPainter(image = Icons.Filled.Favorite), contentDescription = null, tint = Color.Red)

                }
            }
        }



}


//@Composable
//@Preview
//fun PreviewCharacter(){
//    CharacterPreview(character = AnimeCharacterPageItem(
//        id = 13,
//        age = "26",
//        gender = "Female",
//        imageUrl = "",
//        name = "Rem",
//        favourites = 3841
//    )){}
//}