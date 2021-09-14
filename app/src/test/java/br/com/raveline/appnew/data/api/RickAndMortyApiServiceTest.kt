package br.com.raveline.appnew.data.api

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.Charset

class RickAndMortyApiServiceTest {

private lateinit var service:RickAndMortyApiService
private lateinit var server:MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RickAndMortyApiService::class.java)
    }

    private fun enqueueMockResponse(fileName:String){
        val inputStream = javaClass.classLoader?.getResourceAsStream(fileName)
        val source = inputStream?.source()?.buffer()
        val mockResponse = MockResponse()

        mockResponse.setBody(source!!.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)
    }

    @Test
    fun `getAllCharacters after send request expecting return`(){
        runBlocking {
            enqueueMockResponse("allcharacters.json")
            val responseBody = service.requestAllCharacters()
            val request = server.takeRequest()

            println(responseBody.body())


            assertThat(responseBody).isNotNull()
        }
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}