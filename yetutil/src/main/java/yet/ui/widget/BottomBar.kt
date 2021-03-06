package yet.ui.widget

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import yet.theme.Colors
import yet.theme.IconSize
import yet.ui.ext.*
import yet.ui.res.ColorStated
import yet.ui.res.sized
import yet.ui.viewcreator.createImageView
import yet.ui.viewcreator.createTextViewC
import yet.util.log.logd
import yet.util.log.xlog
import java.util.*

/**
 * Created by entaoyang@163.com on 16/3/13.
 */


class BottomBar(context: Context) : TablePanel(context), IActionModeSupport {
	override var modeName: String = ""
	override var allActions: ArrayList<Action> = ArrayList<Action>()
	private val actionBack = ArrayList<Action>()

	override fun onResotreDefault() {
		allActions.clear()
		allActions.addAll(actionBack)
	}

	override fun onBackupDefault() {
		actionBack.addAll(allActions)
	}

	override fun onCleanData() {
		allActions.clear()
	}

	override val actionPanelView: View
		get() = this

	//颜色相关
	var TEXT_COLOR = Colors.TextColorMinor
	var TEXT_PRESSED = Colors.Fade
	var TEXT_RISK = Color.WHITE
	var BG_COLOR = Color.WHITE
	var BG_PRESSED = Colors.Fade
	var BG_RISK = Colors.Risk
	var LINE_COLOR = Color.LTGRAY//可以改变

	val HEIGHT = 50
	val IMG_SIZE = IconSize.Normal


	var onAction: (BottomBar, Action) -> Unit = {
		bar, action ->
	}

	private val onClickListener = View.OnClickListener { v ->
		val a = v.tag as Action
		a.onAction(a)
		onAction.invoke(this@BottomBar, a)
	}

//	init {
//		linearParam().widthFill().heightWrap().set(this)
//	}


	override fun onRebuild() {
		this.removeAllViews()
		this.setRowHeight(HEIGHT)
		this.backColor(Colors.PageGray)
		this.padding(0, 1, 0, 0)

		var needVLine = true
		val items = visibleAcitons
		var visibleCount = items.size
		for (action in items) {
			if (action.icon != null && action.label.isNotEmpty()) {
				needVLine = false
			}
		}
		this.setHorSpace(if (needVLine) 1 else 0)
		this.addRow()
		allActions.forEach {
			logd("Action: ", it.tag, it.hidden)
		}
		logd("重建: ", items.size)
		for (action in items) {
			logd("Action构建", action.tag)
			val view: View = if (action.label.isEmpty()) {
				if (action.icon == null) {
					xlog.e("neithor Label OR icon NOT SET")
					throw IllegalArgumentException("neithor Label OR icon NOT SET")
				} else {//only  icon
					val iv = context.createImageView()
					iv.setImageDrawable(action.icon?.sized(IMG_SIZE))
					iv.scaleType = ImageView.ScaleType.CENTER_INSIDE
					iv.setBackgroundDrawable(ColorStated(BG_COLOR).pressed(BG_PRESSED).value)
					iv
				}
			} else {//label
				val tv = context.createTextViewC()
				tv.gravityCenter().padding(1, 2, 1, 1).text(action.label)
				if (action.risk) {
					tv.backDrawable(ColorStated(BG_RISK).pressed(BG_PRESSED).value).textColor(TEXT_RISK)
				} else {
					tv.backDrawable(ColorStated(BG_COLOR).pressed(BG_PRESSED).value).textColor(TEXT_COLOR, TEXT_PRESSED)
				}
				if (action.icon != null) {
					action.icon?.setBounds(0, 0, dp(IMG_SIZE), dp(IMG_SIZE))
					tv.compoundDrawablePadding = 0
					tv.setCompoundDrawables(null, action.icon, null, null)
					tv.textSizeTiny()
				} else {
					tv.textSizeNormal()
				}
				tv
			}
			view.tag = action
			view.setOnClickListener(onClickListener)
			this.addItemView(view)

			val addedCount = this.itemViewCount
			if (visibleCount <= 4) {
				//1 row
			} else if (visibleCount < 8) {
				val firstRowCols = visibleCount / 2
				if (addedCount == firstRowCols) {
					this.addRow()
				}
			} else {
				val mod = visibleCount % 4
				if (addedCount % 4 == mod && visibleCount != addedCount) {
					this.addRow()
				}
			}
		}
	}
}
