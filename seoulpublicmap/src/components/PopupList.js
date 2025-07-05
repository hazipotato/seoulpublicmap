import React from 'react';

function PopupList({ onClose, sidebarWidth }) {
  return (
    <div style={{
      position: 'fixed',
      top: 0,
      left: sidebarWidth,  // 사이드바 너비를 고려해서 왼쪽 여백을 설정
      right: 0,
      bottom: 0,
      backgroundColor: 'rgba(0, 0, 0, 0.2)',  // 반투명 배경
      display: 'flex',
      justifyContent: 'center',
      alignItems: 'center',
      zIndex: 1,  
    }}>
      <div style={{
        backgroundColor: 'white',
        padding: '20px',
        borderRadius: '8px',
        width: '300px',  // 팝업 너비
        maxWidth: '90%',  // 화면 크기에 맞춰 최대 90% 너비
        boxShadow: '0px 0px 10px rgba(0, 0, 0, 0.3)',  // 그림자 효과
        textAlign: 'center',
      }}>
        <h2>팝업 제목</h2>
        <p>팝업 내용</p>
        <button
          onClick={onClose}
          style={{
            marginTop: '15px',
            padding: '10px 20px',
            backgroundColor: '#007BFF',
            color: 'white',
            border: 'none',
            borderRadius: '5px',
            cursor: 'pointer',
          }}
        >
          닫기
        </button>
      </div>
    </div>
  );
}

export default PopupList;
