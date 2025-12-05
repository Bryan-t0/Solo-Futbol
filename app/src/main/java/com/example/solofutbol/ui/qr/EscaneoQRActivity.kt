// EscaneoQRActivity.kt
package com.example.solofutbol.ui.qr

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Size
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.example.solofutbol.R
import com.example.solofutbol.databinding.ActivityEscanearQrBinding
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import java.nio.ByteBuffer
import java.util.concurrent.Executors

class EscaneoQRActivity : AppCompatActivity() {

    private lateinit var b: ActivityEscanearQrBinding
    private var camera: Camera? = null
    private val exec = Executors.newSingleThreadExecutor()
    private var done = false

    private val permisoCamara = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { ok -> if (ok) iniciarCamara() else finish() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityEscanearQrBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.btnVolver.setOnClickListener { finish() }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_GRANTED) {
            iniciarCamara()
        } else {
            permisoCamara.launch(Manifest.permission.CAMERA)
        }
    }

    private fun iniciarCamara() {
        val providerFuture = ProcessCameraProvider.getInstance(this)
        providerFuture.addListener({
            val provider = providerFuture.get()

            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(b.cameraPreview.surfaceProvider)
            }

            val selector = CameraSelector.DEFAULT_BACK_CAMERA

            val analisis = ImageAnalysis.Builder()
                .setTargetResolution(Size(1280, 720))
                .setOutputImageFormat(ImageAnalysis.OUTPUT_IMAGE_FORMAT_RGBA_8888)
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()

            val opciones = BarcodeScannerOptions.Builder()
                .setBarcodeFormats(
                    Barcode.FORMAT_QR_CODE,
                    Barcode.FORMAT_EAN_13,
                    Barcode.FORMAT_EAN_8,
                    Barcode.FORMAT_CODE_128,
                    Barcode.FORMAT_CODE_39,
                    Barcode.FORMAT_UPC_A,
                    Barcode.FORMAT_UPC_E
                ).build()
            val scanner = BarcodeScanning.getClient(opciones)

            analisis.setAnalyzer(exec) { proxy ->
                try {
                    if (done) { proxy.close(); return@setAnalyzer }
                    val bitmap = proxy.toBitmapRGBA()
                    val rotation = proxy.imageInfo.rotationDegrees
                    val image = InputImage.fromBitmap(bitmap, rotation)

                    scanner.process(image)
                        .addOnSuccessListener { codigos ->
                            if (!done && codigos.isNotEmpty()) {
                                done = true
                                val valor = codigos.first().rawValue.orEmpty().trim()
                                runOnUiThread {
                                    findViewById<TextView>(R.id.txtResultado).text = valor
                                }
                                setResult(RESULT_OK, Intent().putExtra("sku", valor))
                                finish()
                            }
                        }
                        .addOnCompleteListener { proxy.close() }
                } catch (_: Throwable) {
                    proxy.close()
                }
            }

            provider.unbindAll()
            camera = provider.bindToLifecycle(this, selector, preview, analisis)
        }, ContextCompat.getMainExecutor(this))
    }

    private fun ImageProxy.toBitmapRGBA(): Bitmap {
        val plane = planes[0]
        val buffer: ByteBuffer = plane.buffer
        val bytes = ByteArray(buffer.remaining())
        buffer.get(bytes)
        val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        bmp.copyPixelsFromBuffer(ByteBuffer.wrap(bytes))
        return bmp
    }

    override fun onDestroy() {
        super.onDestroy()
        exec.shutdownNow()
    }
}
