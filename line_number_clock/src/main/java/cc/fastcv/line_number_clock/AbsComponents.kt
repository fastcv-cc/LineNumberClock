package cc.fastcv.line_number_clock

import android.graphics.Canvas

abstract class AbsComponents {

    abstract val type: ComponentsType

    abstract fun draw(canvas: Canvas, progress: Float)

}