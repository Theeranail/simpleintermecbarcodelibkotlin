package com.khuntheeranai.simpleintermeckotlin

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.CheckBox
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.intermec.aidc.BarcodeReader
import java.util.*

class SymbologyActivity : AppCompatActivity() {
    var bcr: BarcodeReader? = null
    var dataAdapter: SymbologyAdapter? = null

    //number of symbologies for enabling and disabling
    private val SYMBOLOGY_SIZE = 40

    var listView: ListView? = null
    var selctedPos = 0

    var selectedItem: SymbologyItems? = null

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_symbology)

        //set lock the orientation
        //otherwise, the onDestory will trigger
        //when orientation changes

        //set lock the orientation
        //otherwise, the onDestory will trigger
        //when orientation changes
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        //get BarcodeReader instance
        bcr = MainActivity.bcr
        if (bcr != null) {
            //Generate list View from ArrayList
            displaySymbologyListView()
        }
    }

    private fun displaySymbologyListView() {
        //Array list of symbologies
        val symbologyList = ArrayList<SymbologyItems>()
        for (i in 1..SYMBOLOGY_SIZE) {
            val itemList: ArrayList<Object> = getSymbologyStatus(i)
            val symbItem = SymbologyItems(
                i,
                (itemList[0] as String),
                (itemList[1] as Boolean)
            )
            symbologyList.add(symbItem)
        }
        //create an ArrayAdaptar from the String Array

        //create an ArrayAdaptar from the String Array
        dataAdapter = SymbologyAdapter(this, R.layout.symbology_info, symbologyList)

        // Assign adapter to ListView

        // Assign adapter to ListView
        listView!!.adapter = dataAdapter
        listView!!.onItemClickListener =
            OnItemClickListener { parent, view, position, id ->
                //get the selected symbology item
                val symbItem = parent.getItemAtPosition(position) as SymbologyItems
                val chkBox =
                    view.findViewById<View>(R.id.checkBoxStatus) as CheckBox
                if (chkBox.isChecked) {
                    //uncheck item if it was checked
                    chkBox.isChecked = false
                    setSymbologyStatus(symbItem.id, false)
                } else {
                    //check item if it was unchecked
                    chkBox.isChecked = true
                    setSymbologyStatus(symbItem.id, true)
                }
            }
    }

    private fun getSymbologyStatus(i: Int): ArrayList<Object> {
        var status = false
        var name: String? = null
        val dataList = ArrayList<Object>()

        when (i) {
            1 -> {
                name = "Australian Post"
                status = bcr!!.symbology.australianPost.isEnabled
            }
            2 -> {
                name = "Aztec"
                status = bcr!!.symbology.aztec.isEnabled
            }
            3 -> {
                name = "Bpo"
                status = bcr!!.symbology.bpo.isEnabled
            }
            4 -> {
                name = "Canada Post"
                status = bcr!!.symbology.canadaPost.isEnabled
            }
            5 -> {
                name = "Codabar"
                status = bcr!!.symbology.codabar.isEnabled
            }
            6 -> {
                name = "Codablock A"
                status = bcr!!.symbology.codablockA.isEnabled
            }
            7 -> {
                name = "Codablock F"
                status = bcr!!.symbology.codablockF.isEnabled
            }
            8 -> {
                name = "Code 11"
                status = bcr!!.symbology.code11.isEnabled
            }
            9 -> {
                name = "Code 128"
                status = bcr!!.symbology.code128.isEnabled
            }
            10 -> {
                name = "Code 39"
                status = bcr!!.symbology.code39.isEnabled
            }
            11 -> {
                name = "Code 93"
                status = bcr!!.symbology.code93.isEnabled
            }
            12 -> {
                name = "Datamatrix"
                status = bcr!!.symbology.datamatrix.isEnabled
            }
            13 -> {
                name = "Dutch Post"
                status = bcr!!.symbology.dutchPost.isEnabled
            }
            14 -> {
                name = "EanUpc.Ean13"
                status = bcr!!.symbology.eanUpc.isEan13Enabled
            }
            15 -> {
                name = "EanUpc.Ean8"
                status = bcr!!.symbology.eanUpc.isEan8Enabled
            }
            16 -> {
                name = "EanUpc.UPCA"
                status = bcr!!.symbology.eanUpc.isUPCAEnabled
            }
            17 -> {
                name = "EanUpc.UPCE"
                status = bcr!!.symbology.eanUpc.isUPCEEnabled
            }
            18 -> {
                name = "EanUpc.UPCE1"
                status = bcr!!.symbology.eanUpc.isUPCE1Enabled
            }
            19 -> {
                name = "Gs1 Composite"
                status = bcr!!.symbology.gs1Composite.isEnabled
            }
            20 -> {
                name = "Gs1 Databar Expanded"
                status = bcr!!.symbology.gs1DataBarExpanded.isEnabled
            }
            21 -> {
                name = "Gs1 Databar Limited"
                status = bcr!!.symbology.gs1DataBarLimited.isEnabled
            }
            22 -> {
                name = "Gs1 Databar Omni Directional"
                status = bcr!!.symbology.gs1DataBarOmniDirectional.isEnabled
            }
            23 -> {
                name = "Han Xin"
                status = bcr!!.symbology.hanXin.isEnabled
            }
            24 -> {
                name = "Infomail"
                status = bcr!!.symbology.infomail.isEnabled
            }
            25 -> {
                name = "Intelligen Mail"
                status = bcr!!.symbology.intelligentMail.isEnabled
            }
            26 -> {
                name = "Interleaved 2 of 5"
                status = bcr!!.symbology.interleaved2Of5.isEnabled
            }
            27 -> {
                name = "Japan Post"
                status = bcr!!.symbology.japanPost.isEnabled
            }
            28 -> {
                name = "Matrix 2 of 5"
                status = bcr!!.symbology.matrix2Of5.isEnabled
            }
            29 -> {
                name = "Maxicode"
                status = bcr!!.symbology.maxicode.isEnabled
            }
            30 -> {
                name = "Micro PDF 417"
                status = bcr!!.symbology.microPdf417.isEnabled
            }
            31 -> {
                name = "MSI"
                status = bcr!!.symbology.msi.isEnabled
            }
            32 -> {
                name = "PDF 417"
                status = bcr!!.symbology.pdf417.isEnabled
            }
            33 -> {
                name = "Planet"
                status = bcr!!.symbology.planet.isEnabled
            }
            34 -> {
                name = "Plessey"
                status = bcr!!.symbology.plessey.isEnabled
            }
            35 -> {
                name = "Postnet"
                status = bcr!!.symbology.postnet.isEnabled
            }
            36 -> {
                name = "QR Code"
                status = bcr!!.symbology.qrCode.isEnabled
            }
            37 -> {
                name = "Standard 2 of 5"
                status = bcr!!.symbology.standard2Of5.isEnabled
            }
            38 -> {
                name = "Sweden Post"
                status = bcr!!.symbology.swedenPost.isEnabled
            }
            39 -> {
                name = "Telepen"
                status = bcr!!.symbology.telepen.isEnabled
            }
            40 -> {
                name = "TLC 39"
                status = bcr!!.symbology.tlc39.isEnabled
            }
        }
        dataList.add(0, name as Object)
        dataList.add(1, status as Object)

        return dataList
    }

    private fun setSymbologyStatus(id: Int, isEnabled: Boolean) {
        when (id) {
            1 -> bcr!!.symbology.australianPost.setEnable(isEnabled)
            2 -> bcr!!.symbology.aztec.setEnable(isEnabled)
            3 -> bcr!!.symbology.bpo.setEnable(isEnabled)
            4 -> bcr!!.symbology.canadaPost.setEnable(isEnabled)
            5 -> bcr!!.symbology.codabar.setEnable(isEnabled)
            6 -> bcr!!.symbology.codablockA.setEnable(isEnabled)
            7 -> bcr!!.symbology.codablockF.setEnable(isEnabled)
            8 -> bcr!!.symbology.code11.setEnable(isEnabled)
            9 -> bcr!!.symbology.code128.setEnable(isEnabled)
            10 -> bcr!!.symbology.code39.setEnable(isEnabled)
            11 -> bcr!!.symbology.code93.setEnable(isEnabled)
            12 -> bcr!!.symbology.datamatrix.setEnable(isEnabled)
            13 -> bcr!!.symbology.dutchPost.setEnable(isEnabled)
            14 -> bcr!!.symbology.eanUpc.setEan13Enable(isEnabled)
            15 -> bcr!!.symbology.eanUpc.setEan8Enable(isEnabled)
            16 -> bcr!!.symbology.eanUpc.setUPCAEnable(isEnabled)
            17 -> bcr!!.symbology.eanUpc.setUPCEEnable(isEnabled)
            18 -> bcr!!.symbology.eanUpc.setUPCE1Enable(isEnabled)
            19 -> bcr!!.symbology.gs1Composite.setEnable(isEnabled)
            20 -> bcr!!.symbology.gs1DataBarExpanded.setEnable(isEnabled)
            21 -> bcr!!.symbology.gs1DataBarLimited.setEnable(isEnabled)
            22 -> bcr!!.symbology.gs1DataBarOmniDirectional.setEnable(isEnabled)
            23 -> bcr!!.symbology.hanXin.setEnable(isEnabled)
            24 -> bcr!!.symbology.infomail.setEnable(isEnabled)
            25 -> bcr!!.symbology.intelligentMail.setEnable(isEnabled)
            26 -> bcr!!.symbology.interleaved2Of5.setEnable(isEnabled)
            27 -> bcr!!.symbology.japanPost.setEnable(isEnabled)
            28 -> bcr!!.symbology.matrix2Of5.setEnable(isEnabled)
            29 -> bcr!!.symbology.maxicode.setEnable(isEnabled)
            30 -> bcr!!.symbology.microPdf417.setEnable(isEnabled)
            31 -> bcr!!.symbology.msi.setEnable(isEnabled)
            32 -> bcr!!.symbology.pdf417.setEnable(isEnabled)
            33 -> bcr!!.symbology.planet.setEnable(isEnabled)
            34 -> bcr!!.symbology.plessey.setEnable(isEnabled)
            35 -> bcr!!.symbology.postnet.setEnable(isEnabled)
            36 -> bcr!!.symbology.qrCode.setEnable(isEnabled)
            37 -> bcr!!.symbology.standard2Of5.setEnable(isEnabled)
            38 -> bcr!!.symbology.swedenPost.setEnable(isEnabled)
            39 -> bcr!!.symbology.telepen.setEnable(isEnabled)
            40 -> bcr!!.symbology.tlc39.setEnable(isEnabled)
        }
    }

}
