package ru.northbringer.bia_vacation.filterScreen.domain.api

import io.ktor.client.*
import io.ktor.client.features.auth.*
import io.ktor.client.features.auth.providers.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import kotlinx.serialization.Serializable
import ru.northbringer.bia_vacation.data.ApplicationDispatcher

class Network {

    private val url = "https://twiki.bia-tech.ru/rest/holidayProvider/1.0/personalHoliday/days?login=akorneeva&date=20221212"
    private val httpClient = HttpClient {

        install(JsonFeature) {
            val json = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
            serializer = KotlinxSerializer(json)
        }

        install(Auth) {
            basic {
                sendWithoutRequest = true
                password = "зфыыцщкв"
                username = "akorneeva"
            }
        }
    }

    fun getData(callback: (Info) -> Unit) {
        GlobalScope.launch(ApplicationDispatcher) {
            val result: Info = httpClient.get(url)
            callback(result)
        }

    }
}

@Serializable
data class Info(
    val daysLeft: Double
)