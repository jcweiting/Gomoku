package com.newebpay.gomoku

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat

class GameBoard: View {

    private var mPoints: Array<Array<Point?>> = Array(11) { Array(11) { null } }
    private var screenWidth = 0f                         //螢幕寬度
    private var screenHeight = 0f                        //螢幕高度
    private var dotRadius = 0                            //外圓半徑

    //畫筆
    private lateinit var mNormalPaint: Paint

    //畫筆顏色
    private val mNormalColor = ContextCompat.getColor(context, R.color.black_2E3442)

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defaultStyle: Int) : super(context, attrs, defaultStyle)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        initPaint()
        initGameBoard(canvas)
        drawShow(canvas)
    }

    private fun initGameBoard(canvas: Canvas?) {
        var offsetX = 0f                //正方形棋盤，距離布局左邊的距離
        var offsetY = 0f                //正方形棋盤，距離布局頂部的距離
        val squareWidth = width / 10    //每個方格的大小
        dotRadius = squareWidth / 4     //中心點座標

        var count = 0

        for (i in 0.. 10){
            for (j in 0 .. 10){
                count++
                mPoints[i][j] = Point(screenWidth/10*x, screenHeight/10*y, count)
                GameLog.i("i = $i ; j = $j ; count = $count")
            }
        }


//        mPoints[0][0] = Point(0f, 0f, 0)
//        mPoints[0][1] = Point(0f, screenHeight/10, 0)
//        mPoints[0][2] = Point(0f, screenHeight/10*2, 0)

//        //直的第0條 (變動x軸)
//        canvas?.drawLine(0f, 0f, 0f, screenHeight, mNormalPaint)
//        //直的第1條 (變動x軸)
//        canvas?.drawLine(screenWidth/10, 0f, screenWidth/10, screenHeight, mNormalPaint)
//        //直的第2條 (變動x軸)
//        canvas?.drawLine(screenWidth/10*2, 0f, screenWidth/10*2, screenHeight, mNormalPaint)
//        //直的第3條 (變動x軸)
//        canvas?.drawLine(screenWidth/10*3, 0f, screenWidth/10*3, screenHeight, mNormalPaint)
//        //直的第4條 (變動x軸)
//        canvas?.drawLine(screenWidth/10*4, 0f, screenWidth/10*4, screenHeight, mNormalPaint)
//        //直的第5條 (變動x軸)
//        canvas?.drawLine(screenWidth/2, 0f, screenWidth/2, screenHeight, mNormalPaint)
//        //直的第6條 (變動x軸)
//        canvas?.drawLine(screenWidth/10*6, 0f, screenWidth/10*6, screenHeight, mNormalPaint)
//        //直的第7條 (變動x軸)
//        canvas?.drawLine(screenWidth/10*7, 0f, screenWidth/10*7, screenHeight, mNormalPaint)
//        //直的第8條 (變動x軸)
//        canvas?.drawLine(screenWidth/10*8, 0f, screenWidth/10*8, screenHeight, mNormalPaint)
//        //直的第9條 (變動x軸)
//        canvas?.drawLine(screenWidth/10*9, 0f, screenWidth/10*9, screenHeight, mNormalPaint)
//        //直的第10條 (變動x軸)
//        canvas?.drawLine(screenWidth, 0f, screenWidth, screenHeight, mNormalPaint)

    }

    private fun drawShow(canvas: Canvas?) {

//        for (i in 0.. 10){
//            for (j in 0 .. 10){
//
//                val point = mPoints[i][j]
//                point?.let {
//                    canvas?.drawCircle(point.centerX, point.centerY, 50f, mNormalPaint)
//                }
//            }
//        }

        //棋盤格線(直線)
        for (i in 0 .. 10){
            canvas?.drawLine(screenWidth/10*i, 0f, screenWidth/10*i, screenHeight, mNormalPaint)
        }

        //棋盤格線(橫線)
        for (j in 0 .. 10){
            canvas?.drawLine(0f, screenHeight/10*j, screenWidth, screenHeight/10*j, mNormalPaint)
        }
    }

    private fun initPaint() {
        val mStrokeAlpha = 230

        //畫筆預設值
        mNormalPaint = Paint()
        mNormalPaint.color = mNormalColor
        mNormalPaint.isAntiAlias = true                         //設置畫筆是否抗鋸齒
        mNormalPaint.style = Paint.Style.STROKE                 //設置畫筆樣式：Stroke 描邊; Fill 填充; Fill and Stroke 描邊加填充
        mNormalPaint.strokeWidth = 5f                           //設置畫筆寬度
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val desireWidth = 1000
        val desireHeight = 1000
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize: Int = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize: Int = MeasureSpec.getSize(heightMeasureSpec)

        //MeasureWidth
        screenWidth = when (widthMode) {
            MeasureSpec.EXACTLY -> {
                widthSize.toFloat()
            }
            MeasureSpec.AT_MOST -> {
                desireWidth.coerceAtMost(widthSize).toFloat()
            }
            else -> {
                desireWidth.toFloat()
            }
        }

        //Measure Height
        screenHeight = when (heightMode) {
            MeasureSpec.EXACTLY -> {
                heightSize.toFloat()
            }
            MeasureSpec.AT_MOST -> {
                desireHeight.coerceAtMost(heightSize).toFloat()
            }
            else -> {
                desireHeight.toFloat()
            }
        }

        setMeasuredDimension(width, height)
    }
}