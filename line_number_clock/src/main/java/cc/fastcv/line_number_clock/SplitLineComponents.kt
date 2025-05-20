package cc.fastcv.line_number_clock

import android.graphics.Canvas

/**
 * 数字分割线 由3个圆点组成
 */
class SplitLineComponents(
    private val array: Array<CircleDrawer>,
) : AbsComponents() {

    override fun draw(canvas: Canvas) {
        for ((index, circle) in array.withIndex()) {
            circle.drawBaseCircle(canvas)
            if (index == 1) {
                circle.drawBlackPoint(canvas)
            }
        }
    }

    override val type: ComponentsType
        get() = ComponentsType.COMPONENTS_SPLIT_LINE

}