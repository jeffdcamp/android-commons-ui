package org.dbtools.android.commons.ui.compose.image

import co.touchlab.kermit.Logger
import coil.intercept.Interceptor
import coil.request.ErrorResult
import coil.request.ImageResult
import coil.size.Size
import coil.size.pxOrElse
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl

/**
 * Interceptor to allow COIL to work with [ImageKitAsset].
 *
 * Usage: Register this interceptor to COIL's component registry by setting a default image loader in onCreate of your App.kt.
 * ```
 *  Coil.setDefaultImageLoader(
 *       ImageLoader.Builder(this)
 *           componentRegistry {
 *               add(ImageKitAssetInterceptor("myimagekit"))
 *           }
 *           .build()
 *   )
 * ```
 *
 * @property endpoint Imagekit endpoint (Example: use "demo" for "https://ik.imagekit.io/demo")
 */
class ImageKitAssetInterceptor(val endpoint: String): Interceptor {
    override suspend fun intercept(chain: Interceptor.Chain): ImageResult {
        val data = chain.request.data
        val request = if (data is ImageKitAsset) {
            chain.request.newBuilder().data(map(data, chain.size)).build()
        } else {
            chain.request
        }

        return try {
            chain.proceed(request)
        } catch (e: OutOfMemoryError) {
            Logger.e(e) { "Out of Memory requesting image [${request.data}]" }
            ErrorResult(null, request, e)
        }
    }

    private fun map(data: ImageKitAsset, size: Size): HttpUrl {
        if (data.identifier.isBlank()) {
            return "".toHttpUrl()
        }

        val builder = ImagekitUrlBuilder(
            endpoint = endpoint,
            imageAssetId = data.identifier,
        )

        when (data.preferredSize) {
            ImageKitAssetAspect.CONFINED_WIDTH -> builder.width(size.width.pxOrElse { 1 })
            ImageKitAssetAspect.CONFINED_HEIGHT -> builder.height(size.height.pxOrElse { 1 })
            ImageKitAssetAspect.CONFINED_WIDTH_HEIGHT -> {
                builder.width(size.width.pxOrElse { 1 })
                builder.height(size.height.pxOrElse { 1 })
            }
        }

        // Blur
        data.blur?.let { builder.blur(it) }

        // Focus
        if (data.focusX != null && data.focusY != null) {
            builder.focus(data.focusX, data.focusY)
        }

        return builder.create().toHttpUrl()
    }
}

data class ImageKitAsset(
    val identifier: String,
    val preferredSize: ImageKitAssetAspect = ImageKitAssetAspect.CONFINED_HEIGHT,
    val blur: Int? = null,
    val focusX: Int? = null,
    val focusY: Int? = null,
)

enum class ImageKitAssetAspect {
    CONFINED_WIDTH, // optimal for portrait images
    CONFINED_HEIGHT, // optimal for landscape images
    CONFINED_WIDTH_HEIGHT
}