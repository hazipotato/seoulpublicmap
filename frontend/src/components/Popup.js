import React, { useState } from 'react';

function Popup({ onClose, sidebarWidth }) {
  const [type, setType] = useState("List"); // List or Course
  const [Name, setName] = useState("");
  const [explanation, setExplanation] = useState("");

  const [saving, setSaving] = useState(false);

  const handleSubmit = async () => {
    setSaving(true);

    const endpoint = type === "List" ? "/Lists" : "/courses";

    const data = {
      Name,
      explanation,
    };

    try {
<<<<<<< HEAD:frontend/src/components/Popup.js
      const response = await fetch(endpoint, {
        method: "POST", //HTTP 요청 방식 지정
        headers: {
          "Content-Type": "application/json", //내가 보내는 데이터는 JSON 형식임을 서버에 알려줌
        },
        body: JSON.stringify(data),
      });

      if (response.ok) {
        alert(`${type} 저장 성공!`);
        setName("");
        setExplanation("");
      }
      else {
        alert("저장 실패");
      }
  }
    catch (error) {
      console.error("Error:", error);
      alert("서버 오류 발생")
    }
  };

=======


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
   
>>>>>>> 50207d68dcd66ffb50127cb379cf5abfb42c3482:frontend/src/components/PopupList.js
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
<<<<<<< HEAD:frontend/src/components/Popup.js
            style={{width: "45%"}}
            onClick={() => setType("List")}>List</button>
          <button
            style={{width: "45%"}}
            onClick={() => setType("Course")}>Course</button>
=======
             onClick={() => setType("List")}
             style={{ width: "45%", fontWeight: type === "List" ? "700" : "400" }}
           >List</button>
           <button
             onClick={() => setType("Course")}
             style={{ width: "45%", fontWeight: type === "Course" ? "700" : "400" }}
           >Course</button>
>>>>>>> 50207d68dcd66ffb50127cb379cf5abfb42c3482:frontend/src/components/PopupList.js
        </div>
        <h3>Title</h3>
        <div style={{display: 'flex', justifyContent: 'center'}}>
          <input
            type="text"
            placeholder="제목"
            value={Name}
            onChange={(e) => setName(e.target.value)}
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

export default Popup;
