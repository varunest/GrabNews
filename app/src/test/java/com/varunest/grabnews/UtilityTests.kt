package com.varunest.grabnews

import com.varunest.grabnews.utils.CommonUtils
import org.junit.Test

import org.junit.Assert.*
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UtilityTests {
    @Test
    fun timeAgo_utility_isCorrect() {
        assertEquals(CommonUtils.getTimeAgo(Date()), "moments ago")
        assertEquals(
            CommonUtils.getTimeAgo(
                Date(System.currentTimeMillis() - 66 * 1000)
            ), "a minute ago"
        )
        assertEquals(
            CommonUtils.getTimeAgo(
                Date(System.currentTimeMillis() - 600 * 1000)
            ), "10 minutes ago"
        )
        assertEquals(
            CommonUtils.getTimeAgo(
                Date(System.currentTimeMillis() - 3600 * 1000)
            ), "an hour ago"
        )
        assertEquals(
            CommonUtils.getTimeAgo(
                Date(System.currentTimeMillis() - 3 * 3600 * 1000)
            ), "3 hours ago"
        )
    }
}
