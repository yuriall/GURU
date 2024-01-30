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

        // Fragment에서 MapFragment를 사용하는 방식으로 변경
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.map_fragment) as MapFragment
        mapFragment.getMapAsync(this)

        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)


        return view
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        Log.d("onRequest", "onRequestPermissionsResult")

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
        this.naverMap = map
        readShelterDataAndAddMarkers()

        naverMap.uiSettings.isLocationButtonEnabled = true
        naverMap.uiSettings.isScaleBarEnabled = true
        naverMap.locationSource = locationSource
        ActivityCompat.requestPermissions(
            requireActivity(),
            PERMISSIONS,
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }

    private fun readShelterDataAndAddMarkers() {
        try {
            val inputStream = requireActivity().assets.open("filtered_final_data.csv")
            val reader = BufferedReader(InputStreamReader(inputStream, Charsets.UTF_8))
            val csvReader = CSVReader(reader)
            csvReader.readNext()

            var nextLine: Array<String>?
            while (csvReader.readNext().also { nextLine = it } != null) {
                val latitude = nextLine?.get(5)?.toDoubleOrNull()
                val longitude = nextLine?.get(6)?.toDoubleOrNull()
                val facilityName = nextLine?.get(2)
                val capacityName = nextLine?.get(4)?.toIntOrNull()

                if (latitude != null && longitude != null && facilityName != null) {
                    val shelterMarker = Marker().apply {
                        position = LatLng(latitude, longitude)
                        map = naverMap
                    }

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
        val dialogBuilder = AlertDialog.Builder(requireContext())
        dialogBuilder.setTitle("대피소 정보")

        val capacityText = capacityName?.let { "수용인원: $it" } ?: ""
        val newLine = if (facilityName != null && capacityName != null) "\n" else ""

        dialogBuilder.setMessage("$facilityName$newLine$capacityText")
        dialogBuilder.setPositiveButton("확인", null)

        val dialog = dialogBuilder.create()
        dialog.show()
    }
}
