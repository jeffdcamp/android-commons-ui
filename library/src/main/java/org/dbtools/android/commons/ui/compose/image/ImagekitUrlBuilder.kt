@file:Suppress("unused")

package org.dbtools.android.commons.ui.compose.image

class ImagekitUrlBuilder(
    private val endpoint: String,
    private val imageAssetId: String,
    private val baseUrl: String = "https://ik.imagekit.io",
    private var transformationPosition: TransformationPosition = TransformationPosition.QUERY
) {
    private val transformationList: MutableList<String> = ArrayList()
    private val transformationMap = HashMap<String, Any>()
    private val path: String? = null
    private val isSource: Boolean = true
    private val queryParams: HashMap<String, String> = hashMapOf()
    private val streamingParam: HashMap<String, String> = hashMapOf()
    private var rawParams: String? = null

    /**
     * Method to specify the height of the output image.
     * @param height Accepts integer value greater than 1 and if a value between 0 and 1 is specified, then it acts as a percentage height.
     * For eg, 0.1 means 10% of the original height, 0.2 means 20% of the original height.
     * @return the current ImagekitUrlConstructor object.
     */
    fun height(height: Int): ImagekitUrlBuilder {
        transformationMap[TransformationMapping.HEIGHT] = height
        transformationList.add("${TransformationMapping.HEIGHT}-$height")
        return this
    }

    /**
     * Method to specify the width of the output image.
     * @param width Accepts integer value greater than 1 and if a value between 0 and 1 is specified, then it acts as a percentage width.
     * For eg, 0.1 means 10% of the original width, 0.2 means 20% of the original width.
     * @return the current ImagekitUrlConstructor object.
     */
    fun width(width: Int): ImagekitUrlBuilder {
        transformationMap[TransformationMapping.WIDTH] = width
        transformationList.add("${TransformationMapping.WIDTH}-$width")
        return this
    }

    /**
     * Method to specify the aspect ratio of the output image or the ratio of width to height of the output image.
     * This transform must be used along with either the height or the width transform.
     * @param width Accepts integer value greater than equal to 1
     * @param height Accepts integer value greater than equal to 1
     * @return the current ImagekitUrlConstructor object.
     */
    fun aspectRatio(width: Int, height: Int): ImagekitUrlBuilder {
        val s = "${TransformationMapping.ASPECT_RATIO}-$width-$height"
        transformationMap[TransformationMapping.ASPECT_RATIO] = s
        transformationList.add(s)
        return this
    }

    /**
     * Method to specify the output quality of the lossy formats like JPG and WebP. A higher quality number means a
     * larger size of the output image with high quality. A smaller number means low quality image at a smaller size.
     * @param quality Accepts integer value between 1 and 100.
     * Default value is picked from the dashboard settings. It is set to 80.
     * @return the current ImagekitUrlConstructor object.
     */
    fun quality(quality: Int): ImagekitUrlBuilder {
        transformationMap[TransformationMapping.QUALITY] = quality
        transformationList.add("${TransformationMapping.QUALITY}-$quality")
        return this
    }

    /**
     * Method to decide the final value of height and width of the output image based on the aspect ratio of the input
     * image and the requested transform.
     * @param cropType Accepts value of type CropType. Possible values include maintain_ratio, force, at_least and at_max.
     * Default value - maintain_ratio
     * @see CropType
     * @return the current ImagekitUrlConstructor object.
     */
    fun crop(cropType: CropType): ImagekitUrlBuilder {
        transformationMap[TransformationMapping.CROP] = cropType
        transformationList.add("${TransformationMapping.CROP}-${cropType.value}")
        return this
    }

    /**
     * Method used to specify the strategy of how the input image is used for cropping to get the output image.
     * @param cropMode Accepts value of type CropMode. Possible values include resize, extract, pad_extract and pad_resize.
     * Default value - resize
     * @see CropMode
     * @return the current ImagekitUrlConstructor object.
     */
    fun cropMode(cropMode: CropMode): ImagekitUrlBuilder {
        transformationMap[TransformationMapping.CROP_MODE] = cropMode
        transformationList.add("${TransformationMapping.CROP_MODE}-${cropMode.value}")
        return this
    }

    /**
     * Method used to specify the focus using cropped image coordinates
     * @param x Accepts value of x coordinate for focus.
     * @param y Accepts value of y coordinate for focus.
     * @return the current ImagekitUrlConstructor object.
     */
    fun focus(x: Int, y: Int): ImagekitUrlBuilder {
        transformationMap[TransformationMapping.X] = x
        transformationMap[TransformationMapping.Y] = y
        transformationList.add("${TransformationMapping.X}-${x}")
        transformationList.add("${TransformationMapping.Y}-${y}")
        return this
    }

    /**
     * Method used to specify the focus which is coupled with the extract type of crop mode (crop mode is not needed
     * if you are using auto focus) to get the area of the input image that should be focussed on to get the output image.
     * @param focusType Accepts value of type FocusType. Possible values include center, top, left, bottom, right,
     * top_left, top_right, bottom_left, bottom_right and auto.
     * Default value - center
     * @see FocusType
     * @return the current ImagekitUrlConstructor object.
     */
    fun focus(focusType: FocusType): ImagekitUrlBuilder {
        transformationMap[TransformationMapping.FOCUS] = focusType
        transformationList.add("${TransformationMapping.FOCUS}-${focusType.value}")
        return this
    }

    /**
     * Method used to specify the format of the output image. If no output format is specified and
     * the “Dynamic image format selection” option is selected in your dashboard settings, then the output format is
     * decided on the basis of the user’s device capabilities and input image format. If dynamic image format selction
     * is switched off, and no output format is specified then the format of the output image is same as that of the input image.
     * @param format Accepts value of type FocusType. Possible values include auto, webp, jpg, jpeg and pnt.
     * Default value - auto
     * @see Format
     * @return the current ImagekitUrlConstructor object.
     */
    fun format(format: Format): ImagekitUrlBuilder {
        transformationMap[TransformationMapping.FORMAT] = format
        transformationList.add("${TransformationMapping.FORMAT}-${format.value}")
        return this
    }

    /**
     * Method used to specify the radius to be used to get a rounded corner image.
     * This option is applied after resizing of the image, if any.
     * @param radius Possible values include positive integer.
     * @return the current ImagekitUrlConstructor object.
     */
    fun radius(radius: Int): ImagekitUrlBuilder {
        transformationMap[TransformationMapping.RADIUS] = radius
        transformationList.add("${TransformationMapping.RADIUS}-${radius}")
        return this
    }

    /**
     * Method used to get a perfectly rounded image.
     * This option is applied after resizing of the image, if any.
     * @return the current ImagekitUrlConstructor object.
     */
    fun round(): ImagekitUrlBuilder {
        transformationMap[TransformationMapping.RADIUS] = "max"
        transformationList.add("${TransformationMapping.RADIUS}-${"max"}")
        return this
    }

    /**
     * Method used to specify the background color as RGB hex code (e.g. FF0000) or an RGBA code (e.g. FFAABB50)
     * to be used for the image.
     * @param backgroundColor Default value - Black 000000
     * @return the current ImagekitUrlConstructor object.
     */
    fun background(backgroundColor: String): ImagekitUrlBuilder {
        transformationMap[TransformationMapping.BACKGROUND] =
            backgroundColor.uppercase()
        transformationList.add("${TransformationMapping.BACKGROUND}-${backgroundColor.uppercase()}")
        return this
    }

    /**
     * Method used to specify the width and color of the border that is added around the image.
     * The width is a positive integer that specifies the border width in pixels.
     * The border color is specified as a standard RGB hex code e.g b-{width}_{color}
     * @param borderWidth width of the border
     * @param borderColor color of the border as RGB hex code
     * @return the current ImagekitUrlConstructor object.
     */
    fun border(borderWidth: Int, borderColor: String): ImagekitUrlBuilder {
        val s = "${TransformationMapping.BORDER}-${borderWidth}_${borderColor.uppercase()}"
        transformationMap[TransformationMapping.BORDER] = s
        transformationList.add(s)
        return this
    }

    /**
     * Method used to specify the degrees by which the output image has to be rotated or specifies the use of
     * EXIF Orientation tag for the rotation of the image using auto.
     * @param rotation Possible values include 0, 90, 180, 270, 360 and auto.
     * Default value - center
     * @see Rotation
     * @return the current ImagekitUrlConstructor object.
     */
    fun rotation(rotation: Rotation): ImagekitUrlBuilder {
        transformationMap[TransformationMapping.ROTATION] = rotation
        transformationList.add("${TransformationMapping.ROTATION}-${rotation.value}")
        return this
    }

    /**
     * Method to specify the Gaussian blur that has to be applied to the image. The value of blur decides the radius of
     * the Gaussian blur that is applied. Higher the value, higher is the radius of Gaussian blur.
     * @param blur Accepts integer value between 1 and 100.
     * @return the current ImagekitUrlConstructor object.
     */
    fun blur(blur: Int): ImagekitUrlBuilder {
        transformationMap[TransformationMapping.BLUR] = blur
        transformationList.add("${TransformationMapping.BLUR}-${blur}")
        return this
    }

    /**
     * Method to specify the Named transformations which is an alias for the entire transformation string.
     * E.g we can create a named transform media_library_thumbnail for transformation string tr:w-150,h-150,f-center,c-at_max
     * @param namedTransformation
     * @return the current ImagekitUrlConstructor object.
     */
    fun named(namedTransformation: String): ImagekitUrlBuilder {
        transformationMap[TransformationMapping.NAMED] = namedTransformation
        transformationList.add("${TransformationMapping.NAMED}-${namedTransformation}")
        return this
    }

    /**
     * Method to specify if the output JPEG image should be rendered progressively. In progressive rendering,
     * the client instead of downloading the image row-wise (baseline loading), renders a low-quality pixelated
     * full image and then gradually keeps on adding more pixels and information to the image. It gives faster-perceived load times.
     * @param flag Possible values include true and false. Default value - false
     * @return the current ImagekitUrlConstructor object.
     */
    fun progressive(flag: Boolean): ImagekitUrlBuilder {
        transformationMap[TransformationMapping.PROGRESSIVE] = flag
        transformationList.add("${TransformationMapping.PROGRESSIVE}-$flag")
        return this
    }

    /**
     * Method to specify if the output image (if in PNG or WebP format) should be compressed losslessly.
     * In lossless compression, the output file size would be larger than the regular lossy compression but at the same time,
     * the perceived quality can be better in certain cases, especially for computer generated graphics.
     * Using lossless compression is not recommended for photographs.
     * @param flag Possible values include true and false. Default value - false
     * @return the current ImagekitUrlConstructor object.
     */
    fun lossless(flag: Boolean): ImagekitUrlBuilder {
        transformationMap[TransformationMapping.LOSSLESS] = flag
        transformationList.add("${TransformationMapping.LOSSLESS}-${flag}")
        return this
    }

    /**
     * Method to specify if the redundant pixels of the original image need to be removed. It uses a default logic
     * to identify the redundant surrounding region and removes it. This transformation is useful for images that have
     * a solid / nearly-solid background and the object in the center. This transformation will trim
     * the background from the edges, leaving only the central object in the picture.
     * @param flag Possible values include true and false.
     * @return the current ImagekitUrlConstructor object.
     * @see trim
     */
    fun trim(flag: Boolean): ImagekitUrlBuilder {
        transformationMap[TransformationMapping.TRIM] = flag
        transformationList.add("${TransformationMapping.TRIM}-${flag}")
        return this
    }

    /**
     * Method to specify the number of redundant pixels of the original image that need to be removed.
     * This transformation is useful for images that have a solid / nearly-solid background and the object in the center.
     * This transformation will trim the background from the edges, leaving only the central object in the picture.
     * @param value Number of pixels from the edge that need to be removed across all four sides.
     * @return the current ImagekitUrlConstructor object.
     * @see trim
     */
    fun trim(value: Int): ImagekitUrlBuilder {
        transformationMap[TransformationMapping.TRIM] = value
        transformationList.add("${TransformationMapping.TRIM}-${value}")
        return this
    }

    /**
     * Method used to specify if the output image should contain all the metadata that is initially available from
     * the original image. Enabling this is not recommended because this metadata is not relevant for rendering on the
     * web and mobile apps. The only reason where you should enable the metadata option is when you have knowingly wanted
     * the additional data like camera information, lens information and other image profiles attached to your image.
     * Possible values include true and false.
     * @param flag Default value - false
     * @return the current ImagekitUrlConstructor object.
     */
    fun metadata(flag: Boolean): ImagekitUrlBuilder {
        transformationMap[TransformationMapping.METADATA] = flag
        transformationList.add("${TransformationMapping.METADATA}-${flag}")
        return this
    }

    /**
     * Method used to specify if the output image should contain the color profile that is initially available
     * from the original image. It is recommended to remove the color profile before serving the image on web and apps.
     * However, in cases where you feel that the image looks faded or washed-out after using ImageKit and want to preserve
     * the colors in your image, then you should set this option to true. Possible values include true and false.
     * @param flag Default value - false
     * @return the current ImagekitUrlConstructor object.
     */
    fun colorProfile(flag: Boolean): ImagekitUrlBuilder {
        transformationMap[TransformationMapping.COLOR_PROFILE] = flag
        transformationList.add("${TransformationMapping.COLOR_PROFILE}-${flag}")
        return this
    }

    /**
     * Method to specify the default image which is delivered in case the image that is requested using ImageKit does not exist.
     * @param defaultImage
     * @return the current ImagekitUrlConstructor object.
     */
    fun defaultImage(defaultImage: String): ImagekitUrlBuilder {
        transformationMap[TransformationMapping.DEFAULT_IMAGE] = defaultImage
        transformationList.add("${TransformationMapping.DEFAULT_IMAGE}-${defaultImage}")
        return this
    }

    /**
     * Method to specify the device pixel ratio to be used to calculate the dimension of the output image. It is useful
     * when creating image transformations for devices with high density screens (DPR greater than 1) like the iPhone.
     * The DPR option works only when either the height or the width or both are specified for resizing the image
     * If the resulting height or width after considering the specified DPR value is less than 1px or more than 5000px
     * then the value of DPR is not considered and the normal height or width specified in the transformation string is used.
     * @param dpr Possible values: 0.1 to 5.0
     * @return the current ImagekitUrlConstructor object.
     */
    fun dpr(dpr: Float): ImagekitUrlBuilder {
        transformationMap[TransformationMapping.DPR] = dpr
        @Suppress("ImplicitDefaultLocale") // no translations here
        transformationList.add(String.format("${TransformationMapping.DPR}-%.2f", dpr))
        return this
    }

    /**
     * Method sharpens the input image. It is useful to highlight the edges and finer details in the image.
     * If just e-sharpen is used, then a default sharpening is performed on the image. This behavior can be controlled
     * by specifying a number that controls the extent of sharpening performed - higher the number,
     * more the sharpening
     * @param value
     * @return the current ImagekitUrlConstructor object.
     */
    fun effectSharpen(value: Int = 0): ImagekitUrlBuilder {
        if (value == 0) {
            transformationMap[TransformationMapping.EFFECT_SHARPEN] = true
            transformationList.add(TransformationMapping.EFFECT_SHARPEN)
        } else {
            transformationMap[TransformationMapping.EFFECT_SHARPEN] = value
            transformationList.add("${TransformationMapping.EFFECT_SHARPEN}-${value}")
        }
        return this
    }

    /**
     * Set the parameters to fetch the video in an adaptive streaming format.
     * @param format The desired streaming format to be fetched (HLS or DASH).
     * @param resolutions The list of video resolutions to be made available to choose from during video streaming.
     * @return the current ImagekitUrlConstructor object.
     */
    fun setAdaptiveStreaming(
        format: StreamingFormat,
        resolutions: List<Int>
    ): ImagekitUrlBuilder {
        streamingParam["ik-master"] = format.extension
        transformationMap[TransformationMapping.STREAMING_RESOLUTION] = resolutions
        transformationList.add("${TransformationMapping.STREAMING_RESOLUTION}-${resolutions.joinToString(separator = "_")}")
        return this
    }

    /**
     * Add the raw transformation options passed as a string to the transformations list.
     * @param params The string containing the comma-separated transformation parameters.
     * @return the current ImagekitUrlConstructor object.
     */
    fun raw(params: String): ImagekitUrlBuilder {
        rawParams = params
        return this
    }

    /**
     * Unsharp masking (USM) is an image sharpening technique.
     * Method allows you to apply and control unsharp mask on your images. The amount of sharpening can be controlled
     * by varying the 4 parameters - radius, sigma, amount and threshold. This results in perceptually better images
     * compared to just e-sharpen.
     * @param radius Possible values include positive floating point values.
     * @param sigma Possible values include positive floating point values.
     * @param amount Possible values include positive floating point values.
     * @param threshold Possible values include positive floating point values.
     * @return the current ImagekitUrlConstructor object.
     */
    fun effectUSM(
        radius: Float,
        sigma: Float,
        amount: Float,
        threshold: Float
    ): ImagekitUrlBuilder {
        @Suppress("ImplicitDefaultLocale") // no translations here
        val s = String.format(
            "${TransformationMapping.EFFECT_USM}-%.2f-%.2f-%.2f-%.2f",
            radius,
            sigma,
            amount,
            threshold
        )
        transformationMap[TransformationMapping.EFFECT_USM] = s
        transformationList.add(s)
        return this
    }

    /**
     * Method used to automatically enhance the contrast of the image by using the full range of intensity values
     * that a particular image format allows. This basically means that the lighter portions of the image would become
     * even lighter and the darker portions of the image would become even brighter, thereby enhancing the contrast.
     * @param flag
     * @return the current ImagekitUrlConstructor object.
     */
    fun effectContrast(flag: Boolean): ImagekitUrlBuilder {
        transformationMap[TransformationMapping.EFFECT_CONTRAST] = flag

        if (flag) {
            transformationList.add(TransformationMapping.EFFECT_CONTRAST)
        }

        return this
    }

    /**
     * Method to turn an image into its grayscale version.
     * @param flag Accepts boolean value of either true or false. Default value is false.
     * @return the current ImagekitUrlConstructor object.
     */
    fun effectGray(flag: Boolean): ImagekitUrlBuilder {
        transformationMap[TransformationMapping.EFFECT_GRAY] = flag

        if (flag)
            transformationList.add(TransformationMapping.EFFECT_GRAY)

        return this
    }

    /**
     * Method to retrieve the original image
     * @return the current ImagekitUrlConstructor
     */
    fun original(flag: Boolean): ImagekitUrlBuilder {
        transformationMap[TransformationMapping.ORIGINAL] = flag
        if (flag)
            transformationList.add("${TransformationMapping.ORIGINAL}-true")

        return this
    }

    /**
     * Some transformations are dependent on the sequence in which they are carried out.
     * Method used to add sequence dependent steps in a transform chain to obtain predictable results.
     * @return the current ImagekitUrlConstructor object.
     */
    fun chainTransformation(): ImagekitUrlBuilder {
        transformationMap[TransformationMapping.TRANSFORMATION_STEP] = ":"
        transformationList.add(":")

        return this
    }

    /**
     * Method allows adding custom transformations to the image.
     * @return the current ImagekitUrlConstructor object.
     */
    fun addCustomTransformation(key: String, value: String): ImagekitUrlBuilder {
        transformationMap[key] = value
        transformationList.add("${key}-${value}")
        return this
    }

    /**
     * Method allows adding custom Query Parameter to the image.
     * @return the current ImagekitUrlConstructor object.
     */
    fun addCustomQueryParameter(key: String, value: String): ImagekitUrlBuilder {
        queryParams[key] = value
        return this
    }

    /**
     * Method allows adding custom Query Parameters to the image.
     * @return the current ImagekitUrlConstructor object.
     */
    fun addCustomQueryParameter(params: HashMap<String, String>): ImagekitUrlBuilder {
        params.forEach { (key, value) -> queryParams[key] = value }
        return this
    }

    /**
     * Used to create the url using the transformations specified before invoking this method.
     * @return the Url used to fetch an image after applying the specified transformations.
     */
    @Suppress("NestedBlockDepth") // simplify
    fun create(): String {
        var urlPath = ""

        if (rawParams != null) {
            transformationList.addAll(rawParams?.split(',').orEmpty())
        }

        if (transformationList.isNotEmpty()) {
            val transforms = transformationList.joinToString(",") { transformation -> transformation }
                .replace(",:,", ":")
            if (isSource) {
                transformationPosition = TransformationPosition.QUERY
                if (urlPath.contains("?tr=")) {
                    urlPath = urlPath.substring(0, urlPath.indexOf("?tr="))
                }
                if (urlPath.contains("/tr:")) {
                    urlPath = urlPath.replace(
                        urlPath.substring(urlPath.indexOf("/tr:"), urlPath.lastIndexOf("/")),
                        ""
                    )
                }
                if (streamingParam.containsKey("ik-master")) {
                    urlPath = urlPath.plus("/ik-master.${streamingParam["ik-master"]}")
                }
                queryParams["tr"] = transforms
            } else {
                if (transformationPosition == TransformationPosition.PATH) {
                    urlPath = "${addPathParams(urlPath)}/${path}"
                    if (streamingParam.containsKey("ik-master")) {
                        urlPath = urlPath.plus("/ik-master.${streamingParam["ik-master"]}")
                    }
                }
                if (transformationPosition == TransformationPosition.QUERY) {
                    urlPath = "${urlPath}/${path}"
                    if (streamingParam.containsKey("ik-master")) {
                        urlPath = urlPath.plus("/ik-master.${streamingParam["ik-master"]}")
                    }
                    queryParams["tr"] = transforms
                }
            }
        } else {
            if (!isSource) {
                urlPath = "${urlPath}/${path}"
            }
        }

        val queryParamsStringBuilder = StringBuilder()
        if (queryParamsStringBuilder.isNotEmpty())
            queryParamsStringBuilder.append('&')

        queryParamsStringBuilder.append(queryParams.map { (key, value) ->
            "${key}=${value}"
        }.joinToString("&"))

        return """$baseUrl/$endpoint/${urlPath.replace("=", "%3D")}$imageAssetId${if (queryParamsStringBuilder.isNotEmpty()) "?" else ""}$queryParamsStringBuilder"""
    }

    private fun addPathParams(endpoint: String): String {
        var url = "${endpoint}/tr:"
        for (t in 0 until transformationList.size) {
            url = when {
                transformationList[t].contentEquals(":") -> "${url}${transformationList[t]}"
                url.endsWith(":") -> "${url}${transformationList[t]}"
                else -> "${url},${transformationList[t]}"
            }
        }

        return url
    }
}

enum class TransformationPosition {
    PATH,
    QUERY
}

enum class CropType(val value: String) {
    MAINTAIN_RATIO("maintain_ratio"),
    FORCE("force"),
    AT_LEAST("at_least"),
    AT_MAX("at_max")
}

enum class CropMode(val value: String) {
    RESIZE("resize"),
    EXTRACT("extract"),
    PAD_EXTRACT("pad_extract"),
    PAD_RESIZE("pad_resize")
}

enum class FocusType(val value: String) {
    CENTER("center"),
    TOP("top"),
    LEFT("left"),
    BOTTOM("bottom"),
    RIGHT("right"),
    TOP_LEFT("top_left"),
    TOP_RIGHT("top_right"),
    BOTTOM_LEFT("bottom_left"),
    BOTTOM_RIGHT("bottom_right"),
    AUTO("auto")
}

enum class Format(val value: String) {
    AUTO("auto"),
    WEBP("webp"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png")
}

enum class Rotation(val value: String) {
    AUTO("auto"),
    VALUE_0("0"),
    VALUE_90("90"),
    VALUE_180("180"),
    VALUE_270("270"),
    VALUE_360("360")
}

enum class StreamingFormat(val extension: String) {
    HLS("m3u8"),
    DASH("mpd")
}

internal object TransformationMapping {
    const val HEIGHT = "h"
    const val WIDTH = "w"
    const val ASPECT_RATIO = "ar"
    const val QUALITY = "q"
    const val CROP = "c"
    const val CROP_MODE = "cm"
    const val X = "x"
    const val Y = "y"
    const val FOCUS = "fo"
    const val FORMAT = "f"
    const val RADIUS = "r"
    const val BACKGROUND = "bg"
    const val BORDER = "b"
    const val ROTATION = "rt"
    const val BLUR = "bl"
    const val NAMED = "n"
    const val PROGRESSIVE = "pr"
    const val LOSSLESS = "lo"
    const val TRIM = "t"
    const val METADATA = "md"
    const val COLOR_PROFILE = "cp"
    const val DEFAULT_IMAGE = "di"
    const val DPR = "dpr"
    const val EFFECT_SHARPEN = "e-sharpen"
    const val EFFECT_USM = "e-usm"
    const val EFFECT_CONTRAST = "e-contrast"
    const val EFFECT_GRAY = "e-grayscale"
    const val ORIGINAL = "orig"
    const val TRANSFORMATION_STEP = ":"
    const val STREAMING_RESOLUTION = "sr"
}