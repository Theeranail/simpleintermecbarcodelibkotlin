package com.khuntheeranai.simpleintermeckotlin

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.khuntheeranai.simpleintermecbarcodelib.ScanBarcodeEventListener
import com.khuntheeranai.simpleintermecbarcodelib.ScanBarcodeManager
import com.khuntheeranai.simpleintermecbarcodelib.models.BarcodeModel
import kotlinx.android.synthetic.main.activity_barcode.*
//BarcodeReadListener
class BarcodeActivity : AppCompatActivity(), ScanBarcodeEventListener {
//    private var bcr: BarcodeReader? = null

    private lateinit var scanBarcodeManager: ScanBarcodeManager

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barcode)

        //set lock the orientation
        //otherwise, the onDestory will trigger
        //when orientation changes

        //set lock the orientation
        //otherwise, the onDestory will trigger
        //when orientation changes
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        scanBarcodeManager = ScanBarcodeManager(this);
        scanBarcodeManager.scanBarcodeEventListener = this;
        scanBarcodeManager.startScan()
//        try {
//            //get bar code instance from MainActivity
//            bcr = MainActivity.bcr
//            if (bcr != null) {
//                //enable scanner
//                bcr!!.setScannerEnable(true)
//
//                //set listener
//                bcr!!.addBarcodeReadListener(this)
//            }
//        } catch (e: BarcodeReaderException) {
//            e.printStackTrace()
//        }
    }

//    override fun barcodeRead(aBarcodeReadEvent: BarcodeReadEvent?) {
//        var list: ArrayList<String> = ArrayList()
//        list.add("Device ID: " + aBarcodeReadEvent!!.getDeviceId())
//        list.add("Barcode data: " + aBarcodeReadEvent!!.getBarcodeData())
//        list.add("Symbolgy ID: " + aBarcodeReadEvent!!.getSymbolgyId())
//        list.add("Symbolgy Name: " + aBarcodeReadEvent!!.getSymbologyName())
//        list.add("Udsi: " + aBarcodeReadEvent!!.getUdsi())
//        list.add("Aim: " + aBarcodeReadEvent!!.getAim())
//        list.add("Code mark: " + aBarcodeReadEvent!!.getCodeMark())
//        list.add("Timestamp: " + aBarcodeReadEvent!!.getTimestamp())
//
//        val dataAdapter = ArrayAdapter(
//            this,
//            android.R.layout.simple_list_item_1, list
//        )
//
//        //update UI list
//        runOnUiThread { listViewBarcodeData.adapter = dataAdapter }
//    }

    override fun onScanBarcodeResponse(barcodeData: BarcodeModel) {

        var list: ArrayList<String> = ArrayList()
        list.add("Device ID: " + barcodeData.deviceId)
        list.add("Barcode data: " + barcodeData.barcodeData)
        list.add("Symbolgy ID: " + barcodeData.symbolgyId)
        list.add("Symbolgy Name: " + barcodeData.symbologyName)
        list.add("Udsi: " + barcodeData.udsi)
        list.add("Aim: " + barcodeData.aim)
        list.add("Code mark: " + barcodeData.codeMark)
        list.add("Timestamp: " + barcodeData.timestamp)

        val dataAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, list
        )

        //update UI list
        runOnUiThread { listViewBarcodeData.adapter = dataAdapter }
    }

    override fun onBarcodeReaderFailed() {
        Toast.makeText(this,"onBarcodeReaderFailed",Toast.LENGTH_LONG).show()
    }
}
