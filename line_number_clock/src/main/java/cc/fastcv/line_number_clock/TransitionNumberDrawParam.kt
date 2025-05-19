package cc.fastcv.line_number_clock

/**
 * 过渡数字绘制参数
 */
class TransitionNumberDrawParam(override val params: Array<CircleDrawParam>) : AbsNumberDrawParam()

//计算过渡数字绘制参数
fun AbsNumberDrawParam.transition(newNumber: AbsNumberDrawParam, progress: Float): AbsNumberDrawParam {
    val circleDrawParam0 = this.params[0].transition(newNumber.params[0],progress)
    val circleDrawParam1 = this.params[1].transition(newNumber.params[1],progress)
    val circleDrawParam2 = this.params[2].transition(newNumber.params[2],progress)
    val circleDrawParam3 = this.params[3].transition(newNumber.params[3],progress)
    val circleDrawParam4 = this.params[4].transition(newNumber.params[4],progress)
    val circleDrawParam5 = this.params[5].transition(newNumber.params[5],progress)
    return TransitionNumberDrawParam(
        arrayOf(
            circleDrawParam0,
            circleDrawParam1,
            circleDrawParam2,
            circleDrawParam3,
            circleDrawParam4,
            circleDrawParam5,
        )
    )
}