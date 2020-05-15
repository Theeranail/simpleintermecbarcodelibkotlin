package com.khuntheeranai.simpleintermecbarcodelib

import android.content.Context
import com.intermec.aidc.*
import com.khuntheeranai.simpleintermecbarcodelib.models.BarcodeModel

class ScanBarcodeManager(context: Context) : BarcodeReadListener {

    var context: Context
    var scanBarcodeEventListener: ScanBarcodeEventListener? = null

    init {
        this.context = context;
    }

    fun initScanBarcode() {
        AidcManager.connectService(this.context, object : AidcManager.IServiceListener {
            override fun onConnect() {
                // to receive bar code requests and virtual wedge
                try {
                    //Initial bar code reader instance
                    bcr = BarcodeReader()

                    //disable virtual wedge
                    wedge = VirtualWedge()
                    wedge!!.setEnable(false)
                } catch (e: BarcodeReaderException) {
                    e.printStackTrace()
                } catch (e: VirtualWedgeException) {
                    e.printStackTrace()
                }
            }

            override fun onDisconnect() {

            }
        })
    }

    fun startScan() {
        try {
            bcr?.setScannerEnable(true)
            bcr?.addBarcodeReadListener(this)
        } catch (e: BarcodeReaderException) {
            e.printStackTrace()
        }

    }

    fun stopScanBarcode() {
        try {
            wedge?.let {
                it.setEnable(true)
                wedge = null
            }
            bcr?.let {
                it.close()
                bcr = null
            }
        } catch (e: VirtualWedgeException) {
            e.printStackTrace()
        }

        //disconnect from data collection service
        AidcManager.disconnectService()
    }

    companion object {
        @JvmStatic
        var bcr: com.intermec.aidc.BarcodeReader? = null

        @JvmStatic
        var wedge: com.intermec.aidc.VirtualWedge? = null
    }

    override fun barcodeRead(barcodeReadEvent: BarcodeReadEvent?) {
        barcodeReadEvent?.let {
            var dataBarcode = BarcodeModel(
                deviceId = it.deviceId,
                barcodeData = it.barcodeData,
                symbolgyId = it.symbolgyId,
                symbologyName = it.symbologyName,
                udsi = it.udsi,
                aim = it.aim,
                codeMark = it.codeMark,
                timestamp = it.timestamp
            );
            scanBarcodeEventListener?.onScanBarcodeResponse(dataBarcode)
        }
        if (barcodeReadEvent == null) {
            scanBarcodeEventListener?.onBarcodeReaderFailed()
        }
    }
}