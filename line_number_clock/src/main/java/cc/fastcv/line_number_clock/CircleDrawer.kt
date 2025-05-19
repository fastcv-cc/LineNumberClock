package cc.fastcv.line_number_clock

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

//一个圆单元参数
class CircleDrawer {

    var x: Float = 0.0f
    var y: Float = 0.0f
    var radius: Float = 0.0f
    var paint: Paint? = null


    //绘制基础圆
    fun drawBaseCircle(canvas: Canvas) {
        paint?.let {
            //画圆
            //TODO 定义颜色
            it.color = Color.parseColor("#F4EBEB")
            canvas.drawCircle(x, y, radius, it)
            //画小白点
            it.color = Color.WHITE
            canvas.drawCircle(x, y, it.strokeWidth / 2, it)
        }
    }

    //绘制圆中心点
    fun drawBlackPoint(canvas: Canvas) {
        paint?.let {
            //画小黑点
            it.color = Color.BLACK
            canvas.drawCircle(x, y, it.strokeWidth / 2, it)
        }
    }


    fun drawByCircleDrawParam(
        canvas: Canvas,
        param: CircleDrawParam
    ) {
        paint?.let {
            //画小黑点
            it.color = Color.BLACK
            it.alpha = param.line1Alpha
            canvas.drawCircle(x, y, it.strokeWidth / 2, it)
            //画线1
            canvas.save()
            canvas.translate(x, y)
            canvas.rotate(param.line1Angle)
            it.alpha = param.line1Alpha
            canvas.drawLine(0f, 0f, radius, 0f, it)
            canvas.restore()
            canvas.save()
            canvas.translate(x, y)
            canvas.rotate(param.line2Angle)
            it.alpha = param.line2Alpha
            canvas.drawLine(0f, 0f, radius, 0f, it)
            canvas.restore()
        }
    }

}