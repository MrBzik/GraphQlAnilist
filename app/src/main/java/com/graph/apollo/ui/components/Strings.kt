package com.graph.apollo.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.graph.apollo.R

object Strings {

    @Composable
    fun AnnotatedString(resId: Int, value: String){

        Text(text = buildAnnotatedString {

            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Bold
                )
            ){
                append(stringResource(resId))
            }

            append(" $value")

        })

    }

}