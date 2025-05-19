package cc.fastcv.line_number_clock

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.icu.util.Calendar
import android.util.AttributeSet
import android.util.TypedValue

class ComponentsManager {

    //小圆半径
    private var radius = 0f

    //圆之间的间距
    private var circleMargin = 0f

    //原点半径
    private var pointRadius = 0f

    //宽高比
    private var aspectRatio = 0f

    //使用的宽高
    private var useWidth = 0f
    private var useHeight = 0f

    //小圆的二维数组
    private val circleArray = Array(3) {
        return@Array Array(14) {
            CircleDrawer()
        }
    }

    //画线的画笔
    private val paint = Paint().apply {
        setColor(Color.BLACK)
        style = Paint.Style.FILL
    }

    private lateinit var hourComponents: TimeComponents
    private lateinit var split1Components: SplitLineComponents
    private lateinit var minuteComponents: TimeComponents
    private lateinit var split2Components: SplitLineComponents
    private lateinit var secondComponents: TimeComponents

    fun initConfigureInfo(context: Context, attrs: AttributeSet?) {
        radius = dp2px(context, 20)
        circleMargin = dp2px(context, 4)
        pointRadius = dp2px(context, 2)
        paint.strokeWidth = circleMargin
        aspectRatio =
            (14 * radius * 2 + circleMargin * 15) * 1.0f / (3 * radius * 2 + circleMargin * 4)
    }

    fun initComponents(w: Int, h: Int) {
        val realAspectRatio = w * 1.0f / h
        if (realAspectRatio > aspectRatio) {
            //以高为基准
            useHeight = h * 1.0f
            useWidth = useHeight * aspectRatio
        } else {
            //以宽为基准
            useWidth = w * 1.0f
            useHeight = useWidth / aspectRatio
        }
        //重新计算圆半径
        radius = (useWidth - circleMargin * 15) / 14 / 2
        //以画布左上角为原点计算小圆的分布和分组
        countCirclePosition()
    }

    fun draw(canvas: Canvas, progress: Int) {
        val calendar: Calendar = Calendar.getInstance()
        val hour = calendar[Calendar.HOUR_OF_DAY]
        val minute = calendar[Calendar.MINUTE]
        val second = calendar[Calendar.SECOND]
        val drawProgress = (progress + 1) * 1f / 1000
        hourComponents.setNumber(hour)
        minuteComponents.setNumber(minute)
        secondComponents.setNumber(second)
        drawComponents(canvas, drawProgress)
    }

    private fun drawComponents(canvas: Canvas, drawProgress: Float) {
        hourComponents.draw(canvas, drawProgress)
        split1Components.draw(canvas, drawProgress)
        minuteComponents.draw(canvas, drawProgress)
        split2Components.draw(canvas, drawProgress)
        secondComponents.draw(canvas, drawProgress)
    }

    private fun countCirclePosition() {
        //根据属性计算所有圆的位置
        initAllCircleParamArray()
        initHourComponents()
        initSplit1Components()
        initMinuteComponents()
        initSplit2Components()
        initSecondComponents()
    }

    private fun initSecondComponents() {
        secondComponents = TimeComponents(
            arrayOf(
                arrayOf(
                    circleArray[0][10], circleArray[0][11], circleArray[0][12], circleArray[0][13]
                ),
                arrayOf(
                    circleArray[1][10], circleArray[1][11], circleArray[1][12], circleArray[1][13]
                ),
                arrayOf(
                    circleArray[2][10], circleArray[2][11], circleArray[2][12], circleArray[2][13]
                ),
            )
        )
    }

    private fun initSplit2Components() {
        split2Components = SplitLineComponents(
            arrayOf(
                circleArray[0][9],
                circleArray[1][9],
                circleArray[2][9],
            )
        )
    }

    private fun initMinuteComponents() {
        minuteComponents = TimeComponents(
            arrayOf(
                arrayOf(circleArray[0][5], circleArray[0][6], circleArray[0][7], circleArray[0][8]),
                arrayOf(circleArray[1][5], circleArray[1][6], circleArray[1][7], circleArray[1][8]),
                arrayOf(circleArray[2][5], circleArray[2][6], circleArray[2][7], circleArray[2][8]),
            )
        )
    }

    private fun initSplit1Components() {
        split1Components = SplitLineComponents(
            arrayOf(
                circleArray[0][4],
                circleArray[1][4],
                circleArray[2][4],
            )
        )
    }

    private fun initHourComponents() {
        hourComponents = TimeComponents(
            arrayOf(
                arrayOf(circleArray[0][0], circleArray[0][1], circleArray[0][2], circleArray[0][3]),
                arrayOf(circleArray[1][0], circleArray[1][1], circleArray[1][2], circleArray[1][3]),
                arrayOf(circleArray[2][0], circleArray[2][1], circleArray[2][2], circleArray[2][3]),
            )
        )
    }

    private fun initAllCircleParamArray() {
        var positionY: Float
        var positionX: Float
        for (i in 0 until 3) {
            positionY = (i + 1) * circleMargin + (1 + 2 * i) * radius * 1.0f
            for (i1 in 0 until 14) {
                positionX = (i1 + 1) * circleMargin + (1 + 2 * i1) * radius * 1.0f
                circleArray[i][i1].x = positionX
                circleArray[i][i1].y = positionY
                circleArray[i][i1].radius = radius
                circleArray[i][i1].paint = paint
            }
        }
    }

    private fun dp2px(context: Context, dpValue: Int): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP, dpValue.toFloat(), context.resources.displayMetrics
        )
    }
}