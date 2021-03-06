package net.yet.yetlibdemo

import android.content.Context
import android.widget.LinearLayout
import yet.ext.putBool
import yet.ui.activities.Pages
import yet.ui.activities.openPage
import yet.ui.page.TitledPage

/**
 * Created by entaoyang@163.com on 2016-10-07.
 */
typealias OnResult = (String) -> Unit

class MainPage : TitledPage() {

	fun test1(r: OnResult) {
		r.invoke("Hello")
	}


	override fun onCreateContent(context: Context, contentView: LinearLayout) {
		titleBar.title = "Test"

		titleBar.addAction("test").onAction {
			test()
		}

	}

	fun test() {
		openPage(LoginPage()) {
			putBool(Pages.FULL_SCREEN, true)
		}

	}


}