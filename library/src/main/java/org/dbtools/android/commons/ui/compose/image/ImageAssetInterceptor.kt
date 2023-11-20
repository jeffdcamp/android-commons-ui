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

        if (data.round) {
            builder.round()
        }

        if (data.trim) {
            builder.trim(true)
        }

        if (data.focusX != null && data.focusY != null) {
            builder.focus(data.focusX, data.focusY)
        }

        if (data.aspectWidth != null && data.aspectHeight != null) {
            builder.aspectRatio(data.aspectWidth, data.aspectHeight)
        }

        data.quality?.let { builder.quality(it) }
        data.radius?.let { builder.radius(it) }
        data.backgroundColor?.let { builder.background(it) }
        data.rotation?.let { builder.rotation(it) }
        data.blur?.let { builder.blur(it) }
        data.effectSharpen?.let { builder.effectSharpen(it) }
        data.focusType?.let { builder.focus(it) }
        data.cropType?.let { builder.crop(it) }
        data.cropModeType?.let { builder.cropMode(it) }
        data.format?.let { builder.format(it) }

        return builder.create().toHttpUrl()
    }
}

data class ImageKitAsset(
    val identifier: String,
    val preferredSize: ImageKitAssetAspect = ImageKitAssetAspect.CONFINED_HEIGHT,
    val aspectWidth: Int? = null,
    val aspectHeight: Int? = null,
    val quality: Int? = null,
    val radius: Int? = null,
    val round: Boolean = false,
    val trim: Boolean = false,
    val backgroundColor: String? = null,
    val rotation: Rotation? = null,

    val cropType: CropType? = null,
    val cropModeType: CropMode? = null,
    val focusType: FocusType? = null,

    val blur: Int? = null,
    val effectSharpen: Int? = null,
    val focusX: Int? = null,
    val focusY: Int? = null,

    val format: Format? = null,
    val streamingFormat: StreamingFormat? = null,

)

enum class ImageKitAssetAspect {
    CONFINED_WIDTH, // optimal for portrait images
    CONFINED_HEIGHT, // optimal for landscape images
    CONFINED_WIDTH_HEIGHT
}