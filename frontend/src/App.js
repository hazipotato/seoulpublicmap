import Header from './components/Header';
import Sidebar from './components/Sidebar';
import PopupList from './components/PopupList';
import Search from './components/Search';
import React, { useState, useEffect } from 'react';

import './App.css';

function App() {
  const [sidebarOpen, setSidebarOpen] = useState(false);
  const [showPopupList, setPopupListOpen] = useState(false);
  const [map, setMap] = useState(null);      // 지도 참조
  const [markers, setMarkers] = useState([]); // 마커담는 배열

  const toggleSidebar = () => {
    setSidebarOpen(prev => !prev); // 열려 있으면 닫고, 닫혀 있으면 열고
  };

  const togglePopupList = () => {
    setPopupListOpen(prev => !prev);
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
    script.src     =
      '//dapi.kakao.com/v2/maps/sdk.js?appkey=d654c0c9a4aff2d1bc327337babca6a4&autoload=false&libraries=services';  // 이거 키 빼놔야함
    script.onload  = () => window.kakao.maps.load(initMap);
    document.head.appendChild(script);
  }, []);

// ------------ 키워드로 검색 + 핑 출력 -----------------------
  const searchPlaces = (keyword) => {
      if (!map || !window.kakao) return;

      //이전 마커 제거배열 지우기
      markers.forEach((mk) => mk.setMap(null));
      setMarkers([]);

       //이거(ps)가 장소 검색 객체
      const ps = new window.kakao.maps.services.Places();
      ps.keywordSearch(keyword, (data, status) => {
        if (status !== window.kakao.maps.services.Status.OK) return;

        const bounds = new window.kakao.maps.LatLngBounds();
        const newMarkers = data.map((p) => {
          const pos = new window.kakao.maps.LatLng(p.y, p.x);
          bounds.extend(pos);
          const mk = new window.kakao.maps.Marker({ map, position: pos });
          return mk;
        });
        map.setBounds(bounds);
        setMarkers(newMarkers);
      });
    };


  return (
    <div style={{height: '100dvh'}}>
      <Header onMenuClick={toggleSidebar} />
      <Sidebar
        isOpen={sidebarOpen} onClose={() => setSidebarOpen(false)}
        onAddListClick={togglePopupList} />
      {showPopupList && <PopupList onClose={togglePopupList} sidebarWidth={sidebarWidth}/>}
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
        <Search onSearch={searchPlaces} />
      </div>
      
    </div>
  );
}

export default App;



