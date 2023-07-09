import com.example.testtaskstarwars.data.repository.DataBaseRepositoryImpl
import com.example.testtaskstarwars.data.room.dao.StarWarsDao
import com.example.testtaskstarwars.data.room.entities.FilmsEntity
import com.example.testtaskstarwars.data.room.entities.PeopleEntity
import com.example.testtaskstarwars.data.room.entities.PlanetEntity
import com.example.testtaskstarwars.data.room.entities.StarshipsEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.junit.Assert.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DataBaseRepositoryImplTest {

    private val dao = mock<StarWarsDao>()
    private val repository = DataBaseRepositoryImpl(dao)

    private val peopleEntity = PeopleEntity(
        id = 1,
        name = "Luke Skywalker",
        gender = "Male",
        starships = 2
    )
    private val starshipEntity = StarshipsEntity(
        id = 1,
        name = "X-wing",
        model = "T-65 X-wing",
        manufacturer = "Incom Corporation",
        passengers = "1"
    )

    private val planetEntity = PlanetEntity(
        id = 1,
        name = "Tatooine",
        diameter = "10465",
        population = "200000"
    )
    private val filmEntity = FilmsEntity(
        id = 4,
        title = "A New Hope",
        director = "George Lucas",
        producer = "Gary Kurtz, Rick McCallum"
    )

    @Test
    fun getAllPeopleTest() = runBlockingTest {
        val expected = listOf(peopleEntity)
        whenever(dao.getAllPeople()).thenReturn(flowOf(expected))

        val actual = repository.getAllPeople().first()

        verify(dao).getAllPeople()
        assertEquals(expected, actual)
    }

    @Test
    fun getPeopleByNameTest() = runBlockingTest {
        val expected = listOf(peopleEntity)
        whenever(dao.getPeopleByName(peopleEntity.name)).thenReturn(flowOf(expected))

        val actual = repository.getPeopleByName(peopleEntity.name).first()

        verify(dao).getPeopleByName(peopleEntity.name)
        assertEquals(expected, actual)
    }

    @Test
    fun insertPeopleTest() = runBlockingTest {
        repository.insertPeople(peopleEntity)

        verify(dao).insertPeople(peopleEntity)
    }

    @Test
    fun deletePeopleTest() = runBlockingTest {
        repository.deletePeople(peopleEntity)

        verify(dao).deletePeople(peopleEntity)
    }


    @Test
    fun containsPeopleTest() = runBlockingTest {
        whenever(dao.containsPeople(peopleEntity.name)).thenReturn(flowOf(true))

        val actual = repository.containsPeople(peopleEntity.name).first()

        verify(dao).containsPeople(peopleEntity.name)
        assertTrue(actual)
    }

    // Tests for StarshipsEntity

    @Test
    fun getAllStarshipsTest() = runBlockingTest {
        val expected = listOf(starshipEntity)
        whenever(dao.getAllStarships()).thenReturn(flowOf(expected))

        val actual = repository.getAllStarships().first()

        verify(dao).getAllStarships()
        assertEquals(expected, actual)
    }

    @Test
    fun getStarshipsByNameTest() = runBlockingTest {
        val expected = listOf(starshipEntity)
        whenever(dao.getStarshipsByName(starshipEntity.name)).thenReturn(flowOf(expected))

        val actual = repository.getStarshipsByName(starshipEntity.name).first()

        verify(dao).getStarshipsByName(starshipEntity.name)
        assertEquals(expected, actual)
    }

    @Test
    fun insertStarshipTest() = runBlockingTest {
        repository.insertStarship(starshipEntity)

        verify(dao).insertStarship(starshipEntity)
    }

    @Test
    fun deleteStarshipTest() = runBlockingTest {
        repository.deleteStarship(starshipEntity)

        verify(dao).deleteStarship(starshipEntity)
    }

    @Test
    fun containsStarshipsTest() = runBlockingTest {
        whenever(dao.containsStarships(starshipEntity.name)).thenReturn(flowOf(true))

        val actual = repository.containsStarships(starshipEntity.name).first()

        verify(dao).containsStarships(starshipEntity.name)
        assertTrue(actual)
    }

    @Test
    fun getAllPlanetsTest() = runBlockingTest {
        val expected = listOf(planetEntity)
        whenever(dao.getAllPlanets()).thenReturn(flowOf(expected))

        val actual = repository.getAllPlanets().first()

        verify(dao).getAllPlanets()
        assertEquals(expected, actual)
    }

    @Test
    fun getPlanetByNameTest() = runBlockingTest {
        val expected = listOf(planetEntity)
        whenever(dao.getPlanetByName(planetEntity.name)).thenReturn(flowOf(expected))

        val actual = repository.getPlanetByName(planetEntity.name).first()

        verify(dao).getPlanetByName(planetEntity.name)
        assertEquals(expected, actual)
    }

    @Test
    fun insertPlanetTest() = runBlockingTest {
        repository.insertPlanet(planetEntity)

        verify(dao).insertPlanet(planetEntity)
    }

    @Test
    fun deletePlanetTest() = runBlockingTest {
        repository.deletePlanet(planetEntity)

        verify(dao).deletePlanet(planetEntity)
    }

    @Test
    fun containsPlanetTest() = runBlockingTest {
        whenever(dao.containsPlanet(planetEntity.name)).thenReturn(flowOf(true))

        val actual = repository.containsPlanet(planetEntity.name).first()

        verify(dao).containsPlanet(planetEntity.name)
        assertTrue(actual)
    }

}
