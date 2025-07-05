import Header from './components/Header';
import Sidebar from './components/Sidebar';
import PopupList from './components/PopupList';
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
    <div>
      <Header onMenuClick={toggleSidebar} />
      <Sidebar
        isOpen={sidebarOpen} onClose={() => setSidebarOpen(false)}
        onAddListClick={togglePopupList} />
      {showPopupList && <PopupList onClose={togglePopupList} sidebarWidth={sidebarWidth}/>}
    </div>
  );
}

export default App;
