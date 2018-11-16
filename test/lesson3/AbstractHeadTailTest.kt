package lesson3

import java.util.*
import kotlin.test.*

abstract class AbstractHeadTailTest {
    private lateinit var tree: SortedSet<Int>

    protected fun fillTree(empty: SortedSet<Int>) {
        this.tree = empty
        //В произвольном порядке добавим числа от 1 до 10
        tree.add(5)
        tree.add(1)
        tree.add(2)
        tree.add(7)
        tree.add(9)
        tree.add(10)
        tree.add(8)
        tree.add(4)
        tree.add(3)
        tree.add(6)
    }


    protected fun doHeadSetTest() {
        var set: SortedSet<Int> = tree.headSet(5)
        assertEquals(true, set.contains(1))
        assertEquals(true, set.contains(2))
        assertEquals(true, set.contains(3))
        assertEquals(true, set.contains(4))
        assertEquals(false, set.contains(5))
        assertEquals(false, set.contains(6))
        assertEquals(false, set.contains(7))
        assertEquals(false, set.contains(8))
        assertEquals(false, set.contains(9))
        assertEquals(false, set.contains(10))


        set = tree.headSet(127)
        for (i in 1..10)
            assertEquals(true, set.contains(i))

    }

    protected fun doTailSetTest() {
        var set: SortedSet<Int> = tree.tailSet(5)
        assertEquals(false, set.contains(1))
        assertEquals(false, set.contains(2))
        assertEquals(false, set.contains(3))
        assertEquals(false, set.contains(4))
        assertEquals(true, set.contains(5))
        assertEquals(true, set.contains(6))
        assertEquals(true, set.contains(7))
        assertEquals(true, set.contains(8))
        assertEquals(true, set.contains(9))
        assertEquals(true, set.contains(10))

        set = tree.tailSet(-128)
        for (i in 1..10)
            assertEquals(true, set.contains(i))

    }

    protected fun doHeadSetRelationTest() {
        val set: SortedSet<Int> = tree.headSet(7)
        assertEquals(6, set.size)
        assertEquals(10, tree.size)
        tree.add(0)
        assertTrue(set.contains(0))
        tree.add(12)
        assertFalse(set.contains(12))

        tree.clear()
        fillTree(tree)
    }

    protected fun doHeadSetRelationTestAddition() {
        tree.clear()
        for (i in 200 downTo 30) {
            tree.add(i)
        }
        val set: SortedSet<Int> = tree.headSet(30)
        tree.add(29)
        tree.add(28)
        assertTrue(set.contains(28))

        tree.clear()
        fillTree(tree)
    }


    protected fun doTailSetRelationTest() {
        val set: SortedSet<Int> = tree.tailSet(4)
        assertEquals(7, set.size)
        assertEquals(10, tree.size)
        tree.add(12)
        assertTrue(set.contains(12))
        assertTrue(tree.contains(12))
        tree.add(0)
        assertFalse(set.contains(0))

        tree.clear()
        fillTree(tree)
    }

    protected fun doTailSetRelationTestAddition() {
        tree.clear()
        for (i in 30..200) {
            tree.add(i)
        }
        val set: SortedSet<Int> = tree.tailSet(200)
        tree.add(290)
        tree.add(280)
        assertTrue(set.contains(280))
        assertFalse(set.contains(30))

        tree.clear()
        fillTree(tree)
    }


    protected fun doSubSetTest() {
        tree.clear()
        for (i in 50 downTo 30) {
            tree.add(i)
        }
        for (i in 10..30) {
            tree.add(i)
        }
        for (i in 10 downTo 0) {
            tree.add(i)
        }
        val set: SortedSet<Int> = tree.subSet(15,50)
        assertTrue(set.contains(17))
        assertTrue(set.contains(40))
        assertFalse(set.contains(0))
        assertEquals(tree.size,51)
        assertEquals(set.size,36)

        tree.clear()
        fillTree(tree)
    }
}