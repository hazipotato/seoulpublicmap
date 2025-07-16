import Header from './components/Header';
import Sidebar from './components/Sidebar';
import PopupList from './components/PopupList';
import Search from './components/Search';
import React, { useState, useEffect } from 'react';

import './App.css';

function App() {
  const [sidebarOpen, setSidebarOpen] = useState(false);
  const [showPopupList, setPopupListOpen] = useState(false);

  const toggleSidebar = () => {
    setSidebarOpen(prev => !prev); // 열려 있으면 닫고, 닫혀 있으면 열고
  };

  const togglePopupList = () => {
    setPopupListOpen(prev => !prev);
  };

  const sidebarWidth = sidebarOpen ? '22.22vw' : '0';
//--------------카카오맵 로드!!!! ----------
  useEffect(() => {
    // 이미 스크립트가 있으면 중복 추가 방지(index.html에 스크립트를 추가하면 react렌더링이랑 타이밍이 안맞아서 가려진대여. -> 그래서 useEffect에서 추가함)
    if (document.getElementById('kakao-map-script')) {
      if (window.kakao && window.kakao.maps) {
        window.kakao.maps.load(() => {
          const container = document.getElementById('map');
          const options = {
            center: new window.kakao.maps.LatLng(37.5665, 126.9780),
            level: 3
          };
          new window.kakao.maps.Map(container, options);
        });
      }
      return;
    }
    //스크립트 없는 경우 동적으로 추가(새로고침, 처음 실행 등등등의 경우우)
    const script = document.createElement('script');
    script.id = 'kakao-map-script';
    script.src = "//dapi.kakao.com/v2/maps/sdk.js?appkey=d654c0c9a4aff2d1bc327337babca6a4&autoload=false";
    script.async = true;
    script.onload = () => {
      window.kakao.maps.load(() => {
        const container = document.getElementById('map');
        const options = {
          center: new window.kakao.maps.LatLng(37.5665, 126.9780),
          level: 3
        };
        new window.kakao.maps.Map(container, options);
      });
    };
    document.head.appendChild(script);
  }, []);

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
        <Search></Search>
      </div>

    </div>
  );
}

export default App;


