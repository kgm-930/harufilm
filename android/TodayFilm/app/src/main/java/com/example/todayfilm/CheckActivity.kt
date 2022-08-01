package com.example.todayfilm

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Matrix
import android.media.MediaMetadataRetriever
import android.os.Bundle
import android.util.Log
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import com.daasuu.mp4compose.FillMode
import com.daasuu.mp4compose.Rotation
import com.daasuu.mp4compose.composer.Mp4Composer
import com.example.todayfilm.databinding.ActivityCheckBinding
import java.io.File
import java.io.FileOutputStream


class CheckActivity : AppCompatActivity() {
    private val TAG = "테스트용 로그"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityCheckBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 촬영한 영상 경로 및 확장자 제외한 파일명
        val srcPath = intent.getStringExtra("path").toString()
        var name = srcPath.substring(srcPath.lastIndexOf(File.separator)+1)
        name = name.substring(0 until (name.length - 4))

        // 결과가 저장될 경로
        val destPath = this.externalCacheDir.toString() + "/${name}_trimmed.mp4"

        // 영상 길이 구해서 시작 시간, 종료 시간 구하기
        val mediaMetadataRetriever = MediaMetadataRetriever()
        mediaMetadataRetriever.setDataSource(srcPath)
        val endTime =
            mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
                ?.toLong()
        val startTime = if (endTime!! >= 2500) {
            endTime - 2500
        }  else {
            0
        }

        // 영상 길이 자르기
        Mp4Composer(srcPath, destPath)
            .rotation(Rotation.ROTATION_270)
            .size(720, 480)
            .fillMode(FillMode.PRESERVE_ASPECT_FIT)
            .trim(startTime, endTime)
            .listener(object : Mp4Composer.Listener {
                override fun onProgress(progress: Double) {
                    Log.d(TAG, "onProgress = $progress")
                }

                override fun onCurrentWrittenVideoTime(timeUs: Long) {
                }

                override fun onCompleted() {
                    runOnUiThread {
                        // 영상 미리보기
                        binding.videoView.setVideoPath(destPath)
                        binding.videoView.setMediaController(MediaController(this@CheckActivity))
                        binding.videoView.start()
                    }
                }

                override fun onCanceled() {
                    File(srcPath).delete()
                    Log.d(TAG, "onCanceled")
                }

                override fun onFailed(exception: Exception) {
                    File(srcPath).delete()
                    Log.e(TAG, "onFailed()", exception)
                }
            })
            .start()

        // 3초로 잘린 영상
        val resultFile = File(destPath)

        // 다시시도 버튼
        binding.cameraRetry.setOnClickListener{
            // 소스 파일 및 결과 영상 제거
            File(srcPath).delete()
            resultFile.delete()

            // camera activity로 이동
            val intent = Intent(this, CameraActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }

        // 확인 버튼
        binding.cameraOk.setOnClickListener{
            // 결과 영상을 cache에서 file 디렉토리로 옮기기
            val movedFile = File(this.getExternalFilesDir(null), "${name}.mp4")
            resultFile.copyTo(movedFile)
            resultFile.delete()

            // 영상의 마지막 프레임 추출해서 같은 위치에 같은 이름으로 저장
            var bitmap = mediaMetadataRetriever.getFrameAtTime(endTime * 1000 - 1)
            val rotateMatrix = Matrix()
            rotateMatrix.postRotate(270F)
            bitmap = Bitmap.createBitmap(bitmap!!, 0,0, bitmap.width, bitmap.height, rotateMatrix, false)
            val imageFile = File(this.getExternalFilesDir(null), "${name}.jpg")
            val fileOutputStream = FileOutputStream(imageFile)
            bitmap!!.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
            fileOutputStream.flush()

            // 소스 파일 지우기
            File(srcPath).delete()

            // 홈으로 이동
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }
    }
}