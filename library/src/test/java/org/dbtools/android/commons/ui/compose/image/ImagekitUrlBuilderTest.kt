package org.dbtools.android.commons.ui.compose.image

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class ImagekitUrlBuilderTest {

    @Test
    fun testBasic() {
        val url = ImagekitUrlBuilder("demo", "medium_cafe_B1iTdD0C.jpg")
            .create()

        assertThat(url).isEqualTo("https://ik.imagekit.io/demo/medium_cafe_B1iTdD0C.jpg")
    }

    @Test
    fun testBasicWithCrop() {
        val url = ImagekitUrlBuilder("demo", "medium_cafe_B1iTdD0C.jpg")
            .width(500)
            .height(1000)
            .create()

        assertThat(url).isEqualTo("https://ik.imagekit.io/demo/medium_cafe_B1iTdD0C.jpg?tr=w-500,h-1000")
    }
}