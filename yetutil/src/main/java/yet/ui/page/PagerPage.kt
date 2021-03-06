package yet.ui.page

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import yet.ui.ext.*
import yet.ui.widget.*
import java.util.*

/**
 * Created by yangentao on 16/4/10.
 */
open class PagerPage : TitledPage() {
	lateinit var viewPager: AbstractViewPager<String>
	lateinit var switchView: SwitchView
	var items: ArrayList<String> = ArrayList()
	var views: ArrayList<View> = ArrayList()

	override fun onCreateContent(context: Context, contentView: LinearLayout) {
		switchView = object : SwitchView(context) {
			override fun onSelectChanged(index: Int, itemName: String) {
				viewPager.setCurrentItem(index, true)
			}

			override fun onConfigItem(itemView: SwitchItemView) {
				this@PagerPage.onConfigItem(itemView)
			}
		}
		switchView.setItems(items)
		contentView.addViewParam(switchView) { widthFill().heightDp(45).gravityCenter() }

		viewPager = object : AbstractViewPager<String>(context) {

			override fun newView(context: Context, position: Int, item: String): View {
				return views[position]
			}

			override fun onPageSelected(position: Int, item: String) {
				switchView.select(item, false)
			}
		}
		contentView.addView(viewPager, linearParam().widthFill().heightDp(0).weight_(1))
		viewPager.setItems(items)
	}

	fun select(key: String) {
		switchView.select(key, true)
	}

	fun select(index: Int) {
		switchView.select(items[index], true)
	}

	open fun onConfigItem(itemView: SwitchItemView) {

	}

	fun setItems(labels: Array<String>, views: Array<View>) {
		this.views.clear()
		this.items.clear()
		for (s in labels) {
			this.items.add(s)
		}
		for (v in views) {
			this.views.add(v)
		}
		switchView.setItems(items)
		viewPager.setItems(items)
	}

	fun setItems(labels: List<String>, views: List<View>) {
		this.items.clear()
		this.items.addAll(labels)
		this.views.clear()
		this.views.addAll(views)
		switchView.setItems(items)
		viewPager.setItems(items)
	}
}
