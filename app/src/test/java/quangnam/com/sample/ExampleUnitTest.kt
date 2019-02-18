package quangnam.com.sample

import io.reactivex.Observable
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {
    @Test
    @Throws(Exception::class)
    fun addition_isCorrect() {
        assertEquals(4, (2 + 2).toLong())
    }

    @Test
    @Throws(Exception::class)
    fun rxTest() {
        Observable.just(1, 2)
                .doOnNext {
//                    if (it == 2) throw RuntimeException("Test")
                }
                .doOnSubscribe { System.out.println("Subscribe") }
                .doOnTerminate { System.out.println("Finished") }
                .subscribe({ System.out.println(it) },
                        { it.printStackTrace() })
    }
}