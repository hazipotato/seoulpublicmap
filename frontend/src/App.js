import Header from './components/Header';
import Sidebar from './components/Sidebar';
import Popup from './components/Popup';
import Search from './components/Search';
import React, { useState, useEffect } from 'react';

import './App.css';

function App() {
  const [sidebarOpen, setSidebarOpen] = useState(false);
  const [showPopup, setPopupOpen] = useState(false);
  const [map, setMap] = useState(null);      // 지도 참조
  const [markers, setMarkers] = useState([]); // 마커담는 배열
  const [showSearch, setShowSearch] = useState(false);

  const toggleSidebar = () => {
    setSidebarOpen(prev => !prev); // 열려 있으면 닫고, 닫혀 있으면 열고
  };

  const togglePopup = () => {
    setPopupOpen(prev => !prev);
  };

  const toggleAdd = () => {
    setShowSearch(prev => !prev);
  };

  const sidebarWidth = sidebarOpen ? '22.22vw' : '0';
//--------------카카오맵 로드!!!! ----------

  useEffect(() => {
    // 지도 초기화
    const initMap = () => {
      const container = document.getElementById('map');
      const options   = { center: new window.kakao.maps.LatLng(37.5665, 126.9780), level: 3 };
      const m = new window.kakao.maps.Map(container, options);
      setMap(m);
    };

    // 이미 스크립트가 있으면 중복 추가 방지(index.html에 스크립트를 추가하면 react렌더링이랑 타이밍이 안맞아서 가려진대여. -> 그래서 useEffect에서 추가함)
    const existing = document.getElementById('kakao-map-script');
    if (existing) {
      if (window.kakao && window.kakao.maps) window.kakao.maps.load(initMap);
      return;
      }

    //스크립트 없는 경우 동적으로 추가(새로고침, 처음 실행 등등등의 경우우)
    const script   = document.createElement('script');
    script.id      = 'kakao-map-script';
    script.async   = true;
    const appkey = process.env.REACT_APP_KAKAO_JS_KEY;
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?appkey=${appkey}&autoload=false&libraries=services`;

    script.onload  = () => window.kakao.maps.load(initMap);
    document.head.appendChild(script);
  }, []);

// ------------ 키워드로 검색 + 핑 출력 -----------------------
  const searchPlaces = async (keyword) => {
    if (!map) return;

    // 기존 마커 정리
    markers.forEach(m => m.setMap(null));
    setMarkers([]);

    const res = await fetch("/api/places/search?query=" + encodeURIComponent(keyword));
    if (!res.ok) return;
    const data = await res.json(); // [{id,name,latitude,longitude}]

    const bounds = new window.kakao.maps.LatLngBounds();
    const ms = data.map(p => {
      const pos = new window.kakao.maps.LatLng(p.latitude, p.longitude);
      bounds.extend(pos);
      return new window.kakao.maps.Marker({ map, position: pos });
    });
    if (!bounds.isEmpty()) map.setBounds(bounds);
    setMarkers(ms);
  };


  return (
    <div style={{height: '100dvh'}}>
      <Header onMenuClick={toggleSidebar} />
      <Sidebar
        isOpen={sidebarOpen} onClose={() => setSidebarOpen(false)}
        onAddListClick={togglePopup}
        onToggleAdd={toggleAdd}/>
      {showPopup && <Popup onClose={togglePopup} sidebarWidth={sidebarWidth}/>}
      <div id="map" style={{ width: "100vw", height: "100vh", position: "absolute", top: 0, left: 0, zIndex: 0 }}></div>
      <div
        style={{
          position: 'fixed',
          width: '100%',
          bottom: 0,
          display: 'flex',
          justifyContent: 'center',
          zIndex: 4, // 필요하면 추가
        }}>
        {showSearch && (
          <div
            style={{
              position: 'fixed',
              width: '100%',
              bottom: 0,
              display: 'flex',
              justifyContent: 'center',
              zIndex: 4,
            }}>
            <Search onSearch={searchPlaces} />
          </div>
        )}
      </div>

    </div>
  );
}

export default App;



