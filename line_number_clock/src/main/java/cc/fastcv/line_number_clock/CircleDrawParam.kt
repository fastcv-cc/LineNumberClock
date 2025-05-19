package cc.fastcv.line_number_clock

/**
 * 圆绘制参数
 * angle: -180 ~ 180
 * alpha: 0 ~ 255
 */
data class CircleDrawParam(
    var line1Angle: Float,
    var line1Alpha: Int,
    var line2Angle: Float,
    var line2Alpha: Int
) {
    //参数变更差计算
    fun transition(newParam: CircleDrawParam, progress: Float): CircleDrawParam {
        val tempLine1Angle: Float = line1Angle + (newParam.line1Angle - line1Angle) * progress
        val tempLine1Alpha: Int =
            (line1Alpha + (newParam.line1Alpha - line1Alpha) * progress).toInt()
        val tempLine2Angle: Float = line2Angle + (newParam.line2Angle - line2Angle) * progress
        val tempLine2Alpha: Int =
            (line2Alpha + (newParam.line2Alpha - line2Alpha) * progress).toInt()
        return CircleDrawParam(tempLine1Angle, tempLine1Alpha, tempLine2Angle, tempLine2Alpha)
    }
}