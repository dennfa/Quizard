import './App.css'
import {Route, Routes} from "react-router-dom";
import MainPage from "./Pages/MainPage.tsx";
import CreateQuiz from "./Pages/CreateQuiz.tsx";

export default function App() {

  return (
    <Routes>
      <Route path="/" element={<MainPage/>}/>
      <Route path="/create" element={<CreateQuiz/>}/>
    </Routes>
  )
}
