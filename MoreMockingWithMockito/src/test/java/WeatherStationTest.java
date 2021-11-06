import instruments.Anemometer;
import instruments.Barometer;
import instruments.Hygrometer;
import instruments.Thermometer;
import instruments.satellite.SatelliteUplink;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

class WeatherStationTest {

  WeatherStation weatherStation;
  SatelliteUplink uplinkMock;
  Anemometer anemometer;
  Barometer barometer;
  Hygrometer hygrometer;
  Thermometer thermometer;

  @BeforeEach
  void setUp() {
    uplinkMock = Mockito.spy(SatelliteUplink.class);
    weatherStation = new WeatherStation();
    anemometer = Mockito.spy(Anemometer.class);
    barometer = Mockito.spy(Barometer.class);
    hygrometer = Mockito.spy(Hygrometer.class);
    thermometer = Mockito.spy(Thermometer.class);
    weatherStation.setHygrometer(this.hygrometer);
    weatherStation.setBarometer(this.barometer);
    weatherStation.setThermometer(this.thermometer);
    weatherStation.setSatelliteUplink(this.uplinkMock);
    weatherStation.setAnemometer(this.anemometer);
  }

  @Test
  void runStormWarningCheck() {
    Mockito.when(hygrometer.getCurrentHumidity()).thenReturn(40.0, 20.0);
    Mockito.when(barometer.getAtmosphericPressure()).thenReturn(700.0, 700.0);
    Mockito.when(thermometer.getCurrentTemperature()).thenReturn(80.0,80.0);

    assertEquals(true, weatherStation.runStormWarningCheck());
    assertEquals(true, weatherStation.runStormWarningCheck());

    Mockito.verify(uplinkMock).runStormCheckForArea(40.0,700.0,80.0);
    Mockito.verify(uplinkMock).runStormCheckForArea(20.0,700.0,80.0);
    Mockito.verify(uplinkMock).checkNearbyAreaStorms();
    Mockito.verify(hygrometer, times(2)).getCurrentHumidity();
    Mockito.verify(barometer, times(2)).getAtmosphericPressure();
    Mockito.verify(thermometer, times(2)).getCurrentTemperature();
  }

  @Test
  void runTornadoWarningCheck() {
    Mockito.when(hygrometer.getCurrentHumidity()).thenReturn(20.0, 31.0);
    Mockito.when(barometer.getAtmosphericPressure()).thenReturn(80.0,1200.0);
    Mockito.when(anemometer.getWindSpeed()).thenReturn(700.0,15.0);

    assertEquals(true, weatherStation.runTornadoWarningCheck());
    assertEquals(true, weatherStation.runTornadoWarningCheck());

    Mockito.verify(uplinkMock).runTornadoCheckForArea(20.0,80.0,700.0);
    Mockito.verify(uplinkMock).runTornadoCheckForArea(31.0,1200.0,15.0);
    Mockito.verify(uplinkMock).checkNearbyAreaTornadoes();
    Mockito.verify(hygrometer, times(2)).getCurrentHumidity();
    Mockito.verify(anemometer, times(2)).getWindSpeed();
    Mockito.verify(barometer, times(2)).getAtmosphericPressure();
  }

  @Test
  void anemometerCalibrationCheckFalse() {
    Mockito.when(anemometer.getWindSpeed()).thenReturn(10.0,0.0);
    Mockito.when(anemometer.getWindDirInDegrees()).thenReturn(10.0,0.0);

    assertEquals(false, weatherStation.anemometerCalibrationCheck());

    Mockito.verify(anemometer, times(2)).getWindSpeed();
    Mockito.verify(anemometer, times(2)).getWindDirInDegrees();
  }

  @Test
  void anemometerCalibrationCheckTrue() {
    Mockito.when(anemometer.getWindSpeed()).thenReturn(10.0,10.0);
    Mockito.when(anemometer.getWindDirInDegrees()).thenReturn(10.0,10.0);

    assertEquals(true, weatherStation.anemometerCalibrationCheck());

    Mockito.verify(anemometer, times(2)).getWindSpeed();
    Mockito.verify(anemometer, times(2)).getWindDirInDegrees();
  }

  @Test
  void temperatureCalibrationTestFalse() {
    Mockito.when(thermometer.getCurrentTemperature()).thenReturn(10.0,0.0);

    assertEquals(false, weatherStation.temperatureCalibrationTest());

    Mockito.verify(thermometer, times(2)).getCurrentTemperature();
  }

  @Test
  void temperatureCalibrationTestTrue() {
    Mockito.when(thermometer.getCurrentTemperature()).thenReturn(10.0,10.0);

    assertEquals(true, weatherStation.temperatureCalibrationTest());

    Mockito.verify(thermometer, times(2)).getCurrentTemperature();
  }

}