package com.shuklavijay249.unitconverterapp.compose

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.shuklavijay249.unitconverterapp.ConverterViewModel
import com.shuklavijay249.unitconverterapp.ConverterViewModelFactory
import com.shuklavijay249.unitconverterapp.compose.converter.TopScreen
import com.shuklavijay249.unitconverterapp.compose.history.HistoryScreen

@Composable
fun BaseScreen(
    factory: ConverterViewModelFactory,
    modifier : Modifier = Modifier,
    converterViewModel : ConverterViewModel = viewModel(factory = factory)
){

   val list = converterViewModel.getConversions()
   val historyList = converterViewModel.resultList.collectAsState(initial = emptyList())

   val configuration = LocalConfiguration.current
   var isLandscape by remember{ mutableStateOf(false)}

   when(configuration.orientation){
         Configuration.ORIENTATION_LANDSCAPE -> {
             isLandscape = true
             Row(modifier = modifier.padding(30.dp).fillMaxSize(),
             horizontalArrangement = Arrangement.SpaceAround
             ) {
                 TopScreen(list,
                     converterViewModel.selectedConversion,
                     converterViewModel.inputText,
                     converterViewModel.typedValue,
                     isLandscape
                 ){ message1,message2 ->
                     converterViewModel.addResult(message1,message2)
                 }
                 Spacer(modifier = modifier.width(10.dp))
                 HistoryScreen(
                     historyList,{item ->
                         converterViewModel.removeResult(item)
                     },
                     {
                         converterViewModel.clearAll()
                     }
                 )
             }

         } else->{
       isLandscape = false
       Column(modifier = modifier.padding(30.dp)) {
           TopScreen(list,
               converterViewModel.selectedConversion,
               converterViewModel.inputText,
               converterViewModel.typedValue,
               isLandscape
           ){ message1,message2 ->
               converterViewModel.addResult(message1,message2)
           }
           Spacer(modifier = modifier.height(20.dp))
           HistoryScreen(
               historyList,{item ->
                   converterViewModel.removeResult(item)
               },
               {
                   converterViewModel.clearAll()
               }
           )
       }
         }
   }



}