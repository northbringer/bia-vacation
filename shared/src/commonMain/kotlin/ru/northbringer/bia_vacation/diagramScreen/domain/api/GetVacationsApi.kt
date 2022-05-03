package ru.northbringer.bia_vacation.diagramScreen.domain.api

import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
//import ru.northbringer.bia_vacation.data.ApplicationDispatcher
import ru.northbringer.bia_vacation.diagramScreen.domain.usecase.Task
internal val ApplicationDispatcher: CoroutineDispatcher = Dispatchers.Main

/*
class GetVacationsApi {
    private val url = "https://www.breakingbadapi.com/api/characters?name="
    private val httpClient = HttpClient {

        install(JsonFeature) {
            val json = Json {  }
            serializer = KotlinxSerializer(json)
        }
    }

    fun getData(name: String, callback: (MutableList<Task>) -> Unit) {
        GlobalScope.launch(ApplicationDispatcher) {
            val result: MutableList<Task> = httpClient.get(url + name)
            callback(result)
        }
    }
}*/
