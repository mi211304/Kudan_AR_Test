package com.xlsoft.kudanar

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import eu.kudan.kudan.*

class MainActivity : ARActivity() {


    private lateinit var imageTrackable: ARImageTrackable
    private lateinit var secondImageTrackable: ARImageTrackable
    private lateinit var thirdImageTrackable: ARImageTrackable
    private lateinit var fourthImageTrackable: ARImageTrackable
    private lateinit var fifthImageTrackable: ARImageTrackable


    // Permission のリクエストを OS 標準の requestPermissions メソッドで行う
    private fun permissionsRequest(){
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.INTERNET, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA), 111)

        }
    }


    private fun permissionsNotSelected() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Permissions required")
        builder.setMessage("Please enable the requested permissions in the app settings in order to use this demo app")
        builder.setPositiveButton("Set permission") { dialog, _ ->
            dialog.cancel()
            val intent = Intent()
            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            intent.data = Uri.parse("package:eu.kudan.ar")
            startActivity(intent)
        }
        val noPermission = builder.create()
        noPermission.show()
    }

    // requestPermissions　ダイアログの結果を受け、全て許可されていなければ、permissionsNotSelected を呼び出し
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            111 -> {
                if (grantResults.isNotEmpty() &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[2] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[3] == PackageManager.PERMISSION_GRANTED) {
                    // パーミッションが必要な処理
                } else {
                    permissionsNotSelected()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val key = ARAPIKey.getInstance()
        key.setAPIKey("API_KEY")    //KudanARのAPIキー

        permissionsRequest()
    }

    /*override fun setup() {
        super.setup()

        // Initialise the image trackable and load the image.
        val imageTrackable = ARImageTrackable("")
        imageTrackable.loadFromAsset("")

        // Get the single instance of the image tracker.
        val imageTracker = ARImageTracker.getInstance()
        imageTracker.initialise()

        //Add the image trackable to the image tracker.
        imageTracker.addTrackable(imageTrackable)



        // Initialise the image node with our image
        val imageNode = ARImageNode("")

        // Add the image node as a child of the trackable's world
        imageTrackable.world.addChild(imageNode)

        // imageNode のサイズを Trackable のサイズに合わせる
        val textureMaterial = imageNode.material as ARTextureMaterial
        val scale = imageTrackable.width / textureMaterial.texture.width
        imageNode.scaleByUniform(scale)
    }*/

    override fun setup() {
        addImageTrackable()

        addImageNode()
        addSecondImageNode()
        addThirdImageNode()
        addfourthImageNode()
        addfifthImageNode()
    }


    private fun addImageTrackable(){

        imageTrackable = ARImageTrackable("ARマーカー名")       //ARマーカーの名前を決める
        imageTrackable.loadFromAsset("")                       //ARマーカーとなる画像をASSETからきめる

        secondImageTrackable = ARImageTrackable("ARマーカー名")
        secondImageTrackable.loadFromAsset("")

        thirdImageTrackable = ARImageTrackable("ARマーカー名")
        thirdImageTrackable.loadFromAsset("")

        fourthImageTrackable = ARImageTrackable("ARマーカー名")
        fourthImageTrackable.loadFromAsset("")

        fifthImageTrackable = ARImageTrackable("ARマーカー名")
        fifthImageTrackable.loadFromAsset("")

        val imageTracker = ARImageTracker.getInstance()
        imageTracker.initialise()

        imageTracker.addTrackable(imageTrackable)
        imageTracker.addTrackable(secondImageTrackable)
        imageTracker.addTrackable(thirdImageTrackable)
        imageTracker.addTrackable(fourthImageTrackable)
        imageTracker.addTrackable(fifthImageTrackable)
    }


    //ARマーカーに対応した画像がマーカー上に表示される
    private fun addImageNode(){

        // ARImageNode を画像を指定して初期化
        val imageNode = ARImageNode("")             //表示したい画像をassetsからきめる

        // imageNode のサイズを Trackable のサイズに合わせる
        val textureMaterial = imageNode.material as ARTextureMaterial
        val scale = imageTrackable.width / textureMaterial.texture.width
        imageNode.scaleByUniform(scale)

        // imageNode を trackable の world に追加
        imageTrackable.world.addChild(imageNode)
    }

    private fun addSecondImageNode(){

        val imageNode = ARImageNode("")

        val textureMaterial = imageNode.material as ARTextureMaterial
        val scale = secondImageTrackable.width / textureMaterial.texture.width
        imageNode.scaleByUniform(scale)

        secondImageTrackable.world.addChild(imageNode)
    }

    private fun addThirdImageNode(){

        val imageNode = ARImageNode("")

        val textureMaterial = imageNode.material as ARTextureMaterial
        val scale = thirdImageTrackable.width / textureMaterial.texture.width
        imageNode.scaleByUniform(scale)

        thirdImageTrackable.world.addChild(imageNode)
    }

    private fun addfourthImageNode(){

        val imageNode = ARImageNode("")

        val textureMaterial = imageNode.material as ARTextureMaterial
        val scale = fourthImageTrackable.width / textureMaterial.texture.width
        imageNode.scaleByUniform(scale)

        fourthImageTrackable.world.addChild(imageNode)
    }

    private fun addfifthImageNode(){

        val imageNode = ARImageNode("")

        val textureMaterial = imageNode.material as ARTextureMaterial
        val scale = fifthImageTrackable.width / textureMaterial.texture.width
        imageNode.scaleByUniform(scale)

        fifthImageTrackable.world.addChild(imageNode)
    }

}













