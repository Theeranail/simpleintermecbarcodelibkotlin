package com.khuntheeranai.simpleintermecbarcodelib

import com.khuntheeranai.simpleintermecbarcodelib.models.BarcodeModel

interface ScanBarcodeEventListener {
    fun onScanBarcodeResponse(barcodeData: BarcodeModel);
    fun onConnected() {}
    fun onDisconnect() {}
    fun onBarcodeReaderFailed()
}