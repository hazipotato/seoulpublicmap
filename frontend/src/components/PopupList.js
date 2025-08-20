import React, { useState } from 'react';

function PopupList({ onClose, sidebarWidth }) {
  const [type, setType] = useState("List"); // List or Course
  const [listName, setListName] = useState("");
  const [explanation, setExplanation] = useState("");

  const [saving, setSaving] = useState(false);

  const handleSubmit = async () => {
    setSaving(true);

    try {


      if (!listName.trim()) {
             alert("제목을 입력하세요");
              setSaving(false);
              return;
            }

            // 2) List/Course 엔드포인트 분기
            const endpoint =
              type === "Course"
                ? "http://localhost:4000/api/courses"
                : "http://localhost:4000/api/lists";

            const res = await fetch(endpoint, {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({
          name: listName.trim(),
          explanation
        }),
    });
  

    if (res.ok) {
      alert("저장 성공!");
      onClose();
    }
    else {
      alert("저장 실패");
    }
  }
    catch (err) {
      console.error(err);
      alert("에러 발생");
    }
    finally {
      setSaving(false);
    }
  }
   
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
        padding: '50px',
        borderRadius: '30px',
        width: '400px',  // 팝업 너비
        maxWidth: '90%',  // 화면 크기에 맞춰 최대 90% 너비
        boxShadow: '0px 0px 10px rgba(0, 0, 0, 0.25)',  // 그림자 효과
        textAlign: 'left',
        display: 'flex',
        flexDirection: 'column'
      }}>
        <h2>Add List / Course</h2>
        <h3>Type</h3>
        <div style={{display: 'flex', justifyContent: 'space-between'}}>
          <button
             onClick={() => setType("List")}
             style={{ width: "45%", fontWeight: type === "List" ? "700" : "400" }}
           >List</button>
           <button
             onClick={() => setType("Course")}
             style={{ width: "45%", fontWeight: type === "Course" ? "700" : "400" }}
           >Course</button>
        </div>
        <h3>Title</h3>
        <div style={{display: 'flex', justifyContent: 'center'}}>
          <input
            type="text"
            placeholder="제목"
            value={listName}
            onChange={(e) => setListName(e.target.value)}
          />
        </div>
        <h3>Explanation(Optional)</h3>
        <div style={{display: 'flex', justifyContent: 'center'}}>
          <input
            type="text"
            placeholder=" 설명 "
            value={explanation}
            onChange={(e) => setExplanation(e.target.value)}
          />
        </div>
        <button
          onClick={handleSubmit}
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
          저장하기
        </button>
      </div>
    </div>
  );
}

export default PopupList;
