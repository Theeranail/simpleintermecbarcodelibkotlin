# Intermec Barcode Reader Library
##### Last version : Master
** #Thai version**

[simpleintermecbarcodelibkotlin](https://github.com/Theeranail/simpleintermecbarcodelibkotlin "simpleintermecbarcodelibkotlin")  เป็น library ที่ใช้ในการรับ event การอ่าน barcode หรือ qr code จาก Internal Scanner ของอุปกรณ์เครื่อง handheld Intermec ที่เป็น Android
ทำขึ้นมาเพื่อกรณีที่ไม่ต้องการใช้งาน Virtual Wedge ที่เครื่องมีมาให้ และให้เรียกใช้งานได้ง่าย

**วิธีการใช้งาน**
##### Gradle (Project)
    allprojects {
        repositories {
           //.......
            maven { url 'https://jitpack.io' }
        }
    }
##### Gradle Dependencies
    implementation com.github.Theeranail:simpleintermecbarcodelibkotlin:{version}
##### MainActivity
เรียกใช้งาน Instances ScanBarcodeManager แค่ครั้งเดียว
<code>scanBarcodeManager = ScanBarcodeManager(this)</code>
<code>scanBarcodeManager.initScanBarcode()</code>
สั่งปิดการเชื่อมต่อ BarcodeReader ใน onDestroy
<code> scanBarcodeManager.stopScanBarcode()</code>



        private lateinit var scanBarcodeManager: ScanBarcodeManager
    
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
    
            scanBarcodeManager = ScanBarcodeManager(this)
            scanBarcodeManager.initScanBarcode()
        }
    
        override fun onDestroy() {
            super.onDestroy()
            scanBarcodeManager.stopScanBarcode()
        }
		
##### ScanBarcodeActivity
สำหรับการ scan เรียกใช้งาน Instances ScanBarcodeManager
- set ค่า Listener เพื่อรับค่าการ scan
<code>scanBarcodeManager.scanBarcodeEventListener = this;</code>
- สั่งเปิดการ Scanner Barcode
<code>scanBarcodeManager.startScan()</code>


        class BarcodeActivity : AppCompatActivity(), ScanBarcodeEventListener {
            private lateinit var scanBarcodeManager: ScanBarcodeManager
        
            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_barcode)
        
                scanBarcodeManager = ScanBarcodeManager(this);
                scanBarcodeManager.scanBarcodeEventListener = this;
                scanBarcodeManager.startScan()
        
            }
        
            override fun onBarcodeReaderFailed() {
                Toast.makeText(this,"onBarcodeReaderFailed", Toast.LENGTH_LONG).show()
            }
        
            override fun onScanBarcodeResponse(barcodeData: BarcodeModel) {
                Log.e("BarcodeModel","Device ID: " + barcodeData.deviceId)
                Log.e("BarcodeModel","Barcode data: " + barcodeData.barcodeData)
                Log.e("BarcodeModel","Symbolgy ID: " + barcodeData.symbolgyId)
                Log.e("BarcodeModel","Symbolgy Name: " + barcodeData.symbologyName)
                Log.e("BarcodeModel","Udsi: " + barcodeData.udsi)
                Log.e("BarcodeModel","Aim: " + barcodeData.aim)
                Log.e("BarcodeModel","Code mark: " + barcodeData.codeMark)
                Log.e("BarcodeModel","Timestamp: " + barcodeData.timestamp)
        }
		
#### Support  Version
- **Android Version(SDK) :  4.2.2, 4.4, 6.0 or later**
- **Devices Handheld : Intermec CN51(test แล้ว)**

#### Special Thanks
- [https://support.honeywellaidc.com/s/question/0D52K00003eMVBF/unable-to-stopdisable-the-physical-scan-key-during-the-app-loading-scanpal-eda-50](https://support.honeywellaidc.com/s/question/0D52K00003eMVBF/unable-to-stopdisable-the-physical-scan-key-during-the-app-loading-scanpal-eda-50 "https://support.honeywellaidc.com/s/question/0D52K00003eMVBF/unable-to-stopdisable-the-physical-scan-key-during-the-app-loading-scanpal-eda-50")

- [https://support.honeywellaidc.com/s/article/How-to-use-the-Android-Mobility-SDK-in-Android-Studio](https://support.honeywellaidc.com/s/article/How-to-use-the-Android-Mobility-SDK-in-Android-Studio "https://support.honeywellaidc.com/s/article/How-to-use-the-Android-Mobility-SDK-in-Android-Studio")

- [https://www.honeywellaidc.com/en/-/media/en/files-public/technical-publications/computers/cn51/935-078-003.pdf](https://www.honeywellaidc.com/en/-/media/en/files-public/technical-publications/computers/cn51/935-078-003.pdf "https://www.honeywellaidc.com/en/-/media/en/files-public/technical-publications/computers/cn51/935-078-003.pdf")

- [https://support.honeywellaidc.com/](https://support.honeywellaidc.com/ "https://support.honeywellaidc.com/")

&copy; 2020 Khuntheeranai
