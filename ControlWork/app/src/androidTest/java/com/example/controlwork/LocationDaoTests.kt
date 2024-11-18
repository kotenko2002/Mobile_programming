package com.example.controlwork

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.controlwork.infrastructure.db.AppDatabase
import com.example.controlwork.infrastructure.db.LocationDao
import com.example.controlwork.models.location.Location
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class LocationDaoTests {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var locationDao: LocationDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        locationDao = database.locationDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun testUpsertAndRetrieveLocations() = runBlocking {
        val locations = listOf(
            Location(id = 1, name = "Kyiv", country = "UA"),
            Location(id = 2, name = "London", country = "GB")
        )
        locationDao.upsertLocations(locations)

        val result = locationDao.getLocationsCount()
        Assert.assertEquals(2, result)
    }

    @Test
    fun testSearchLocationsByName() = runBlocking {
        val locations = listOf(
            Location(id = 1, name = "Kyiv", country = "UA"),
            Location(id = 2, name = "Krakow", country = "PL"),
            Location(id = 3, name = "Berlin", country = "DE")
        )
        locationDao.upsertLocations(locations)

        val searchResults = locationDao.searchLocationsByName("Kr").getOrAwaitValue()
        Assert.assertEquals(1, searchResults.size)
        Assert.assertEquals("Krakow", searchResults[0].name)
    }

    private fun <T> LiveData<T>.getOrAwaitValue(): T {
        var data: T? = null
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(o: T) {
                data = o
                latch.countDown()
                this@getOrAwaitValue.removeObserver(this)
            }
        }
        this.observeForever(observer)
        latch.await(2, TimeUnit.SECONDS)
        return data as T
    }
}
