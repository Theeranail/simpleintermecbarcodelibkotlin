package com.khuntheeranai.simpleintermeckotlin

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.khuntheeranai.simpleintermecbarcodelib.ScanBarcodeManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var scanBarcodeManager: ScanBarcodeManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set lock the orientation
        //otherwise, the onDestory will trigger
        //when orientation changes
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED

        scanBarcodeManager = ScanBarcodeManager(this)
        scanBarcodeManager.initScanBarcode()

        ActivitySetting()

//        // Make sure the BarcodeReader depended service is connected and
//        // register a callback for service connect and disconnect events.
//        AidcManager.connectService(this, object : IServiceListener {
//            override fun onConnect() {
//
//                // The depended service is connected and it is ready
//                // to receive bar code requests and virtual wedge
//                try {
//                    //Initial bar code reader instance
//                    bcr = BarcodeReader()
//
//                    //disable virtual wedge
//                    wedge = VirtualWedge()
//                    wedge!!.setEnable(false)
//                } catch (e: BarcodeReaderException) {
//                    e.printStackTrace()
//                } catch (e: VirtualWedgeException) {
//                    e.printStackTrace()
//                }
//
//                //set action for other activities
//                ActivitySetting()
//            }
//
//            override fun onDisconnect() {
//                //add disconnect message/action here
//            }
//        })
    }

    fun ActivitySetting() {
        buttonBarcode.setOnClickListener(View.OnClickListener { //get the intent action string from AndroidManifest.xml
            val barcodeIntent = Intent("android.intent.action.BARCODEACTIVITY")
            startActivity(barcodeIntent)
        })
        buttonSymbology.setOnClickListener(View.OnClickListener { //get the intent action string from AndroidManifest.xml
            val symbologyIntent = Intent("android.intent.action.simpleintermecbarcodelib.SymbologyActivity")
            startActivity(symbologyIntent)
        })
//        buttonSymbologyOptions.setOnClickListener(View.OnClickListener { //get the intent action string from AndroidManifest.xml
//            val symbologyOptionsIntent = Intent("android.intent.action.SYMBOLOGYOPTIONSACTIVITY")
//            startActivity(symbologyOptionsIntent)
//        })
    }

    override fun onDestroy() {
        super.onDestroy()
        scanBarcodeManager.stopScanBarcode()
    }

    companion object{
        @JvmStatic
        var bcr: com.intermec.aidc.BarcodeReader? = null
        @JvmStatic
        var wedge: com.intermec.aidc.VirtualWedge? = null
    }
}
