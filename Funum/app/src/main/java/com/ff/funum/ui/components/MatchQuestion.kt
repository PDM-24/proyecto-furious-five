package com.ff.funum.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ff.funum.R
import com.ff.funum.model.Respuesta_match

@Composable
fun MatchQuestion(
    getMatchColumns: () -> Pair<List<Respuesta_match>, List<Respuesta_match>>,
    resolved: Boolean,
    onResolve: ( MutableList<Boolean> ) -> Unit,
    onNext: () -> Unit
) {
    val (leftItems, rightItems) = getMatchColumns()
    Text(
        text = "Une los items que correspondan entre si",
        style = MaterialTheme.typography.headlineSmall,
        textAlign = TextAlign.Center,
        color = Color.White
    )
    ItemsDisplay(leftItems, rightItems, resolved, onResolve = onResolve, onNext = onNext)
}

@Composable
fun ItemsDisplay(
    leftItems: List<Respuesta_match>,
    rightItems: List<Respuesta_match>,
    resolved: Boolean,
    onResolve: ( MutableList<Boolean> ) -> Unit,
    onNext: () -> Unit
) {
    val itemLeft = remember {
        mutableStateOf(0)
    }
    val itemRight = remember {
        mutableStateOf(0)
    }
    val connections = remember {
        mutableMapOf(1 to 0, 2 to 0, 3 to 0, 4 to 0)
    }
    val correctValues = remember {
        mutableListOf(false, false, false, false)
    }

    Row (
        modifier = Modifier.height(IntrinsicSize.Min),
        verticalAlignment = Alignment.CenterVertically
    ){
        Column(
            modifier = Modifier.width(IntrinsicSize.Max).weight(2f),
            verticalArrangement = Arrangement.spacedBy(5.dp),
        ){
            leftItems.forEachIndexed { index, s ->
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        itemLeft.value = index + 1
                        if(itemRight.value != 0){
                            connections.replaceAll { t, u -> if(u == itemRight.value) 0 else u}
                            connections[itemLeft.value] = itemRight.value

                            if(connections[itemLeft.value] != 0 && (s.tipo == rightItems[connections[index + 1]!! -1].tipo)){
                                correctValues[connections[index + 1]!! -1] = true
                            }else{
                                correctValues[connections[index + 1]!! -1] = false
                            }

                            itemLeft.value = 0
                            itemRight.value = 0
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor =
                        if(index == (itemLeft.value - 1) && !resolved){
                            Color(0xFF4FC3F7)
                        }else if(connections[index + 1] != 0 && (s.tipo == rightItems[connections[index + 1]!! -1].tipo && resolved)){
                            Color(0xFF4CAF50)
                        }
                        else if(resolved){
                            Color(0xFFF44336)
                        }
                        else{
                            colorResource(id = R.color.secondaryThird)
                        }
                    )
                ) {
                    Text(
                        text = s.descripcion,
                        color = Color.White,
                        fontSize = 18.sp
                    )
                }
            }
        }
        Canvas(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height
            val canvasPortion = canvasHeight/8
            //1, 3, 5, 7
            connections.forEach { entry ->
                val startPos = when(entry.key){
                    1 -> 1
                    2 -> 3
                    3 -> 5
                    4 -> 7
                    else -> 1
                }
                val endPos = when(entry.value){
                    1 -> 7
                    2 -> 5
                    3 -> 3
                    4 -> 1
                    else -> 0
                }
                drawLine(
                    start = Offset(x = 0.dp.toPx(), y = canvasPortion * (startPos)),
                    end = Offset(x = canvasWidth, y = (canvasHeight - canvasPortion*endPos)),
                    color = when(endPos){
                        0 -> Color.Transparent
                        else -> Color.LightGray
                    },
                    strokeWidth = 3.dp.toPx()
                )
            }

        }
        Column(
            modifier = Modifier
                .width(IntrinsicSize.Max)
                .weight(2f)
            ,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ){
            rightItems.forEachIndexed { index, s ->
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        itemRight.value = index + 1
                        if(itemLeft.value != 0){
                            connections.replaceAll { t, u -> if(u == itemRight.value) 0 else u}
                            connections[itemLeft.value] = itemRight.value

                            if(connections[itemLeft.value] != 0 && (s.tipo == leftItems[itemLeft.value - 1].tipo)){
                                correctValues[connections[itemLeft.value]!! -1] = true
                            }else{
                                correctValues[connections[itemLeft.value]!! -1] = false
                            }

                            itemLeft.value = 0
                            itemRight.value = 0
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor =
                        if(index == (itemRight.value - 1) && !resolved){
                            Color(0xFF4FC3F7)
                        }else if(correctValues[index] && resolved){
                            Color(0xFF4CAF50)
                        }
                        else if(resolved){
                            Color(0xFFF44336)
                        }
                        else{
                            colorResource(id = R.color.secondaryThird)
                        }
                    )
                ) {
                    Text(
                        text = s.descripcion,
                        color = Color.White,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }

    CheckButtonMatch(resolved = resolved, onResolve = onResolve, onNext = onNext, correctValues)
}

@Composable
fun CheckButtonMatch(resolved: Boolean, onResolve: ( MutableList<Boolean> ) -> Unit, onNext: () -> Unit, correctMatches: MutableList<Boolean>) {
    Button(
        modifier = Modifier
            .width(175.dp),
        colors = ButtonDefaults.buttonColors(
            colorResource(id = R.color.secondaryFirst)
        ),
        onClick = {
            if(resolved)
                onNext()
            else
                onResolve(correctMatches)
        }
    ) {
        Text(
            color = Color.White,
            text =
            if(!resolved){
                "Comprobar"
            }else{
                "Siguiente"
            },
            style = TextStyle(fontWeight = FontWeight.Bold)
        )
    }
}