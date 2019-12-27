package com.app.douban_movie.ui.adapters.decoration

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.text.TextPaint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.app.douban_movie.R
import com.app.douban_movie.utils.Logger


class HeaderDecoration(
    dataList: List<HeaderBean>,
    context: Context,
    decorationCallback: DecorationCallback
) : RecyclerView.ItemDecoration() {
    private val dataList: List<HeaderBean>
    private val callback: DecorationCallback
    private val textPaint: TextPaint
    private val paint: Paint
    private val topGap: Int
    private val alignBottom: Int
    private val fontMetrics: Paint.FontMetrics

    init {
        val res = context.resources
        this.dataList = dataList
        callback = decorationCallback
        // 设置悬浮栏的画笔 Paint
        paint = Paint()
        paint.color = res.getColor(R.color.light_grey)
        // 设置悬浮栏中文本的画笔
        textPaint = TextPaint()
        textPaint.isAntiAlias = true
        textPaint.textSize = Math.round(res.displayMetrics.density * 14).toFloat();
        textPaint.color = res.getColor(R.color.black)
        textPaint.textAlign = Paint.Align.LEFT
        fontMetrics = Paint.FontMetrics()
        // 决定悬浮栏的高度
        topGap = res.getDimensionPixelSize(R.dimen.sticky_height)
        // 决定文本的显示位置
        alignBottom = res.getDimensionPixelSize(R.dimen.sticky_font_align)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        val groupId = callback.getGroupId(position)
        if (groupId == "-1") return
        if (position == 0 || isFirstInGroup(position)) {
            outRect.top = topGap
            if (dataList[position].name == "") {
                outRect.top = 0
            }
        } else {
            outRect.top = 0
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val view = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(view)
            val groupId = callback.getGroupId(position)
            if (groupId == "-1") return
            val textLine = callback.getGroupFirstLine(position)
            if (textLine.isNullOrEmpty()) {
                val top = view.top.toFloat()
                val bottom = view.top.toFloat()
                c.drawRect(left.toFloat(), top, right.toFloat(), bottom, paint)
                return
            } else {
                if (position == 0 || isFirstInGroup(position)) {
                    val top = view.top - topGap.toFloat()
                    val bottom = view.top.toFloat()
                    // 绘制悬浮栏
                    c.drawRect(left.toFloat(), top, right.toFloat(), bottom, paint)
                    // 绘制文本
                    c.drawText(textLine, left.toFloat(), bottom, textPaint)
                }
            }
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        val itemCount = state.itemCount
        val childCount = parent.childCount
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        val lineHeight = textPaint.textSize + fontMetrics.descent
        var preGroupId = ""
        var groupId = "-1"
        for (i in 0 until childCount) {
            val view = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(view)
            preGroupId = groupId
            groupId = callback.getGroupId(position)
            if (groupId == "-1" || groupId == preGroupId) continue
            val textLine = callback.getGroupFirstLine(position)
            if (textLine.isNullOrEmpty()) continue
            val viewBottom = view.bottom
            var textY = Math.max(topGap, view.top).toFloat()
            // 下一个和当前不一样，移动当前
            if (position + 1 < itemCount) {
                val nextGroupId = callback.getGroupId(position + 1)
                // 组内最后一个view进入header
                if (nextGroupId != groupId && viewBottom < textY) {
                    textY = viewBottom.toFloat()
                }
            }
            //textY - topGap决定了悬浮栏绘制的高度和位置
            c.drawRect(left.toFloat(), textY - topGap, right.toFloat(), textY, paint)
            //left+alignBottom 决定了文本往左偏移的多少（加-->向左移）
            //textY-alignBottom 决定了文本往右偏移的多少 (减-->向上移)
            c.drawText(textLine, left + alignBottom.toFloat(), textY - alignBottom, textPaint)
        }
    }

    /**
     * 判断是否为第一个位置
     */
    private fun isFirstInGroup(position: Int): Boolean {
        return if (position == 0) {
            true
        } else {
            val prevGroupId = callback.getGroupId(position - 1)
            val groupId = callback.getGroupId(position)
            prevGroupId != groupId
        }
    }


    //定义一个借口方便外界的调用
    interface DecorationCallback {
        fun getGroupId(position: Int): String
        fun getGroupFirstLine(position: Int): String
    }

    data class HeaderBean(val name: String)
}



