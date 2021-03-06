package yet.ui.ext

import android.widget.Switch
import yet.theme.Colors
import yet.ui.res.ImageStated
import yet.ui.res.Shapes
import yet.util.app.OS

/**
 * Created by entaoyang@163.com on 16/6/3.
 */


fun <T : Switch> T.themeSwitch(): T {
	if (OS.GE50) {
		return this
	}
	if (OS.API >= 16) {
		this.thumbTextPadding = dp(10)
	}

	val w1 = 30
	val h1 = 30

	val d1 = Shapes.rect {
		corner = dp(h1 / 2)
		fillColor = 0xFFCCCCCC.toInt()
		size(w1, h1)
	}
	val d2 = Shapes.rect {
		corner = dp(h1 / 2)
		fillColor = 0xFF4A90E2.toInt()
		size(w1, h1)
	}
	val d3 = Shapes.rect {
		corner = dp(h1 / 2)
		fillColor = Colors.LightGray
		strokeWidth = dp(1)
		strokeColor = Colors.GrayMajor
		size(w1, h1)
	}

	this.thumbDrawable = ImageStated(d1).checked(d2).enabled(d3, false).value


	val w = 50
	val h = 30
	val dd1 = Shapes.rect {
		corner = dp(h / 2)
		fillColor = Colors.WHITE
		strokeWidth = dp(1)
		strokeColor = Colors.LightGray
		size(w, h)
	}
	val dd2 = Shapes.rect {
		corner = dp(h / 2)
		fillColor = Colors.Safe
		size(w, h)
	}
	val dd3 = Shapes.rect {
		corner = dp(h / 2)
		fillColor = Colors.LightGray
		strokeWidth = dp(1)
		strokeColor = Colors.WHITE
		size(w, h)
	}
	this.trackDrawable = ImageStated(dd1).checked(dd2).enabled(dd3, false).value
	return this
}
