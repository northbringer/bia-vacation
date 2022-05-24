package ru.northbringer.bia_vacation.android.gant_view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.os.Parcelable
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.applyCanvas
import androidx.core.graphics.createBitmap
import androidx.core.graphics.withTranslation
import android.graphics.Bitmap
import androidx.core.content.res.ResourcesCompat
import kotlinx.datetime.*
import ru.northbringer.bia_vacation.android.R
import ru.northbringer.bia_vacation.diagramScreen.domain.usecase.Task

@SuppressLint("NewApi")
class GantView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    // region Paint
    // Для строк
    private val rowPaint = Paint().apply { style = Paint.Style.STROKE }

    private val separatorsPaint = Paint().apply {
        strokeWidth = resources.getDimension(R.dimen.gant_separator_width)
        color = ContextCompat.getColor(context, R.color.greySeparatorColor)
    }
    private val shadowPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.white)
    }

    private val daysPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = resources.getDimension(R.dimen.gant_period_name_text_size)
        color = ContextCompat.getColor(context, R.color.black)
    }
    private val monthsPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = resources.getDimension(R.dimen.gant_period_name_text_size)
        color = ContextCompat.getColor(context, R.color.greyMonthTextColor)
        typeface = ResourcesCompat.getFont(context, R.font.roboto_regular)
    }

    private val daysWhitePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = resources.getDimension(R.dimen.gant_period_name_text_size)
        color = ContextCompat.getColor(context, R.color.white)
    }

    private val vacationShapePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = ContextCompat.getColor(context, R.color.vacationBlue)
    }

    private val vacationInnerShapePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = ContextCompat.getColor(context, R.color.white)
    }

    private val vacationTextPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = resources.getDimension(R.dimen.gant_task_name)
        color = ContextCompat.getColor(context, R.color.greyTextColor)
    }

    private val monthPanelPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.white)
    }

    private val circlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.brand)
    }
    // endregion

    //region Цвета и размеры
    private val columnWidth = resources.getDimensionPixelSize(R.dimen.gant_day_width)

    private val rowHeight = resources.getDimensionPixelSize(R.dimen.gant_row_height)

    private val vacationCornerRadius = resources.getDimension(R.dimen.gant_vacation_corner_radius)

    private val vacationVerticalMargin =
        resources.getDimension(R.dimen.gant_vacation_vertical_margin)

    private val vacationInnerVerticalMargin =
        resources.getDimension(R.dimen.gant_vacation_inner_vertical_margin)

    private val vacationInnerHorizontalMargin =
        resources.getDimension(R.dimen.gant_vacation_inner_horizontal_margin)

    private val dayCircleRadius = resources.getDimension(R.dimen.gant_day_circle_radius)

    private val vacationTextHorizontalMargin =
        resources.getDimension(R.dimen.gant_task_text_horizontal_margin)

    private val monthWidth = resources.getDimensionPixelSize(R.dimen.gant_month_width)

    private val contentWidth: Int
        get() = columnWidth * periods.getValue(periodType).size + monthWidth
    // endregion

    // region Вспомогательные сущности для рисования

    // Rect для рисования строк
    private val rowRect = Rect()
    private val monthRect = Rect()

    private lateinit var bitmap: Bitmap

    private val lastPoint = PointF()
    private var lastPointerId = 0
    private val transformations = Transformations()

    // endregion

    // region Время
    private var periodType = PeriodType.DAY
    private val periods = initPeriod()
    // endregion


    // region Измерение
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.UNSPECIFIED) {
            contentWidth
        } else {
            MeasureSpec.getSize(widthMeasureSpec)
        }

        val contentHeight = rowHeight * (getRowsCount()) + resources.getDimension(R.dimen.pager_height).toInt()//rowHeight * (tasks.size) + 1
        val heightSpecSize = MeasureSpec.getSize(heightMeasureSpec)
        val height = when (MeasureSpec.getMode(heightMeasureSpec)) {
            MeasureSpec.UNSPECIFIED -> contentHeight
            MeasureSpec.EXACTLY -> heightSpecSize
            MeasureSpec.AT_MOST -> contentHeight.coerceAtMost(heightSpecSize)
            else -> error("Unreachable")
        }

        setMeasuredDimension(width, height)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        rowRect.set(0, 0, w, rowHeight)
        monthRect.set(0, rowHeight, monthWidth, monthWidth * 10)
        updateTasksRects()

        bitmap = createBitmap(contentWidth, h).applyCanvas {
            drawPeriods()
            drawTasks()
        }
    }

    private fun updateTasksRects() {
        uiTasks.forEachIndexed { index, uiTask -> uiTask.updateRect(index) }
    }
    // endregion

    // region Рисование
    override fun onDraw(canvas: Canvas) = with(canvas) {
        // drawRows()
        withTranslation(transformations.translationX) {
            drawBitmap(bitmap, 0f, 0f, rowPaint)
        }
        drawTasksNames()
        drawMonths()
    }

    private fun Canvas.drawMonths() {
        drawRect(monthRect, monthPanelPaint)
        val list = listOf(
            "ЯНВАРЬ",
            "ФЕВРАЛЬ",
            "МАРТ",
            "АПРЕЛЬ",
            "МАЙ",
            "ИЮНЬ",
            "ИЮЛЬ",
            "АВГУСТ",
            "СЕНТЯБРЬ",
            "ОКТЯБРЬ",
            "НОЯБРЬ",
            "ДЕКАБРЬ"
        )

        val monthStartX = resources.getDimensionPixelOffset(R.dimen.gant_row_height - 15)
        var currentRow = 1
        drawLine(
            0f, rowHeight.toFloat(), contentWidth.toFloat(),
            rowHeight.toFloat(), separatorsPaint
        )
        list.forEachIndexed { index, month ->
            val tasksPerMonth = tasksMap[index + 1] ?: 0
            val monthStartY = rowHeight * (currentRow + 0.5f)
            drawText(month, monthStartX.toFloat(), monthStartY, monthsPaint)
            currentRow += if (tasksPerMonth == 0) 1 else tasksPerMonth
            val horizontalSeparatorY = rowHeight * (currentRow)
            drawLine(
                monthWidth.toFloat(), horizontalSeparatorY.toFloat(), contentWidth.toFloat(),
                horizontalSeparatorY.toFloat(), separatorsPaint
            )
        }
        drawLine(
            monthWidth.toFloat() - resources.getDimension(R.dimen.gant_separator_width),
            rowHeight.toFloat(),
            monthWidth.toFloat(),
            height.toFloat(),
            separatorsPaint
        )

        shadowPaint.setShadowLayer(8f, 0f, 4f, Color.GRAY)
        drawRect(
            0f,
            rowHeight.toFloat(),
            contentWidth.toFloat(),
            rowHeight.toFloat() + 1f,
            shadowPaint
        )
    }

    @SuppressLint("NewApi")
    private fun Canvas.drawPeriods() {
        val currentPeriods = periods.getValue(periodType)
        val nameY = daysPaint.getTextBaselineByCenter(rowHeight / 2f)
        // drawLine(monthsWidth.toFloat(), rowHeight.toFloat(), monthsWidth.toFloat(), height.toFloat(), separatorsPaint)
        currentPeriods.forEachIndexed { index, periodName ->
            Log.i("TAG", daysPaint.measureText(periodName).toString())
            val nameX =
                columnWidth * (index + 0.5f) - daysPaint.measureText(periodName) / 2 + monthWidth
            if (Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).dayOfMonth == index+1) {
                drawCircle(
                    monthWidth + columnWidth * (index + 0.5f),
                    rowHeight / 2f,
                    dayCircleRadius,
                    circlePaint
                )
                drawText(periodName, nameX, nameY, daysWhitePaint)
            } else {
                drawText(periodName, nameX, nameY, daysPaint)
            }

            val verticalSeparatorX = columnWidth * (index + 1f) + monthWidth
            drawLine(
                verticalSeparatorX,
                rowHeight.toFloat(),
                verticalSeparatorX,
                height.toFloat(),
                separatorsPaint
            )
        }
    }

    private fun Canvas.drawTasks() {
        var count = 0
        var currentRow = 0
        for (i in 1..12) {
            if (count < uiTasks.size && uiTasks[count].task.dateStart.monthNumber == i) {
                while (count < uiTasks.size && uiTasks[count].task.dateStart.monthNumber == i) {
                    val uiTask = uiTasks[count]
                    uiTask.updateRect(currentRow)
                    val taskRect = uiTask.rect
                    drawRoundRect(
                        taskRect,
                        vacationCornerRadius,
                        vacationCornerRadius,
                        vacationShapePaint
                    )
                    count++
                    currentRow++
                }

            } else {
                currentRow++
            }
        }
    }

    private fun Canvas.drawTasksNames() {
        val minTextLeft = vacationTextHorizontalMargin + monthWidth
        uiTasks.forEach { uiTask ->
            val taskRect = uiTask.rect
            val taskInnerRect = uiTask.innerRect
            val taskName = uiTask.task.name
            val taskWithDates =
                taskName + "   " + getDate(uiTask.task.dateStart) + " - " + getDate(uiTask.task.dateEnd)
            val untranslatedTextLeft = taskRect.left + vacationTextHorizontalMargin
            val textLeft =
                (untranslatedTextLeft + transformations.translationX).coerceAtLeast(minTextLeft)
            val maxTextWidth =
                taskRect.right + transformations.translationX - textLeft - vacationTextHorizontalMargin

            if (maxTextWidth > 0) {
                val textY = vacationTextPaint.getTextBaselineByCenter(taskRect.centerY())
                val charsCount =
                    vacationTextPaint.breakText(taskWithDates, true, maxTextWidth, null)
                taskInnerRect.set(
                    textLeft - vacationInnerHorizontalMargin,
                    taskRect.top + vacationInnerVerticalMargin,
                    textLeft + vacationTextPaint.measureText(
                        taskWithDates.substring(
                            0,
                            charsCount
                        )
                    ) + vacationInnerHorizontalMargin,
                    taskRect.bottom - vacationInnerVerticalMargin
                )
                drawRoundRect(
                    taskInnerRect,
                    vacationCornerRadius,
                    vacationCornerRadius,
                    vacationInnerShapePaint
                )
                drawText(taskWithDates.substring(0, charsCount), textLeft, textY, vacationTextPaint)
            }

        }
    }

    @SuppressLint("NewApi")
    private fun getDate(input: LocalDate): String {
        var out = input.dayOfMonth.toString() + " "
        when (input.monthNumber) {
            1 -> out += "января"
            2 -> out += "февраля"
            3 -> out += "марта"
            4 -> out += "апреля"
            5 -> out += "мая"
            6 -> out += "июня"
            7 -> out += "июля"
            8 -> out += "августа"
            9 -> out += "сентября"
            10 -> out += "октября"
            11 -> out += "ноября"
            12 -> out += "декабря"
        }
        return out
    }

    private fun Paint.getTextBaselineByCenter(center: Float) = center - (descent() + ascent()) / 2
    // endregion


    // region Тачи

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event ?: return false
        val dx = kotlin.math.abs(event.x - lastPoint.x)
        val dy = kotlin.math.abs(event.y - lastPoint.y)
        if (dx > dy) {
            parent?.requestDisallowInterceptTouchEvent(true)
        }
        return if (event.pointerCount == 1) processMove(event) else false
    }

    private fun processMove(event: MotionEvent): Boolean {
        return when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                lastPoint.set(event.x, event.y)
                lastPointerId = event.getPointerId(0)
                true
            }
            MotionEvent.ACTION_MOVE -> {
                if (width < contentWidth) {
                    val pointerId = event.getPointerId(0)
                    if (lastPointerId == pointerId) {
                        transformations.addTranslation(event.x - lastPoint.x)
                    }

                    lastPoint.set(event.x, event.y)
                    lastPointerId = event.getPointerId(0)
                    true
                } else {
                    false
                }
            }
            else -> false
        }
    }
    // endregion

    override fun onSaveInstanceState(): Parcelable {
        return SavedState(super.onSaveInstanceState()).also(transformations::onSaveInstanceState)
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        if (state is SavedState) {
            super.onRestoreInstanceState(state.superState)
            transformations.onRestoreInstanceState(state)
        } else {
            super.onRestoreInstanceState(state)
        }
    }

    private fun initPeriod(): Map<PeriodType, List<String>> {
        return PeriodType.values().associateWith { periodType ->
            val startDate = LocalDate(2022, 1, 1)//today.minusMonths(MONTH_COUNT + 1)
            val endDate = LocalDate(2022, 1, 31)//today.plusMonths(MONTH_COUNT + 6)
            var lastDate = startDate
            mutableListOf<String>().apply {
                while (lastDate <= endDate) {
                    add(periodType.getDateString(lastDate))
                    lastDate = periodType.increment(lastDate)
                }
            }
        }
    }

    private inner class UiTask(val task: Task) {
        val rect = RectF()
        val innerRect = RectF()

        val isRectOnScreen: Boolean
            get() = rect.top < height && (rect.right > 0 || rect.left < rect.width())

        fun updateRect(index: Int) {
            fun getX(date: LocalDate): Float? {
                val periodIndex =
                    periods.getValue(periodType).indexOf(periodType.getDateString(date))
                return if (periodIndex >= 0) {
                    columnWidth * (periodIndex + periodType.getPercentOfPeriods(date)) + monthWidth

                } else {
                    null
                }
            }

            rect.set(
                getX(task.dateStart) ?: -vacationCornerRadius,
                rowHeight * (index + 1f) + vacationVerticalMargin,
                (getX(task.dateEnd) ?: (width + vacationCornerRadius)) + columnWidth,
                rowHeight * (index + 2f) - vacationVerticalMargin
            )

            innerRect.set(
                vacationInnerHorizontalMargin + (getX(task.dateStart) ?: -vacationCornerRadius),
                rowHeight * (index + 1f) + vacationInnerVerticalMargin,
                (getX(task.dateEnd)
                    ?: (width + vacationCornerRadius)) + columnWidth - vacationInnerHorizontalMargin,
                rowHeight * (index + 2f) - vacationInnerVerticalMargin
            )
        }
    }


    private inner class Transformations {
        var translationX = 0f
            private set

        private val minTranslation: Float
            get() = (width - contentWidth).toFloat().coerceAtMost(0f)

        fun addTranslation(dx: Float) {
            translationX = (translationX + dx).coerceIn(minTranslation, 0f)
            invalidate()
        }

        fun onSaveInstanceState(state: SavedState) {
            translationX = state.translationX
        }

        fun onRestoreInstanceState(state: SavedState) {
            translationX = state.translationX
        }
    }


    private var tasks: MutableList<Task> = mutableListOf()
    private var uiTasks: List<UiTask> = emptyList()
    private var tasksMap: MutableMap<Int, Int> = mutableMapOf()

    fun setTasks(tasks: MutableList<Task>) {
        if (tasks != this.tasks) {
            this.tasks = tasks
            tasks.sortBy { it.dateStart }
            uiTasks = tasks.map(::UiTask)
            for (i in 1..12) {
                tasksMap[i] = 0
            }
            tasks.forEach {
                val month: Int = it.dateStart.month.value
                tasksMap[month] = tasksMap.getValue(month) + 1
            }
            updateTasksRects()
            requestLayout() //пересчитывание размеров
            invalidate()
        }

    }

    private fun getRowsCount(): Int {
        var res = 0
        tasksMap.values.forEach {
            if (it > 0) {
                res += (it - 1)
            }
        }
        return res + 13
    }
}