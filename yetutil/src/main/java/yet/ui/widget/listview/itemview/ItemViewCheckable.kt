package yet.ui.widget.listview.itemview

import android.widget.Checkable

/**
 * Created by entaoyang@163.com on 16/3/13.
 */

interface ItemViewCheckable : Checkable {

    var isCheckModel: Boolean

    fun setCheckboxMarginRight(dp: Int)
}
