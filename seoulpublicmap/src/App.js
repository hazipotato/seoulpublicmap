import Header from './components/Header';
import Sidebar from './components/Sidebar';
import PopupList from './components/PopupList';
import Search from './components/Search';
import React, { useState } from 'react';

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

  return (
    <div style={{height: '100dvh'}}>
      <Header onMenuClick={toggleSidebar} />
      <Sidebar
        isOpen={sidebarOpen} onClose={() => setSidebarOpen(false)}
        onAddListClick={togglePopupList} />
      {showPopupList && <PopupList onClose={togglePopupList} sidebarWidth={sidebarWidth}/>}
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
