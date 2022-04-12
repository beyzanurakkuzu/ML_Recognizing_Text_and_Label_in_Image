package com.beyzaakkuzu.ml.dialog

import android.app.AlertDialog
import android.content.Context
import android.view.Gravity
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView

open class DialogMain(context: Context):DialogView.UIView, ProgressBar(context){

    private val llPadding= 30
    private val ll:LinearLayout= LinearLayout(context)
    private val tvText:TextView= TextView(context)
    private val builder=AlertDialog.Builder(context)
    private lateinit var llParam : LinearLayout.LayoutParams
    private lateinit var dialog:AlertDialog

    init{
        setAlertDialog()
    }

    private fun setAlertDialog(){
        ll.orientation=LinearLayout.HORIZONTAL
        ll.gravity=Gravity.CENTER
        llParam=LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
        ll.layoutParams= llParam
        this.isIndeterminate= true
        tvText.layoutParams=llParam
        setViewToBuilder()
    }
    override fun dismissDialog(builder:AlertDialog){
        builder.dismiss()
    }
    override fun showDialog(builder: AlertDialog){
        builder.show()
        if(builder.window!= null){
            val layoutParams= WindowManager.LayoutParams()
            layoutParams.copyFrom(builder.window!!.attributes)
            layoutParams.width=LinearLayout.LayoutParams.WRAP_CONTENT
            layoutParams.height=LinearLayout.LayoutParams.WRAP_CONTENT
            builder.window!!.attributes=layoutParams
        }
    }
    override  fun message(message:String){
        tvText.text=message
    }

    override fun textColor(color:Int){
        tvText.setTextColor(color)
    }
    override fun textSize(txtsize:Float){
        tvText.textSize= txtsize
    }
    override fun alertCancellable(cancel:Boolean){
        builder.setCancelable(cancel)
    }
    override fun getAlertBuilder():AlertDialog{
        return dialog
    }
    override fun padding(left:Int,top:Int,right:Int,bottom:Int){
        this.setPadding(left,top,right,bottom)
    }
    private fun setViewToBuilder(){
        if(ll.parent != null) ll.removeAllViews()
        ll.addView(this)
        ll.addView(tvText)
        builder.setView(ll)
        dialog=builder.create()
    }

}