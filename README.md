# NMEA
NMEA 0183 is a combined electrical and data specification for communication between marine electronics such as echo sounder, sonars, anemometer, gyrocompass, autopilot, GPS receivers and many other types of instruments

The following data text:

```
$GPGGA,130232.000,4529.9178,N,00913.9201,E,1,06,
1.35,133.5,M,48.0,M,,*68
$GPGSA,A,3,30,13,28,20,
05,15,,,,,,,1.63,1.35,0.92*02
$GPGSV,3,1,12,13,8
4,343,21,28,54,066,26,15,51,301,11,20,40,268,23*
77
$GPGSV,3,2,12,05,28,198,25,30,25,069,22,24,17
,264,19,17,12,127,*71
$GPGSV,3,3,12,07,03,074,,0
8,02,022,,19,01,146,,46,,,*4F
$GPRMC,130232.000
,A,4529.9178,N,00913.9201,E,0.40,260.06,100218,,,
A*6F
$GPVTG,260.06,T,,M,0.40,N,0.75,K,A*39
```
will be easily parsed with this java code:

```java

import com.clickntap.nmea.NMEA;
import com.clickntap.nmea.NMEAUtils;
import com.clickntap.nmea.Position;

...

NMEA nmea = new NMEA();
JSONObject json = nmea.parse(text);
Position position = NMEAUtils.parsePosition(json);

```

The Output JSON:

```json
{
  "messages": [
    "$GPGGA,104734.000,4529.9248,N,00913.9249,E,2,06,1.50,110.3,M,48.0,M,0000,0000*67",
    "$GPGSA,A,3,30,05,09,07,13,28,,,,,,,1.75,1.50,0.90*0C",
    "$GPRMC,104734.000,A,4529.9248,N,00913.9249,E,0.53,241.11,120218,,,D*67",
    "$GPVTG,241.11,T,,M,0.53,N,0.98,K,D*38"
  ],
  "items": [
    {
      "fixQuality": 2,
      "altitude": 110.3,
      "altitudeMetric": "M",
      "code": "GGA",
      "latitude": 45.498747,
      "horizontalDilutionOfPosition": 1.5,
      "geoidalMetric": "M",
      "numberOfSatellites": 6,
      "time": "104734.000",
      "longitude": 9.232082,
      "geoidal": 48
    },
    {
      "mode": "3",
      "code": "GSA",
      "automatic": "A"
    },
    {
      "date": "120218",
      "code": "RMC",
      "latitude": 45.498747,
      "speedKnots": 0.53,
      "time": "104734.000",
      "validity": "A",
      "trackMadeGood": 241.11,
      "longitude": 9.232082
    },
    {
      "code": "VTG",
      "speedKnots": 0.53,
      "trackMadeGood": 241.11,
      "speedKmPerHours": 0.98
    }
  ],
  "info": {
    "date": "120218",
    "altitude": 110.3,
    "latitude": 45.498747,
    "speedKnots": 0.53,
    "numberOfSatellites": 6,
    "time": "104734.000",
    "longitude": 9.232082,
    "speedKmPerHours": 0.98
  }
}
```

The interpreted sentences are:

$GPGGA - Global Positioning System Fix Data
$GPGSA - GPS DOP and active satellites
$GPGSV - GPS Satellites in view
$GPRMC - Recommended minimum specific GPS/Transit data
$GPVTG - Track made good and ground speed

But it's really simple add new interpreter.
