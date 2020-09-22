package game

import junit.framework.TestCase.assertEquals
import org.junit.Test

class TestInitializer {

    @Test
    fun test00getLife_0percent(){
        val initializer = RandomGameOfLifeInitializer(10, 10)
        val liveCells = initializer.getLive(0)
        assertEquals(0, liveCells.size)
    }

    @Test
    fun test01getLife_100percent(){
        val initializer = RandomGameOfLifeInitializer(10, 10)
        val liveCells = initializer.getLive(100)
        assertEquals(100, liveCells.size)
    }

    @Test
    fun test02getLife_50percent(){
        val initializer = RandomGameOfLifeInitializer(10, 10)
        val liveCells = initializer.getLive(50)
        assertEquals(50, liveCells.size)
    }

    @Test
    fun test03getLife_LowerThan0percent(){
        val initializer = RandomGameOfLifeInitializer(10, 10)
        val liveCells = initializer.getLive(-10)
        assertEquals(0, liveCells.size)
    }

    @Test
    fun test03getLife_HigherThan100percent(){
        val initializer = RandomGameOfLifeInitializer(10, 10)
        val liveCells = initializer.getLive(150)
        assertEquals(100, liveCells.size)
    }

}