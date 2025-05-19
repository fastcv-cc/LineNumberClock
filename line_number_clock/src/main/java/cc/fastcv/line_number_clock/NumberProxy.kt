package cc.fastcv.line_number_clock

import android.graphics.Canvas

/**
 * 指的是数字时钟中的一个数字单位
 * 从设计图中可以看出 一个数字单位 由6个圆点组成
 * 然后需要具体数字的绘制参数
 * @param array 传入的6个圆点信息数组
 * @param number 当前数字图形（绘制）参数
 */
class NumberProxy(
    private val array: Array<Array<CircleDrawer>>,
    private val number: AbsNumberDrawParam
) {

    //绘制数字
    fun draw(canvas: Canvas) {
        for ((index, minimalUhrCircles) in array.withIndex()) {
            for ((index1, minimalUhrCircle) in minimalUhrCircles.withIndex()) {
                minimalUhrCircle.drawBaseCircle(canvas)
                minimalUhrCircle.drawByCircleDrawParam(canvas, number.params[index * 2 + index1])
            }
        }
    }

}