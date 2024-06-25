package com.ff.funum.ui.screens

import android.annotation.SuppressLint
import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.dataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ff.funum.data.api.ApiClient
import com.ff.funum.data.api.DeleteTopic
import com.ff.funum.data.api.EndExamBody
import com.ff.funum.data.api.Lessons
import com.ff.funum.data.api.Pregunta_match_api
import com.ff.funum.data.api.Pregunta_opcion_multiple_Api
import com.ff.funum.data.api.Respuesta_match_api
import com.ff.funum.data.api.Respuesta_opcion_multiple_api
import com.ff.funum.data.api.TemaApi
import com.ff.funum.data.api.TopicAPI
import com.ff.funum.data.api.UpdateTopic
import com.ff.funum.data.listLessons
import com.ff.funum.data.repository.Repository
import com.ff.funum.model.Exam
import com.ff.funum.model.ExamData
import com.ff.funum.model.Pregunta_match
import com.ff.funum.model.Pregunta_opcion_multiple
import com.ff.funum.model.Respuesta_match
import com.ff.funum.model.Respuesta_opcion_multiple
import com.ff.funum.model.SortedQuestion
import com.ff.funum.model.Tema
import com.ff.funum.ui.screens.Quiz.QuizUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LessonsViewModel(application: Application) : AndroidViewModel(application) {
    private val api = ApiClient.apiService
    private val repository = Repository(application)

    var rol: Boolean = true;
    var admin: Boolean = false;
    @SuppressLint("SuspiciousIndentation")
    fun getAllLessons() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val token = repository.getToken()
                    if (token != null) {
                        val response = repository.getAllLessons(token)
                        listLessons.clear()
                        listLessons.addAll(listOf(response))
                    } else {
                        Log.i("LessonsViewModel", "Token is null")
                    }

            } catch (e: Exception) {
                when (e) {
                    is HttpException -> {
                        Log.i("LessonsViewModel", e.toString())
                    }

                    else -> {
                        Log.i("LessonsViewModel", e.toString())
                    }
                }
            }
        }
    }
    fun rol(){

        viewModelScope.launch(Dispatchers.IO){
            try {
                val token = repository.getToken()
                val response =  api.getUser("Bearer $token")
               if(response.roles.contains("admin")){
                   admin=true
                   rol=false
               }
                Log.i("MainViewModel",response.toString())
            }catch (e:Exception){
                when(e){
                    is retrofit2.HttpException -> {
                        e.message?.let { Log.i("MainViewmodel", it) }
                    }
                    else -> {
                        Log.i("MainViewModel", e.toString())
                    }
                }
            }
        }
    }
    fun beginTopic(lessonId: String?, topicId: String?){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val token = repository.getToken()
                if(token != null){
                    val usuario = api.getUser("Bearer $token")
                    val lessonStarted = usuario.lecciones.any{
                        it.leccion == lessonId
                    }
                    if (!lessonStarted){
                        api.beginLesson("Bearer $token", lessonId)
                    }
                    api.beginTopic("Bearer $token", topicId)
                }
            }catch (e: Exception) {
                when (e) {
                    is HttpException -> {
                        Log.i("LessonsViewModel", e.toString())
                    }
                    else -> {
                        Log.i("LessonsViewModel", e.toString())
                    }
                }
            }
        }
    }

    fun endLesson(lessonId: String?){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val token = repository.getToken()
                if(token != null){
                    api.endLesson("Bearer $token", lessonId)
                }
            }catch (e: Exception) {
                when (e) {
                    is HttpException -> {
                        Log.i("LessonsViewModel", e.toString())
                    }

                    else -> {
                        Log.i("LessonsViewModel", e.toString())
                    }
                }
            }
        }
    }

    fun endTopic(topicId: String?){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val token = repository.getToken()
                if(token != null){
                    api.endTopic("Bearer $token", topicId)
                }
            }catch (e: Exception) {
                when (e) {
                    is HttpException -> {
                        Log.i("LessonsViewModel", e.toString())
                    }
                    else -> {
                        Log.i("LessonsViewModel", e.toString())
                    }
                }
            }
        }
    }

    private val _selectedTopic = MutableStateFlow(TopicAPI())
    val selectedTopic = _selectedTopic.asStateFlow()

    fun saveDataFromSelectedTopic(topicSelected: TopicAPI){
        _selectedTopic.value = topicSelected
    }

    //Gestiona la informacion de Update topic screen
    var updatedTopic :TopicAPI= TopicAPI()
    var topic: TopicAPI=TopicAPI(id = "",imagen = listOf(""))
    //Update topic
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun updateTopic(topic: TopicAPI, idLesson:String, idTopic:String="",visible: Boolean) {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val token = repository.getToken()
                val response = topic.id?.let {
                    UpdateTopic(topic.nombre,topic.contenido,topic.ponderacion,topic.visibility,topic.imagen,
                        it,idLesson)
                }?.let { api.updateTopic(it, idTopic, "Bearer $token") }
                if (response != null) {
                    if(visible!=topic.visibility){
                        val visibility = response.id?.let { api.toggleTopicVisibility(it,"Bearer $token") }
                    }
                }
                Log.i("MainViewModel",response.toString())
            }catch (e:Exception){
                when(e){
                    is retrofit2.HttpException -> {
                        e.message?.let { Log.i("MainViewmodel", it) }
                    }
                    else -> {
                        Log.i("MainViewModel", e.toString())
                    }
                }
            }
        }
    }
    //Toggle topic visibility
    fun toggleTopicVisibility(idTopic:String="") {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val token = repository.getToken()
                val response = api.toggleTopicVisibility( idTopic, "Bearer $token")
                Log.i("MainViewModel",response.toString())
            }catch (e:Exception){
                when(e){
                    is retrofit2.HttpException -> {
                        e.message?.let { Log.i("MainViewmodel", it) }
                    }
                    else -> {
                        Log.i("MainViewModel", e.toString())
                    }
                }
            }
        }
    }

    var _idLesson: String=""
    var _idTopic: String=""
    //Delete topic
    fun deleteTopic(idTopic:String, idLesson: String) {
        viewModelScope.launch(Dispatchers.IO){
            try{
                val token = repository.getToken()
                val response = token?.let { api.deleteTopic(DeleteTopic(idLesson),idTopic, "Bearer $it") }
                Log.i("MainViewModel",response.toString())
            }catch (e:Exception){
                when(e){
                    is retrofit2.HttpException->{
                        Log.i("MainViewModel",e.message())
                    }
                    else ->{
                        Log.i("MainViewModel",e.toString())
                    }
                }
            }
        }
    }

    //Gestiona la informacion de Update lesson screen
    var updatedLesson :Lessons= Lessons()
    var lesson:Lessons=Lessons()

    //Update lesson
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun updateLesson(lesson: Lessons,idLesson:String="",visible:Boolean) {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val token = repository.getToken()
                val response = api.updateLesson(lesson, idLesson, "Bearer $token")
                if(visible!=lesson.visibility){
                    val visibility = response.id?.let { api.toggleLessonVisibility(it,"Bearer $token") }
                }

                Log.i("MainViewModel",response.toString())
            }catch (e:Exception){
                when(e){
                    is retrofit2.HttpException -> {
                        e.message?.let { Log.i("MainViewmodel", it) }
                    }
                    else -> {
                        Log.i("MainViewModel", e.toString())
                    }
                }
            }
        }
    }
    //Toggle lesson visibility
    fun toggleLessonVisibility(idLesson:String="") {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val token = repository.getToken()
                val response = api.toggleLessonVisibility( idLesson, "Bearer $token")
                Log.i("MainViewModel",response.toString())
            }catch (e:Exception){
                when(e){
                    is retrofit2.HttpException -> {
                        e.message?.let { Log.i("MainViewmodel", it) }
                    }
                    else -> {
                        Log.i("MainViewModel", e.toString())
                    }
                }
            }
        }
    }

    //Delete lesson
    fun deleteLesson(idLesson:String="") {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val token = repository.getToken()
                val response = api.deleteLesson( idLesson, "Bearer $token")
                Log.i("MainViewModel",response.toString())
            }catch (e:Exception){
                when(e){
                    is retrofit2.HttpException -> {
                        e.message?.let { Log.i("MainViewmodel", it) }
                    }
                    else -> {
                        Log.i("MainViewModel", e.toString())
                    }
                }
            }
        }
    }

    // Funciones para el Quiz
    var auxUiState by mutableStateOf(QuizUiState())

    private val _uiState = MutableStateFlow<UiState>(UiState.Ready)
    val uiState : StateFlow<UiState> = _uiState

    val examState: MutableState<List<SortedQuestion>> = mutableStateOf(arrayListOf())

    fun checkMultipleChoiceAnswer(){
        if(getMultipleChoiceCorrectAnswer() == auxUiState.selectedAnswer){
            auxUiState.points++
        }
    }

    fun checkMatchAnswer(correctMatches: List<Boolean>){
        val correct = correctMatches.contains(false)
        if(!correct){
            auxUiState.points++
        }
    }

    fun getCurrentQuestion(): SortedQuestion {
        return examState.value[auxUiState.currentQuestionIndex]
    }

    fun getMultipleChoiceCorrectAnswer(): String{
        val currentQuestion = getCurrentQuestion().question as Pregunta_opcion_multiple
        currentQuestion.respuestas.forEach {
            if(it.correcta){
                return it.descripcion
            }
        }
        return ""
    }

    fun updateOptionSelected(selectedOption:String){
        auxUiState = auxUiState.copy(selectedAnswer = selectedOption)
    }

    fun resolveQuestion(correctMatches: MutableList<Boolean> = arrayListOf()){
        auxUiState = auxUiState.copy(resolved = true)
        if(getCurrentQuestion().type == "multiple"){
            checkMultipleChoiceAnswer()
        }
        else {
            checkMatchAnswer(correctMatches)
        }
    }

    fun onNext(){
        auxUiState.currentQuestionIndex++
        auxUiState = auxUiState.copy(selectedAnswer = null, resolved = false)
    }

    private fun sortExam(): List<SortedQuestion>{
        val _exam = mutableListOf<SortedQuestion>()
        ExamData.preguntas_opcion_multiple.forEach {
            _exam.add(
                SortedQuestion(
                    type = "multiple",
                    question = Pregunta_opcion_multiple(
                        enunciado = it.enunciado,
                        respuestas = it.respuestas.shuffled().toMutableList(),
                        tema = it.tema,
                    )
                )
            )
        }
        ExamData.preguntas_match.forEach {
            _exam.add(
                SortedQuestion(
                    type = "match",
                    question = Pregunta_match(
                        tema = it.tema,
                        enunciado = it.enunciado,
                        respuestas = it.respuestas.shuffled().toMutableList()
                    )
                )
            )
        }

        _exam.shuffle()

        return _exam.toList()
    }

    //Implementar funcion para obtener el token

    fun getMatchColumns(): Pair<List<Respuesta_match>, List<Respuesta_match>>{
        val respuestasMatch = getCurrentQuestion().question as Pregunta_match
        val leftColumn = respuestasMatch.respuestas.distinctBy { it.tipo }
        val rightColumn = respuestasMatch.respuestas.reversed().distinctBy { it.tipo }

        return Pair(leftColumn, rightColumn)
    }

    fun getExam(examId: String){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val token = repository.getToken()
                _uiState.value = UiState.Loading
                val examApi = api.getExam(
                    token = "Bearer $token",
                    examId = examId
                ).exam

                val transformMultipleChoiceAnswer: (Respuesta_opcion_multiple_api) -> Respuesta_opcion_multiple = {
                    Respuesta_opcion_multiple(
                        descripcion = it.descripcion,
                        correcta = it.correcta
                    )
                }

                val transformMultipleChoice: (Pregunta_opcion_multiple_Api) -> Pregunta_opcion_multiple = {
                    Pregunta_opcion_multiple(
                        enunciado = it.enunciado,
                        tema = Tema(nombre = it.tema.nombre),
                        respuestas = it.respuestas.map(transformMultipleChoiceAnswer) as MutableList<Respuesta_opcion_multiple>,
                    )
                }

                val transformMatchAnswer: (Respuesta_match_api) -> Respuesta_match = {
                    Respuesta_match(
                        descripcion = it.descripcion,
                        tipo = it.tipo
                    )
                }

                val transformMatch: (Pregunta_match_api) -> Pregunta_match = {
                    Pregunta_match(
                        enunciado = it.enunciado,
                        tema = Tema(nombre = it.tema.nombre),
                        respuestas = it.respuestas.map(transformMatchAnswer) as MutableList<Respuesta_match>,
                    )
                }

                val transformTema: (TemaApi) -> Tema = {
                    Tema(
                        nombre = it.nombre
                    )
                }

                ExamData = Exam(
                    ponderacion = examApi.tipo_Examen.ponderacion,
                    temas = examApi.temas.map(transformTema) as MutableList<Tema>,
                    visibilidad = examApi.visibilidad,
                    preguntas_opcion_multiple = examApi.preguntas_opcion_multiple.map(transformMultipleChoice) as MutableList<Pregunta_opcion_multiple>,
                    preguntas_match = examApi.preguntas_match.map(transformMatch) as MutableList<Pregunta_match>,
                    nombre = examApi.nombre
                )

                auxUiState.name = ExamData.nombre
                examState.value = sortExam()

                _uiState.value = UiState.Ready
            }catch (e: Exception){
                Log.e("get exam", e.toString())
            }

        }
    }

    fun startExam(examId: String = ""){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val token = repository.getToken()
                val response = repository.getDate(examId)
                if(response == null){
                    _uiState.value = UiState.Loading

                    val usuario = api.getUser("Bearer $token")
                    val lessonStarted = usuario.lecciones.any{
                        it.leccion == auxUiState.lesson
                    }
                    if (!lessonStarted){
                        api.beginLesson("Bearer $token", auxUiState.lesson)
                    }

                    val beginRespose = api.beginExam("Bearer $token", examId).examenes
                    repository.saveData(beginRespose.last().examen, beginRespose.last().fecha_hora_inicio)
                    _uiState.value = UiState.Ready
                }
            }catch (e: Exception){
                Log.e("start/end exam", e.toString())
            }
        }
    }

    fun finishExam(examId: String = "", calificacion: Int){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val token = repository.getToken()
                val _response: String? = repository.getDate(examId)

                if(_response != null){

                    _uiState.value = UiState.Loading

                    api.finishExam("Bearer $token", examId, EndExamBody(calificacion, _response))
                    repository.deleteNamePreferences(examId)

                    val usuario = api.getUser("Bearer $token")
                    val lesson = usuario.lecciones.find{
                        it.leccion == auxUiState.lesson
                    }
                    if (lesson?.fecha_hora_fin == null){
                        api.endLesson("Bearer $token", auxUiState.lesson)
                    }
                }

                _uiState.value = UiState.Success(msg = "Examen finalizado con exito")
            }catch (e: Exception){
                Log.e("start/end exam", e.toString())
                _uiState.value = UiState.Error(msg = "Ocurrio un error al finalizar el examen")
            }
        }
    }

    fun setStateToReady() {
        _uiState.value = UiState.Ready
    }

    fun resetQuiz() {
        auxUiState = auxUiState.copy(
            currentQuestionIndex = 0,
            points = 0,
            resolved = false,
            selectedAnswer = null,
            name = ""
        )
        examState.value = arrayListOf()
    }

    init {
        resetQuiz()
    }
    var showDeleteTopicDialog = mutableStateOf(false)
    fun OpenDeleteTopicDialog(){

        showDeleteTopicDialog.value = true
    }

    fun onDialogDeleteTopicDismiss(){
        showDeleteTopicDialog.value = false
    }

    fun onDialogDeleteTopicConfirm(){
        showDeleteTopicDialog.value = false
    }
    var showDialog = mutableStateOf(false)
    fun OpenDialog(){

        showDialog.value = true
    }

    fun onDialogDismiss(){
        showDialog.value = false
    }

    fun onDialogConfirm(){
        showDialog.value = false
    }
}

sealed class UiState {
    data object Loading : UiState()
    data object Ready : UiState()
    data class Success (val msg : String) : UiState()
    data class Error (val msg : String) : UiState()
}
