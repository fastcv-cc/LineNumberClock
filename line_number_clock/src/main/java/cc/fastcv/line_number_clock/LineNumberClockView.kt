package cc.fastcv.line_number_clock

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import java.util.Calendar

class LineNumberClockView : View, DefaultLifecycleObserver {

    constructor(context: Context?) : super(context) {
        initParams(context, null)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initParams(context, attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initParams(context, attrs)
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        initParams(context, attrs)
    }

    private val manager = ComponentsManager()

    private fun initParams(context: Context?, attrs: AttributeSet?) {
        if (context == null) {
            return
        }
        manager.initConfigureInfo(context, attrs)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        //计算最小宽高值
        manager.initComponents(w, h)
    }

    private var progress = 999

    private val handler = Handler(Looper.getMainLooper())

    private val showRunnable = Runnable {
        Log.d("LineNumberClockView", "System.currentTimeMillis() : ${System.currentTimeMillis()}")
        showInAnim()
        showNext()
    }

    private fun showNext() {
        handler.postDelayed(showRunnable, 1000L - System.currentTimeMillis() % 1000L)
    }

    fun bindLifecycle(lifecycle: Lifecycle) {
        lifecycle.addObserver(this)
    }

    private fun showInAnim() {
        progress = 0
        val anim = ValueAnimator.ofInt(0, 999).apply {
            addUpdateListener {
                progress = it.animatedValue as Int
                invalidate()
            }
            duration = 650L
        }
        anim.start()
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        postDelayed(showRunnable, 1000L - System.currentTimeMillis() % 1000L)
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        handler.removeCallbacks(showRunnable)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        manager.draw(canvas, progress)
    }
}