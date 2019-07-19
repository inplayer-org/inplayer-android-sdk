package com.inplayersdk

import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.URLDecoder

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    
    
    @Test
    fun addition_isCorrect() {
        
        val url = "viktor.inplayer://#token=valuetoken&refresh_token=valuerefresh"
        
        val getParamsFromUri = url.removePrefix("viktor.inplayer:" + "//#")
    
        assertEquals("token=valuetoken&refresh_token=valuerefresh", getParamsFromUri)
        
        val components =
            splitQuery(getParamsFromUri)
        
        val token = components["token"]
        val refreshToken = components["refresh_token"]
        
        assertEquals("valuetoken", token)
        assertEquals("valuerefresh", refreshToken)
        
    }
    
    fun splitQuery(url: String): Map<String, String> {
        val query_pairs = LinkedHashMap<String, String>()
        
        val pairs = url.split("&".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        for (pair in pairs) {
            val idx = pair.indexOf("=")
            query_pairs[URLDecoder.decode(pair.substring(0, idx), "UTF-8")] =
                URLDecoder.decode(pair.substring(idx + 1), "UTF-8")
        }
        return query_pairs
    }
}
