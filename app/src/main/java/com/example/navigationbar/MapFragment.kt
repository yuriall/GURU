package com.example.navigationbar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.overlay.Marker
import android.Manifest
import android.util.Log
import androidx.appcompat.app.AlertDialog
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import com.opencsv.CSVReader


class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var locationSource: FusedLocationSource
    private lateinit var naverMap: NaverMap

    // 위치 접근을 위한 상수 권환
    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
        private val PERMISSIONS = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_map, container, false)

        //네이버 지도를 얻어와 초기화
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.map_fragment) as MapFragment
        mapFragment.getMapAsync(this)
        // 위치 권환 처리를 위해 초기화
        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)


        return view
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        Log.d("onRequest", "onRequestPermissionsResult")
        // 위치 권한 결과
        if (locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
            if (!locationSource.isActivated) {
                Log.d("권한 거부", "권한 거부됨")
                naverMap.locationTrackingMode = LocationTrackingMode.None
            } else {
                Log.d("권한 승인", "권한 승인됨")
                naverMap.locationTrackingMode = LocationTrackingMode.Follow
            }
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onMapReady(map: NaverMap) {
        // 지도 준비되면 콜백
        this.naverMap = map
        // 대피소 데이터 가져와서 마커 추가
        readShelterDataAndAddMarkers()
        // 지도 구성 UI설정
        naverMap.uiSettings.isLocationButtonEnabled = true
        naverMap.uiSettings.isScaleBarEnabled = true
        // 위치 설정
        naverMap.locationSource = locationSource

        //위치 권환 요청
        ActivityCompat.requestPermissions(
            requireActivity(),
            PERMISSIONS,
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }

    private fun readShelterDataAndAddMarkers() {
        try {
            // assets 폴더에서 csv 파일 가져와서 데이터 읽기
            val inputStream = requireActivity().assets.open("filtered_final_data.csv")
            val reader = BufferedReader(InputStreamReader(inputStream, Charsets.UTF_8))
            val csvReader = CSVReader(reader)
            csvReader.readNext()

            var nextLine: Array<String>?
            while (csvReader.readNext().also { nextLine = it } != null) {
                //csv 파일에서 필요한 대피소 데이터 추출
                val latitude = nextLine?.get(5)?.toDoubleOrNull()
                val longitude = nextLine?.get(6)?.toDoubleOrNull()
                val facilityName = nextLine?.get(2)
                val capacityName = nextLine?.get(4)?.toIntOrNull()

                if (latitude != null && longitude != null && facilityName != null) {
                    // 대피소 위도, 경도 데이터를 이용하여 마커 생성
                    val shelterMarker = Marker().apply {
                        position = LatLng(latitude, longitude)
                        map = naverMap
                    }
                    // 대피소 정보를 표시하는 클릭리스너 설정
                    shelterMarker.setOnClickListener {
                        showShelterInfoDialog(facilityName, capacityName)
                        true
                    }
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun showShelterInfoDialog(facilityName: String?, capacityName: Int?) {
        // 대피소 정보 표시하는 다이얼로그 (창) 생성
        val dialogBuilder = AlertDialog.Builder(requireContext())
        dialogBuilder.setTitle("대피소 정보")

        val capacityText = capacityName?.let { "수용인원: $it" } ?: ""
        val newLine = if (facilityName != null && capacityName != null) "\n" else ""
        // 다이얼로그 메세지
        dialogBuilder.setMessage("$facilityName$newLine$capacityText")
        dialogBuilder.setPositiveButton("확인", null)
        // 다이얼로그 생성, 표시
        val dialog = dialogBuilder.create()
        dialog.show()
    }
}
