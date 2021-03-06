/*
 * WiFi Analyzer
 * Copyright (C) 2016  VREM Software Development <VREMSoftwareDevelopment@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.aqnote.app.wifi.wifi.graph.tools;

import com.jjoe64.graphview.series.BaseSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.aqnote.app.wifi.wifi.band.WiFiWidth;
import com.aqnote.app.wifi.wifi.model.WiFiDetail;
import com.aqnote.app.wifi.wifi.model.WiFiSignal;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class SeriesCacheTest {
    @Mock
    private BaseSeries<DataPoint> series0;
    @Mock
    private BaseSeries<DataPoint> series1;
    @Mock
    private BaseSeries<DataPoint> series2;

    private List<BaseSeries<DataPoint>> series;

    private SeriesCache fixture;

    @Before
    public void setUp() {
        series = Arrays.asList(series0, series1, series2);
        fixture = new SeriesCache();
    }

    @Test
    public void testAddNewSeries() throws Exception {
        // setup
        List<WiFiDetail> wiFiDetails = withData();
        // validate
        for (WiFiDetail wiFiDetail : wiFiDetails) {
            assertTrue(fixture.contains(wiFiDetail));
        }
    }

    @Test
    public void testAddExistingSeries() throws Exception {
        // setup
        List<WiFiDetail> wiFiDetails = withData();
        // execute
        BaseSeries<DataPoint> actual = fixture.add(wiFiDetails.get(0), series1);
        // validate
        assertEquals(series0, actual);
    }

    @Test
    public void testRemoveAll() throws Exception {
        // setup
        List<WiFiDetail> wiFiDetails = withData();
        // execute
        List<BaseSeries<DataPoint>> actual = fixture.remove(new TreeSet<WiFiDetail>());
        // validate
        assertEquals(series.size(), actual.size());
        for (int i = 0; i < series.size(); i++) {
            assertEquals(series.get(i), actual.get(i));
            assertFalse(fixture.contains(wiFiDetails.get(i)));
        }
    }

    @Test
    public void testRemoveNone() throws Exception {
        // setup
        List<WiFiDetail> wiFiDetails = withData();
        // execute
        List<BaseSeries<DataPoint>> actual = fixture.remove(new TreeSet<>(wiFiDetails));
        // validate
        assertTrue(actual.isEmpty());
    }

    @Test
    public void testRemoveOne() throws Exception {
        // setup
        int index = 1;
        List<WiFiDetail> wiFiDetails = withData();
        // execute
        List<BaseSeries<DataPoint>> actual = fixture.remove(new TreeSet<>(Arrays.asList(wiFiDetails.get(0), wiFiDetails.get(2))));
        // validate
        assertEquals(index, actual.size());
        assertEquals(series.get(index), actual.get(0));
        assertFalse(fixture.contains(wiFiDetails.get(index)));
    }

    @Test
    public void testFind() throws Exception {
        // setup
        List<WiFiDetail> wiFiDetails = withData();
        // execute
        WiFiDetail actual = fixture.find(series1);
        // validate
        assertEquals(wiFiDetails.get(1), actual);
    }

    private WiFiDetail makeWiFiDetail(String SSID) {
        return new WiFiDetail(SSID, "BSSID", StringUtils.EMPTY, new WiFiSignal(100, 100, WiFiWidth.MHZ_20, 5));
    }

    private List<WiFiDetail> withData() {
        List<WiFiDetail> results = new ArrayList<>();
        for (int i = 0; i < series.size(); i++) {
            WiFiDetail wiFiDetail = makeWiFiDetail("SSID" + i);
            results.add(wiFiDetail);
            assertEquals(series.get(i), fixture.add(wiFiDetail, series.get(i)));
        }
        return results;
    }

}