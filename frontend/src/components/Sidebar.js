import React, { useState, useEffect } from "react";

import "../assets/style/style.css";
import hideIcon from "../assets/image/hide.png";
import moreIcon from "../assets/image/more.png";
import addIcon from "../assets/image/add.png";

import DisplayList from "./DisplayList";

export default function Sidebar({
  isOpen,
  onClose,
  onAddListClick,
  onToggleAdd,
  showSearch,
}) {
  const onBtnClick = () => {
    console.log("Button clicked");
  };

  const [data, setData] = useState([]);

  useEffect(() => {
    fetch("http://localhost:4000/lists") // json-server endpoint
      .then((res) => res.json())
      .then((json) => setData(json))
      .catch((err) => console.error(err));
  }, []);

  return (
    <aside
      style={{
        width: isOpen ? "22.22vw" /* 또는 '22.22%' (뷰포트 기준 너비)' */ : "0",
        transition: "width 0.3s",
        overflow: "hidden",
        backgroundColor: "#ffffff",
        color: "black",
        height: "100dvh",
        padding: isOpen ? "1rem" : "0",
        boxShadow: "0 4px 4px rgba(0, 0, 0, 0.25)",
        position: "absolute",
        zIndex: 2,
      }}
    >
      <h1 style={{ marginBottom: 0 }}>List</h1>
      <div style={{ display: "flex", justifyContent: "center" }}>
        <input
          type="text"
          className="search-input"
          placeholder=" 검색창 (임시) "
        />
      </div>

      <div
        style={{
          display: "flex",
          flexDirection: "row",
          justifyContent: "space-between",
        }}
      >
        <ul style={{ padding: "0", margin: "0" }}>
          {data.map((item) => {
            const displayName = item.name || item.Name; // 소문자/대문자 처리
            return (
              <div>
                <div
                  style={{
                    display: "flex",
                    flexDirection: "row",
                    justifyContent: "space-between",
                  }}
                >
                  <h4 style={{ margin: "0" }} key={item.id}>
                    {displayName}
                  </h4>
                  <DisplayList />
                </div>

                <ul>
                  <li>청킹</li>
                  <li>그루</li>
                  <li>타르트앤</li>
                  <li>커피한약방</li>
                </ul>
              </div>
            );
          })}
        </ul>
      </div>

      <div
        style={{
          display: "flex",
          width: "100%",
          justifyContent: "space-between",
        }}
      >
        <button
          id="Add"
          onClick={onAddListClick}
          style={{
            width: "100%",
            aspectRatio: 6 / 1,
            backgroundColor: "white",
            border: "none",
            borderRadius: "5px",
            boxShadow: "0 4px 4px rgba(0, 0, 0, 0.25)",
          }}
        >
          <img src={addIcon} alt="add icon"></img>Add List / Course
        </button>
      </div>
    </aside>
  );
}
