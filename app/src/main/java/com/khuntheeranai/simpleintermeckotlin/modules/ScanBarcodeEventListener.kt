package com.khuntheeranai.simpleintermeckotlin.modules

import com.khuntheeranai.simpleintermeckotlin.models.BarcodeModel

interface ScanBarcodeEventListener {
    fun onScanBarcodeResponse(barcodeData: BarcodeModel);
    fun onConnected() {}
    fun onDisconnect() {}
    fun onBarcodeReaderFailed()
}