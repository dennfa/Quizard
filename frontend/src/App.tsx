import './App.css'
import {Route, Routes} from "react-router-dom";
import MainPage from "./Pages/MainPage.tsx";

export default function App() {

  return (
    <Routes>
      <Route path="/" element={<MainPage/>}/>
    </Routes>
  )
}
