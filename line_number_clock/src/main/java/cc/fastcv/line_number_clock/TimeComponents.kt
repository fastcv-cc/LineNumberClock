package cc.fastcv.line_number_clock

import android.graphics.Canvas

class TimeComponents(private val array: Array<Array<CircleDrawer>>) :
    AbsComponents() {
    override val type: ComponentsType
        get() = ComponentsType.COMPONENTS_TYPE_TIME

    private var lastLeftNumber: AbsNumberDrawParam? = null
    private var newLeftNumber: AbsNumberDrawParam? = null
    private var lastRightNumber: AbsNumberDrawParam? = null
    private var newRightNumber: AbsNumberDrawParam? = null

    fun setNumber(number: Int) {
        newLeftNumber = transformNumber(number / 10)
        newRightNumber = transformNumber(number % 10)
    }

    override fun draw(canvas: Canvas, progress: Float) {
        if (progress == 1f) {
            lastLeftNumber = newLeftNumber
            lastRightNumber = newRightNumber
        }
        val leftTempNumber: AbsNumberDrawParam =
            lastLeftNumber!!.transition(newLeftNumber!!, progress)
        val rightTempNumber: AbsNumberDrawParam =
            lastRightNumber!!.transition(newRightNumber!!, progress)
        val leftNumber = NumberProxy(
            arrayOf(
                arrayOf(array[0][0], array[0][1]),
                arrayOf(array[1][0], array[1][1]),
                arrayOf(array[2][0], array[2][1]),
            ), leftTempNumber
        )
        leftNumber.draw(canvas)
        val rightNumber = NumberProxy(
            arrayOf(
                arrayOf(array[0][2], array[0][3]),
                arrayOf(array[1][2], array[1][3]),
                arrayOf(array[2][2], array[2][3]),
            ), rightTempNumber
        )
        rightNumber.draw(canvas)

    }

    private fun transformNumber(i: Int): AbsNumberDrawParam? {
        return when (i) {
            0 -> Number0()
            1 -> Number1()
            2 -> Number2()
            3 -> Number3()
            4 -> Number4()
            5 -> Number5()
            6 -> Number6()
            7 -> Number7()
            8 -> Number8()
            9 -> Number9()
            else -> null
        }
    }
}