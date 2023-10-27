import './App.css'
import {Route, Routes} from "react-router-dom";
import MainPage from "./Pages/MainPage.tsx";
import CreateQuiz from "./Pages/CreateQuiz.tsx";
import TakeQuiz from "./Pages/TakeQuiz.tsx";

export default function App() {

  return (
    <Routes>
      <Route path="/" element={<MainPage/>}/>
      <Route path="/create" element={<CreateQuiz/>}/>
      <Route path="/take" element={<TakeQuiz/>}/>
    </Routes>
  )
}
