package yet.ui.res

import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import yet.theme.Colors
import yet.ui.ext.dp

/**
 * TODO 阴影
 */
object Shapes {
	open class LineOption {
		//px
		var strokeWidth: Int = 1
		var strokeColor: Int = Colors.BLACK
	}

	open class RectOption : LineOption() {
		//null则不填充
		var fillColor: Int? = null
		//0则是直角, px
		var corner: Int = 0

		var width: Int = 0
		var height: Int = 0

		fun size(wDp: Int, hDp: Int = wDp) {
			width = dp(wDp)
			height = dp(hDp)
		}
	}

	class OvalOption : RectOption()

	fun line(strokeWidthDp: Int, strokeColor: Int): GradientDrawable {
		return line {
			this.strokeWidth = dp(strokeWidthDp)
			this.strokeColor = strokeColor
		}
	}

	fun line(block: LineOption.() -> Unit): GradientDrawable {
		val lo = LineOption()
		lo.block()
		val gd = GradientDrawable()
		gd.shape = GradientDrawable.LINE
		gd.setStroke(lo.strokeWidth, lo.strokeColor)
		return gd
	}


	fun rect(block: RectOption.() -> Unit): Drawable {
		val ro = RectOption()
		ro.block()
		val gd = GradientDrawable()
		gd.shape = GradientDrawable.RECTANGLE
		if (ro.corner > 0) {
			gd.cornerRadius = ro.corner.toFloat()
		}
		if (ro.fillColor != null) {
			gd.setColor(ro.fillColor!!)
		}
		if (ro.strokeWidth > 0) {
			gd.setStroke(ro.strokeWidth, ro.strokeColor)
		}
		if (ro.width > 0 && ro.height > 0) {
			gd.setSize(ro.width, ro.height)
		}
		return gd
	}

	fun oval(block: OvalOption.() -> Unit): GradientDrawable {
		val oo = OvalOption()
		oo.block()
		val drawable = GradientDrawable()
		drawable.shape = GradientDrawable.OVAL
		if (oo.fillColor != null) {
			drawable.setColor(oo.fillColor!!)
		}
		if (oo.strokeWidth > 0) {
			drawable.setStroke(oo.strokeWidth, oo.strokeColor)
		}
		if (oo.width > 0 && oo.height > 0) {
			drawable.setSize(oo.width, oo.height)
		}
		return drawable
	}


}
