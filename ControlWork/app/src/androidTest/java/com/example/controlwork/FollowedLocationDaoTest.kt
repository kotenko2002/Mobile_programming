package com.example.controlwork

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.controlwork.infrastructure.db.AppDatabase
import com.example.controlwork.infrastructure.db.FollowedLocationDao
import com.example.controlwork.infrastructure.db.LocationDao
import com.example.controlwork.models.location.FollowedLocation
import com.example.controlwork.models.location.Location
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class FollowedLocationDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var followedLocationDao: FollowedLocationDao
    private lateinit var locationDao: LocationDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        followedLocationDao = database.followedLocationDao()
        locationDao = database.locationDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun testUpsertFollowedLocation() = runBlocking {
        val location = Location(id = 1, name = "Kyiv", country = "UA")
        locationDao.upsertLocations(listOf(location))

        val followedLocation = FollowedLocation(locationId = location.id)
        followedLocationDao.upsertFollowedLocation(followedLocation)

        val result = followedLocationDao.getFollowedLocationsWithNames().getOrAwaitValue()
        Assert.assertEquals(1, result.size)
        Assert.assertEquals("Kyiv", result[0].name)
        Assert.assertEquals("UA", result[0].country)
    }

    @Test
    fun testDeleteFollowedLocation() = runBlocking {
        val location = Location(id = 1, name = "Kyiv", country = "UA")
        locationDao.upsertLocations(listOf(location))

        val followedLocation = FollowedLocation(locationId = location.id)
        followedLocationDao.upsertFollowedLocation(followedLocation)

        followedLocationDao.deleteFollowedLocation(location.id)

        val result = followedLocationDao.getFollowedLocationsWithNames().getOrAwaitValue()
        Assert.assertTrue(result.isEmpty())
    }

    @Test
    fun testGetFollowedLocationsWithNames() = runBlocking {
        val locations = listOf(
            Location(id = 1, name = "Kyiv", country = "UA"),
            Location(id = 2, name = "London", country = "GB")
        )
        locationDao.upsertLocations(locations)

        val followedLocations = listOf(
            FollowedLocation(locationId = 1),
            FollowedLocation(locationId = 2)
        )
        followedLocations.forEach { followedLocationDao.upsertFollowedLocation(it) }

        val result = followedLocationDao.getFollowedLocationsWithNames().getOrAwaitValue()
        Assert.assertEquals(2, result.size)
        Assert.assertEquals("Kyiv", result[0].name)
        Assert.assertEquals("London", result[1].name)
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