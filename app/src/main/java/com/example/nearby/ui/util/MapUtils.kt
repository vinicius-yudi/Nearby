package com.example.nearby.ui.util

import com.google.android.gms.maps.model.LatLng

fun findSouthwestPoint(points: List<LatLng>): LatLng {
    if (points.isEmpty()) return LatLng(0.0, 0.0)

    var southwestPoint = points[0]

    for (point in points) {
        if (point.latitude < southwestPoint.latitude || (
                    point.latitude == southwestPoint.latitude && point.longitude < southwestPoint.longitude)
        ) {
            southwestPoint = point
        }
    }
    return southwestPoint
}

fun findNortheastPoint(points: List<LatLng>): LatLng {
    if (points.isEmpty()) return LatLng(0.0, 0.0)

    var northeastPoint = points[0]

    for (point in points) {
        if (point.latitude > northeastPoint.latitude || (
                    point.latitude == northeastPoint.latitude && point.longitude > northeastPoint.longitude)
        ) {
            northeastPoint = point
        }
    }
    return northeastPoint
}




