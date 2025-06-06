package cc.fastcv.line_number_clock

/**
 * 数字绘制参数
 * 指代每个数据默认的绘制参数
 * 每个数字由6个圆点组成
 */
abstract class AbsNumberDrawParam {
    abstract val params: Array<CircleDrawParam>
}